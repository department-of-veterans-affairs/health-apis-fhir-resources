package gov.va.api.health.r4.api.swaggerexamples;

import gov.va.api.health.r4.api.resources.Parameters;
import java.util.List;

public class SwaggerParameters {

  /** Simulates a VC Request. */
  public Parameters vcRequest() {
    return Parameters.builder()
        .parameter(
            List.of(
                Parameters.Parameter.builder()
                    .name("credentialType")
                    .valueUri("https://smarthealth.cards#covid19")
                    .build(),
                Parameters.Parameter.builder()
                    .name("includeIdentityClaim")
                    .valueUri("Patient.name")
                    .build()))
        .build();
  }

  /** Simulates a VC Response. */
  public Parameters vcResponse() {
    return Parameters.builder()
        .parameter(
            List.of(
                Parameters.Parameter.builder()
                    .name("verifiableCredential")
                    .valueString("<<Health Cards as JWS>>")
                    .build()))
        .build();
  }
}
