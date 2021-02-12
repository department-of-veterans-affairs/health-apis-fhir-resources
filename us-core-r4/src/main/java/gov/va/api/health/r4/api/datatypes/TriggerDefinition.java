package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class TriggerDefinition {

  @Pattern(regexp = Fhir.CODE)
  @Valid
  String type;

  @Pattern(regexp = Fhir.STRING)
  @Valid
  String name;

  @Valid Timing timing;

  @Valid DataRequirement data;

  @Valid Expression condition;

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @ZeroOrOneOf(fields = {"timingTiming", "timingReference", "timingDate", "timingDateTime"})
  public static class Timing {

    gov.va.api.health.r4.api.datatypes.Timing timingTiming;

    Reference timingReference;

    @Pattern(regexp = Fhir.DATE)
    @Valid
    String timingDate;

    @Pattern(regexp = Fhir.DATETIME)
    @Valid
    String timingDateTime;
  }
}
