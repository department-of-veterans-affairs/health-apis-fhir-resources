package gov.va.api.health.r4.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.va.api.health.autoconfig.configuration.JacksonConfig;
import gov.va.api.health.r4.api.bundle.MixedBundle;
import gov.va.api.health.r4.api.bundle.MixedEntry;
import gov.va.api.health.r4.api.resources.Resource;
import java.util.List;
import lombok.SneakyThrows;

public class RoundTrip {
  @SneakyThrows
  public static void assertRoundTrip(Object object) {
    ObjectMapper mapper =
        JacksonConfig.createMapper().registerModule(new Resource.ResourceModule());
    String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    Object evilTwin = mapper.readValue(json, object.getClass());
    assertThat(evilTwin).isEqualTo(object);

    if (object instanceof Resource) {
      ObjectMapper resourceMapper =
          JacksonConfig.createMapper().registerModule(new Resource.ResourceModule());
      MixedBundle bundle =
          MixedBundle.builder()
              .entry(List.of(MixedEntry.builder().resource((Resource) object).build()))
              .build();
      Object bundleEvilTwin =
          resourceMapper.readValue(resourceMapper.writeValueAsString(bundle), MixedBundle.class);
      assertThat(bundleEvilTwin).isEqualTo(bundle);
    }
  }
}
