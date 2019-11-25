package gov.va.api.health.argonaut.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.argonaut.api.resources.Condition;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.Search;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.datatypes.Coding;
import gov.va.api.health.dstu2.api.elements.Reference;

public class SwaggerCondition {

  /**
   * An example Condition.
   *
   * @return an example Condition.
   */
  public static Condition condition() {
    return Condition.builder()
        .resourceType("Condition")
        .id("b34bacd3-42b6-5613-b1c2-1abafe1248ba")
        .patient(
            Reference.builder()
                .reference("https://dev-api.va.gov/services/argonaut/v0/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .code(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .code("38341003")
                            .system("https://www.snomed.org/snomed-ct")
                            .display("Hypertension")
                            .build()))
                .text("Hypertension")
                .build())
        .category(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder().system("http://argonaut.hl7.org").code("problem").build()))
                .build())
        .clinicalStatus(Condition.ClinicalStatusCode.active)
        .verificationStatus(Condition.VerificationStatusCode.unknown)
        .dateRecorded("2013-04-14")
        .onsetDateTime("2013-04-15T01:15:52Z")
        .build();
  }

  /**
   * An example Condition.Bundle.
   *
   * @return an example Condition.Bundle.
   */
  public static Condition.Bundle conditionBundle() {
    return Condition.Bundle.builder()
        .resourceType("Bundle")
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://dev-api.va.gov/services/argonaut/v0/Condition?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://dev-api.va.gov/services/argonaut/v0/Condition?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://dev-api.va.gov/services/argonaut/v0/Condition?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Condition.Entry.builder()
                    .fullUrl(
                        "https://dev-api.va.gov/services/argonaut/v0/Condition/b34bacd3-42b6-5613-b1c2-1abafe1248ba")
                    .resource(
                        Condition.builder()
                            .resourceType("Condition")
                            .id("b34bacd3-42b6-5613-b1c2-1abafe1248ba")
                            .patient(
                                Reference.builder()
                                    .reference(
                                        "https://dev-api.va.gov/services/argonaut/v0/Patient/2000163")
                                    .display("Mr. Aurelio227 Cruickshank494")
                                    .build())
                            .code(
                                CodeableConcept.builder()
                                    .coding(
                                        asList(
                                            Coding.builder()
                                                .code("38341003")
                                                .system("https://www.snomed.org/snomed-ct")
                                                .display("Hypertension")
                                                .build()))
                                    .text("Hypertension")
                                    .build())
                            .category(
                                CodeableConcept.builder()
                                    .coding(
                                        asList(
                                            Coding.builder()
                                                .system("http://argonaut.hl7.org")
                                                .code("problem")
                                                .build()))
                                    .build())
                            .clinicalStatus(Condition.ClinicalStatusCode.active)
                            .verificationStatus(Condition.VerificationStatusCode.unknown)
                            .dateRecorded("2013-04-14")
                            .onsetDateTime("2013-04-15T01:15:52Z")
                            .build())
                    .search(Search.builder().mode(SearchMode.match).build())
                    .build()))
        .build();
  }
}
