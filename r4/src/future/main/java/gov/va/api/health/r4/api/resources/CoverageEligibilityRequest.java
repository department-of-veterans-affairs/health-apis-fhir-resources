package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Money;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
  description = "https://www.hl7.org/fhir/R4/coverageeligibilityrequest.html",
  example = "coverageEligibilityRequest:com.example.Example#example"
)
@ZeroOrOneOf(
  fields = {"servicedDate", "servicedPeriod"},
  message = "Only one serviced value may be specified."
)
public class CoverageEligibilityRequest implements Resource {

  // Ancestor -- Resource
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

  // CoverageEligibilityRequest Resource
  @Valid List<Identifier> identifier;

  @NotNull Status status;

  @Valid CodeableConcept priority;

  @NotEmpty List<Purpose> purpose;

  @NotNull @Valid Reference patient;

  @Pattern(regexp = Fhir.DATE)
  String servicedDate;

  @Valid Period servicedPeriod;

  @NotBlank
  @Pattern(regexp = Fhir.DATETIME)
  String created;

  @Valid Reference enterer;

  @Valid Reference provider;

  @NotNull @Valid Reference insurer;

  @Valid Reference facility;

  @Valid List<SupportingInfo> supportingInfo;

  @Valid List<Insurance> insurance;

  @Valid List<Item> item;

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

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "CoverageEligibilityRequestSupportingInfo")
  public static class SupportingInfo implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

    @NotNull @Valid Reference information;

    @Pattern(regexp = Fhir.BOOLEAN)
    String appliesToAll;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "CoverageEligibilityRequestInsurance")
  public static class Insurance implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Pattern(regexp = Fhir.BOOLEAN)
    String focal;

    @NotNull @Valid Reference coverage;

    @Pattern(regexp = Fhir.STRING)
    String businessArrangement;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "CoverageEligibilityRequestItem")
  public static class Item implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> supportingInfoSequence;

    @Valid CodeableConcept category;

    @Valid CodeableConcept productOrService;

    @Valid List<CodeableConcept> modifier;

    @Valid Reference provider;

    @Valid SimpleQuantity quantity;

    @Valid Money unitPrice;

    @Valid Reference facility;

    @Valid List<Diagnosis> diagnosis;

    @Valid List<Reference> detail;

    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Schema(name = "CoverageEligibilityRequestItemDiagnosis")
    @ZeroOrOneOf(
      fields = {"diagnosisCodeableConcept", "diagnosisReference"},
      message = "Only one diagnosis value may be specified."
    )
    public static class Diagnosis implements BackboneElement {

      @Pattern(regexp = Fhir.ID)
      String id;

      @Valid List<Extension> extension;

      @Valid List<Extension> modifierExtension;

      @Valid CodeableConcept diagnosisCodeableConcept;

      @Valid Reference diagnosisReference;
    }
  }
}
