package gov.va.api.health.dstu2.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.Search;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.datatypes.Timing;
import gov.va.api.health.dstu2.api.elements.Reference;
import gov.va.api.health.dstu2.api.resources.MedicationStatement;

public class SwaggerMedicationStatement {
  /**
   * An example MedicationStatement.
   *
   * @return an example MedicationStatement.
   */
  public static MedicationStatement medicationStatement() {
    return MedicationStatement.builder()
        .id("1f46363d-af9b-5ba5-acda-b384373a9af2")
        .patient(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .dateAsserted("2013-04-15T01:15:52Z")
        .status(MedicationStatement.Status.active)
        .medicationReference(
            Reference.builder()
                .reference(
                    "https://sandbox-api.va.gov/services/argonaut/v0/Medication/7b550d7f-2db8-5002-bc0c-150a70d02944")
                .display("Hydrochlorothiazide 25 MG")
                .build())
        .dosage(
            asList(
                MedicationStatement.Dosage.builder()
                    .text("Once per day.")
                    .timing(
                        Timing.builder()
                            .code(
                                CodeableConcept.builder().text("As directed by physician.").build())
                            .build())
                    .route(CodeableConcept.builder().text("As directed by physician.").build())
                    .build()))
        .build();
  }

  /**
   * An example MedicationStatement.Bundle.
   *
   * @return an example MedicationStatement.Bundle.
   */
  public static MedicationStatement.Bundle medicationStatementBundle() {
    return MedicationStatement.Bundle.builder()
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationStatement?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationStatement?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationStatement?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                MedicationStatement.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationStatement/1f46363d-af9b-5ba5-acda-b384373a9af2")
                    .resource(
                        MedicationStatement.builder()
                            .id("1f46363d-af9b-5ba5-acda-b384373a9af2")
                            .patient(
                                Reference.builder()
                                    .reference(
                                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                                    .display("Mr. Aurelio227 Cruickshank494")
                                    .build())
                            .dateAsserted("2013-04-15T01:15:52Z")
                            .status(MedicationStatement.Status.active)
                            .medicationReference(
                                Reference.builder()
                                    .reference(
                                        "https://sandbox-api.va.gov/services/argonaut/v0/Medication/7b550d7f-2db8-5002-bc0c-150a70d02944")
                                    .display("Hydrochlorothiazide 25 MG")
                                    .build())
                            .dosage(
                                asList(
                                    MedicationStatement.Dosage.builder()
                                        .text("Once per day.")
                                        .timing(
                                            Timing.builder()
                                                .code(
                                                    CodeableConcept.builder()
                                                        .text("As directed by physician.")
                                                        .build())
                                                .build())
                                        .route(
                                            CodeableConcept.builder()
                                                .text("As directed by physician.")
                                                .build())
                                        .build()))
                            .build())
                    .search(Search.builder().mode(SearchMode.match).build())
                    .build()))
        .build();
  }
}
