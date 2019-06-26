package gov.va.api.health.stu3.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.stu3.api.Fhir;
import gov.va.api.health.stu3.api.elements.Element;
import gov.va.api.health.stu3.api.elements.Extension;
import gov.va.api.health.stu3.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "http://hl7.org/fhir/STU3/datatypes.html#Signature")
@ExactlyOneOf(fields = {"whoUri", "whoReference"})
@ZeroOrOneOf(fields = {"onBehalfOfUri", "onBehalfOfReference"})
public class Signature implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  @Valid @NotEmpty List<Coding> type;

  @NotNull
  @Pattern(regexp = Fhir.INSTANT)
  String when;

  @Pattern(regexp = Fhir.URI)
  String whoUri;

  @Valid Reference whoReference;

  @Pattern(regexp = Fhir.URI)
  String onBehalfOfUri;

  @Valid Reference onBehalfOfReference;

  @Pattern(regexp = Fhir.CODE)
  String contentType;

  @Pattern(regexp = Fhir.BASE64)
  String blob;
}
