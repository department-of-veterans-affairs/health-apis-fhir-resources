package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.samples.SampleTerminologyCapabilities;
import org.junit.jupiter.api.Test;

public class TerminologyCapabilitiesTest {

  private final SampleTerminologyCapabilities data = SampleTerminologyCapabilities.get();

  @Test
  public void terminologyCapabilities() {
    assertRoundTrip(data.terminologyCapabilities());
  }
}
