package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(description = "https://www.hl7.org/fhir/R4/metadatatypes.html#DataRequirement")
@ZeroOrOneOf(fields = {"subjectCodeableConcept", "subjectReference"})
public final class DataRequirement implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  @NotBlank
  @Pattern(regexp = Fhir.CODE)
  String type;

  List<@Pattern(regexp = Fhir.CANONICAL) String> profile;

  @Valid CodeableConcept subjectCodeableConcept;

  @Valid Reference subjectReference;

  List<@Pattern(regexp = Fhir.STRING) String> mustSupport;

  @Valid List<CodeFilter> codeFilter;

  @Valid List<DateFilter> dateFilter;

  @Min(1)
  Integer limit;

  @Valid List<Sort> sort;

  public enum SortDirection {
    ascending,
    descending
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(
      fieldVisibility = JsonAutoDetect.Visibility.ANY,
      isGetterVisibility = JsonAutoDetect.Visibility.NONE)
  @Schema(name = "CodeFilter")
  @ExactlyOneOf(fields = {"path", "searchParam"})
  public static final class CodeFilter implements Element {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Pattern(regexp = Fhir.STRING)
    String path;

    @Pattern(regexp = Fhir.STRING)
    String searchParam;

    @Pattern(regexp = Fhir.CANONICAL)
    String valueSet;

    @Valid List<Coding> code;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(
      fieldVisibility = JsonAutoDetect.Visibility.ANY,
      isGetterVisibility = JsonAutoDetect.Visibility.NONE)
  @Schema(name = "DateFilter")
  @ZeroOrOneOf(fields = {"valueDateTime", "valuePeriod", "valueDuration"})
  @ExactlyOneOf(fields = {"path", "searchParam"})
  public static final class DateFilter implements Element {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Pattern(regexp = Fhir.STRING)
    String path;

    @Pattern(regexp = Fhir.STRING)
    String searchParam;

    @Pattern(regexp = Fhir.DATETIME)
    String valueDateTime;

    @Valid Period valuePeriod;

    @Valid Duration valueDuration;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(
      fieldVisibility = JsonAutoDetect.Visibility.ANY,
      isGetterVisibility = JsonAutoDetect.Visibility.NONE)
  @Schema(name = "Sort")
  public static final class Sort implements Element {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @NotBlank
    @Pattern(regexp = Fhir.STRING)
    String path;

    @NotBlank
    @Pattern(regexp = Fhir.CODE)
    String direction;
  }
}
