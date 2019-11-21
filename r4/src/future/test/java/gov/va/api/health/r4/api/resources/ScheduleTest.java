package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.samples.SampleSchedules;
import org.junit.Test;

public class ScheduleTest {

  private final SampleSchedules data = SampleSchedules.get();

  @Test
  public void coverageEligibilityResponse() {
    assertRoundTrip(data.schedule());
  }
}
