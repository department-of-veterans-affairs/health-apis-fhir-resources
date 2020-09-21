package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Optional;
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
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-patient.html",
    example =
        "${uscorer4.patient:gov.va.api.health.uscorer4.api.swaggerexamples.SwaggerPatient#patient}")
@ZeroOrOneOfs({
  @ZeroOrOneOf(
      fields = {"deceasedBoolean", "deceasedDateTime"},
      message = "Only one deceased field may be specified"),
  @ZeroOrOneOf(
      fields = {"multipleBirthBoolean", "multipleBirthInteger"},
      message = "Only one multipleBirth field may be specified")
})
public class Patient implements Resource {
  // Anscestor -- Resource
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

  // R4 Patient Resource
  @Valid @NotEmpty List<Identifier> identifier;

  Boolean active;

  @Valid @NotEmpty List<HumanName> name;

  @Valid List<ContactPoint> telecom;

  @NotNull Gender gender;

  @Pattern(regexp = Fhir.DATE)
  String birthDate;

  Boolean deceasedBoolean;

  @Pattern(regexp = Fhir.DATETIME)
  String deceasedDateTime;

  @Valid List<Address> address;

  @Valid CodeableConcept maritalStatus;

  Boolean multipleBirthBoolean;

  Integer multipleBirthInteger;

  @Valid List<Attachment> photo;

  @Valid List<PatientContact> contact;

  @Valid List<Communication> communication;

  @Valid List<Reference> generalPractitioner;

  @Valid Reference managingOrganization;

  @Valid List<Link> link;

  @JsonIgnore
  @AssertTrue(message = "US-Core-Ethnicity extension is not valid")
  @SuppressWarnings("unused")
  private boolean isValidEthnicityExtension() {
    return isValidUsCoreExtensionCount(
        "http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity", 1);
  }

  @JsonIgnore
  @AssertTrue(message = "US-Core-Race extension is not valid")
  @SuppressWarnings("unused")
  private boolean isValidRaceExtension() {
    return isValidUsCoreExtensionCount(
        "http://hl7.org/fhir/us/core/StructureDefinition/us-core-race", 5);
  }

  private boolean isValidUsCoreExtensionCount(String url, int maxAllowedOmbExtensionCount) {
    if (extension == null) {
      return true;
    }
    Optional<Extension> usCoreExtension =
        extension.stream().filter(e -> url.equals(e.url())).findFirst();
    if (!usCoreExtension.isPresent()) {
      return true;
    }
    int ombExtensionCount = 0;
    int textExtensionCount = 0;
    for (Extension e : usCoreExtension.get().extension()) {
      if ("ombCategory".equals(e.url())) {
        ombExtensionCount++;
      } else if ("text".equals(e.url())) {
        textExtensionCount++;
      }
    }
    return ombExtensionCount <= maxAllowedOmbExtensionCount && textExtensionCount == 1;
  }

  public enum Gender {
    male,
    female,
    other,
    unknown
  }

  public enum Type {
    @JsonProperty("replaced-by")
    replaced_by,
    replaces,
    refer,
    seealso
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Bundle.BundleBuilder.class)
  @Schema(
      name = "PatientBundle",
      example =
          "${uscorer4.patientBundle:gov.va.api.health.uscorer4.api.swaggerexamples."
              + "SwaggerPatient#patientBundle}")
  public static class Bundle extends AbstractBundle<Entry> {
    /** Patient bundle builder. */
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
        @Valid List<Entry> entry,
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
  @Schema(name = "Communication")
  public static class Communication implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid @NotNull CodeableConcept language;

    Boolean preferred;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Entry.EntryBuilder.class)
  @Schema(name = "PatientEntry")
  public static class Entry extends AbstractEntry<Patient> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Patient resource,
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
  @Schema(name = "Link")
  public static class Link implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull @Valid Reference other;

    @NotNull Type type;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "PatientContact")
  public static class PatientContact implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<CodeableConcept> relationship;

    @Valid HumanName name;

    @Valid List<ContactPoint> telecom;

    @Valid Address address;

    Gender gender;

    @Valid Reference organization;

    @Valid Period period;
  }
}
