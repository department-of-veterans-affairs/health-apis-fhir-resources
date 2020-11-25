package gov.va.api.health.stu3.api.datatypes;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.va.api.health.autoconfig.configuration.JacksonConfig;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CodeableConceptTest {
  static ObjectMapper mapper = new JacksonConfig().objectMapper();

  static List<Coding> codings(String... display) {
    return Stream.of(display).map(d -> Coding.builder().display(d).build()).collect(toList());
  }

  static Stream<Arguments> textValue() {
    return Stream.of(
        arguments(CodeableConcept.builder().build(), null),
        arguments(CodeableConcept.builder().text("cc").build(), "cc"),
        arguments(CodeableConcept.builder().text("cc").coding(codings()).build(), "cc"),
        arguments(CodeableConcept.builder().text("cc").coding(codings("c1", "c2")).build(), "cc"),
        arguments(CodeableConcept.builder().text("").coding(codings("c1", "c2")).build(), "c1"),
        arguments(CodeableConcept.builder().coding(codings("c1", "c2")).build(), "c1"),
        arguments(CodeableConcept.builder().coding(codings(null, "c2")).build(), "c2"),
        arguments(CodeableConcept.builder().coding(codings(null, null)).build(), null));
  }

  @ParameterizedTest
  @MethodSource
  @SneakyThrows
  void textValue(CodeableConcept cc, String expectedText) {
    assertThat(cc.text()).isEqualTo(expectedText);
    String json = mapper.writeValueAsString(cc);
    Map<?, ?> raw = mapper.readValue(json, Map.class);
    assertThat(raw.get("text")).isEqualTo(expectedText);
  }
}
