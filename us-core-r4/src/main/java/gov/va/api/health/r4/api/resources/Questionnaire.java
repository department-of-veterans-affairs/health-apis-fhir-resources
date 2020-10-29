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
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.ContactDetail;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
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
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
    description = "https://www.hl7.org/fhir/R4/questionnaire.html",
    example =
        "${r4.questionnaire:gov.va.api.health.r4.api.swaggerexamples.SwaggerQuestionnaire#questionnaire}")
public class Questionnaire implements DomainResource {
  @NotBlank @Builder.Default String resourceType = "Questionnaire";

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

  @Pattern(regexp = Fhir.URI)
  String url;

  @Valid List<Identifier> identifier;

  String version;

  String name;

  String title;

  List<@Pattern(regexp = Fhir.CANONICAL) String> derivedFrom;

  @Valid @NotNull QuestionnaireStatus status;

  Boolean experimental;
  
  List<@Pattern(regexp = Fhir.CODE) String> subjectType;
  
  @Pattern(regexp = Fhir.DATETIME) String date;
  
  String publisher;
  
  @Valid List<ContactDetail> contact;
  
  @Pattern(regexp = Fhir.MARKDOWN) String description;
  
  @Valid List<UsageContext> useContext;
  
  @Valid List<CodeableConcept> jurisdiction;
  
  @Pattern(regexp = Fhir.MARKDOWN) String purpose;
  
  @Pattern(regexp = Fhir.MARKDOWN) String copyright; 
  
  @Pattern(regexp = Fhir.DATETIME) String approvalDate;
  
  @Pattern(regexp = Fhir.DATETIME) String lastReviewDate;
  
  @Valid Period effectivePeriod;
  
  @Valid List<Code> code;
  
  public enum QuestionnaireStatus {
    draft,
    active,
    retired,
    unknown
  }

  // ----------------------

  @Valid CodeableConcept cancelationReason;
  @Valid List<CodeableConcept> serviceType;
  @Valid List<CodeableConcept> specialty;
  @Valid CodeableConcept appointmentType;
 
  @Valid List<Reference> reasonReference;

  @Min(0)
  Integer priority;

  @Pattern(regexp = Fhir.STRING)
  String description;

  @Valid List<Reference> supportingInformation;

  @Pattern(regexp = Fhir.INSTANT)
  String start;

  @Pattern(regexp = Fhir.INSTANT)
  String end;

  @Min(1)
  Integer minutesDuration;

  @Valid List<Reference> slot;

  @Pattern(regexp = Fhir.DATETIME)
  String created;

  @Pattern(regexp = Fhir.STRING)
  String comment;

  @Pattern(regexp = Fhir.STRING)
  String patientInstruction;

  @Valid List<Reference> basedOn;

  @NotEmpty @Valid List<Participant> participant;

  @Valid List<Period> requestedPeriod;

  @SuppressWarnings("unused")
  public enum Required {
    required,
    optional,
    @JsonProperty("information-only")
    information_only
  }

  @SuppressWarnings("unused")
  public enum ParticipationStatus {
    accepted,
    declined,
    tentative,
    @JsonProperty("needs-action")
    needs_action
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Appointment.Bundle.BundleBuilder.class)
  @Schema(
      name = "AppointmentBundle",
      example =
          "${r4.appointmentBundle:gov.va.api.health.r4.api.swaggerexamples."
              + "SwaggerAppointment#appointmentBundle}")
  public static class Bundle extends AbstractBundle<Appointment.Entry> {

    /** Appointment bundle builder. */
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
        @Valid List<Appointment.Entry> entry,
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
  @Schema(name = "AppointmentParticipant")
  public static class Participant implements BackboneElement {

    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<CodeableConcept> type;

    @Valid Reference actor;

    @Valid Required required;

    @NotNull @Valid ParticipationStatus status;

    @Valid Period period;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Appointment.Entry.EntryBuilder.class)
  @Schema(name = "AppointmentEntry")
  public static class Entry extends AbstractEntry<Appointment> {

    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Appointment resource,
        @Valid Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }
}
