package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.ZeroOrOneOfVerifier;
import gov.va.api.health.r4.api.samples.SampleCoverageEligibilityRequests;
import org.junit.jupiter.api.Test;

public class CoverageEligibilityRequestTest {

  private final SampleCoverageEligibilityRequests data = SampleCoverageEligibilityRequests.get();

  @Test
  public void coverageEligibilityRequest() {
    assertRoundTrip(data.coverageEligibilityRequest());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.diagnosisWithCodeableConcept())
        .fieldPrefix("diagnosis")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.diagnosisWithReference())
        .fieldPrefix("diagnosis")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.coverageEligibilityRequestWithServicedDate())
        .fieldPrefix("serviced")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.coverageEligibilityRequestWithServicedPeriod())
        .fieldPrefix("serviced")
        .build()
        .verify();
  }
}
