package gov.va.api.health.validation.api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class ZeroOrOneOfVerifierTest {
  @Test
  public void allFields() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    assertThrows(
        IllegalStateException.class,
        () -> {
          ZeroOrOneOfVerifier.builder()
              .sample(new ZeroOrOne("zeroOr", 1, ZeroOrOneEnum.ZERO))
              .fieldPrefix("zeroOrOne")
              .knownTypes(types)
              .stringTypes(stringTypes)
              .build()
              .verify();
        });
  }

  @Test
  public void oneEnum() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    ZeroOrOneOfVerifier.builder()
        .sample(new ZeroOrOne(null, null, ZeroOrOneEnum.ONE))
        .fieldPrefix("zeroOrOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test
  public void oneInteger() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    ZeroOrOneOfVerifier.builder()
        .sample(new ZeroOrOne(null, 1, null))
        .fieldPrefix("zeroOrOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test
  public void oneString() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    ZeroOrOneOfVerifier.builder()
        .sample(new ZeroOrOne("zeroOr", null, null))
        .fieldPrefix("zeroOrOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test
  public void twoFields() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    assertThrows(
        IllegalStateException.class,
        () -> {
          ZeroOrOneOfVerifier.builder()
              .sample(new ZeroOrOne("zeroOr", 1, null))
              .fieldPrefix("zeroOrOne")
              .knownTypes(types)
              .stringTypes(stringTypes)
              .build()
              .verify();
        });
  }

  @Test
  public void zeroFields() {
    Map<String, Supplier<?>> stringTypes = ImmutableMap.of("", () -> "hello");
    Map<Class<?>, Supplier<?>> types =
        ImmutableMap.of(String.class, () -> "hello", Integer.class, () -> 1);
    ZeroOrOneOfVerifier.builder()
        .sample(new ZeroOrOne(null, null, null))
        .fieldPrefix("zeroOrOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  enum ZeroOrOneEnum {
    ZERO,
    ONE
  }

  @Data
  @AllArgsConstructor
  @ZeroOrOneOf(fields = {"zeroOrOneString", "zeroOrOneInt", "zeroOrOneEnum"})
  private class ZeroOrOne {
    String zeroOrOneString;

    Integer zeroOrOneInt;

    ZeroOrOneEnum zeroOrOneEnum;
  }
}
