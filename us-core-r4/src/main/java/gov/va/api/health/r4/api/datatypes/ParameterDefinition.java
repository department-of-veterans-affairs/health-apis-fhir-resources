package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "https://www.hl7.org/fhir/R4/metadatatypes.html#parameterdefinition")
public class ParameterDefinition {

  @Pattern(regexp = Fhir.CODE)
  @Valid
  String name;

  @Pattern(regexp = Fhir.CODE)
  @Valid
  @NotBlank
  String use;

  Integer min;

  @Pattern(regexp = Fhir.STRING)
  @Valid
  String max;

  @Pattern(regexp = Fhir.STRING)
  @Valid
  String documentation;

  @Pattern(regexp = Fhir.CODE)
  @Valid
  @NotBlank
  String type;

  @Pattern(regexp = Fhir.CANONICAL)
  @Valid
  String profile;
}
