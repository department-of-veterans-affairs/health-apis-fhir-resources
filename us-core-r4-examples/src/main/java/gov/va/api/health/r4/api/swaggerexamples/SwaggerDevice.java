package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Device;
import gov.va.api.health.r4.api.resources.Device.DeviceNameType;

public class SwaggerDevice {

  /**
   * An example Device.
   *
   * @return an example Device.
   */
  public static Device device() {
    return Device.builder()
        .resourceType("Device")
        .id("800001608621")
        .patient(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/fhir/v0/r4/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .owner(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/fhir/v0/r4/Organization/528")
                .display("JONESBORO VA CLINIC")
                .build())
        .type(
            CodeableConcept.builder()
                .text("Prosthesis, device (physical object)")
                .coding(
                    asList(
                        Coding.builder().system("http://snomed.info/sct").code("53350007").build()))
                .build())
        .manufacturer("BOSTON SCIENTIFIC")
        .deviceName(
            asList(
                Device.DeviceName.builder().name("L331").type(DeviceNameType.model_name).build(),
                Device.DeviceName.builder()
                    .name("PACEMAKER")
                    .type(DeviceNameType.user_friendly_name)
                    .build()))
        .lotNumber("A19031")
        .serialNumber("819569")
        .build();
  }

  /**
   * An example Device.
   *
   * @return an example Device.
   */
  public static Device.Bundle deviceBundle() {
    return Device.Bundle.builder()
        .resourceType("Bundle")
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Device?patient=2000163&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Device?patient=2000163&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Device?patient=2000163&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Device.Entry.builder()
                    .fullUrl("https://sandbox-api.va.gov/services/fhir/v0/r4/Device/800001608621")
                    .resource(device())
                    .build()))
        .build();
  }
}
