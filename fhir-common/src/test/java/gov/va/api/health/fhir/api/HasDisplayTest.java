package gov.va.api.health.fhir.api;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HasDisplayTest {
  static List<FugaziDisplay> codings(String... display) {
    return Stream.of(display).map(FugaziDisplay::new).collect(toList());
  }

  static Stream<Arguments> textValue() {
    return Stream.of(
        arguments(null, null, null),
        arguments("first", null, "first"),
        arguments("first", codings(), "first"),
        arguments("first", codings("c1", "c2"), "first"),
        arguments("", codings("c1", "c2"), "c1"),
        arguments(null, codings("c1", "c2"), "c1"),
        arguments(null, codings(null, "c2"), "c2"),
        arguments(null, codings(null, null), null));
  }

  @ParameterizedTest
  @MethodSource
  @SneakyThrows
  void textValue(String firstChoice, List<FugaziDisplay> displays, String expectedText) {
    assertThat(HasDisplay.anyDisplay(firstChoice, displays)).isEqualTo(expectedText);
  }

  @Getter
  @AllArgsConstructor
  private static class FugaziDisplay implements HasDisplay {
    String display;
  }
}
