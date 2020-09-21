package gov.va.api.health.r4.api.resources;

import gov.va.api.health.r4.api.RoundTrip;
import gov.va.api.health.r4.api.samples.SampleCapabilityStatements;
import org.junit.jupiter.api.Test;

public class CapabilityStatementTest {

  @Test
  public void capability() {
    RoundTrip.assertRoundTrip(SampleCapabilityStatements.get().capability());
  }
}
