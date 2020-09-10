package gov.va.api.health.stu3.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class FhirTest {

  @Test
  public void base64RegexDoesNotMatchBadStrings() {
    // Make sure that = is only allowed as the last 1 or 2 characters of the string.
    assertThat(Pattern.matches(Fhir.BASE64, "SSBqdXN0IGF0ZSBhIHBlYW51dA=o"))
        .withFailMessage("Padding character in wrong location is invalid but was not rejected.")
        .isFalse();
    assertThat(Pattern.matches(Fhir.BASE64, ""))
        .withFailMessage("Empty string is invalid but was not rejected.")
        .isFalse();
    assertThat(Pattern.matches(Fhir.BASE64, "SSBqdXN0IGF0ZSBhIHBlYW51dAo"))
        .withFailMessage("Missing padding/improper size is invalid but was not rejected.")
        .isFalse();
  }
}
