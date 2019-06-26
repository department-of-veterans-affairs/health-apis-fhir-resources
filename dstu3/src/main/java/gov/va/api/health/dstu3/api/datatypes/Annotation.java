package gov.va.api.health.dstu3.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.dstu3.api.Fhir;
import gov.va.api.health.dstu3.api.elements.Element;
import gov.va.api.health.dstu3.api.elements.Extension;
import gov.va.api.health.dstu3.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
@Schema(description = "http://hl7.org/fhir/STU3/datatypes.html#Annotation")
@ZeroOrOneOf(
  fields = {"authorReference", "authorString"},
  message = "Only one author value may be specified"
)
public class Annotation implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;
  @Valid Reference authorReference;
  String authorString;

  @Pattern(regexp = Fhir.DATETIME)
  String time;

  @NotNull String text;
}
