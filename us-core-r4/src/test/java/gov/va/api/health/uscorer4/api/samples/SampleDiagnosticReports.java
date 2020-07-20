package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.uscorer4.api.resources.DiagnosticReport;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public final class SampleDiagnosticReports {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  private CodeableConcept categorySliceLab() {
    return CodeableConcept.builder()
        .id("123")
        .extension(singletonList(extension()))
        .coding(
            singletonList(
                Coding.builder()
                    .system("http://terminology.hl7.org/CodeSystem/v2-0074")
                    .code("LAB")
                    .build()))
        .text("Laboratory")
        .build();
  }

  public DiagnosticReport diagnosticReport() {
    return DiagnosticReport.builder()
        .id("123")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .basedOn(singletonList(reference()))
        .status(DiagnosticReport.DiagnosticReportStatus.unknown)
        .category(singletonList(categorySliceLab()))
        .code(codeableConcept())
        .subject(reference())
        .encounter(reference())
        .effectiveDateTime("2020-07-20T12:47:00Z")
        .issued("2020-07-20T12:47:00Z")
        .performer(singletonList(reference()))
        .resultsInterpreter(singletonList(reference()))
        .specimen(singletonList(reference()))
        .result(singletonList(reference()))
        .imagingStudy(singletonList(reference()))
        .media(singletonList(media()))
        .conclusion("Concluded")
        .conclusionCode(singletonList(codeableConcept()))
        .presentedForm(singletonList(attachment()))
        .build();
  }

  private DiagnosticReport.Media media() {
    return DiagnosticReport.Media.builder()
        .id("123")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .comment("Commented")
        .link(reference())
        .build();
  }
}
