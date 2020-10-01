package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.DiagnosticReport;
import java.util.List;

public class SwaggerDiagnosticReport {

  /**
   * An example DiagnosticReport.
   *
   * @return an example DiagnosticReport.
   */
  public static DiagnosticReport diagnosticReport() {
    return DiagnosticReport.builder()
        .resourceType("DiagnosticReport")
        .id("I2-M2QUOOXL3O73NUZCB7HEOVQ2GAGQFOATAYXW5FMU3I57IYQDE6RQ0000")
        .status(DiagnosticReport.DiagnosticReportStatus._final)
        .category(
            singletonList(
                CodeableConcept.builder()
                    .coding(
                        singletonList(
                            Coding.builder()
                                .system("http://terminology.hl7.org/CodeSystem/v2-0074")
                                .code("LAB")
                                .display("Laboratory")
                                .build()))
                    .build()))
        .code(CodeableConcept.builder().text("panel").build())
        .effectiveDateTime("2020-07-20T01:15:52Z")
        .issued("2020-07-20T01:15:52Z")
        .subject(
            Reference.builder()
                .reference(
                    "https://sandbox-api.va.gov/services/fhir/v0/r4/Patient/1011537977V693883")
                .display("Mr. Aurelio Cruickshank")
                .build())
        .result(
            List.of(
                Reference.builder()
                    .reference(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Observation/I2-6LCCQZ7GTK5X3P54BHCIZ2Q75IFIV5ZK7F4OUU7JUP72XENSV5ZQ0000")
                    .display("WBC COUNT")
                    .build(),
                Reference.builder()
                    .reference(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Observation/I2-MC6GKVYKVRED3ZLSG4EPUW5XMRLGOY46XCIGKDV4KNEBV5Y52NZA0000")
                    .display("HEMOGLOBIN")
                    .build()))
        .build();
  }

  /**
   * An example DiagnosticReport bundle.
   *
   * @return an example DiagnosticReport bundle.
   */
  public static DiagnosticReport.Bundle diagnosticReportBundle() {
    return DiagnosticReport.Bundle.builder()
        .resourceType("Bundle")
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/DiagnosticReport?patient=1011537977V693883&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/DiagnosticReport?patient=1011537977V693883&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/DiagnosticReport?patient=1011537977V693883&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                DiagnosticReport.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/DiagnosticReport/I2-M2QUOOXL3O73NUZCB7HEOVQ2GAGQFOATAYXW5FMU3I57IYQDE6RQ0000")
                    .resource(diagnosticReport())
                    .build()))
        .build();
  }
}
