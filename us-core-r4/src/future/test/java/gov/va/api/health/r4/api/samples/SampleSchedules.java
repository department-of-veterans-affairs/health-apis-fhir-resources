package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.Schedule;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleSchedules {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Schedule schedule() {
    return Schedule.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .active(true)
        .serviceCategory(singletonList(codeableConcept()))
        .serviceType(singletonList(codeableConcept()))
        .specialty(singletonList(codeableConcept()))
        .actor(singletonList(reference()))
        .planningHorizon(period())
        .comment("comment")
        .build();
  }
}
