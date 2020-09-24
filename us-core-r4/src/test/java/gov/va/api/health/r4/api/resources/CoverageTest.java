package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType.searchset;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.resources.Coverage.Bundle;
import gov.va.api.health.r4.api.resources.Coverage.Entry;
import gov.va.api.health.r4.api.samples.SampleCoverages;
import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import gov.va.api.health.validation.api.ExactlyOneOfVerifier;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class CoverageTest {

  private final SampleCoverages data = SampleCoverages.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildCoverageBundles() {
    Entry entry =
        Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://coverage.com")
            .id("123")
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://coverage/1"))
                        .build()))
            .resource(data.coverageWithValueQuanitity())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();

    Bundle bundle =
        Bundle.builder()
            .entry(singletonList(entry))
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://coverage.com/2"))
                        .build()))
            .type(searchset)
            .signature(data.signature())
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void coverage() {
    assertRoundTrip(data.coverageWithValueQuanitity());
    assertRoundTrip(data.coverageWithValueMoney());
  }

  @Test
  public void relatedGroups() {
    ExactlyOneOfVerifier.builder()
        .sample(data.costToBeneficiaryWithValueQuantity())
        .fieldPrefix("value")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }

  @Test
  public void validationFailsGivenBadCoverageClass() {
    var coverage = data.coverageWithValueQuanitity();
    coverage
        .coverageClass()
        .get(0)
        .type()
        .coding(
            List.of(
                Coding.builder().code("group").build(), Coding.builder().code("group").build()));
    assertThat(violationsOf(coverage)).isNotEmpty();
  }

  @Test
  public void validationSucceedsGivenGoodCoverageClass() {
    var coverage = data.coverageWithValueQuanitity();
    coverage
        .coverageClass()
        .get(0)
        .type()
        .coding(
            List.of(Coding.builder().code("group").build(), Coding.builder().code("plan").build()));
    assertThat(violationsOf(coverage)).isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
