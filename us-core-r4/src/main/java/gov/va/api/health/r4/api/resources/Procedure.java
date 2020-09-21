package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
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
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ExactlyOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-procedure.html",
    example =
        "${uscorer4.procedure:gov.va.api.health."
            + "uscorer4.api.swaggerexamples.SwaggerProcedure#procedure}")
@ExactlyOneOfs({
  @ExactlyOneOf(
      fields = {"performedDateTime", "performedPeriod"},
      message = "performedDateTime or performedPeriod, but not both.")
})
public class Procedure implements Resource {
  // Ancestors
  @NotBlank @Builder.Default String resourceType = "Procedure";

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

  // Procedure
  @Valid List<Identifier> identifier;

  @Valid List<Reference> instantiatesCanonical;

  @Pattern(regexp = Fhir.URI)
  String instantiatesUri;

  @Valid List<Reference> basedOn;

  @Valid List<Reference> partOf;

  // TODO: Consult Data-Sources on the differences from the Argonaut Profile
  @Valid @NotNull Status status;

  @Valid CodeableConcept statusReason;

  @Valid CodeableConcept category;

  @Valid @NotNull CodeableConcept code;

  @Valid @NotNull Reference subject;

  @Valid Reference encounter;

  @Pattern(regexp = Fhir.DATETIME)
  String performedDateTime;

  @Valid Period performedPeriod;

  @Valid Reference recorder;

  @Valid Reference asserter;

  @Valid List<Performer> performer;

  @Valid Reference location;

  @Valid List<CodeableConcept> reasonCode;

  @Valid List<Reference> reasonReference;

  @Valid List<CodeableConcept> bodySite;

  @Valid CodeableConcept outcome;

  @Valid List<Reference> report;

  @Valid List<CodeableConcept> complication;

  @Valid List<Reference> complicationDetail;

  @Valid List<CodeableConcept> followUp;

  @Valid List<Annotation> note;

  @Valid List<FocalDevice> focalDevice;

  @Valid List<Reference> usedReference;

  @Valid List<CodeableConcept> usedCode;

  public enum Status {
    preparation,
    @JsonProperty("in-progress")
    in_progress,
    @JsonProperty("not-done")
    not_done,
    @JsonProperty("on-hold")
    on_hold,
    stopped,
    completed,
    @JsonProperty("entered-in-error")
    entered_in_error,
    unknown
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = Visibility.ANY)
  @JsonDeserialize(builder = Procedure.Bundle.BundleBuilder.class)
  @Schema(
      name = "ProcedureBundle",
      example =
          "${uscorer4.procedureBundle:gov.va.api.health."
              + "uscorer4.api.swaggerexamples.SwaggerProcedure#procedureBundle}")
  public static class Bundle extends AbstractBundle<Entry> {

    /** Build a Procedure bundle. */
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
        @Valid List<Procedure.Entry> entry,
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
  @JsonAutoDetect(fieldVisibility = Visibility.ANY)
  @JsonDeserialize(builder = Procedure.Entry.EntryBuilder.class)
  @Schema(name = "ProcedureEntry")
  public static class Entry extends AbstractEntry<Procedure> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Procedure resource,
        @Valid AbstractEntry.Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = Visibility.ANY)
  public static class FocalDevice implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept action;

    @Valid @NotNull Reference manipulated;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = Visibility.ANY)
  public static class Performer implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept function;

    @Valid @NotNull Reference actor;

    @Valid Reference onBehalfOf;
  }
}
