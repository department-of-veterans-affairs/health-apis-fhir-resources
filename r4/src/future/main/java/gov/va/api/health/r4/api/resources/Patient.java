package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(
  fieldVisibility = JsonAutoDetect.Visibility.ANY,
  isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(
  description = "https://www.hl7.org/fhir/R4/patient.html",
  example = "patient:com.example.Example#example"
)
@ZeroOrOneOfs({
  @ZeroOrOneOf(
    fields = {"deceasedBoolean", "deceasedDateTime"},
    message = "Only one deceased field may be specified"
  ),
  @ZeroOrOneOf(
    fields = {"multipleBirthBoolean", "multipleBirthInteger"},
    message = "Only one deceased field may be specified"
  )
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
  @Valid List<Identifier> identifier;

  @Pattern(regexp = Fhir.BOOLEAN)
  String active;

  @Valid List<HumanName> name;
  @Valid List<ContactPoint> telecom;
  Gender gender;

  @Pattern(regexp = Fhir.DATE)
  String birthDate;

  @Pattern(regexp = Fhir.BOOLEAN)
  String deceasedBoolean;

  @Pattern(regexp = Fhir.DATETIME)
  String deceasedDateTime;

  @Valid List<Address> address;
  @Valid CodeableConcept maritalStatus;

  @Pattern(regexp = Fhir.BOOLEAN)
  String multipleBirthBoolean;

  @Pattern(regexp = Fhir.INTEGER)
  String multipleBirthInteger;

  @Valid List<Attachment> photo;
  @Valid List<PatientContact> contact;
  @Valid List<Communication> communication;
  @Valid List<Reference> generalPractitioner;
  @Valid Reference managingOrganization;
  @Valid List<Link> link;

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

    @Valid @NonNull CodeableConcept language;

    @Pattern(regexp = Fhir.BOOLEAN)
    String preferred;
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

    @NonNull @Valid Reference other;

    @NonNull Type type;
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
