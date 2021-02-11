package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
@Schema(description = "https://www.hl7.org/fhir/R4/metadatatypes.html#DataRequirement")
public class DataRequirement {

  @Pattern(regexp = Fhir.CODE)
  String type;

  @Pattern(regexp = Fhir.URI)
  List<String> profile;

  @Valid Subject subject;

  @Pattern(regexp = Fhir.STRING)
  List<String> mustSupport;

  @Valid List<Element> codeFilter;

  @Valid List<Element> dateFilter;

  @Valid
  @Min(1)
  Integer limit;

  @Valid List<Element> sort;

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Subject {

    @Valid CodeableConcept subjectCodeableConcept;

    @Valid Reference subjectReference;
  }
}
