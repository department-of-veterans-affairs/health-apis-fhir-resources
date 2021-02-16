package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
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
@Schema(description = "https://www.hl7.org/fhir/R4/metadatatypes.html#triggerdefinitionz")
@ZeroOrOneOf(fields = {"timingTiming", "timingReference", "timingDate", "timingDateTime"})
public class TriggerDefinition implements Element {

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  @Pattern(regexp = Fhir.CODE)
  String type;

  @Pattern(regexp = Fhir.STRING)
  String name;

  @Valid Timing timing;

  @Valid DataRequirement data;

  @Valid Expression condition;

  Timing timingTiming;

  Reference timingReference;

  @Pattern(regexp = Fhir.DATE)
  String timingDate;

  @Pattern(regexp = Fhir.DATETIME)
  String timingDateTime;
}
