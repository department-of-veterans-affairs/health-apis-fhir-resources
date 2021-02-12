package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.samples.SampleParameters;
import org.junit.jupiter.api.Test;

public class ParametersTest {

  private final SampleParameters data = SampleParameters.get();

  @Test
  void roundTrip() {
    assertRoundTrip(data.parameters());
  }
}
