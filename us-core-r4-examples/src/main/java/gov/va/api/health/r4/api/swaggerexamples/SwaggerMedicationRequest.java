package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Timing;
import gov.va.api.health.r4.api.elements.Dosage;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.MedicationRequest;
import java.util.List;

public class SwaggerMedicationRequest {

  /**
   * An example MedicationRequest.
   *
   * @return an example MedicationRequest.
   */
  public static MedicationRequest medicationRequest() {
    return MedicationRequest.builder()
        .id("medrx0302")
        .status(MedicationRequest.Status.active)
        .intent(MedicationRequest.Intent.order)
        .medicationReference(
            Reference.builder()
                .reference(
                    "https://sandbox-api.va.gov/services/fhir/v0/r4/Medication"
                        + "/I2-LTU3MVTE2VLWXUQKKCRRTEWJDE000000")
                .display("Azithromycin 250mg capsule")
                .build())
        .subject(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/fhir/v0/r4/Patient/32000225")
                .display("Mrs. Sheba703 Harris789")
                .build())
        .authoredOn("2015-01-15T12:03:52Z")
        ._requester(
            Extension.builder()
                .extension(
                    asList(
                        Extension.builder()
                            .url("http://hl7.org/fhir/extension-data-absent-reason.html")
                            .valueCode("unknown")
                            .build()))
                .build())
        .reasonCode(
            asList(
                CodeableConcept.builder()
                    .text("Traveller's Diarrhea (disorder)")
                    .coding(
                        List.of(
                            Coding.builder()
                                .system("http://snomed.info/sct")
                                .code("11840006")
                                .display("Traveler's diarrhea")
                                .build()))
                    .build()))
        .note(asList(Annotation.builder().authorString("Patient told to take with food").build()))
        .dosageInstruction(
            asList(
                Dosage.builder()
                    .text("Once per day.")
                    .timing(
                        Timing.builder()
                            .code(
                                CodeableConcept.builder().text("As directed by physician.").build())
                            .build())
                    .route(CodeableConcept.builder().text("As directed by physician.").build())
                    .build()))
        .substitution(
            MedicationRequest.Substitution.builder()
                .allowedBoolean(true)
                .reason(CodeableConcept.builder().text("formulary policy").build())
                .build())
        .build();
  }

  /**
   * An example MedicationRequest.
   *
   * @return an example MedicationRequest.
   */
  public static MedicationRequest.Bundle medicationRequestBundle() {
    return MedicationRequest.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/"
                            + "MedicationRequest?patient=32000225&intent=order&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/"
                            + "MedicationRequest?patient=32000225&intent=order&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/"
                            + "MedicationRequest?patient=32000225&intent=order&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                MedicationRequest.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/MedicationRequest/"
                            + "I2-AOV4FXGQLPIXGZPTMTWY7Y7KJ4000000")
                    .resource(medicationRequest())
                    .search(
                        AbstractEntry.Search.builder().mode(AbstractEntry.SearchMode.match).build())
                    .build()))
        .build();
  }
}
