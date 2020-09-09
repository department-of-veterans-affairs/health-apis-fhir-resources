package gov.va.api.health.uscorer4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Duration;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(
    description =
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-encounter.html")
public class Encounter implements Resource {

  @NotNull @Valid Coding encounterClass;

  @Pattern(regexp = Fhir.ID)
  String id;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Meta meta;

  @Valid Narrative text;

  @Valid List<SimpleResource> contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  @Valid List<Identifier> identifier;

  @NotNull Encounter.Status status;

  @Valid List<StatusHistory> statusHistory;

  @Valid List<ClassHistory> classHistory;

  @Valid List<CodeableConcept> type;

  @Valid CodeableConcept serviceType;

  @Valid List<CodeableConcept> priority;

  @Valid Reference subject;

  @Valid List<Reference> episodeOfCare;

  @Valid List<Reference> basedOn;

  @Valid List<Participant> participant;

  @Valid List<Reference> appointment;

  @NotNull @Valid Period period;

  @Valid Duration length;

  @Valid List<CodeableConcept> reasonCode;

  @Valid List<Reference> reasonReference;

  @Valid List<Reference> account;

  @Valid Hospitalization hospitalization;

  @Valid List<Location> location;

  @Valid Reference serviceProvider;

  @Valid Reference partOf;

  public enum Status {
    planned,
    arrived,
    triaged,
    @JsonProperty("in-progress")
    in_progress,
    onleave,
    finished,
    cancelled
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "ClassHistory")
  public static class ClassHistory implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull Coding encounterClass;

    @NotNull @Valid Period period;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "StatusHistory")
  public static class StatusHistory implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull Encounter.Status status;

    @NotNull @Valid Period period;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "Participant")
  public static class Participant implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<CodeableConcept> type;

    @NotNull @Valid Period period;

    @Valid Reference individual;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "Diagnosis")
  public static class Diagnosis implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid Reference condition;

    @Valid CodeableConcept use;

    @Valid
    @Min(1)
    Integer rank;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "Hospitalization")
  public static class Hospitalization implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<Identifier> preAdmissionIdentifier;

    @Valid Reference origin;

    @Valid CodeableConcept admitSource;

    @Valid CodeableConcept reAdmission;

    @Valid List<CodeableConcept> dietPreference;

    @Valid List<CodeableConcept> specialCourtesy;

    @Valid List<CodeableConcept> specialArrangement;

    @Valid Reference destination;

    @Valid CodeableConcept dischargeDisposition;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "Location")
  public static class Location implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid Reference location;

    @Valid Status status;

    @Valid CodeableConcept physicalType;

    @Valid Period period;

    public enum Status {
      planned,
      active,
      reserved,
      completed
    }
  }
}
