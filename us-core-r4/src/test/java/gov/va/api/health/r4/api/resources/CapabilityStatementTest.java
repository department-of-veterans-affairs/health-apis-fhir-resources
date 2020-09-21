package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.samples.SampleCapabilityStatements;
import org.junit.jupiter.api.Test;

public class CapabilityStatementTest {

  @Test
  public void capability() {
    assertRoundTrip(SampleCapabilityStatements.get().capability());
  }
}
