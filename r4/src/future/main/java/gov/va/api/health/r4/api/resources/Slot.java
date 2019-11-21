package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
  isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(
  description = "https://www.hl7.org/fhir/R4/slot.html", 
  example = "slot:com.example.Example#example"
)
public class Slot {

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

  // Slot Resource
  @Valid List<Identifier> identifier;

  @Valid List<CodeableConcept> serviceCategory;

  @Valid List<CodeableConcept> serviceType;
  @Valid List<CodeableConcept> specialty;
  @Valid CodeableConcept appointmentType;
  @NotNull @Valid Reference schedule;

  @NotNull Status status;

  @NotBlank
  @Pattern(regexp = Fhir.INSTANT)
  String start;

  @NotBlank
  @Pattern(regexp = Fhir.INSTANT)
  String end;

  @Pattern(regexp = Fhir.BOOLEAN)
  String overbooked;

  @Pattern(regexp = Fhir.STRING)
  String comment;

  public enum Status {
    busy,
    free,
    @JsonProperty("busy-unavailable")
    busy_unavailable,
    @JsonProperty("busy-tentative")
    busy_tentative,
    @JsonProperty("entered-in-error")
    entered_in_error
  }
}
