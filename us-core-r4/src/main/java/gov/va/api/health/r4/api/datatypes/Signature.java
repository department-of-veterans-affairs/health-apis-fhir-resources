package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "http://hl7.org/fhir/R4/datatypes.html#Signature")
public class Signature implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  @Valid @NotEmpty List<Coding> type;

  @Pattern(regexp = Fhir.INSTANT)
  @NotBlank
  String when;

  @NotNull @Valid Reference who;

  @Valid Reference onBehalfOf;

  @Pattern(regexp = Fhir.CODE)
  String targetFormat;

  @Pattern(regexp = Fhir.CODE)
  String sigFormat;

  @Pattern(regexp = Fhir.BASE64)
  String data;
}
