package gov.va.api.health.r4.api;

import static java.util.Collections.singletonList;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.va.api.health.r4.api.elements.Extension;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/** This class provides data absent reasons. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataAbsentReason {
  /**
   * Create a new Extension that indicates a field is absent for a given reason.
   *
   * <p>See https://www.hl7.org/fhir/R4/valueset-data-absent-reason.html
   */
  public static Extension of(@NonNull String value) {
    return Extension.builder()
        .extension(
            singletonList(
                Extension.builder()
                    .url("https://hl7.org/fhir/extension-data-absent-reason.html")
                    .valueCode(value)
                    .build()))
        .build();
  }

  /**
   * Create a new Extension that indicates a field is absent because for the given reason.
   *
   * <p>See https://www.hl7.org/fhir/R4/valueset-data-absent-reason.html
   */
  public static Extension of(@NonNull Reason reason) {
    return of(reason.value());
  }

  @SuppressWarnings("unused")
  public enum Reason {
    unknown,
    asked,
    temp,
    @JsonProperty("not-asked")
    not_asked,
    masked,
    unsupported,
    astext,
    error,
    NaN;

    private String value() {
      switch (this) {
        case not_asked:
          return "not-asked";
        default:
          return toString();
      }
    }
  }
}
