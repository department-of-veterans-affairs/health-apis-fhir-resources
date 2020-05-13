package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Extension;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
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
@Schema(description = "https://www.hl7.org/fhir/r4/datatypes.html#Duration")
public class Duration implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  BigDecimal value;

  @Pattern(regexp = "(<|<=|>=|>)")
  String comparator;

  @Pattern(regexp = Fhir.STRING)
  String unit;

  @Pattern(regexp = Fhir.URI)
  String system;

  @Pattern(regexp = Fhir.CODE)
  String code;
}
