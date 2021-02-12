package gov.va.api.health.r4.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
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
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-practitionerrole.html",
    example =
        "${r4.practitionerRole:gov.va.api.health.r4.api.swaggerexamples"
            + ".SwaggerPractitionerRole#practitionerRole}")
public class PractitionerRole implements Resource {
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

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  @Valid List<Identifier> identifier;

  Boolean active;

  @Valid Period period;

  @Valid @NotNull Reference practitioner;

  @Valid @NotNull Reference organization;

  @Valid List<CodeableConcept> code;

  @Valid List<CodeableConcept> specialty;

  @Valid List<Reference> location;

  @Valid List<Reference> healthcareService;

  @Valid List<ContactPoint> telecom;

  @Valid List<PractitionerAvailableTime> availableTime;

  @Valid List<PractitionerNotAvailable> notAvailable;

  String availabilityExceptions;

  @Valid List<Reference> endpoint;

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "System and value must be set per contact point")
  private boolean isValidTelecom() {
    if (telecom == null) {
      return true;
    }
    return telecom.stream().noneMatch(e -> e.system() == null || e.value() == null);
  }

  public enum DayOfWeek {
    mon,
    tue,
    wed,
    thu,
    fri,
    sat,
    sun
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = PractitionerRole.Bundle.BundleBuilder.class)
  @Schema(
      name = "PractitionerRoleBundle",
      example =
          "${r4.practitionerRoleBundle:gov.va.api.health."
              + "r4.api.swaggerexamples.SwaggerPractitionerRole#practitionerRoleBundle}")
  public static final class Bundle extends AbstractBundle<Entry> {
    /** Builder constructor. */
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
        @Valid List<PractitionerRole.Entry> entry,
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
  @Schema(name = "PractitionerRoleEntry")
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = PractitionerRole.Entry.EntryBuilder.class)
  public static final class Entry extends AbstractEntry<PractitionerRole> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid PractitionerRole resource,
        @Valid AbstractEntry.Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @Schema(name = "PractitionerAvailableTime")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class PractitionerAvailableTime implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    List<DayOfWeek> daysOfWeek;

    Boolean allDay;

    @Pattern(regexp = Fhir.TIME)
    String availableStartTime;

    @Pattern(regexp = Fhir.TIME)
    String availableEndTime;
  }

  @Data
  @Builder
  @Schema(name = "PractitionerNotAvailable")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class PractitionerNotAvailable implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank String description;
    @Valid Period during;
  }
}
