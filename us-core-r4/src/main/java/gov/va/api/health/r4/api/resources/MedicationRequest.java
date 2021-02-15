package gov.va.api.health.r4.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Duration;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Dosage;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
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
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-medicationrequest.html",
    example =
        "${r4.medication:gov.va.api.health."
            + "r4.api.swaggerexamples.SwaggerMedicationRequest#medicationRequest}")
@ZeroOrOneOfs(
    @ZeroOrOneOf(
        fields = {"reportedBoolean", "reportedReference"},
        message = "Only one reported field may be specified"))
@ExactlyOneOfs({
  @ExactlyOneOf(
      fields = {"medicationCodeableConcept", "medicationReference"},
      message = "medicationCodeableConcept or medicationReference, but not both."),
  @ExactlyOneOf(
      fields = {"requester", "_requester"},
      message = "Exactly one requester field must be specified")
})
public class MedicationRequest implements Resource {
  // Ancestors
  @NotBlank @Builder.Default String resourceType = "MedicationRequest";

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid List<Resource> contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  // Medication Request
  @Valid List<Identifier> identifier;

  @Valid @NotNull Status status;

  @Valid CodeableConcept statusReason;

  @Valid @NotNull Intent intent;

  @Valid List<CodeableConcept> category;

  @Valid Priority priority;

  Boolean doNotPerform;

  Boolean reportedBoolean;

  @Valid Reference reportedReference;

  @Valid CodeableConcept medicationCodeableConcept;

  @Valid Reference medicationReference;

  @Valid @NotNull Reference subject;

  @Valid Reference encounter;

  @Valid List<Reference> supportingInformation;

  @Pattern(regexp = Fhir.DATETIME)
  @NotNull
  String authoredOn;

  @Valid Reference requester;
  @Valid Extension _requester;

  @Valid Reference performer;

  @Valid CodeableConcept performerType;

  @Valid Reference recorder;

  @Valid List<CodeableConcept> reasonCode;

  @Valid List<Reference> reasonReference;

  List<@Pattern(regexp = Fhir.CANONICAL) String> instantiatesCanonical;

  List<@Pattern(regexp = Fhir.URI) String> instantiatesUri;

  @Valid List<Reference> basedOn;

  @Valid Identifier groupIdentifier;

  @Valid CodeableConcept courseOfTherapyType;

  @Valid List<Reference> insurance;

  @Valid List<Annotation> note;

  @Valid List<Dosage> dosageInstruction;

  @Valid DispenseRequest dispenseRequest;

  @Valid Substitution substitution;

  @Valid Reference priorPrescription;

  @Valid List<Reference> detectedIssue;

  @Valid List<Reference> eventHistory;

  public enum Status {
    active,
    @JsonProperty("on-hold")
    on_hold,
    cancelled,
    completed,
    @JsonProperty("entered-in-error")
    entered_in_error,
    stopped,
    draft,
    unknown
  }

  public enum Intent {
    proposal,
    plan,
    order,
    @JsonProperty("original-order")
    original_order,
    @JsonProperty("reflex-order")
    reflex_order,
    @JsonProperty("filler-order")
    filler_order,
    @JsonProperty("instance-order")
    instance_order,
    option
  }

  public enum Priority {
    routine,
    urgent,
    asap,
    stat
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = MedicationRequest.Bundle.BundleBuilder.class)
  @Schema(
      name = "MedicationRequestBundle",
      example =
          "${r4.medicationRequestBundle:gov.va.api.health."
              + "r4.api.swaggerexamples.SwaggerMedicationRequest#medicationRequestBundle}")
  public static class Bundle extends AbstractBundle<MedicationRequest.Entry> {
    /** Build a Medication Request bundle. */
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
        @Valid List<MedicationRequest.Entry> entry,
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
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = MedicationRequest.Entry.EntryBuilder.class)
  @Schema(name = "MedicationRequestEntry")
  public static class Entry extends AbstractEntry<MedicationRequest> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid MedicationRequest resource,
        @Valid AbstractEntry.Search search,
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
  @Schema(name = "MedicationRequestDispenseRequest")
  public static class DispenseRequest implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid InitialFill initialFill;

    @Valid Duration dispenseInterval;

    @Valid Period validityPeriod;

    @Min(0)
    Integer numberOfRepeatsAllowed;

    @Valid SimpleQuantity quantity;

    @Valid Duration expectedSupplyDuration;

    @Valid Reference performer;

    @Data
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Schema(name = "MedicationRequestDispenseRequestInitialFill")
    public static class InitialFill implements BackboneElement {
      @Pattern(regexp = Fhir.ID)
      String id;

      @Valid List<Extension> extension;

      @Valid List<Extension> modifierExtension;

      @Valid SimpleQuantity quantity;

      @Valid Duration duration;
    }
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "MedicationRequestSubstitution")
  @ExactlyOneOf(
      fields = {"allowedBoolean", "allowedCodeableConcept"},
      message = "allowedBoolean or allowedCodeableConcept, but not both")
  public static class Substitution implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    Boolean allowedBoolean;

    @Valid CodeableConcept allowedCodeableConcept;

    @Valid CodeableConcept reason;
  }
}
