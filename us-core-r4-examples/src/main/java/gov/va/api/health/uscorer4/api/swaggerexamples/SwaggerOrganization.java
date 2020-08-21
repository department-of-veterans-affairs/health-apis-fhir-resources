package gov.va.api.health.uscorer4.api.swaggerexamples;

import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.ContactPoint.ContactPointSystem;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.uscorer4.api.resources.Organization;
import java.util.List;

public class SwaggerOrganization {
  /**
   * An example Organization.
   *
   * @return an example Organization read
   */

  public static Organization organization() {
    return Organization.builder()
        .resourceType("Organization")
        .id("I2-ZJURFG76GQN5LW7WP56TXADUFM000000")
        .identifier(
            List.of(
                Identifier.builder()
                    .value("1205983228")
                    .system("http://hl7.org/fhir/sid/us-npi")
                    .build()))
        .active(true)
        .name("NEW AMSTERDAM CBOC")
        .telecom(List.of(
            ContactPoint.builder()
                .system(ContactPointSystem.phone)
                .value("800 555-7710")
                .build(),
            ContactPoint.builder()
                .system(ContactPointSystem.phone)
                .value("800 555-7710")
                .build()
        ))
        .address(List.of(Address.builder()
            .text("10 MONROE AVE, SUITE 6B PO BOX 4160 NEW AMSTERDAM OH 44444-4160")
            .line(List.of(
                "10 MONROE AVE, SUITE 6B",
                "PO BOX 4160"
            ))
            .city("NEW AMSTERDAM")
            .state("OH")
            .postalCode("44444-4160")
            .build()))
        .build();
  }

}
