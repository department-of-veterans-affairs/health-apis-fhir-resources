package gov.va.api.health.dstu2.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.dstu2.api.Fhir;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.datatypes.Annotation;
import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.datatypes.Identifier;
import gov.va.api.health.dstu2.api.datatypes.Signature;
import gov.va.api.health.dstu2.api.datatypes.SimpleResource;
import gov.va.api.health.dstu2.api.elements.BackboneElement;
import gov.va.api.health.dstu2.api.elements.Extension;
import gov.va.api.health.dstu2.api.elements.Meta;
import gov.va.api.health.dstu2.api.elements.Narrative;
import gov.va.api.health.dstu2.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ExactlyOneOfs;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOfs;
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
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Schema(
    description = "http://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-procedure.html",
    example =
        "${dstu2.procedure:gov.va.api.health.dstu2.api.swaggerexamples"
            + ".SwaggerProcedure#procedure}")
@ExactlyOneOfs({
  @ExactlyOneOf(
      fields = {"status", "_status"},
      message = "Status or _Status, but not both."),
  @ExactlyOneOf(
      fields = {"performedDateTime", "performedPeriod"},
      message = "performedDateTime or performedPeriod, but not both.")
})
@ZeroOrOneOfs(
    @ZeroOrOneOf(
        fields = {"reasonCodeableConcept", "reasonReference"},
        message = "At most one reason may be specified."))
public class Procedure implements Resource {
  @NotBlank String resourceType;

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
  @Valid List<Identifier> identifier;

  @Valid @NotNull Reference subject;

  @Valid Status status;
  @Valid Extension _status;

  @Valid CodeableConcept category;
  @Valid @NotNull CodeableConcept code;

  Boolean notPerformed;
  @Valid List<CodeableConcept> reasonNotPerformed;

  @Valid List<CodeableConcept> bodySite;

  @Valid CodeableConcept reasonCodeableConcept;
  @Valid Reference reasonReference;

  @Valid List<Performer> performer;

  @Valid String performedDateTime;
  @Valid String performedPeriod;

  @Valid Reference encounter;
  @Valid Reference location;
  @Valid CodeableConcept outcome;
  @Valid List<Reference> report;
  @Valid List<CodeableConcept> complication;
  @Valid List<CodeableConcept> followUp;
  @Valid Reference request;
  @Valid List<Annotation> notes;

  @Valid List<FocalDevice> focalDevice;
  @Valid List<Reference> used;

  public enum Status {
    @JsonProperty("in-progress")
    in_progress,
    aborted,
    completed,
    @JsonProperty("entered-in-error")
    entered_in_error
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = Visibility.ANY)
  @JsonDeserialize(builder = Procedure.Bundle.BundleBuilder.class)
  @Schema(
      name = "ProcedureBundle",
      example =
          "${dstu2.procedureBundle:gov.va.api.health.dstu2.api.swaggerexamples"
              + ".SwaggerProcedure#procedureBundle}")
  public static class Bundle extends AbstractBundle<Entry> {
    @Builder
    public Bundle(
        @NotBlank String resourceType,
        @Pattern(regexp = Fhir.ID) String id,
        @Valid Meta meta,
        @Pattern(regexp = Fhir.URI) String implicitRules,
        @Pattern(regexp = Fhir.CODE) String language,
        @NotNull BundleType type,
        @Min(0) Integer total,
        @Valid List<BundleLink> link,
        @Valid List<Entry> entry,
        @Valid Signature signature) {
      super(resourceType, id, meta, implicitRules, language, type, total, link, entry, signature);
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
        @Valid Search search,
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

    @Valid Reference actor;
    @Valid CodeableConcept role;
  }
}
