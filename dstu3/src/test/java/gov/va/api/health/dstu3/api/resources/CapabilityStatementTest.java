package gov.va.api.health.dstu3.api.resources;

import static gov.va.api.health.dstu3.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.dstu3.api.samples.SampleCapabilityStatement;
import org.junit.Test;

public class CapabilityStatementTest {
  @Test
  public void capability() {
    assertRoundTrip(SampleCapabilityStatement.get().capabilityStatement());
  }
}
