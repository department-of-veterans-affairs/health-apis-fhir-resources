package gov.va.api.health.stu3.api.datatypes;

import static java.util.Collections.singletonList;

import gov.va.api.health.stu3.api.RoundTrip;
import gov.va.api.health.stu3.api.resources.OperationOutcome;
import gov.va.api.health.stu3.api.samples.SampleDataTypes;
import java.util.Arrays;
import org.junit.Test;

public class DataTypesTest {

  private final SampleDataTypes data = SampleDataTypes.get();

  @Test
  public void dataTypes() {
    RoundTrip.assertRoundTrip(data.annotation());
    RoundTrip.assertRoundTrip(data.sampledData());
    RoundTrip.assertRoundTrip(data.range());
  }

  @Test
  public void operationOutcome() {
    RoundTrip.assertRoundTrip(
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
