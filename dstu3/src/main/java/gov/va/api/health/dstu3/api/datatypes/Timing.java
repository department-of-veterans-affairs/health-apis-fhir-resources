package gov.va.api.health.dstu3.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.dstu3.api.Fhir;
import gov.va.api.health.dstu3.api.elements.Element;
import gov.va.api.health.dstu3.api.elements.Extension;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
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
@Schema(description = "http://hl7.org/fhir/STU3/datatypes.html#Timing")
public class Timing implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  List<@Pattern(regexp = Fhir.DATETIME) String> event;

  @Valid CodeableConcept code;
  @Valid Repeat repeat;

  @SuppressWarnings("unused")
  public enum EventTiming {
    HS,
    WAKE,
    C,
    CM,
    CD,
    CV,
    AC,
    ACM,
    ACD,
    ACV,
    PC,
    PCM,
    PCD,
    PCV
  }

  @SuppressWarnings("unused")
  public enum UnitsOfTime {
    s,
    min,
    h,
    d,
    wk,
    mo,
    a
  }

  public enum DaysOfWeek {
    mon,
    tue,
    wed,
    thu,
    fri,
    sat,
    sun
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @ZeroOrOneOf(fields = {"boundsQuantity", "boundsRange", "boundsPeriod"})
  public static class Repeat implements Element {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid Duration boundsQuantity;
    @Valid Range boundsRange;
    @Valid Period boundsPeriod;
    @Valid List<Extension> extension;
    Integer count;
    Integer countMax;
    Double duration;
    Double durationMax;
    @Valid UnitsOfTime durationUnits;
    Integer frequency;
    Integer frequencyMax;
    Double period;
    Double periodMax;
    @Valid UnitsOfTime periodUnits;
    DaysOfWeek daysOfWeek;
    EventTiming when;
    @PositiveOrZero Integer offset;
  }
}
