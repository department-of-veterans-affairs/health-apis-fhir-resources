package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
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
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(description = " http://hl7.org/fhir/us/core/StructureDefinition-us-core-practitioner.html")
public class Practitioner implements Resource {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid List<Resource> contained;

  @Valid List<Extension> extensions;

  @Valid List<Extension> modifierExtensions;

  @Valid @NotEmpty List<Identifier> identifier;

  Boolean active;

  @Valid @NotEmpty List<HumanName> name;

  @Valid List<ContactPoint> telecom;

  @Valid List<Address> address;

  @Valid GenderCode gender;

  @Pattern(regexp = Fhir.DATE)
  String birthDate;

  @Valid List<Attachment> photo;

  @Valid List<Qualification> qualification;

  @Valid List<CodeableConcept> communication;

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "At most one IdentifierClia can be specified.")
  private boolean isValidIdentifier() {
    if (identifier == null) {
      return false;
    }
    return identifier.stream().filter(e -> e.system() != null && e.value() != null).count() >= 1;
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "At most one IdentifierClia can be specified.")
  private boolean isValidName() {
    if (name == null) {
      return false;
    }
    return name.stream().filter(e -> e.family() != null).count() >= 1;
  }

  enum GenderCode {
    male,
    female,
    other,
    unknown
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "Qualification")
  public static class Qualification implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<Identifier> identifier;

    @Valid @NotNull CodeableConcept code;

    @Valid Period period;

    @Valid Reference issuer;
  }
}
