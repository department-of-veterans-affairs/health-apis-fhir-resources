package gov.va.api.health.validation.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class ExactlyOneOfVerifierTest {
  @Test
  public void blankPrefixIsAnIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
          Map<Class<?>, Supplier<?>> types =
              ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
          ExactlyOneOfVerifier.builder()
              .sample(new ExactlyOne("exactly", null))
              .fieldPrefix("")
              .knownTypes(types)
              .stringTypes(stringTypes)
              .build()
              .verify();
        });
  }

  @Test
  public void exactlyBoth() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
          Map<Class<?>, Supplier<?>> types =
              ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
          ExactlyOneOfVerifier.builder()
              .sample(new ExactlyOne("exactly", 1))
              .fieldPrefix("exactlyOne")
              .knownTypes(types)
              .stringTypes(stringTypes)
              .build()
              .verify();
        });
  }

  @Test
  public void exactlyNeither() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
          Map<Class<?>, Supplier<?>> types =
              ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
          ExactlyOneOfVerifier.builder()
              .sample(new ExactlyOne(null, null))
              .fieldPrefix("exactlyOne")
              .knownTypes(types)
              .stringTypes(stringTypes)
              .build()
              .verify();
        });
  }

  @Test
  public void exactlyOneInteger() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    ExactlyOneOfVerifier.builder()
        .sample(new ExactlyOne(null, 1))
        .fieldPrefix("exactlyOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test
  public void exactlyOneString() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    ExactlyOneOfVerifier.builder()
        .sample(new ExactlyOne("exactly", null))
        .fieldPrefix("exactlyOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Data
  @AllArgsConstructor
  @ExactlyOneOf(fields = {"exactlyOneString", "exactlyOneInt"})
  private class ExactlyOne {
    String exactlyOneString;

    Integer exactlyOneInt;
  }
}
