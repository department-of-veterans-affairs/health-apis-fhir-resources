package gov.va.api.health.validation.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.opentest4j.AssertionFailedError;

public class ExactlyOneOfVerifierTest {
  @Test(expected = IllegalArgumentException.class)
  public void blankPrefixIsAnIllegalArgument() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
    ExactlyOneOfVerifier.builder()
        .sample(new ExactlyOne("exactly", null))
        .fieldPrefix("")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test(expected = AssertionFailedError.class)
  public void exactlyBoth() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
    ExactlyOneOfVerifier.builder()
        .sample(new ExactlyOne("exactly", 1))
        .fieldPrefix("exactlyOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test(expected = AssertionFailedError.class)
  public void exactlyNeither() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
    ExactlyOneOfVerifier.builder()
        .sample(new ExactlyOne(null, null))
        .fieldPrefix("exactlyOne")
        .knownTypes(types)
        .stringTypes(stringTypes)
        .build()
        .verify();
  }

  @Test
  public void exactlyOneInteger() {
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
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
    Map<String, Supplier<?>> stringTypes = new HashMap<>();
    Map<Class<?>, Supplier<?>> types = new HashMap<>();
    types.put(String.class, () -> "hello");
    types.put(Integer.class, () -> 1);
    stringTypes.put("", () -> "hello");
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
