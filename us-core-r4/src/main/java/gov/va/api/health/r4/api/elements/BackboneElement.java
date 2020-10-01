package gov.va.api.health.r4.api.elements;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "https://www.hl7.org/fhir/R4/backboneelement.html")
public interface BackboneElement extends Element {
  List<Extension> modifierExtension();
}
