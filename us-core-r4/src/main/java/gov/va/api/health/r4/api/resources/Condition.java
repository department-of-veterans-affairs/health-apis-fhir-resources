package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Age;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Range;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
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
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-condition.html",
    example =
        "${uscorer4.condition:gov.va.api.health."
            + "uscorer4.api.swaggerexamples.SwaggerCondition#condition}")
@ZeroOrOneOfs({
  @ZeroOrOneOf(
      fields = {"onsetDateTime", "onsetAge", "onsetPeriod", "onsetRange", "onsetString"},
      message = "Only one onset field may be specified"),
  @ZeroOrOneOf(
      fields = {
        "abatementDateTime",
        "abatementAge",
        "abatementPeriod",
        "abatementRange",
        "abatementString"
      },
      message = "Only one abatement field may be specified")
})
public class Condition implements Resource {
  // Ancestors
  @NotBlank @Builder.Default String resourceType = "Condition";

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

  // Condition
  @Valid List<Identifier> identifier;

  @Valid CodeableConcept clinicalStatus;

  @Valid CodeableConcept verificationStatus;

  @Valid @NotEmpty List<CodeableConcept> category;

  @Valid CodeableConcept severity;

  @Valid @NotNull CodeableConcept code;

  @Valid List<CodeableConcept> bodySite;

  @Valid @NotNull Reference subject;

  @Valid Reference encounter;

  @Pattern(regexp = Fhir.DATETIME)
  String onsetDateTime;

  @Valid Age onsetAge;

  @Valid Period onsetPeriod;

  @Valid Range onsetRange;

  String onsetString;

  @Pattern(regexp = Fhir.DATETIME)
  String abatementDateTime;

  @Valid Age abatementAge;

  @Valid Period abatementPeriod;

  @Valid Range abatementRange;

  String abatementString;

  @Pattern(regexp = Fhir.DATETIME)
  String recordedDate;

  @Valid Reference recorder;

  @Valid Reference asserter;

  @Valid List<Stage> stage;

  @Valid List<Evidence> evidence;

  @Valid List<Annotation> note;

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Condition.Bundle.BundleBuilder.class)
  @Schema(
      name = "ConditionBundle",
      example =
          "${uscorer4.conditionBundle:gov.va.api.health."
              + "uscorer4.api.swaggerexamples.SwaggerCondition#conditionBundle}")
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
  @Schema(name = "ConditionEntry")
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Condition.Entry.EntryBuilder.class)
  public static final class Entry extends AbstractEntry<Condition> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Condition resource,
        @Valid AbstractEntry.Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @Schema(name = "ConditionEvidence")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Evidence implements BackboneElement {
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<CodeableConcept> code;

    @Valid List<Reference> detail;
  }

  @Data
  @Builder
  @Schema(name = "ConditionStage")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Stage implements BackboneElement {
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept summary;

    @Valid List<Reference> assessment;

    @Valid CodeableConcept type;
  }
}
