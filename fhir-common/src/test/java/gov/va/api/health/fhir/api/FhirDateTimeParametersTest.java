package gov.va.api.health.fhir.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.Instant;
import java.util.TimeZone;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FhirDateTimeParametersTest {

  @BeforeAll
  static void setDateTime() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"202102,2021-13-11,2021-02-29,2021-03-32"})
  public void boundNotComputableForDate(String parameter) {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new FhirDateTimeParameters(parameter).lowerBound());
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new FhirDateTimeParameters(parameter).upperBound());
  }

  @Test
  public void computeDateTimeParameterLowerBound() {
    assertThat(new FhirDateTimeParameters("2021").lowerBound())
        .isEqualTo(Instant.parse("2021-01-01T00:00:00Z"));
    assertThat(new FhirDateTimeParameters("2021-02").lowerBound())
        .isEqualTo(Instant.parse("2021-02-01T00:00:00Z"));
    assertThat(new FhirDateTimeParameters("2021-02-11").lowerBound())
        .isEqualTo(Instant.parse("2021-02-11T00:00:00Z"));
    assertThat(new FhirDateTimeParameters("2021-02-11T09:00:00Z").lowerBound())
        .isEqualTo(Instant.parse("2021-02-11T09:00:00Z"));
    assertThat(new FhirDateTimeParameters("2021-02-11T09:00:00+01:00").lowerBound())
        .isEqualTo(Instant.parse("2021-02-11T08:00:00Z"));
  }

  @Test
  public void computeDateTimeParameterUpperBound() {
    assertThat(new FhirDateTimeParameters("2021").upperBound())
        .isEqualTo(Instant.parse("2021-12-31T23:59:59.999Z"));
    assertThat(new FhirDateTimeParameters("2021-02").upperBound())
        .isEqualTo(Instant.parse("2021-02-28T23:59:59.999Z"));
    assertThat(new FhirDateTimeParameters("2021-02-11").upperBound())
        .isEqualTo(Instant.parse("2021-02-11T23:59:59.999Z"));
    assertThat(new FhirDateTimeParameters("2021-02-11T09:00:00Z").upperBound())
        .isEqualTo(Instant.parse("2021-02-11T09:00:00.999Z"));
    assertThat(new FhirDateTimeParameters("2021-02-11T09:00:00+01:00").upperBound())
        .isEqualTo(Instant.parse("2021-02-11T08:00:00.999Z"));
  }

  @Test
  public void dateTimeParameterWithPrefixSatisfiesRange() {
    long upperBound = Instant.parse("2021-12-31T23:59:59.999Z").toEpochMilli();
    long lowerBound = Instant.parse("2021-01-01T00:00:00Z").toEpochMilli();
    assertThat(new FhirDateTimeParameters("eq2021").isSatisfied(lowerBound, upperBound)).isTrue();
    assertThat(new FhirDateTimeParameters("eq2021").isSatisfied(lowerBound, upperBound + 1))
        .isFalse();
    assertThat(new FhirDateTimeParameters("ne2021").isSatisfied(lowerBound, upperBound + 1))
        .isTrue();
    assertThat(new FhirDateTimeParameters("ne2021").isSatisfied(lowerBound, upperBound)).isFalse();
    assertThat(new FhirDateTimeParameters("gt2021").isSatisfied(lowerBound, upperBound + 1))
        .isTrue();
    assertThat(new FhirDateTimeParameters("gt2021").isSatisfied(lowerBound, upperBound)).isFalse();
    assertThat(new FhirDateTimeParameters("lt2021").isSatisfied(lowerBound - 1, upperBound - 1))
        .isTrue();
    assertThat(new FhirDateTimeParameters("lt2021").isSatisfied(lowerBound, upperBound)).isFalse();
    assertThat(new FhirDateTimeParameters("ge2021").isSatisfied(lowerBound, upperBound)).isTrue();
    assertThat(new FhirDateTimeParameters("ge2021").isSatisfied(lowerBound - 1, upperBound))
        .isFalse();
    assertThat(new FhirDateTimeParameters("le2021").isSatisfied(lowerBound - 1, upperBound))
        .isTrue();
    assertThat(new FhirDateTimeParameters("le2021").isSatisfied(lowerBound, upperBound + 1))
        .isFalse();
    // upper bound must be less than lower
    assertThat(new FhirDateTimeParameters("sa2021").isSatisfied(upperBound + 1, upperBound + 2))
        .isTrue();
    assertThat(new FhirDateTimeParameters("sa2021").isSatisfied(lowerBound, upperBound)).isFalse();
    // upper must be less than the lower bound
    assertThat(new FhirDateTimeParameters("eb2021").isSatisfied(lowerBound - 2, lowerBound - 1))
        .isTrue();
    assertThat(new FhirDateTimeParameters("eb2021").isSatisfied(lowerBound, upperBound)).isFalse();
    assertThatExceptionOfType(UnsupportedOperationException.class)
        .isThrownBy(() -> new FhirDateTimeParameters("ap2021").isSatisfied(lowerBound, upperBound));
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new FhirDateTimeParameters("ec2021").isSatisfied(lowerBound, upperBound));
  }

  @Test
  public void lowerGreaterThanUpperThrowsException() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(
            () ->
                new FhirDateTimeParameters("2021")
                    .isSatisfied(
                        Instant.parse("2021-01-01T05:00:00Z").toEpochMilli(),
                        Instant.parse("2021-01-01T04:59:59.999Z").toEpochMilli()));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "nope", "1"})
  public void nonValidDateTimeParameterThrowsException(String invalidParameter) {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new FhirDateTimeParameters(invalidParameter));
  }
}
