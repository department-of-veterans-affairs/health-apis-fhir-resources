package gov.va.api.health.fhir.testsupport;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableMap;
import gov.va.api.health.sentinel.ExpectedResponse;
import gov.va.api.health.sentinel.TestClient;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

/** This support class can be used to test standard resource queries, such as reads and searches. */
@Slf4j
@Value
@Builder
public final class ResourceVerifier {
  private static final Set<Class<?>> VERIFIED_PAGE_BOUNDS_CLASSES =
      Collections.newSetFromMap(new ConcurrentHashMap<>());

  private final String apiPath;

  private final Class<?> bundleClass;

  private final TestClient testClient;

  private final Class<?> operationOutcomeClass;

  @Builder.Default int maxCount = 100;

  /**
   * If the response is a bundle, then the query is a search. We want to verify paging parameters
   * restrict page >= 1, _count >=1, and _count <= $(maxCount + 1)
   */
  @SneakyThrows
  final <T> void assertPagingParameterBounds(TestCase<T> tc) {
    if (!bundleClass().isAssignableFrom(tc.response())) {
      return;
    }
    if (VERIFIED_PAGE_BOUNDS_CLASSES.contains(tc.response())) {
      log.info("Verify {} page bounds, skipping repeat {}.", tc.label(), tc.response.getName());
      return;
    }
    log.info("Verify {} page bounds", tc.label());
    VERIFIED_PAGE_BOUNDS_CLASSES.add(tc.response());
    testClient()
        .get(tc.path() + "&page=0", tc.parameters())
        .expect(400)
        .expectValid(operationOutcomeClass());
    testClient()
        .get(tc.path() + "&_count=-1", tc.parameters())
        .expect(400)
        .expectValid(operationOutcomeClass());
    testClient()
        .get(tc.path() + "&_count=0", tc.parameters())
        .expect(200)
        .expectValid(tc.response());
    int tooManies = maxCount() + 1;
    T bundle =
        testClient()
            .get(tc.path() + "&_count=" + tooManies, tc.parameters())
            .expect(200)
            .expectValid(tc.response());
    Method bundleEntryMethod = bundleClass().getMethod("entry");
    ReflectionUtils.makeAccessible(bundleEntryMethod);
    Collection<?> entries = (Collection<?>) bundleEntryMethod.invoke(bundle);
    assertThat(entries.size()).isLessThan(tooManies);
  }

  private <T> T assertRequest(TestCase<T> tc) {
    log.info("Verify {} is {} ({})", tc.label(), tc.response().getSimpleName(), tc.status());
    ExpectedResponse response;
    if (tc.isPost()) {
      response = testClient().post(tc.headers(), tc.path(), tc.body());
    } else {
      response = testClient().get(tc.path(), tc.parameters());
    }
    var expected = response.expect(tc.status()).expectValid(tc.response());
    if (tc.satisfiesCondition() != null) {
      assertThat(tc.satisfiesCondition().test(expected)).isTrue();
    }
    return expected;
  }

  /** Build a TestCase that includes a body. */
  public final <T> TestCase<T> test(
      int status,
      Class<T> response,
      String path,
      Map<String, String> headers,
      String requestBody,
      String... parameters) {
    return TestCase.<T>builder()
        .path(apiPath() + path)
        .headers(ImmutableMap.copyOf(headers))
        .body(requestBody)
        .parameters(parameters)
        .response(response)
        .status(status)
        .build();
  }

  /** Build a basic test case that verifies status and response type. */
  public final <T> TestCase<T> test(
      int status, Class<T> response, String path, String... parameters) {
    return test(status, response, null, path, parameters);
  }

  /** Build a test case with a condition that must be verified for success. */
  public final <T> TestCase<T> test(
      int status,
      Class<T> response,
      Predicate<T> satisfiesCondition,
      String path,
      String... parameters) {
    return TestCase.<T>builder()
        .path(apiPath() + path)
        .parameters(parameters)
        .response(response)
        .status(status)
        .satisfiesCondition(satisfiesCondition)
        .build();
  }

  /** Verify a single test case. */
  public final <T> T verify(TestCase<T> tc) {
    if (!tc.isPost()) {
      assertPagingParameterBounds(tc);
    }
    return assertRequest(tc);
  }

  /** Verify all test cases contained within an array. */
  public final void verifyAll(TestCase<?>... testCases) {
    for (TestCase<?> tc : testCases) {
      try {
        verify(tc);
      } catch (Exception | AssertionError e) {
        log.error("Failure: {}: {}", tc.label(), e.getMessage());
        throw e;
      }
    }
  }

  @Value
  @Builder
  public static final class TestCase<T> {
    String[] parameters;

    int status;

    Class<T> response;

    String path;

    @Builder.Default ImmutableMap<String, String> headers = ImmutableMap.of();

    String body;

    Predicate<T> satisfiesCondition;

    String body() {
      return String.format(body.replaceAll("[{][a-z0-9]+[}]", "%s"), (Object[]) parameters);
    }

    Boolean isPost() {
      return body != null;
    }

    String label() {
      StringBuilder labelBuilder = new StringBuilder();
      labelBuilder.append(path);
      if (isPost()) {
        labelBuilder.append(" with body ").append(body);
      }
      labelBuilder.append(" with parameters ").append(Arrays.toString(parameters));
      return labelBuilder.toString();
    }
  }
}
