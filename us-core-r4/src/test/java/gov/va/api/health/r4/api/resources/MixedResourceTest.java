package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.autoconfig.configuration.JacksonConfig.createMapper;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.MixedBundle;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class MixedResourceTest {
  @Test
  void mixed() {
    String path = "/mixed-resource.json";
    roundTrip(path, MixedBundle.class);
  }

  @SneakyThrows
  private <T> void roundTrip(String path, Class<T> clazz) {
    T response = createMapper().readValue(getClass().getResourceAsStream(path), clazz);
    assertThat(response).isExactlyInstanceOf(clazz);
    String actual = createMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response);
    String expected =
        createMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(createMapper().readTree(getClass().getResourceAsStream(path)));
    assertThat(actual).isEqualTo(expected);
  }
}
