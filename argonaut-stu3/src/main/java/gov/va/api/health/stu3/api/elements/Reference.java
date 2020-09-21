package gov.va.api.health.stu3.api.elements;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.fhir.api.IsReference;
import gov.va.api.health.stu3.api.Fhir;
import gov.va.api.health.stu3.api.datatypes.Identifier;
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
@Schema(description = "http://hl7.org/fhir/STU3/references.html")
public class Reference implements Element, IsReference {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;
  String reference;
  String display;
  @Valid Identifier identifier;
}
