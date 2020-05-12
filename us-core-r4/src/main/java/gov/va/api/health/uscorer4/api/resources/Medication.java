package gov.va.api.health.uscorer4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Ratio;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.*;
import gov.va.api.health.r4.api.resources.Resource;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ExactlyOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
        description =
                "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-medication.html",
        example =
                "${uscorer4.medication:gov.va.api.health."
                        + "uscorer4.api.swaggerexamples.SwaggerMedication#medication}")
public class Medication implements Resource {

    // Ancestors
    @NotBlank @Builder.Default String resourceType = "Medication";

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

    // Medication
    @Valid List<Identifier> identifier;

    @Valid @NotNull CodeableConcept code;

    @Valid Status status;

    @Valid Reference manufacturer;

    @Valid CodeableConcept form;

    @Valid Ratio amount;

    @Valid List<Ingredient> ingredient;

    @Valid List<Batch> batch;

    public enum Status {
        active,
        inactive,
        @JsonProperty("entered-in-error")
        entered_in_error
    }

    @Data
    @Builder
    @Schema(name = "MedicationIngredient")
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @ExactlyOneOfs({
            @ExactlyOneOf(
                    fields = {"itemCodeableConcept", "itemReference"},
                    message = "Exactly one field for item... itemCodeableConcept | itemReference")
    })
    public static class Ingredient implements BackboneElement {
        String id;

        @Valid List<Extension> extension;

        @Valid List<Extension> modifierExtension;

        @Valid CodeableConcept itemCodeableConcept;

        @Valid @NotNull Reference itemReference;
    }

    @Data
    @Builder
    @Schema(name = "MedicationBatch")
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Batch implements BackboneElement {
        String id;

        @Valid List<Extension> extension;

        @Valid List<Extension> modifierExtension;

        String lotNumber;

        @Pattern(regexp = Fhir.DATETIME)
        String expirationDate;
    }
}
