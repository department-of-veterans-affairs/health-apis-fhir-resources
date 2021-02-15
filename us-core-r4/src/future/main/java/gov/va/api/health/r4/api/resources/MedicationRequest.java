package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Duration;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Dosage;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
  description = "https://www.hl7.org/fhir/R4/medicationrequest.html",
  example = "${r4.medicationRequest:com.example.Example#example}"
)
@ZeroOrOneOf(
  fields = {"reportedBoolean", "reportedReference"},
  message = "Only one reported field may be specified"
)
@ExactlyOneOf(
  fields = {"medicationCodeableConcept", "medicationReference"},
  message = "medicationCodeableConcept or medicationReference, but not both"
)
public class MedicationRequest implements Resource {
  @NotBlank @Builder.Default String resourceType = "MedicationRequest";

  // Ancestor -- Resource
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

  // MedicationRequset Resource
  @Valid List<Identifier> identifier;

  @NotNull Status status;

  @Valid CodeableConcept statusReason;

  @NotNull Intent intent;

  @Valid List<CodeableConcept> category;

  Priority priority;

  @Pattern(regexp = Fhir.BOOLEAN)
  String doNotPerform;

  @Pattern(regexp = Fhir.BOOLEAN)
  String reportedBoolean;

  @Valid Reference reportedReference;

  @Valid CodeableConcept medicationCodeableConcept;

  @Valid Reference medicationReference;

  @NotNull @Valid Reference subject;

  @Valid Reference encounter;

  @Valid List<Reference> supportingInformation;

  @Pattern(regexp = Fhir.DATETIME)
  String authoredOn;

  @Valid Reference requester;

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
    unknown;
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
    option;
  }

  public enum Priority {
    routine,
    urgent,
    asap,
    stat;
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

    @Pattern(regexp = Fhir.UNSIGNED_INT)
    String numberOfRepeatsAllowed;

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
    message = "allowedBoolean or allowedCodeableConcept, but not both"
  )
  public static class Substitution implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Pattern(regexp = Fhir.BOOLEAN)
    String allowedBoolean;

    @Valid CodeableConcept allowedCodeableConcept;

    @Valid CodeableConcept reason;
  }
}
