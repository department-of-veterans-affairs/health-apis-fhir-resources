package gov.va.api.health.r4.api;

import gov.va.api.health.fhir.api.FhirDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Fhir {
  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#id")
  public static final String ID = "[A-Za-z0-9\\-\\.]{1,64}";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#code")
  public static final String CODE = "[^\\s]+(\\s[^\\s]+)*";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#uri")
  public static final String URI = "\\S*";

  @Schema(description = "https://www.hl7.org/fhir/datatypes.html#canonical")
  public static final String CANONICAL = URI;

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#base64binary")
  public static final String BASE64 = "(\\s*([0-9a-zA-Z\\+\\=]){4}\\s*)+";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#date")
  public static final String DATE =
      "([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)"
          + "(-(0[1-9]|1[0-2])(-(0[1-9]|[1-2][0-9]|3[0-1]))?)?";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#datetime")
  public static final String DATETIME =
      "([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)(-(0[1-9]|1[0-2])"
          + "(-(0[1-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)"
          + "(\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00)))?)?)?";

  @Schema(
      description =
          "https://www.hl7.org/fhir/R4/datatypes.html#datetime https://www.hl7.org/fhir/R4/search.html#prefix")
  public static final String DATETIME_SEARCH =
      "(ge|le|eq|ne|gt|lt|sa|eb|ap)([0-9]"
          + "([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)"
          + "(-(0[1-9]|1[0-2])(-(0[1-9]|[1-2][0-9]|3[0-1])"
          + "(T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)"
          + "(\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00)))?)?)?";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#time")
  public static final String TIME = "([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)(\\.[0-9]+)?";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#instant")
  public static final String INSTANT =
      "([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)-(0[1-9]|1[0-2])"
          + "-(0[1-9]|[1-2][0-9]|3[0-1])T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)"
          + "(\\.[0-9]+)?(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#oid")
  public static final String OID = "urn:oid:[0-2](\\.(0|[1-9][0-9]*))+";

  public static final String XHTML = "<.+>";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#positiveInt")
  public static final String POSITIVE_INT = "\\+?[1-9][0-9]*";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#unsignedInt")
  public static final String UNSIGNED_INT = "[0]|([1-9][0-9]*)";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#string")
  public static final String STRING = "[ \\r\\n\\t\\S]+";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#boolean")
  public static final String BOOLEAN = "true|false";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#markdown")
  public static final String MARKDOWN = "\\s*(\\S|\\s)*";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#decimal")
  public static final String DECIMAL = "-?(0|[1-9][0-9]*)(\\.[0-9]+)?([eE][+-]?[0-9]+)?";

  @Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#integer")
  public static final String INTEGER = "[0]|[-+]?[1-9][0-9]*";

  /** Deprecated: Use gov.va.api.health.fhir.api.FhirDateTime#parseDateTime(java.lang.String). */
  @SneakyThrows
  @Deprecated
  public static Instant parseDateTime(String dateTime) {
    return FhirDateTime.parseDateTime(dateTime);
  }
}
