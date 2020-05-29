package gov.va.api.health.argonaut.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.argonaut.api.resources.MedicationOrder;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.Search;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.elements.Extension;
import gov.va.api.health.dstu2.api.elements.Reference;

public class SwaggerMedicationOrder {

  /**
   * An example MedicationOrder.
   *
   * @return an example MedicationOrder.
   */
  public static MedicationOrder medicationOrder() {
    return MedicationOrder.builder()
        .resourceType("MedicationOrder")
        .id("f07dd74e-844e-5463-99d4-0ca4d5cbeb41")
        .dateWritten("2013-04-14T06:00:00Z")
        .status(MedicationOrder.Status.active)
        .patient(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        ._prescriber(
            Extension.builder()
                .extension(
                    asList(
                        Extension.builder()
                            .url("http://hl7.org/fhir/StructureDefinition/data-absent-reason")
                            .valueCode("unsupported")
                            .build()))
                .build())
        .medicationReference(
            Reference.builder()
                .reference(
                    "https://sandbox-api.va.gov/services/argonaut/v0/Medication/7b550d7f-2db8-5002-bc0c-150a70d02944")
                .display("Hydrochlorothiazide 25 MG")
                .build())
        .build();
  }

  /**
   * An example MedicationOrder.Bundle.
   *
   * @return an example MedicationOrder.Bundle.
   */
  public static final MedicationOrder.Bundle medicationOrderBundle() {
    return MedicationOrder.Bundle.builder()
        .resourceType("Bundle")
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationOrder?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationOrder?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationOrder?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                MedicationOrder.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/argonaut/v0/MedicationOrder/f07dd74e-844e-5463-99d4-0ca4d5cbeb41")
                    .resource(
                        MedicationOrder.builder()
                            .resourceType("MedicationOrder")
                            .id("f07dd74e-844e-5463-99d4-0ca4d5cbeb41")
                            .dateWritten("2013-04-14T06:00:00Z")
                            .status(MedicationOrder.Status.active)
                            .patient(
                                Reference.builder()
                                    .reference(
                                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                                    .display("Mr. Aurelio227 Cruickshank494")
                                    .build())
                            ._prescriber(
                                Extension.builder()
                                    .extension(
                                        asList(
                                            Extension.builder()
                                                .url(
                                                    "http://hl7.org/fhir/StructureDefinition/data-absent-reason")
                                                .valueCode("unsupported")
                                                .build()))
                                    .build())
                            .medicationReference(
                                Reference.builder()
                                    .reference(
                                        "https://sandbox-api.va.gov/services/argonaut/v0/Medication/7b550d7f-2db8-5002-bc0c-150a70d02944")
                                    .display("Hydrochlorothiazide 25 MG")
                                    .build())
                            .build())
                    .search(Search.builder().mode(SearchMode.match).build())
                    .build()))
        .build();
  }
}
