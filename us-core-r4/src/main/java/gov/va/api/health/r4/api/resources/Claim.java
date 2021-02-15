package gov.va.api.health.r4.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Money;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
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
    description = "https://www.hl7.org/fhir/R4/claim.html",
    example = "${r4.claim:gov.va.api.health.r4.api.swaggerexamples.SwaggerClaim#claim}")
public class Claim implements DomainResource {
  @NotBlank @Builder.Default String resourceType = "Claim";

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  // Ancestor -- DomainResource
  @Valid Narrative text;

  @Valid List<Resource> contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  // Claim Resource
  @Valid List<Identifier> identifier;

  @NotNull Status status;

  @NotNull @Valid CodeableConcept type;

  @Valid CodeableConcept subType;

  @NotNull Use use;

  @NotNull @Valid Reference patient;

  @Valid Period billablePeriod;

  @NotBlank
  @Pattern(regexp = Fhir.DATETIME)
  String created;

  @Valid Reference enterer;

  @Valid Reference insurer;

  @NotNull @Valid Reference provider;

  @NotNull @Valid CodeableConcept priority;

  @Valid CodeableConcept fundsReserve;

  @Valid List<Related> related;

  @Valid Reference prescription;

  @Valid Reference originalPrescription;

  @Valid Payee payee;

  @Valid Reference referral;

  @Valid Reference facility;

  @Valid List<CareTeam> careTeam;

  @Valid List<SupportingInfo> supportingInfo;

  @Valid List<Diagnosis> diagnosis;

  @Valid List<Procedure> procedure;

  @NotEmpty @Valid List<Insurance> insurance;

  @Valid Accident accident;

  @Valid List<Item> item;

  @Valid Money total;

  public enum Status {
    active,
    cancelled,
    draft,
    @JsonProperty("entered-in-error")
    entered_in_error
  }

  public enum Use {
    claim,
    preauthorization,
    predetermination
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimAccident")
  @ZeroOrOneOf(
      fields = {"locationAddress", "locationReference"},
      message = "Only one location field may be specified")
  public static class Accident implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.DATE)
    String date;

    @Valid CodeableConcept type;

    @Valid Address locationAddress;

    @Valid Reference locationReference;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Claim.Bundle.BundleBuilder.class)
  @Schema(
      name = "ClaimBundle",
      example =
          "${r4.claimBundle:gov.va.api.health.r4.api.swaggerexamples.SwaggerClaim#claimBundle}")
  public static class Bundle extends AbstractBundle<Claim.Entry> {
    /** Claim bundle builder. */
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
        @Valid List<Claim.Entry> entry,
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
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimCareTeam")
  public static class CareTeam implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull
    @Min(1)
    Integer sequence;

    @NotNull @Valid Reference provider;

    Boolean responsible;

    @Valid CodeableConcept role;

    @Valid CodeableConcept qualification;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimDiagnosis")
  @ExactlyOneOf(
      fields = {"diagnosisCodeableConcept", "diagnosisReference"},
      message = "diagnosisCodeableConcept or diagnosisReference, but not both")
  public static class Diagnosis implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull
    @Min(1)
    Integer sequence;

    @Valid CodeableConcept diagnosisCodeableConcept;

    @Valid Reference diagnosisReference;

    @Valid List<CodeableConcept> type;

    @Valid CodeableConcept onAdmission;

    @Valid CodeableConcept packageCode;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Claim.Entry.EntryBuilder.class)
  @Schema(name = "ClaimEntry")
  public static class Entry extends AbstractEntry<Claim> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Claim resource,
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
  @Schema(name = "ClaimInsurance")
  public static class Insurance implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull
    @Min(1)
    Integer sequence;

    @NotNull Boolean focal;

    @Valid Identifier identifier;

    @NotNull @Valid Reference coverage;

    @Pattern(regexp = Fhir.STRING)
    String businessArrangement;

    List<@Pattern(regexp = Fhir.STRING) String> preAuthRef;

    @Valid Reference claimResponse;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimItem")
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
        fields = {"servicedDate", "servicedPeriod"},
        message = "Only one serviced field may be specified"),
    @ZeroOrOneOf(
        fields = {"locationCodeableConcept", "locationAddress", "locationReference"},
        message = "Only one location field may be specified")
  })
  public static class Item implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull
    @Min(1)
    Integer sequence;

    List<@Min(1) Integer> careTeamSequence;

    List<@Min(1) Integer> diagnosisSequence;

    List<@Min(1) Integer> procedureSequence;

    List<@Min(1) Integer> informationSequence;

    @Valid CodeableConcept revenue;

    @Valid CodeableConcept category;

    @NotNull @Valid CodeableConcept productOrService;

    @Valid List<CodeableConcept> modifier;

    @Valid List<CodeableConcept> programCode;

    @Pattern(regexp = Fhir.DATE)
    String servicedDate;

    @Valid Period servicedPeriod;

    @Valid CodeableConcept locationCodeableConcept;

    @Valid Address locationAddress;

    @Valid Reference locationReference;

    @Valid SimpleQuantity quantity;

    @Valid Money unitPrice;

    BigDecimal factor;

    @Valid Money net;

    @Valid List<Reference> udi;

    @Valid CodeableConcept bodySite;

    @Valid List<CodeableConcept> subSite;

    @Valid List<Reference> encounter;

    @Valid List<Detail> detail;

    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Schema(name = "ClaimItemDetail")
    public static class Detail implements BackboneElement {
      @Pattern(regexp = Fhir.ID)
      String id;

      @Valid List<Extension> extension;

      @Valid List<Extension> modifierExtension;

      @NotNull
      @Min(1)
      Integer sequence;

      @Valid CodeableConcept revenue;

      @Valid CodeableConcept category;

      @NotNull @Valid CodeableConcept productOrService;

      @Valid List<CodeableConcept> modifier;

      @Valid List<CodeableConcept> programCode;

      @Valid SimpleQuantity quantity;

      @Valid Money unitPrice;

      BigDecimal factor;

      @Valid Money net;

      @Valid List<Reference> udi;

      @Valid List<SubDetail> subDetail;

      @Data
      @Builder
      @NoArgsConstructor(access = AccessLevel.PRIVATE)
      @AllArgsConstructor
      @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
      @Schema(name = "ClaimItemDetailSubDetail")
      public static class SubDetail implements BackboneElement {
        @Pattern(regexp = Fhir.ID)
        String id;

        @Valid List<Extension> extension;

        @Valid List<Extension> modifierExtension;

        @NotNull
        @Min(1)
        Integer sequence;

        @Valid CodeableConcept revenue;

        @Valid CodeableConcept category;

        @NotNull @Valid CodeableConcept productOrService;

        @Valid List<CodeableConcept> modifier;

        @Valid List<CodeableConcept> programCode;

        @Valid SimpleQuantity quantity;

        @Valid Money unitPrice;

        BigDecimal factor;

        @Valid Money net;

        @Valid List<Reference> udi;
      }
    }
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimPayee")
  public static class Payee implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept type;

    @Valid Reference party;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimProcedure")
  @ExactlyOneOf(
      fields = {"procedureCodeableConcept", "procedureReference"},
      message = "procedureCodeableConcept or procedureReference, but not both")
  public static class Procedure implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull
    @Min(1)
    Integer sequence;

    @Valid List<CodeableConcept> type;

    @Pattern(regexp = Fhir.DATETIME)
    String date;

    @Valid CodeableConcept procedureCodeableConcept;

    @Valid Reference procedureReference;

    @Valid List<Reference> udi;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimRelated")
  public static class Related implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid Reference claim;

    @Valid CodeableConcept relationship;

    @Valid Identifier reference;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClaimSupportingInfo")
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
        fields = {"timingDate", "timingPeriod"},
        message = "Only one timing field may be specified"),
    @ZeroOrOneOf(
        fields = {
          "valueBoolean",
          "valueString",
          "valueQuantity",
          "valueAttachment",
          "valueReference"
        },
        message = "Only one value field may be specified")
  })
  public static class SupportingInfo implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull
    @Min(1)
    Integer sequence;

    @NotNull @Valid CodeableConcept category;

    @Valid CodeableConcept code;

    @Pattern(regexp = Fhir.DATE)
    String timingDate;

    @Valid Period timingPeriod;

    Boolean valueBoolean;

    @Pattern(regexp = Fhir.STRING)
    String valueString;

    @Valid Quantity valueQuantity;

    @Valid Attachment valueAttachment;

    @Valid Reference valueReference;

    @Valid CodeableConcept reason;
  }
}
