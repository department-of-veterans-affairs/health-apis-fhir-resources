package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Money;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
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
  isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(
  description = "https://www.hl7.org/fhir/R4/coverageeligibilityresponse.html",
  example = "SWAGGER_EXAMPLE_COVERAGEELIGIBILITYRESPONSE"
)
@ZeroOrOneOf(
  fields = {"servicedDate", "servicedPeriod"},
  message = "Only one serviced value may be specified."
)
@ExactlyOneOf(
  fields = {"request", "_request"},
  message = "Exactly one request value must be specified."
)
public class CoverageEligibilityResponse implements Resource {
  // Anscestor -- Resource
  @Pattern(regexp = Fhir.ID)
  String id;

  @NotBlank String resourceType;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  // Ancestor -- DomainResource
  @Valid Narrative text;
  @Valid List<SimpleResource> contained;
  @Valid List<Extension> extension;
  @Valid List<Extension> modifierExtension;

  // CoverageEligibilityResponse Resource
  @Valid List<Identifier> identifier;
  @NotNull Status status;
  @NotEmpty List<Purpose> purpose;
  @NotNull @Valid Reference patient;

  @Pattern(regexp = Fhir.DATE)
  String servicedDate;

  @Valid Period servicedPeriod;

  @NotBlank
  @Pattern(regexp = Fhir.DATETIME)
  String created;

  @Valid Reference requestor;

  @Valid Reference request;
  @Valid Extension _request;

  @NotNull Outcome outcome;

  @Pattern(regexp = Fhir.STRING)
  String disposition;

  @NotNull @Valid Reference insurer;
  @Valid List<Insurance> insurance;

  @Pattern(regexp = Fhir.STRING)
  String preAuthRef;

  @Valid CodeableConcept form;

  @Valid List<CoverageEligibilityResponseError> error;

  public enum Status {
    active,
    cancelled,
    draft,
    @JsonProperty("entered-in-error")
    entered_in_error
  }

  public enum Purpose {
    @JsonProperty("auth-requirements")
    auth_requirements,
    benefits,
    discovery,
    validation
  }

  public enum Outcome {
    queued,
    complete,
    error,
    partial
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = CoverageEligibilityResponse.Bundle.BundleBuilder.class)
  @Schema(
    name = "CoverageEligibilityResponseBundle",
    example = "SWAGGER_EXAMPLE_COVERAGEELIGIBILITYRESPONSE_BUNDLE"
  )
  public static class Bundle extends AbstractBundle<Entry> {

    /** Coverage bundle builder. */
    @Builder
    public Bundle(
        @NotBlank String resourceType,
        @Pattern(regexp = Fhir.ID) String id,
        @Valid Meta meta,
        @Pattern(regexp = Fhir.URI) String implicitRules,
        @Pattern(regexp = Fhir.CODE) String language,
        @Valid Identifier identifier,
        @NotNull BundleType type,
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
  @JsonDeserialize(builder = CoverageEligibilityResponse.Entry.EntryBuilder.class)
  @Schema(name = "CoverageEligibilityResponseEntry")
  public static class Entry extends AbstractEntry<CoverageEligibilityResponse> {

    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid CoverageEligibilityResponse resource,
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
  @Schema(name = "Insurance")
  @ExactlyOneOf(
    fields = {"coverage", "_coverage"},
    message = "Exactly one coverage value must be specified."
  )
  public static class Insurance implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid Reference coverage;
    @Valid Extension _coverage;

    @Pattern(regexp = Fhir.BOOLEAN)
    String inforce;

    @Valid Period benefitPeriod;

    @Valid List<Item> item;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "Item")
  public static class Item implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept category;
    @Valid CodeableConcept productOrService;
    @Valid List<CodeableConcept> modifier;
    @Valid Reference provider;

    @Pattern(regexp = Fhir.BOOLEAN)
    String excluded;

    @Pattern(regexp = Fhir.STRING)
    String name;

    @Pattern(regexp = Fhir.STRING)
    String description;

    @Valid CodeableConcept network;
    @Valid CodeableConcept unit;
    @Valid CodeableConcept term;
    @Valid List<Benefit> benefit;

    @Pattern(regexp = Fhir.BOOLEAN)
    String authorizationRequired;

    @Valid List<CodeableConcept> authorizationSupporting;

    @Pattern(regexp = Fhir.URI)
    String authorizationUrl;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "Benefit")
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
      fields = {"allowedUnsignedInt", "allowedString", "allowedMoney"},
      message = "Only one effective value may be specified"
    ),
    @ZeroOrOneOf(
      fields = {"usedUnsignedInt", "usedString", "usedMoney"},
      message = "Only one used value may be specified"
    )
  })
  public static class Benefit implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept type;

    @Pattern(regexp = Fhir.UNSIGNED_INT)
    String allowedUnsignedInt;

    @Pattern(regexp = Fhir.STRING)
    String allowedString;

    @Valid Money allowedMoney;

    @Pattern(regexp = Fhir.UNSIGNED_INT)
    String usedUnsignedInt;

    @Pattern(regexp = Fhir.STRING)
    String usedString;

    @Valid Money usedMoney;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "CoverageEligibilityResponseError")
  public static class CoverageEligibilityResponseError implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept code;
  }
}
