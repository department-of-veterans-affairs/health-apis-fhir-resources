package gov.va.api.health.r4.api.samples;

import gov.va.api.health.r4.api.resources.Parameters;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import java.util.List;

@NoArgsConstructor(staticName = "get")
public class SampleParameters {
    @Delegate
    SampleDataTypes dataTypes = SampleDataTypes.get();

    public Parameters parameters() {
        return Parameters.builder()
                .id("x")
                .meta(meta())
                .implicitRules("http://GoodnightRules.com")
                .language("Goodnight Language")
                .parameter(List.of(
                        Parameters.Parameter.builder()
                        .name("Goodnight name")
                        .valueBoolean(Boolean.TRUE)
                        .build()
                ))
                .build();
    }

}
