package gov.va.api.health.stu3.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.stu3.api.Fhir;
import gov.va.api.health.stu3.api.bundle.AbstractBundle;
import gov.va.api.health.stu3.api.bundle.AbstractEntry;
import gov.va.api.health.stu3.api.bundle.BundleLink;
import gov.va.api.health.stu3.api.datatypes.CodeableConcept;
import gov.va.api.health.stu3.api.datatypes.ContactPoint;
import gov.va.api.health.stu3.api.datatypes.Identifier;
import gov.va.api.health.stu3.api.datatypes.Period;
import gov.va.api.health.stu3.api.datatypes.Signature;
import gov.va.api.health.stu3.api.datatypes.SimpleResource;
import gov.va.api.health.stu3.api.elements.BackboneElement;
import gov.va.api.health.stu3.api.elements.Extension;
import gov.va.api.health.stu3.api.elements.Meta;
import gov.va.api.health.stu3.api.elements.Narrative;
import gov.va.api.health.stu3.api.elements.Reference;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(
    description =
        "http://www.fhir.org/guides/argonaut/pd/StructureDefinition-argo-practitionerrole.html")
public class PractitionerRole implements DomainResource {
  @NotBlank @Builder.Default String resourceType = "PractitionerRole";

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;
  @Valid List<SimpleResource> contained;
  @Valid List<Extension> modifierExtension;
  @Valid List<Extension> extension;
  @Valid List<Identifier> identifier;

  Boolean active;

  @Valid Period period;

  @Valid @NotNull Reference practitioner;

  @Valid @NotNull Reference organization;

  @Valid List<CodeableConcept> code;

  @Valid List<CodeableConcept> specialty;

  @Valid List<Reference> location;

  @Valid List<Reference> healthcareService;

  @Valid List<PractitionerContactPoint> telecom;

  List<PractitionerAvailableTime> availableTime;

  List<PractitionerNotAvailable> notAvailable;

  String availabilityExceptions;

  @Valid List<Reference> endpoint;

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = PractitionerRole.Bundle.BundleBuilder.class)
  public static class Bundle extends AbstractBundle<Entry> {
    /** Builder constructor. */
    @Builder
    public Bundle(
        @NotBlank String resourceType,
        @Pattern(regexp = Fhir.ID) String id,
        @Valid Meta meta,
        @Pattern(regexp = Fhir.URI) String implicitRules,
        @Pattern(regexp = Fhir.CODE) String language,
        @NotNull BundleType type,
        @Min(0) Integer total,
        @Valid List<BundleLink> link,
        @Valid List<Entry> entry,
        @Valid Signature signature) {
      super(
          defaultString(resourceType, "Bundle"),
          id,
          meta,
          implicitRules,
          language,
          type,
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
  @JsonDeserialize(builder = PractitionerRole.Entry.EntryBuilder.class)
  public static class Entry extends AbstractEntry<PractitionerRole> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid PractitionerRole resource,
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
  public static class PractitionerContactPoint {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;
    @NotNull ContactPoint.ContactPointSystem system;
    @NotNull String value;
    ContactPoint.ContactPointUse use;

    @Min(1)
    Integer rank;

    @Valid Period period;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class PractitionerNotAvailable implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> modifierExtension;
    @Valid List<Extension> extension;

    @NotNull String description;
    @Valid Period during;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class PractitionerAvailableTime implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> modifierExtension;
    @Valid List<Extension> extension;

    DaysOfWeek daysOfWeek;
    Boolean allDay;

    @Pattern(regexp = Fhir.TIME)
    String availableStartTime;

    @Pattern(regexp = Fhir.TIME)
    String availableEndTime;

    public enum DaysOfWeek {
      mon,
      tue,
      wed,
      thur,
      fri,
      sat,
      sun
    }
  }
}
