package gov.va.api.health.r4.api.elements;

import gov.va.api.health.r4.api.Fhir;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.Pattern;

@Schema(description = "https://www.hl7.org/fhir/R4/element.html")
public interface Element {

  List<Extension> extension();

  @Pattern(regexp = Fhir.STRING)
  String id();
}
