package gov.va.api.health.validation.api;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExactlyOneOfExtensionVerifierTest {

  @Test
  public void exactlyOneField() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(
            String.class,
            () -> "hello",
            ExactlyOneExtension.class,
            () -> new ExactlyOneExtension("hello.com", "nope"));
    ExactlyOneOfExtensionVerifier.builder()
        .sample(new ExactlyOne(null, new ExactlyOneExtension("hello.com", "exactly")))
        .field("exactlyOneString")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .extensionClass(ExactlyOneExtension.class)
        .build()
        .verify();
  }

  @Test
  public void moreThanOneField() {
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
          Map<Class<?>, Supplier<?>> types =
              ImmutableMap.of(
                  String.class,
                  () -> "hello",
                  ExactlyOneExtension.class,
                  () -> new ExactlyOneExtension("hello.com", "nope"));
          ExactlyOneOfExtensionVerifier.builder()
              .sample(new ExactlyOne("exactly", new ExactlyOneExtension("hello.com", "exactly")))
              .field("exactlyOneString")
              .knownTypes(types)
              .stringTypes(stringTypes)
              .extensionClass(ExactlyOneExtension.class)
              .build()
              .verify();
        });
  }

  @Data
  @AllArgsConstructor
  private class ExactlyOneExtension {
    String url;
    String value;
  }

  @Data
  @AllArgsConstructor
  @ExactlyOneOf(fields = {"exactlyOneString", "_exactlyOneString"})
  private class ExactlyOne {
    String exactlyOneString;
    ExactlyOneExtension _exactlyOneString;
  }
}
