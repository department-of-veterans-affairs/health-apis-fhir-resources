package gov.va.api.health.dstu2.api.resources;

import static gov.va.api.health.dstu2.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.dstu2.api.samples.SampleConformance;
import org.junit.jupiter.api.Test;

public class ConformanceTest {
  @Test
  public void conformance() {
    assertRoundTrip(SampleConformance.get().conformance());
  }
}
