package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Range;
import gov.va.api.health.r4.api.datatypes.Ratio;
import gov.va.api.health.r4.api.datatypes.SampledData;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ExactlyOneOfs;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-observation-lab.html",
    example =
        "${r4.observation:"
            + "gov.va.api.health.r4.api.swaggerexamples."
            + "SwaggerObservation#observation}")
@ExactlyOneOfs(
    @ExactlyOneOf(
        fields = {"effectiveDateTime", "effectivePeriod"},
        message = "Exactly one field for effective... effectiveDateTime | effectivePeriod"))
@ZeroOrOneOfs(
    @ZeroOrOneOf(
        fields = {
          "valueQuantity",
          "valueCodeableConcept",
          "valueString",
          "valueBoolean",
          "valueInteger",
          "valueRange",
          "valueRatio",
          "valueSampledData",
          "valueTime",
          "valueDateTime",
          "valuePeriod"
        },
        message = "Only one value field may be set"))
public class Observation implements Resource {
  @Builder.Default String resourceType = "Observation";

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

  @Valid List<Reference> basedOn;

  @Valid List<Reference> partOf;

  @NotNull ObservationStatus status;

  /* ToDo Smoking Status Observation Profile Cardinality is 0..* for category,
   *       deal with cardinality in isValidCategory() instead. */
  @Valid @NotEmpty List<CodeableConcept> category;

  @NotNull CodeableConcept code;

  @NotNull Reference subject;

  @Valid List<Reference> focus;

  @Valid Reference encounter;

  /* ToDo Add effectiveTiming and effectiveInstant for SmokingStatus Observation Profile */
  @Pattern(regexp = Fhir.DATETIME)
  String effectiveDateTime;

  @Valid Period effectivePeriod;

  @Pattern(regexp = Fhir.INSTANT)
  String issued;

  @Valid List<Reference> performer;

  /* ToDo value[x] is considered as a slice definition in some observation profiles,
   *       create a validation method for value[x] based on Observation Type */
  @Valid Quantity valueQuantity;

  @Valid CodeableConcept valueCodeableConcept;

  String valueString;

  Boolean valueBoolean;

  Integer valueInteger;

  @Valid Range valueRange;

  @Valid Ratio valueRatio;

  @Valid SampledData valueSampledData;

  @Pattern(regexp = Fhir.TIME)
  String valueTime;

  @Pattern(regexp = Fhir.DATETIME)
  String valueDateTime;

  @Valid Period valuePeriod;

  @Valid CodeableConcept dataAbsentReason;

  @Valid List<CodeableConcept> interpretation;

  @Valid List<Annotation> note;

  @Valid CodeableConcept bodySite;

  @Valid CodeableConcept method;

  @Valid Reference specimen;

  @Valid Reference device;

  @Valid List<ReferenceRange> referenceRange;

  @Valid List<Reference> hasMember;

  @Valid List<Reference> derivedFrom;

  @Valid List<Component> component;

  @JsonIgnore
  private boolean containsNoMoreThanOneOf(List<String> collection, String value) {
    return collection.stream().filter(v -> v.equals(value)).count() <= 1;
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "One or more category values found to be invalid.")
  private boolean isValidCategory() {
    List<String> sliceTypes = new ArrayList<>();
    for (CodeableConcept cc : category) {
      if (!isValidSliceCodingDefinition(cc.coding())) {
        return false;
      }
      // The only real way to determine there is one CC of each type is by the code values.
      sliceTypes.addAll(
          cc.coding().stream()
              .map(coding -> mapToCategorySliceType(coding.code()))
              .distinct()
              .collect(Collectors.toList()));
    }
    /* US-Core R4 Observation defines that vital signs (Slice: VSCat) and
     * laboratory (Slice: Laboratory) slices in the category array have a cardinality of 1..1
     * Therefore, a lab observation may only exist once in the category array.
     * The same applies to vital-signs. */
    return containsNoMoreThanOneOf(sliceTypes, "Laboratory")
        && containsNoMoreThanOneOf(sliceTypes, "VSCat");
  }

  /**
   * Observation Slicing adds required fields which are optional in the R4 codeableConcept
   * definition.
   *
   * <p>+ Coding 1..* +-- system 1..1 +-- code 1..1
   */
  @JsonIgnore
  private boolean isValidSliceCodingDefinition(List<Coding> r4Coding) {
    if (r4Coding.isEmpty()) {
      return false;
    }
    /* Category system should be a fixed value of:
     *   http://terminology.hl7.org/CodeSystem/observation-category
     * Code is also _technically_ fixed,
     * but for our purposes it can be one of laboratory or vital-signs */
    return r4Coding.stream()
        .filter(Objects::nonNull)
        .allMatch(
            c ->
                c.code() != null
                    && "http://terminology.hl7.org/CodeSystem/observation-category"
                        .equals(c.system()));
  }

  @JsonIgnore
  private String mapToCategorySliceType(String code) {
    /* ToDo Other observation types have a slice type of VSCat and will need to be
     *       added into this switch <-- maybe use enum instead of string */
    switch (code) {
      case "laboratory":
        return "Laboratory";
      case "vital-signs":
        return "VSCat";
      default:
        return "undefined";
    }
  }

  public enum ObservationStatus {
    registered,
    preliminary,
    @JsonProperty("final")
    _final,
    amended,
    corrected,
    cancelled,
    @JsonProperty("entered-in-error")
    entered_in_error,
    unknown
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Observation.Bundle.BundleBuilder.class)
  @Schema(
      name = "ObservationBundle",
      example =
          "${r4.observationBundle:"
              + "gov.va.api.health.r4.api.swaggerexamples."
              + "SwaggerObservation#observationBundle}")
  public static class Bundle extends AbstractBundle<Entry> {
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
        @Valid List<Observation.Entry> entry,
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
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @ZeroOrOneOfs(
      @ZeroOrOneOf(
          fields = {
            "valueQuantity",
            "valueCodeableConcept",
            "valueString",
            "valueBoolean",
            "valueInteger",
            "valueRange",
            "valueRatio",
            "valueSampledData",
            "valueTime",
            "valueDateTime",
            "valuePeriod"
          },
          message = "Only one value field may be set"))
  public static class Component implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept code;

    @Valid Quantity valueQuantity;

    @Valid CodeableConcept valueCodeableConcept;

    String valueString;

    Boolean valueBoolean;

    Integer valueInteger;

    @Valid Range valueRange;

    @Valid Ratio valueRatio;

    @Valid SampledData valueSampledData;

    @Pattern(regexp = Fhir.TIME)
    String valueTime;

    @Pattern(regexp = Fhir.DATETIME)
    String valueDateTime;

    @Valid Period valuePeriod;

    @Valid CodeableConcept dataAbsentReason;

    @Valid List<CodeableConcept> interpretation;

    @Valid List<ReferenceRange> referenceRange;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Observation.Entry.EntryBuilder.class)
  @Schema(name = "ObservationEntry")
  public static class Entry extends AbstractEntry<Observation> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Observation resource,
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
  public static class ReferenceRange implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid SimpleQuantity low;

    @Valid SimpleQuantity high;

    @Valid CodeableConcept type;

    @Valid List<CodeableConcept> appliesTo;

    @Valid Range age;

    String text;

    @JsonIgnore
    @SuppressWarnings("unused")
    @AssertTrue(message = "Reference range is invalid. Must have at least a low or a high or text.")
    private boolean isValidReferenceRange() {
      return low != null || high != null || text != null;
    }
  }
}
