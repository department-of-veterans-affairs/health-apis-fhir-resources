package gov.va.api.health.dstu3.api.datatypes;

import static gov.va.api.health.dstu3.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

import gov.va.api.health.dstu3.api.resources.OperationOutcome;
import gov.va.api.health.dstu3.api.samples.SampleDataTypes;
import java.util.Arrays;
import org.junit.Test;

public class DataTypesTest {

  private final SampleDataTypes data = SampleDataTypes.get();

  @Test
  public void dataTypes() {
    assertRoundTrip(data.annotation());
    assertRoundTrip(data.sampledData());
    assertRoundTrip(data.range());
  }

  @Test
  public void operationOutcome() {
    assertRoundTrip(
        OperationOutcome.builder()
            .id("4321")
            .meta(data.meta())
            .implicitRules("http://HelloRules.com")
            .language("Hello Language")
            .text(data.narrative())
            .contained(singletonList(data.resource()))
            .modifierExtension(
                Arrays.asList(
                    data.extension(), data.extensionWithQuantity(), data.extensionWithRatio()))
            .issue(singletonList(data.issue()))
            .build());
  }
}
