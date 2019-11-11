package gov.va.api.health.r4.api.resources;

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
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Money;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
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
  description = "https://www.hl7.org/fhir/R4/explanationofbenefit.html",
  example = "SWAGGER_EXAMPLE_EXPLANATIONOFBENEFIT"
)
public class ExplanationOfBenefit implements Resource {

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

  // Resource -- ExplanationOfBenefit
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

  @NotNull @Valid Reference insurer;

  @NotNull @Valid Reference provider;

  @Valid CodeableConcept priority;

  @Valid CodeableConcept fundsReserveRequested;

  @Valid CodeableConcept fundsReserve;

  @Valid List<Related> related;

  @Valid Reference prescription;

  @Valid Reference originalPrescription;

  @Valid Payee payee;

  @Valid Reference referral;

  @Valid Reference facility;

  @Valid Reference claim;

  @Valid Reference claimResponse;

  @NotNull Outcome outcome;

  @Pattern(regexp = Fhir.STRING)
  String disposition;

  List<@Pattern(regexp = Fhir.STRING) String> preAuthRef;

  @Valid List<Period> preAuthRefPeriod;

  @Valid List<CareTeam> careTeam;

  @Valid List<SupportingInfo> supportingInfo;

  @Valid List<Diagnosis> diagnosis;

  @Valid List<Procedure> procedure;

  @Pattern(regexp = Fhir.POSITIVE_INT)
  String precedence;

  @NotEmpty @Valid List<Insurance> insurance;

  @Valid Accident accident;

  @Valid List<Item> item;

  @Valid List<AddItem> addItem;

  @Valid List<Adjudication> adjudication;

  @Valid List<Total> total;

  @Valid Payment payment;

  @Valid CodeableConcept formCode;

  @Valid Attachment form;

  @Valid List<ProcessNote> processNote;

  @Valid Period benefitPeriod;

  @Valid List<BenefitBalance> benefitBalance;

  @SuppressWarnings("unused")
  public enum Outcome {
    queued,
    complete,
    error,
    partial
  }

  @SuppressWarnings("unused")
  public enum Status {
    active,
    cancelled,
    draft,
    @JsonProperty("entered-in-error")
    entered_in_error
  }

  @SuppressWarnings("unused")
  public enum Type {
    display,
    print,
    printoper
  }

  @SuppressWarnings("unused")
  public enum Use {
    claim,
    preauthorization,
    predetermination
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = ExplanationOfBenefit.Bundle.BundleBuilder.class)
  @Schema(
    name = "ExplanationOfBenefitBundle",
    example = "SWAGGER_EXAMPLE_EXPLANATIONOFBENEFIT_BUNDLE"
  )
  public static class Bundle extends AbstractBundle<Entry> {

    /** Explanation of benefit bundle builder. */
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
  @JsonDeserialize(builder = ExplanationOfBenefit.Entry.EntryBuilder.class)
  @Schema(name = "ExplanationOfBenefitEntry")
  public static class Entry extends AbstractEntry<ExplanationOfBenefit> {

    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid ExplanationOfBenefit resource,
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
  @Schema(name = "ExplanationOfBenefitAccident")
  @ZeroOrOneOf(
    fields = {"locationAddress", "locationReference"},
    message = "Only one location field may be specified"
  )
  public static class Accident implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Pattern(regexp = Fhir.DATE)
    String date;

    @Valid CodeableConcept type;

    @Valid Address locationAddress;

    @Valid Reference locationReference;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitAddItem")
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
      fields = {"servicedDate", "servicedPeriod"},
      message = "Only one serviced field may be specified"
    ),
    @ZeroOrOneOf(
      fields = {"locationCodeableConcept", "locationAddress", "locationReference"},
      message = "Only one location field may be specified"
    )
  })
  public static class AddItem implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> itemSequence;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> detailSequence;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> subDetailSequence;

    @Valid List<Reference> provider;

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

    @Pattern(regexp = Fhir.DECIMAL)
    String factor;

    @Valid Money net;

    @Valid CodeableConcept bodySite;

    @Valid List<CodeableConcept> subSite;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> noteNumber;

    @Valid List<Adjudication> adjudication;

    @Valid List<AddItemDetail> detail;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitAddItemDetail")
  public static class AddItemDetail implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept productOrService;

    @Valid List<CodeableConcept> modifier;

    @Valid SimpleQuantity quantity;

    @Valid Money unitPrice;

    @Pattern(regexp = Fhir.DECIMAL)
    String factor;

    @Valid Money net;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> noteNumber;

    @Valid List<Adjudication> adjudication;

    @Valid List<AddItemSubDetail> subDetail;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitAddItemSubDetail")
  public static class AddItemSubDetail implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept productOrService;

    @Valid List<CodeableConcept> modifier;

    @Valid SimpleQuantity quantity;

    @Valid Money unitPrice;

    @Pattern(regexp = Fhir.DECIMAL)
    String factor;

    @Valid Money net;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> noteNumber;

    @Valid List<Adjudication> adjudication;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitAdjudication")
  public static class Adjudication implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept category;

    @Valid CodeableConcept reason;

    @Valid Money amount;

    @Pattern(regexp = Fhir.DECIMAL)
    String value;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitBenefitBalance")
  public static class BenefitBalance implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept category;

    @Pattern(regexp = Fhir.BOOLEAN)
    String excluded;

    @Pattern(regexp = Fhir.STRING)
    String name;

    @Pattern(regexp = Fhir.STRING)
    String description;

    @Valid CodeableConcept network;

    @Valid CodeableConcept unit;

    @Valid CodeableConcept term;

    @Valid List<Financial> financial;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitCareTeam")
  public static class CareTeam implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

    @NotNull @Valid Reference provider;

    @Pattern(regexp = Fhir.BOOLEAN)
    String responsible;

    @Valid CodeableConcept role;

    @Valid CodeableConcept qualification;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitFinancial")
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
      fields = {"allowedUnsignedInt", "allowedString", "allowedMoney"},
      message = "Only one allowed field may be specified"
    ),
    @ZeroOrOneOf(
      fields = {"usedUnsignedInt", "usedMoney"},
      message = "Only one used field may be specified"
    )
  })
  public static class Financial implements BackboneElement {

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

    @Valid Money usedMoney;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitItemDetail")
  public static class ItemDetail implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

    @Valid CodeableConcept revenue;

    @Valid CodeableConcept category;

    @NotNull @Valid CodeableConcept productOrService;

    @Valid List<CodeableConcept> modifier;

    @Valid List<CodeableConcept> programCode;

    @Valid SimpleQuantity quantity;

    @Valid Money unitPrice;

    @Pattern(regexp = Fhir.DECIMAL)
    String factor;

    @Valid Money net;

    @Valid List<Reference> udi;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> noteNumber;

    @Valid List<Adjudication> adjudication;

    @Valid List<ItemSubDetail> subDetail;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitDiagnosis")
  @ExactlyOneOf(
    fields = {"diagnosisCodeableConcept", "diagnosisReference"},
    message = "Only one diagnosis field may be specified"
  )
  public static class Diagnosis implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

    @Valid CodeableConcept diagnosisCodeableConcept;

    @Valid Reference diagnosisReference;

    @Valid List<CodeableConcept> type;

    @Valid CodeableConcept onAdmission;

    @Valid CodeableConcept packageCode;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitInsurance")
  public static class Insurance implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.BOOLEAN)
    String focal;

    @NotNull @Valid Reference coverage;

    List<@Pattern(regexp = Fhir.STRING) String> preAuthRef;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitItem")
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
      fields = {"servicedDate", "servicedPeriod"},
      message = "Only one serviced field may be specified"
    ),
    @ZeroOrOneOf(
      fields = {"locationCodeableConcept", "locationAddress", "locationReference"},
      message = "Only one location field may be specified"
    )
  })
  public static class Item implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> careTeamSequence;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> diagnosisSequence;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> procedureSequence;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> informationSequence;

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

    @Pattern(regexp = Fhir.DECIMAL)
    String factor;

    @Valid Money net;

    @Valid List<Reference> udi;

    @Valid CodeableConcept bodySite;

    @Valid List<CodeableConcept> subSite;

    @Valid List<Reference> encounter;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> noteNumber;

    @Valid List<Adjudication> adjudication;

    @Valid List<ItemDetail> detail;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitPayee")
  public static class Payee implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept type;

    @Valid Reference party;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitPayment")
  public static class Payment implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept type;

    @Valid Money adjustment;

    @Valid CodeableConcept adjustmentReason;

    @Pattern(regexp = Fhir.DATE)
    String date;

    @Valid Money amount;

    @Valid Identifier identifier;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitProcedure")
  @ExactlyOneOf(
    fields = {"procedureCodeableConcept", "procedureReference"},
    message = "Only one procedure field may be specified"
  )
  public static class Procedure implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

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
  @Schema(name = "ExplanationOfBenefitProcessNote")
  public static class ProcessNote implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Pattern(regexp = Fhir.POSITIVE_INT)
    String number;

    Type type;

    @Pattern(regexp = Fhir.STRING)
    String text;

    @Valid CodeableConcept language;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitRelated")
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
  @Schema(name = "ExplanationOfBenefitItemSubDetail")
  public static class ItemSubDetail implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

    @Valid CodeableConcept revenue;

    @Valid CodeableConcept category;

    @NotNull @Valid CodeableConcept productOrService;

    @Valid List<CodeableConcept> modifier;

    @Valid List<CodeableConcept> programCode;

    @Valid SimpleQuantity quantity;

    @Valid Money unitPrice;

    @Pattern(regexp = Fhir.DECIMAL)
    String factor;

    @Valid Money net;

    @Valid List<Reference> udi;

    List<@Pattern(regexp = Fhir.POSITIVE_INT) String> noteNumber;

    @Valid List<Adjudication> adjudication;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitSupportingInfo")
  @ZeroOrOneOfs({
    @ZeroOrOneOf(
      fields = {"timingDate", "timingPeriod"},
      message = "Only one timing field may be specified"
    ),
    @ZeroOrOneOf(
      fields = {
        "valueBoolean",
        "valueString",
        "valueQuantity",
        "valueAttachment",
        "valueReference"
      },
      message = "Only one value field may be specified"
    )
  })
  public static class SupportingInfo implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank
    @Pattern(regexp = Fhir.POSITIVE_INT)
    String sequence;

    @NotNull @Valid CodeableConcept category;

    @Valid CodeableConcept code;

    @Pattern(regexp = Fhir.DATE)
    String timingDate;

    @Valid Period timingPeriod;

    @Pattern(regexp = Fhir.BOOLEAN)
    String valueBoolean;

    @Pattern(regexp = Fhir.STRING)
    String valueString;

    @Valid Quantity valueQuantity;

    @Valid Attachment valueAttachment;

    @Valid Reference valueReference;

    @Valid Coding reason;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ExplanationOfBenefitTotal")
  public static class Total implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid CodeableConcept category;

    @NotNull @Valid Money amount;
  }
}
