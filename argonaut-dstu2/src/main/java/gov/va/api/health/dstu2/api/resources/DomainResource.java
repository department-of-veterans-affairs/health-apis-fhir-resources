package gov.va.api.health.dstu2.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.dstu2.api.datatypes.SimpleResource;
import gov.va.api.health.dstu2.api.elements.Extension;
import gov.va.api.health.dstu2.api.elements.Narrative;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "https://www.hl7.org/fhir/domainresource.html")
public interface DomainResource extends Resource {
  List<SimpleResource> contained();

  List<Extension> extension();

  List<Extension> modifierExtension();

  Narrative text();
}
