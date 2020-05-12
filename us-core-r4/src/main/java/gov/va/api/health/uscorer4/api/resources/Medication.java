package gov.va.api.health.uscorer4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.*;
import gov.va.api.health.r4.api.elements.*;
import gov.va.api.health.r4.api.resources.Resource;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ExactlyOneOfs;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonDeserialize(builder = Medication.Bundle.BundleBuilder.class)
    @Schema(
            name = "MedicationBundle",
            example =
                    "${r4.medicationBundle:gov.va.api.health."
                            + "r4.api.swaggerexamples.SwaggerMedication#medicationBundle}")
    public static final class Bundle extends AbstractBundle<Medication.Entry> {
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
                @Valid List<Medication.Entry> entry,
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
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @Schema(name = "MedicationEntry")
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonDeserialize(builder = Medication.Entry.EntryBuilder.class)
    public static final class Entry extends AbstractEntry<Medication> {
        @Builder
        public Entry(
                @Pattern(regexp = Fhir.ID) String id,
                @Valid List<Extension> extension,
                @Valid List<Extension> modifierExtension,
                @Valid List<BundleLink> link,
                @Pattern(regexp = Fhir.URI) String fullUrl,
                @Valid Medication resource,
                @Valid AbstractEntry.Search search,
                @Valid Request request,
                @Valid Response response) {
            super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
        }
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
