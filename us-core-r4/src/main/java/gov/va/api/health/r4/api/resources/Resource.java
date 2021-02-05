package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.MixedBundle;
import gov.va.api.health.r4.api.elements.Meta;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Pattern;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "resourceType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MixedBundle.class, name = "Bundle"),
        @JsonSubTypes.Type(value = Patient.class, name = "Patient"),
        @JsonSubTypes.Type(value = Immunization.class, name = "Immunization"),
        @JsonSubTypes.Type(value = Location.class, name = "Location")
})
@Schema(description = "https://www.hl7.org/fhir/R4/resource.html")
public interface Resource {
  @Pattern(regexp = Fhir.STRING)
  String id();

  @Pattern(regexp = Fhir.STRING)
  String implicitRules();

  @Pattern(regexp = Fhir.STRING)
  String language();

  Meta meta();
}
