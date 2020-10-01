package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Immunization;

public class SwaggerImmunization {

  /**
   * An example Immunization.
   *
   * @return an example Immunization.
   */
  public static Immunization immunization() {
    return Immunization.builder()
        .resourceType("Immunization")
        .id("I2-U4FPJS3E633MAJQBCAA2KAB5BQ000000")
        .status(Immunization.Status.completed)
        .occurrenceString("2017-04-24T01:15:52Z")
        .vaccineCode(
            CodeableConcept.builder()
                .text("meningococcal MCV4P")
                .coding(
                    asList(
                        Coding.builder().system("http://hl7.org/fhir/sid/cvx").code("114").build()))
                .build())
        .patient(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/fhir/v0/r4/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .reaction(
            asList(
                Immunization.Reaction.builder()
                    .detail(Reference.builder().display("Lethargy").build())
                    .build()))
        .build();
  }

  /**
   * An example Immunization.
   *
   * @return an example Immunization.
   */
  public static Immunization.Bundle immunizationBundle() {
    return Immunization.Bundle.builder()
        .resourceType("Bundle")
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Immunization?patient=2000163&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Immunization?patient=2000163&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Immunization?patient=2000163&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Immunization.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Immunization/I2-U4FPJS3E633MAJQBCAA2KAB5BQ000000")
                    .resource(immunization())
                    .build()))
        .build();
  }
}
