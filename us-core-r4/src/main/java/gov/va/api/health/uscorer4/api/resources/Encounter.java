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
import gov.va.api.health.r4.api.resources.DomainResource;
import gov.va.api.health.r4.api.resources.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
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
public class Encounter implements DomainResource {

  @NotNull @Valid Coding encounterClass;

  @Pattern(regexp = Fhir.ID)
  String id;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid Resource contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  @Valid List<Identifier> identifier;

  @NotNull Encounter.Status status;

  @Valid StatusHistory statusHistory;

  @Valid ClassHistory classHistory;

  @Valid List<CodeableConcept> type;

  @Valid List<CodeableConcept> serviceType;

  @Valid List<CodeableConcept> priority;

  @Valid Reference subject;

  @Valid List<Reference> episodeOfCare;

  @Valid List<Reference> basedOn;

  @Valid Participant participant;

  @Valid Reference appointment;

  @NotNull @Valid Period period;

  @Valid Duration length;

  @Valid CodeableConcept reasonCode;

  @Valid List<Reference> reasonReference;

  @Valid Reference account;

  @Valid Hospitalization hospitalization;

  @Valid Location location;

  @Valid Reference serviceProvider;

  @Valid Reference partOf;

  @Override
  public List<SimpleResource> contained() {
    return null;
  }

  @Override
  public List<Extension> extension() {
    return null;
  }

  @Override
  @Pattern(regexp = Fhir.STRING)
  public String id() {
    return null;
  }

  @Override
  @Pattern(regexp = Fhir.STRING)
  public String implicitRules() {
    return null;
  }

  @Override
  @Pattern(regexp = Fhir.STRING)
  public String language() {
    return null;
  }

  @Override
  public Meta meta() {
    return null;
  }

  @Override
  public List<Extension> modifierExtension() {
    return null;
  }

  @Override
  public Narrative text() {
    return null;
  }

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

    @Override
    public List<Extension> extension() {
      return null;
    }

    @Override
    @Pattern(regexp = Fhir.STRING)
    public String id() {
      return null;
    }

    @Override
    public List<Extension> modifierExtension() {
      return null;
    }
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

    @Valid List<CodeableConcept> type;

    @Override
    public List<Extension> extension() {
      return null;
    }

    @Override
    @Pattern(regexp = Fhir.STRING)
    public String id() {
      return null;
    }

    @Override
    public List<Extension> modifierExtension() {
      return null;
    }
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

    @Override
    public List<Extension> extension() {
      return null;
    }

    @Override
    @Pattern(regexp = Fhir.STRING)
    public String id() {
      return null;
    }

    @Override
    public List<Extension> modifierExtension() {
      return null;
    }
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

    @Valid Integer rank;

    @Override
    public List<Extension> extension() {
      return null;
    }

    @Override
    @Pattern(regexp = Fhir.STRING)
    public String id() {
      return null;
    }

    @Override
    public List<Extension> modifierExtension() {
      return null;
    }
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

    @Valid Reference origin;

    @Valid CodeableConcept admitSource;

    @Valid CodeableConcept reAdmission;

    @Valid List<CodeableConcept> dietPreference;

    @Valid List<CodeableConcept> specialCourtesy;

    @Valid List<CodeableConcept> specialArrangement;

    @Valid Reference destination;

    @Valid CodeableConcept dischargeDisposition;

    @Override
    public List<Extension> extension() {
      return null;
    }

    @Override
    @Pattern(regexp = Fhir.STRING)
    public String id() {
      return null;
    }

    @Override
    public List<Extension> modifierExtension() {
      return null;
    }
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

    @Valid List<CodeableConcept> physicalType;

    @Valid Period period;

    @Override
    public List<Extension> extension() {
      return null;
    }

    @Override
    @Pattern(regexp = Fhir.STRING)
    public String id() {
      return null;
    }

    @Override
    public List<Extension> modifierExtension() {
      return null;
    }

    public enum Status {
      planned,
      active,
      reserved,
      completed
    }
  }
}
