package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Claim;
import java.util.Collections;

public class SwaggerClaim {

  /**
   * An example Claim.
   *
   * @return an example Claim.
   */
  public static Claim claim() {
    return Claim.builder()
        .id("1234509876")
        .status(Claim.Status.active)
        .type(
            CodeableConcept.builder()
                .coding(Collections.singletonList(Coding.builder().code("institutional").build()))
                .text("Hospital, clinic and typically inpatient claims.")
                .build())
        .use(Claim.Use.claim)
        .patient(Reference.builder().display("Patient/1239908V993").build())
        .created("2019-10-23T20:48:30.000-06:00")
        .provider(Reference.builder().display("Organization/11153").build())
        .priority(
            CodeableConcept.builder()
                .coding(Collections.singletonList(Coding.builder().code("normal").build()))
                .text("With best effort.")
                .build())
        .procedure(
            Collections.singletonList(
                Claim.Procedure.builder()
                    .sequence(78362914)
                    .type(
                        Collections.singletonList(
                            CodeableConcept.builder()
                                .coding(
                                    Collections.singletonList(
                                        Coding.builder().code("primary").build()))
                                .text(
                                    "The first procedure in a series required to address problem.")
                                .build()))
                    .date("2019-04-12")
                    .procedureCodeableConcept(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(Coding.builder().code("39216").build()))
                            .text("Minimally Invasive Lumbar Spinal Fusion")
                            .build())
                    .build()))
        .insurance(
            Collections.singletonList(
                Claim.Insurance.builder()
                    .sequence(122)
                    .focal(true)
                    .coverage(Reference.builder().display("Coverage/98203441").build())
                    .build()))
        .build();
  }

  /**
   * An example Bundle.
   *
   * @return an example Bundle.
   */
  public static Claim.Bundle claimBundle() {
    return Claim.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Claim?patient=1008679665V880686&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Claim?patient=1008679665V880686&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Claim?patient=1008679665V880686&page=1&_count=15")
                    .build()))
        .entry(asList(Claim.Entry.builder().resource(claim()).build()))
        .build();
  }
}
