package gov.va.api.health.r4.api.swaggerexamples;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Location;
import java.util.List;

public class SwaggerLocation {
  /**
   * An example Location.
   *
   * @return an example Location read
   */
  public static Location location() {
    return Location.builder()
        .id("I2-4KG3N5YUSPTWD3DAFMLMRL5V5U000000")
        .status(Location.Status.active)
        .name("VISUAL IMPAIRMENT SVCS OUTPATIENT REHAB (VISOR)")
        .description("VISUAL IMPAIRMENT SVCS OUTPATIENT REHAB (VISOR)")
        .mode(Location.Mode.instance)
        .type(
            List.of(
                CodeableConcept.builder()
                    .coding(List.of(Coding.builder().display("OUTPATIENT CLINIC").build()))
                    .build()))
        .telecom(
            List.of(
                ContactPoint.builder()
                    .system(ContactPoint.ContactPointSystem.phone)
                    .value("908-647-0180 EXT 4437")
                    .build()))
        .address(
            Address.builder()
                .line(List.of("151 KNOLLCROFT ROAD"))
                .city("LYONS")
                .state("NJ")
                .postalCode("07939")
                .text("151 KNOLLCROFT ROAD LYONS NJ 07939")
                .build())
        .physicalType(
            CodeableConcept.builder()
                .coding(List.of(Coding.builder().display("BUILDING 7").build()))
                .build())
        .managingOrganization(
            Reference.builder()
                .reference(
                    "https://sandbox-api.va.gov/services/fhir/v0/r4/Organization/I2-TYIC2AW2NXNADER4SKRKJQZWRE000000")
                .display("LYONS VA MEDICAL CENTER")
                .build())
        .build();
  }

  /**
   * An example Location bundle.
   *
   * @return an example Location bundle
   */
  public static Location.Bundle locationBundle() {
    return Location.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            List.of(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Location?"
                            + "_id=I2-4KG3N5YUSPTWD3DAFMLMRL5V5U000000&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Location?"
                            + "_id=I2-4KG3N5YUSPTWD3DAFMLMRL5V5U000000&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Location?"
                            + "_id=I2-4KG3N5YUSPTWD3DAFMLMRL5V5U000000&page=1&_count=15")
                    .build()))
        .entry(
            List.of(
                Location.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/"
                            + "Location/I2-4KG3N5YUSPTWD3DAFMLMRL5V5U000000")
                    .resource(location())
                    .build()))
        .build();
  }
}
