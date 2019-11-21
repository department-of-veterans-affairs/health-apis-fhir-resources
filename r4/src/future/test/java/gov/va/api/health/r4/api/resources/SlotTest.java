package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.samples.SampleSlots;
import org.junit.Test;

public class SlotTest {

  private final SampleSlots data = SampleSlots.get();

  @Test
  public void coverageEligibilityResponse() {
    assertRoundTrip(data.slot());
  }
}
