package gov.va.api.health.argonaut.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.argonaut.api.resources.DiagnosticReport;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.Search;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.datatypes.Coding;
import gov.va.api.health.dstu2.api.elements.Reference;

public class SwaggerDiagnosticReport {

  /**
   * An example DiagnosticReport.
   *
   * @return an example DiagnosticReport.
   */
  public static final DiagnosticReport diagnosticReport() {
    return DiagnosticReport.builder()
        .resourceType("DiagnosticReport")
        .id("0757389a-6e06-51bd-aac0-bd0244e51e46")
        .status(DiagnosticReport.Code._final)
        .category(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .system("http://hl7.org/fhir/ValueSet/diagnostic-service-sections")
                            .code("LAB")
                            .display("Laboratory")
                            .build()))
                .build())
        .code(CodeableConcept.builder().text("panel").build())
        .effectiveDateTime("2011-04-04T01:15:52Z")
        .issued("2011-04-04T01:15:52Z")
        .subject(
            Reference.builder()
                .reference("https://dev-api.va.gov/services/argonaut/v0/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .build();
  }

  /**
   * An example DiagnosticReport.Bundle.
   *
   * @return an example DiagnosticReport.Bundle.
   */
  public static final DiagnosticReport.Bundle diagnosticReportBundle() {
    return DiagnosticReport.Bundle.builder()
        .resourceType("Bundle")
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://dev-api.va.gov/services/argonaut/v0/DiagnosticReport?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://dev-api.va.gov/services/argonaut/v0/DiagnosticReport?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://dev-api.va.gov/services/argonaut/v0/DiagnosticReport?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                DiagnosticReport.Entry.builder()
                    .fullUrl(
                        "https://dev-api.va.gov/services/argonaut/v0/DiagnosticReport/0757389a-6e06-51bd-aac0-bd0244e51e46")
                    .resource(
                        DiagnosticReport.builder()
                            .resourceType("DiagnosticReport")
                            .id("0757389a-6e06-51bd-aac0-bd0244e51e46")
                            .status(DiagnosticReport.Code._final)
                            .category(
                                CodeableConcept.builder()
                                    .coding(
                                        asList(
                                            Coding.builder()
                                                .system(
                                                    "http://hl7.org/fhir/ValueSet/diagnostic-service-sections")
                                                .code("LAB")
                                                .display("Laboratory")
                                                .build()))
                                    .build())
                            .code(CodeableConcept.builder().text("panel").build())
                            .effectiveDateTime("2011-04-04T01:15:52Z")
                            .issued("2011-04-04T01:15:52Z")
                            .subject(
                                Reference.builder()
                                    .reference(
                                        "https://dev-api.va.gov/services/argonaut/v0/Patient/2000163")
                                    .display("Mr. Aurelio227 Cruickshank494")
                                    .build())
                            .build())
                    .search(Search.builder().mode(SearchMode.match).build())
                    .build()))
        .build();
  }
}
