package gov.va.api.health.r4.api.samples;

import gov.va.api.health.r4.api.resources.Parameters;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleParameters {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Parameters parameters() {
    return Parameters.builder()
        .id("x")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .parameter(
            List.of(
                Parameters.Parameter.builder()
                    .name("Goodnight name")
                    .valueBoolean(Boolean.TRUE)
                    .part(
                        List.of(
                            Parameters.Parameter.builder().name("code").valueCode("focus").build()))
                    .build()))
        .build();
  }

  public Parameters parametersBoolean() {
    return Parameters.builder()
        .id("x")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .parameter(
            List.of(
                Parameters.Parameter.builder()
                    .name("Goodnight name")
                    .valueBoolean(Boolean.TRUE)
                    .build()))
        .build();
  }

  public Parameters parametersResource() {
    return Parameters.builder()
        .id("x")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .parameter(
            List.of(
                Parameters.Parameter.builder()
                    .name("Goodnight name")
                    .resource(SamplePatients.get().patient())
                    .build()))
        .build();
  }

  public Parameters parametersString() {
    return Parameters.builder()
        .id("x")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .parameter(
            List.of(
                Parameters.Parameter.builder()
                    .name("Goodnight name")
                    .valueString("Some string")
                    .build()))
        .build();
  }
}
