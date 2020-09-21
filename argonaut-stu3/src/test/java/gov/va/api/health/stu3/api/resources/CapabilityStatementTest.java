package gov.va.api.health.stu3.api.resources;

import gov.va.api.health.stu3.api.RoundTrip;
import gov.va.api.health.stu3.api.samples.SampleCapabilityStatement;
import org.junit.jupiter.api.Test;

public class CapabilityStatementTest {
  @Test
  public void capability() {
    RoundTrip.assertRoundTrip(SampleCapabilityStatement.get().capabilityStatement());
  }
}
