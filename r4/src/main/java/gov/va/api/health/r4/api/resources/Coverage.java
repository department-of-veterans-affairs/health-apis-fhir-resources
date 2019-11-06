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
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
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
  description = "https://www.hl7.org/fhir/R4/coverage.html",
  example = "SWAGGER_EXAMPLE_COVERAGE"
)
public class Coverage implements Resource {
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

  // Coverage Resource
  @Valid List<Identifier> identifier;
  @NotNull Status status;
  @Valid CodeableConcept type;
  @Valid Reference policyHolder;
  @Valid Reference subscriber;

  @Pattern(regexp = Fhir.STRING)
  String subscriberId;

  @NotNull @Valid Reference beneficiary;

  @Pattern(regexp = Fhir.STRING)
  String dependent;

  @Valid CodeableConcept relationship;
  @Valid Period period;
  @NotEmpty List<Reference> payor;

  @JsonProperty("class")
  List<CoverageClass> coverageClass;

  @Pattern(regexp = Fhir.POSITIVE_INT)
  String order;

  @Pattern(regexp = Fhir.STRING)
  String network;

  @Valid List<CostToBeneficiary> costToBeneficiary;

  @Pattern(regexp = Fhir.BOOLEAN)
  String subrogation;

  @Valid List<Reference> contract;

  @SuppressWarnings("unused")
  public enum Status {
    active,
    cancelled,
    draft,
    @JsonProperty("entered-in-error")
    entered_in_error
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Coverage.Bundle.BundleBuilder.class)
  @Schema(name = "CoverageBundle", example = "SWAGGER_EXAMPLE_COVERAGE_BUNDLE")
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
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "CoverageCostToBeneficiary")
  @ExactlyOneOf(
    fields = {"valueQuantity", "valueMoney"},
    message = "valueQuantity or valueMoney, but not both"
  )
  public static class CostToBeneficiary implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept type;
    @Valid SimpleQuantity valueQuantity;
    @Valid Money valueMoney;
    @Valid List<Exception> exception;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "CoverageClass")
  public static class CoverageClass implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull CodeableConcept type;

    @Pattern(regexp = Fhir.STRING)
    @NotNull
    String value;

    @Pattern(regexp = Fhir.STRING)
    String name;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Coverage.Entry.EntryBuilder.class)
  @Schema(name = "CoverageEntry")
  public static class Entry extends AbstractEntry<Coverage> {

    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Coverage resource,
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
  @Schema(name = "CostToBeneficiaryException")
  public static class Exception implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull CodeableConcept type;
    @Valid Period period;
  }
}
