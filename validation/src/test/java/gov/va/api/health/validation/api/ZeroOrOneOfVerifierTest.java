package gov.va.api.health.validation.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.opentest4j.AssertionFailedError;

public class ZeroOrOneOfVerifierTest {
  @Test(expected = AssertionFailedError.class)
  public void allFields() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
    ZeroOrOneOfVerifier.builder()
        .sample(new ZeroOrOne("zeroOr", 1, ZeroOrOneEnum.ZERO))
        .fieldPrefix("zeroOrOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test
  public void oneEnum() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
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
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
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
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
    ZeroOrOneOfVerifier.builder()
        .sample(new ZeroOrOne("zeroOr", null, null))
        .fieldPrefix("zeroOrOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test(expected = AssertionFailedError.class)
  public void twoFields() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
    ZeroOrOneOfVerifier.builder()
        .sample(new ZeroOrOne("zeroOr", 1, null))
        .fieldPrefix("zeroOrOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test
  public void zeroFields() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
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
