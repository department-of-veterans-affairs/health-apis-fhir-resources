package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@Schema(
        description =
                "http://hl7.org/fhir/us/core/StructureDefinition-us-core-implantable-device.html",
        example =
                "${r4.implantableDevice:gov.va.api.health.r4.api.swaggerexamples.SwaggerImplantableDevice#implantableDevice}"
)
public class ImplantableDevice implements Resource {
    // Ancestors
    @NotBlank @Builder.Default String resourceType = "ImplantableDevice";

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

    

}
