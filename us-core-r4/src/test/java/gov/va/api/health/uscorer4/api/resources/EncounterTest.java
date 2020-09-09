package gov.va.api.health.uscorer4.api.resources;

import static gov.va.api.health.uscorer4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.uscorer4.api.samples.SampleEncounters;
import org.junit.jupiter.api.Test;

public class EncounterTest {

  private final SampleEncounters data = SampleEncounters.get();

  @Test
  public void encounter() {
    assertRoundTrip(data.encounter());
  }
}
