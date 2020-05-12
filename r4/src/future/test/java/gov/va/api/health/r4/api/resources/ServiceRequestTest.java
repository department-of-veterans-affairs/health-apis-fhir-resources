package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.ZeroOrOneOfVerifier;
import gov.va.api.health.r4.api.samples.SampleServiceRequests;
import org.junit.Test;

public class ServiceRequestTest {

  private final SampleServiceRequests data = SampleServiceRequests.get();

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(
            data.serviceRequest()
                .quantityQuantity(null)
                .quantityRatio(null)
                .quantityRange(data.range()))
        .fieldPrefix("quantity")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(
            data.serviceRequest()
                .quantityQuantity(null)
                .quantityRatio(data.ratio())
                .quantityRange(null))
        .fieldPrefix("quantity")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(
            data.serviceRequest()
                .quantityQuantity(data.quantity())
                .quantityRatio(null)
                .quantityRange(null))
        .fieldPrefix("quantity")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(
            data.serviceRequest()
                .occurrenceDateTime(null)
                .occurrencePeriod(null)
                .occurrenceTiming(data.timing()))
        .fieldPrefix("occurrence")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(
            data.serviceRequest()
                .occurrenceDateTime(null)
                .occurrencePeriod(data.period())
                .occurrenceTiming(null))
        .fieldPrefix("occurrence")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(
            data.serviceRequest()
                .occurrenceDateTime("2015-02-07T13:28:17-05:00")
                .occurrencePeriod(null)
                .occurrenceTiming(null))
        .fieldPrefix("occurrence")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(
            data.serviceRequest()
                .asNeededBoolean(null)
                .asNeededCodeableConcept(data.codeableConcept()))
        .fieldPrefix("asNeeded")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.serviceRequest().asNeededBoolean(true).asNeededCodeableConcept(null))
        .fieldPrefix("asNeeded")
        .build()
        .verify();
  }

  @Test
  public void serviceRequest() {
    assertRoundTrip(data.serviceRequest());
  }
}
