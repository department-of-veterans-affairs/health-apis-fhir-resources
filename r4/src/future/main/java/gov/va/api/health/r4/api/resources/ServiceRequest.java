package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Range;
import gov.va.api.health.r4.api.datatypes.Ratio;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.datatypes.Timing;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOfs;
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
  description = "https://www.hl7.org/fhir/R4/servicerequest.html",
  example = "serviceRequest:com.example.Example#example"
)
@ZeroOrOneOfs({
  @ZeroOrOneOf(
    fields = {"quantityQuantity", "quantityRatio", "quantityRange"},
    message = "Only one quantity field may be specified"
  ),
  @ZeroOrOneOf(
    fields = {"occurrenceDateTime", "occurrencePeriod", "occurrenceTiming"},
    message = "Only one occurrence field may be specified"
  ),
  @ZeroOrOneOf(
    fields = {"asNeededBoolean", "asNeededCodeableConcept"},
    message = "Only one asNeeded field may be specified"
  )
})
public class ServiceRequest implements Resource {

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

  // ServiceRequest Resource
  @Valid List<Identifier> identifier;

  List<@Pattern(regexp = Fhir.CANONICAL) String> instantiatesCanonical;

  List<@Pattern(regexp = Fhir.URI) String> instantiatesUri;

  @Valid List<Reference> basedOn;

  @Valid List<Reference> replaces;

  @Valid Identifier requisition;

  @NotNull Status status;

  @NotNull Intent intent;

  @Valid List<CodeableConcept> category;

  Priority priority;

  @Pattern(regexp = Fhir.BOOLEAN)
  String doNotPerform;

  @Valid CodeableConcept code;

  @Valid List<CodeableConcept> orderDetail;

  @Valid Quantity quantityQuantity;

  @Valid Ratio quantityRatio;

  @Valid Range quantityRange;

  @NotNull @Valid Reference subject;

  @Valid Reference encounter;

  @Pattern(regexp = Fhir.DATETIME)
  String occurrenceDateTime;

  @Valid Period occurrencePeriod;

  @Valid Timing occurrenceTiming;

  @Pattern(regexp = Fhir.BOOLEAN)
  String asNeededBoolean;

  @Valid CodeableConcept asNeededCodeableConcept;

  @Pattern(regexp = Fhir.DATETIME)
  String authoredOn;

  @Valid Reference requester;

  @Valid CodeableConcept performerType;

  @Valid List<Reference> performer;

  @Valid List<CodeableConcept> locationCode;

  @Valid List<Reference> locationReference;

  @Valid List<CodeableConcept> reasonCode;

  @Valid List<Reference> reasonReference;

  @Valid List<Reference> insurance;

  @Valid List<Reference> supportingInfo;

  @Valid List<Reference> specimen;

  @Valid List<CodeableConcept> bodySite;

  @Valid List<Annotation> note;

  @Pattern(regexp = Fhir.STRING)
  String patientInstruction;

  @Valid List<Reference> relevantHistory;

  public enum Status {
    draft,
    active,
    suspended,
    completed,
    @JsonProperty("entered-in-error")
    entered_in_error,
    cancelled;
  }

  public enum Intent {
    proposal,
    plan,
    directive,
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
}
