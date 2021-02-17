package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Extension;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "https://www.hl7.org/fhir/R4/metadatatypes.html#relatedartifact")
public class RelatedArtifact implements Element {

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  @Valid Type type;

  @Pattern(regexp = Fhir.STRING)
  String label;

  @Pattern(regexp = Fhir.STRING)
  String display;

  @Pattern(regexp = Fhir.MARKDOWN)
  String citation;

  @Pattern(regexp = Fhir.URI)
  String url;

  @Valid Attachment document;

  @Pattern(regexp = Fhir.CANONICAL)
  String resource;

  public enum Type {
    documentation,
    justification,
    citation,
    predecessor,
    successor,
    derivedForm,
    dependsOn,
    composedOf
  }
}
