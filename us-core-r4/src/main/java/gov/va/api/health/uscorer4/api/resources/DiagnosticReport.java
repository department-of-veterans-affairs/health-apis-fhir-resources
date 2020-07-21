package gov.va.api.health.uscorer4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Resource;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ExactlyOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
    description =
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-diagnosticreport-lab.html",
    example =
        "${uscorer4.diagnosticReport:gov.va.api.health."
            + "uscorer4.api.swaggerexamples.SwaggerDiagnosticReport#diagnosticReport}")
@ExactlyOneOfs({
  @ExactlyOneOf(
      fields = {"effectiveDateTime", "effectivePeriod"},
      message = "One of effectiveDateTime | effectivePeriod must be set")
})
public class DiagnosticReport implements Resource {
  @NotBlank @Builder.Default String resourceType = "DiagnosticReport";

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid List<SimpleResource> contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  // Diagnostic Report
  @Valid List<Identifier> identifier;

  @Valid List<Reference> basedOn;

  @NotNull DiagnosticReportStatus status;

  /* This is a slice definition.
   * That being the case, it requires fields that plain old CC would not.
   */
  @Valid @NotEmpty List<CodeableConcept> category;

  @Valid @NotNull CodeableConcept code;

  @Valid @NotNull Reference subject;

  @Valid Reference encounter;

  @Pattern(regexp = Fhir.DATETIME)
  String effectiveDateTime;

  @Valid Period effectivePeriod;

  @NotNull
  @Pattern(regexp = Fhir.INSTANT)
  String issued;

  @Valid List<Reference> performer;

  @Valid List<Reference> resultsInterpreter;

  @Valid List<Reference> specimen;

  @Valid List<Reference> result;

  @Valid List<Reference> imagingStudy;

  List<Media> media;

  String conclusion;

  @Valid List<CodeableConcept> conclusionCode;

  @Valid List<Attachment> presentedForm;

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "Slice Definition is Invalid.")
  private boolean isValidCategoryWithSlices() {
    /* Both System and Code are required fields for the slice.
     *  We only support LAB, so check exclusively for LAB values.
     */
    for (CodeableConcept slice : category) {
      // LaboratorySlice is a 1..1 cardinality for the category CodeableConcept array
      int labSliceCount =
          (int)
              slice.coding().stream()
                  .filter(
                      c ->
                          "http://terminology.hl7.org/CodeSystem/v2-0074".equals(c.system())
                              && "LAB".equals(c.code()))
                  .count();
      if (labSliceCount != 1) {
        return false;
      }
    }
    return true;
  }

  public enum DiagnosticReportStatus {
    amended,
    appended,
    cancelled,
    corrected,
    @JsonProperty("entered-in-error")
    entered_in_error,
    @JsonProperty("final")
    _final,
    partial,
    preliminary,
    registered,
    unknown
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = DiagnosticReport.Bundle.BundleBuilder.class)
  @Schema(
      name = "DiagnosticReportBundle",
      example =
          "${uscorer4.diagnosticReportBundle:gov.va.api.health."
              + "uscorer4.api.swaggerexamples.SwaggerDiagnosticReport#diagnosticReportBundle}")
  public static final class Bundle extends AbstractBundle<Entry> {
    /** Builder constructor. */
    @Builder
    public Bundle(
        @NotBlank String resourceType,
        @Pattern(regexp = Fhir.ID) String id,
        @Valid Meta meta,
        @Pattern(regexp = Fhir.URI) String implicitRules,
        @Pattern(regexp = Fhir.CODE) String language,
        @Valid Identifier identifier,
        @NotNull AbstractBundle.BundleType type,
        @Pattern(regexp = Fhir.INSTANT) String timestamp,
        @Min(0) Integer total,
        @Valid List<BundleLink> link,
        @Valid List<Entry> entry,
        @Valid Signature signature) {
      super(
          resourceType,
          id,
          meta,
          implicitRules,
          language,
          identifier,
          type,
          timestamp,
          total,
          link,
          entry,
          signature);
    }
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @Schema(name = "DiagnosticReportEntry")
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = DiagnosticReport.Entry.EntryBuilder.class)
  public static final class Entry extends AbstractEntry<DiagnosticReport> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid DiagnosticReport resource,
        @Valid AbstractEntry.Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @Schema(name = "DiagnosticReportMedia")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Media implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    String comment;

    @NotNull Reference link;
  }
}
