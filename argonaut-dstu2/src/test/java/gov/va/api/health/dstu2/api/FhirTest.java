package gov.va.api.health.dstu2.api;

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

  @Test
  public void base64RegexMatchesGoodStrings() {
    // There are two separate cases in the regex that should be tested. < and > 4 characters.
    assertThat(Pattern.matches(Fhir.BASE64, "QUJD"))
        .withFailMessage("Failed short, valid BASE64 without padding.")
        .isTrue();
    assertThat(Pattern.matches(Fhir.BASE64, "QUI="))
        .withFailMessage("Failed short, valid BASE64 with one padding char.")
        .isTrue();
    assertThat(Pattern.matches(Fhir.BASE64, "QQ=="))
        .withFailMessage("Failed short, valid BASE64 with two padding chars.")
        .isTrue();
    assertThat(Pattern.matches(Fhir.BASE64, "SSBhdGUgbWFueSBwZWFudXRz"))
        .withFailMessage("Failed long, valid BASE64 without padding.")
        .isTrue();
    assertThat(Pattern.matches(Fhir.BASE64, "SSBqdXN0IGF0ZSBhIHBlYW51dAo="))
        .withFailMessage("Failed long, valid BASE64 with one padding char.")
        .isTrue();
    assertThat(Pattern.matches(Fhir.BASE64, "SSBhdGUgYSBmZXcgcGVhbnV0cw=="))
        .withFailMessage("Failed long, valid BASE64 with two padding chars.")
        .isTrue();
  }
}
