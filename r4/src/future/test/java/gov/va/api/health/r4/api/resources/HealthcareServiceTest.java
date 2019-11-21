package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.samples.SampleHealthcareServices;
import org.junit.Test;

public class HealthcareServiceTest {

  private final SampleHealthcareServices data = SampleHealthcareServices.get();

  @Test
  public void healthcareService() {
    assertRoundTrip(data.healthcareService());
  }
}
