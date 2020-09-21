package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.Slot;
import gov.va.api.health.r4.api.resources.Slot.Status;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleSlots {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Slot slot() {
    return Slot.builder()
        .id("1234")
        .resourceType("Slot")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .serviceCategory(singletonList(codeableConcept()))
        .serviceType(singletonList(codeableConcept()))
        .specialty(singletonList(codeableConcept()))
        .appointmentType(codeableConcept())
        .schedule(reference())
        .status(Status.busy)
        .start("2017-01-01T00:00:00Z")
        .end("2017-01-01T00:00:00Z")
        .overbooked(true)
        .comment("comment")
        .build();
  }
}
