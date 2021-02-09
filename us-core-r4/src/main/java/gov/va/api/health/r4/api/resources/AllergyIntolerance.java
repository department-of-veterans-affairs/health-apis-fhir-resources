package gov.va.api.health.r4.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
    description =
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-allergyintolerance.html",
    example =
        "${r4.allergyIntolerance:gov.va.api.health.r4.api.swaggerexamples."
            + "SwaggerAllergyIntolerance#allergyIntolerance}")
@ZeroOrOneOfs({
  @ZeroOrOneOf(
      fields = {"onsetDateTime", "onsetAge", "onsetPeriod", "onsetRange", "onsetString"},
      message = "Only one onset field may be specified")
})
@JsonDeserialize(builder = AllergyIntolerance.AllergyIntoleranceBuilder.class)
public final class AllergyIntolerance implements Resource {
  @NotBlank @Builder.Default String resourceType = "AllergyIntolerance";

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

  @Valid CodeableConcept clinicalStatus;

  @Valid CodeableConcept verificationStatus;

  Type type;

  List<Category> category;

  Criticality criticality;

  @Valid CodeableConcept code;

  @NotNull @Valid Reference patient;

  @Valid Reference encounter;

  @Pattern(regexp = Fhir.DATETIME)
  String onsetDateTime;

  @Valid Age onsetAge;

  @Valid Period onsetPeriod;

  @Valid Range onsetRange;

  String onsetString;

  @Pattern(regexp = Fhir.DATETIME)
  String recordedDate;

  @Valid Reference recorder;

  @Valid Reference asserter;

  @Pattern(regexp = Fhir.DATETIME)
  String lastOccurence;

  @Valid List<Annotation> note;

  @Valid List<Reaction> reaction;

  public enum Category {
    food,
    medication,
    environment,
    biologic
  }

  public enum Criticality {
    low,
    high,
    @JsonProperty("unable-to-assess")
    unable_to_assess,
  }

  public enum Severity {
    mild,
    moderate,
    severe
  }

  public enum Type {
    allergy,
    intolerance
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = AllergyIntolerance.Bundle.BundleBuilder.class)
  @Schema(
      name = "AllergyIntoleranceBundle",
      example =
          "${r4.allergyIntoleranceBundle:gov.va.api.health.r4.api.swaggerexamples."
              + "SwaggerAllergyIntolerance#allergyIntoleranceBundle}")
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
          defaultString(resourceType, "Bundle"),
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
  @Schema(name = "AllergyIntoleranceEntry")
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = AllergyIntolerance.Entry.EntryBuilder.class)
  public static final class Entry extends AbstractEntry<AllergyIntolerance> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid AllergyIntolerance resource,
        @Valid Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @Schema(name = "AllergyIntoleranceReaction")
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static final class Reaction implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept substance;

    @NotEmpty @Valid List<CodeableConcept> manifestation;

    String description;

    @Pattern(regexp = Fhir.DATETIME)
    String onset;

    Severity severity;

    @Valid CodeableConcept exposureRoute;

    @Valid List<Annotation> note;
  }
}
