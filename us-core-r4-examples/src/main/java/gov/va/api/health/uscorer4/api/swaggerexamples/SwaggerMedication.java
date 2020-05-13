package gov.va.api.health.uscorer4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.uscorer4.api.resources.Medication;
import java.util.List;

public class SwaggerMedication {

  /**
   * An example Medication.
   *
   * @return an example Medication.
   */
  public static Medication medication() {
    return Medication.builder()
        .resourceType("Medication")
        .id("I2-U4FPJS3E633MAJQBCAA2KAB5BQ000000")
        .status(Medication.Status.active)
        .code(
            CodeableConcept.builder()
                .text("Methyltestosterone 10 MG Oral Tablet")
                .coding(
                    List.of(
                        Coding.builder()
                            .system("http://www.nlm.nih.gov/research/umls/rxnorm")
                            .code("91805")
                            .build()))
                .build())
        .build();
  }

  /**
   * An example Medication.
   *
   * @return an example Medication.
   */
  public static Medication.Bundle medicationBundle() {
    return Medication.Bundle.builder()
        .resourceType("Bundle")
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/r4/Medication?"
                            + "patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Medication?"
                            + "patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Medication?"
                            + "patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Medication.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Medication/"
                            + "I2-U4FPJS3E633MAJQBCAA2KAB5BQ000000")
                    .resource(medication())
                    .build()))
        .build();
  }
}
