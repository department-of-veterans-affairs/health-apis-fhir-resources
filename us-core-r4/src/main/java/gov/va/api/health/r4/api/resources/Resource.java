package gov.va.api.health.r4.api.resources;

import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Meta;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Pattern;

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
