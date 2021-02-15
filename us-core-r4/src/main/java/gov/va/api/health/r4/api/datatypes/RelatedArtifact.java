package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Extension;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

  @Pattern(regexp = Fhir.CODE)
  @Valid
  @NotBlank
  String type;

  @Pattern(regexp = Fhir.STRING)
  @Valid
  String label;

  @Pattern(regexp = Fhir.STRING)
  @Valid
  String display;

  @Pattern(regexp = Fhir.MARKDOWN)
  @Valid
  String citation;

  @Pattern(regexp = Fhir.URI)
  @Valid
  String url;

  @Valid Attachment document;

  @Pattern(regexp = Fhir.URI)
  @Valid
  String resource;
}
