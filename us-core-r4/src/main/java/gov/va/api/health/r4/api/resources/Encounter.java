package gov.va.api.health.r4.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Duration;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
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
import javax.validation.constraints.AssertTrue;
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
    description =
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-encounter.html")
public class Encounter implements Resource {
  @NotBlank @Builder.Default String resourceType = "Encounter";

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

  @Valid List<Identifier> identifier;

  @NotNull Encounter.Status status;

  @Valid List<StatusHistory> statusHistory;

  @JsonProperty("class")
  @NotNull
  @Valid
  Coding encounterClass;

  @Valid List<ClassHistory> classHistory;

  @NotEmpty @Valid List<CodeableConcept> type;

  @Valid CodeableConcept serviceType;

  @Valid CodeableConcept priority;

  @NotNull @Valid Reference subject;

  @Valid List<Reference> episodeOfCare;

  @Valid List<Reference> basedOn;

  @Valid List<Participant> participant;

  @Valid List<Reference> appointment;

  @Valid Period period;

  @Valid Duration length;

  @Valid List<CodeableConcept> reasonCode;

  @Valid List<Reference> reasonReference;

  @Valid List<Diagnosis> diagnosis;

  @Valid List<Reference> account;

  @Valid Hospitalization hospitalization;

  @Valid List<Location> location;

  @Valid Reference serviceProvider;

  @Valid Reference partOf;

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "Encounter Identifier is Invalid. Check the identifier's system and value.")
  private boolean isValidEncounterIdentifier() {
    /*
     * System and value are required for the identifier,
     * so we must check for them and ensure they aren't null
     */
    if (identifier != null) {
      return identifier.stream().noneMatch(i -> i.system() == null || i.value() == null);
    }
    return true;
  }

  public enum Status {
    planned,
    arrived,
    triaged,
    @JsonProperty("in-progress")
    in_progress,
    onleave,
    finished,
    cancelled,
    @JsonProperty("entered-in-error")
    entered_in_error,
    unknown
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(isGetterVisibility = JsonAutoDetect.Visibility.NONE)
  @JsonDeserialize(builder = Encounter.Bundle.BundleBuilder.class)
  @Schema(name = "EncounterBundle")
  public static class Bundle extends AbstractBundle<Encounter.Entry> {
    /** Creates a bundle of Entries, each entry being an entry of Encounters. */
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
        @Valid List<Encounter.Entry> entry,
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
  @JsonAutoDetect(isGetterVisibility = JsonAutoDetect.Visibility.NONE)
  @JsonDeserialize(builder = Encounter.Entry.EntryBuilder.class)
  @Schema(name = "EncounterEntry")
  public static class Entry extends AbstractEntry<Encounter> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Encounter resource,
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
  @Schema(name = "ClassHistory")
  public static class ClassHistory implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @JsonProperty("class")
    @NotNull
    @Valid
    Coding encounterClass;

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

    @Valid Period period;

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

    @NotNull @Valid List<Extension> modifierExtension;

    @NotNull @Valid Reference condition;

    @Valid CodeableConcept use;

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

    @Valid Identifier preAdmissionIdentifier;

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

    @NotNull @Valid Reference location;

    @Valid Location.Status status;

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
