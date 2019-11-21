package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
  description = "https://www.hl7.org/fhir/R4/healthcareservice.html",
  example = "healthcareService:com.example.Example#example"
)
public class HealthcareService {

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

  // Claim Resource
  @Valid List<Identifier> identifier;

  @Pattern(regexp = Fhir.BOOLEAN)
  String active;

  @Valid Reference providedBy;

  @Valid List<CodeableConcept> category;

  @Valid List<CodeableConcept> type;

  @Valid List<CodeableConcept> specialty;

  @Valid List<Reference> location;

  @Pattern(regexp = Fhir.STRING)
  String name;

  @Pattern(regexp = Fhir.STRING)
  String comment;

  @Pattern(regexp = Fhir.MARKDOWN)
  String extraDetails;

  @Valid Attachment photo;

  @Valid List<ContactPoint> telecom;

  @Valid List<Reference> coverageArea;

  @Valid List<CodeableConcept> serviceProvisionCode;

  @Valid List<Eligibility> eligibility;

  @Valid List<CodeableConcept> program;

  @Valid List<CodeableConcept> characteristic;

  @Valid List<CodeableConcept> communication;

  @Valid List<CodeableConcept> referralMethod;

  @Pattern(regexp = Fhir.BOOLEAN)
  String appointmentRequired;

  @Valid List<AvailableTime> availableTime;

  @Valid List<NotAvailable> notAvailable;

  @Pattern(regexp = Fhir.STRING)
  String availabilityExceptions;

  @Valid List<Reference> endpoint;

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "HealthcareServiceEligibility")
  public static class Eligibility implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept code;

    @Pattern(regexp = Fhir.MARKDOWN)
    String comment;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "HealthcareServiceAvailableTime")
  public static class AvailableTime implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<DaysOfWeek> daysOfWeek;

    @Pattern(regexp = Fhir.BOOLEAN)
    String allDay;

    @Pattern(regexp = Fhir.TIME)
    String availableStartTime;

    @Pattern(regexp = Fhir.TIME)
    String availableEndTime;

    public enum DaysOfWeek {
      mon,
      tue,
      wed,
      thu,
      fri,
      sat,
      sun
    }
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "HealthcareServiceNotAvailable")
  public static class NotAvailable implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept code;

    @NotBlank
    @Pattern(regexp = Fhir.STRING)
    String description;

    @Valid Period during;
  }
}
