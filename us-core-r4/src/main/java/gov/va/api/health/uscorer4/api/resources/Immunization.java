package gov.va.api.health.uscorer4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Resource;
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
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
    description =
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-immunization.html",
    example =
        "${uscorer4.immunization:gov.va.api.health."
            + "uscorer4.api.swaggerexamples.SwaggerImmunization#immunization}")
@ExactlyOneOfs({
  @ExactlyOneOf(
      fields = {"occurrenceDateTime", "occurrenceString"},
      message =
          "Exactly one occurrence field may be specified... occurrenceDateTime | occurrenceString")
})
public class Immunization implements Resource {
  // Ancestors
  @NotBlank @Builder.Default String resourceType = "Immunization";

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

  // Immunization
  @Valid List<Identifier> identifier;

  @Valid @NotNull Status status;

  @Valid CodeableConcept statusReason;

  @Valid @NotNull CodeableConcept vaccineCode;

  @Valid @NotNull Reference patient;

  @Valid Reference encounter;

  @Pattern(regexp = Fhir.DATETIME)
  String occurrenceDateTime;

  String occurrenceString;

  @Pattern(regexp = Fhir.DATETIME)
  String recorded;

  @NotNull Boolean primarySource;

  @Valid CodeableConcept reportOrigin;

  @Valid Reference location;

  @Valid Reference manufacturer;

  String lotNumber;

  @Pattern(regexp = Fhir.DATE)
  String expirationDate;

  @Valid CodeableConcept site;

  @Valid CodeableConcept route;

  @Valid SimpleQuantity doesQuantity;

  @Valid List<Performer> performer;

  @Valid List<Annotation> note;

  @Valid List<CodeableConcept> reasonCode;

  @Valid List<Reference> reasonReference;

  Boolean isSubpotent;

  @Valid List<CodeableConcept> subpotentReason;

  @Valid List<Education> education;

  @Valid List<CodeableConcept> programEligibility;

  @Valid CodeableConcept fundingSource;

  @Valid List<Reaction> reaction;

  @Valid List<ProtocolApplied> protocolApplied;

  public enum Status {
    completed,
    @JsonProperty("entered-in-error")
    entered_in_error,
    @JsonProperty("not-done")
    not_done
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Immunization.Bundle.BundleBuilder.class)
  @Schema(
      name = "ImmunizationBundle",
      example =
          "${r4.immunizationBundle:gov.va.api.health."
              + "r4.api.swaggerexamples.SwaggerImmunization#immunizationBundle}")
  public static final class Bundle extends AbstractBundle<Immunization.Entry> {
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
        @Valid List<Immunization.Entry> entry,
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
  @Schema(name = "ImmunizationEntry")
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Immunization.Entry.EntryBuilder.class)
  public static final class Entry extends AbstractEntry<Immunization> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Immunization resource,
        @Valid AbstractEntry.Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @Schema(name = "ImmunizationPerformer")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Performer implements BackboneElement {
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept function;

    @Valid @NotNull Reference actor;
  }

  @Data
  @Builder
  @Schema(name = "ImmunizationEducation")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Education implements BackboneElement {
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    String documentType;

    @Pattern(regexp = Fhir.URI)
    String reference;

    @Pattern(regexp = Fhir.DATETIME)
    String publicationDate;

    @Pattern(regexp = Fhir.DATETIME)
    String presentationDate;
  }

  @Data
  @Builder
  @Schema(name = "ImmunizationReaction")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Reaction implements BackboneElement {
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Pattern(regexp = Fhir.DATETIME)
    String date;

    @Valid Reference detail;

    Boolean reported;
  }

  @Data
  @Builder
  @Schema(name = "ImmunizationProtocolApplied")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @ExactlyOneOfs({
    @ExactlyOneOf(
        fields = {"doseNumberPositiveInt", "doseNumberString"},
        message = "Exactly one field for doseNumber... doseNumberPositiveInt | doseNumberString")
  })
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
        fields = {"seriesDosesPositiveInt", "seriesDosesString"},
        message =
            "Only one seriesDoses field may be specified... "
                + "seriesDosesPositiveInt | seriesDosesString")
  })
  public static class ProtocolApplied implements BackboneElement {
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    String series;

    @Valid Reference authority;

    @Valid List<CodeableConcept> targetDisease;

    @Pattern(regexp = Fhir.POSITIVE_INT)
    String doseNumberPositiveInt;

    String doseNumberString;

    @Pattern(regexp = Fhir.POSITIVE_INT)
    String seriesDosesPositiveInt;

    String seriesDosesString;
  }
}
