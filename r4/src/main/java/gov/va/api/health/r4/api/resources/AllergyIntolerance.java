package gov.va.api.health.r4.api.resources;

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
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
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
        "http://www.fhir.org/guides/r4/r2/StructureDefinition-argo-allergyintolerance.html",
    example =
        "${r4.allergyIntolerance:gov.va.api.health.r4.api.swaggerexamples."
            + "SwaggerAllergyIntolerance#allergyIntolerance}")
public final class AllergyIntolerance implements Resource {
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

  @Valid CodeableConcept clinicalStatus;

  @Valid CodeableConcept verificationStatus;

  Type type;

  Category category;

  Criticality criticality;
  
  @Valid CodeableConcept code;
  
  @NotNull @Valid Reference patient;
  
  @Valid Encounter encounter;
  
  
  
  
  

  @Pattern(regexp = Fhir.DATETIME)
  String onset;

  @Pattern(regexp = Fhir.DATETIME)
  String recordedDate;

  @Valid Reference recorder;

  @Valid Reference reporter;

  @NotNull @Valid CodeableConcept substance;

  @NotNull Status status;

  @Pattern(regexp = Fhir.DATETIME)
  String lastOccurence;

  @Valid Annotation note;

  @Valid List<Reaction> reaction;

  public enum Category {
    food,
    medication,
    environment,
    biologic
  }

  public enum Certainty {
    unlikely,
    likely,
    confirmed
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

  public enum Status {
    active,
    unconfirmed,
    confirmed,
    inactive,
    resolved,
    refuted,
    @JsonProperty("entered-in-error")
    entered_in_error
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
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = AllergyIntolerance.Entry.EntryBuilder.class)
  @Schema(name = "AllergyIntoleranceEntry")
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
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "AllergyIntoleranceReaction")
  public static final class Reaction implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> modifierExtension;

    @Valid List<Extension> extension;

    @Valid CodeableConcept substance;

    Certainty certainty;

    @NotEmpty @Valid List<CodeableConcept> manifestation;

    String description;

    @Pattern(regexp = Fhir.DATETIME)
    String onset;

    Severity severity;

    @Valid CodeableConcept exposureRoute;

    @Valid Annotation note;
  }
}
