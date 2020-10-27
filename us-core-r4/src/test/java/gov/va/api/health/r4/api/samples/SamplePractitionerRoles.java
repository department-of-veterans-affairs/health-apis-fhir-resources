package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.PractitionerRole;
import gov.va.api.health.r4.api.resources.PractitionerRole.DayOfWeek;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SamplePractitionerRoles {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public PractitionerRole.PractitionerAvailableTime availableTime() {
    return PractitionerRole.PractitionerAvailableTime.builder()
        .id("999")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .daysOfWeek(singletonList(DayOfWeek.fri))
        .allDay(false)
        .availableStartTime("08:00:00")
        .availableEndTime("12:00:00")
        .build();
  }

  public PractitionerRole.PractitionerNotAvailable notAvailable() {
    return PractitionerRole.PractitionerNotAvailable.builder()
        .id("998")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .description("Bye")
        .during(period())
        .build();
  }

  public PractitionerRole practitionerRole() {
    return PractitionerRole.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .active(true)
        .period(period())
        .practitioner(reference())
        .organization(reference())
        .code(singletonList(codeableConcept()))
        .specialty(singletonList(codeableConcept()))
        .location(singletonList(reference()))
        .healthcareService(singletonList(reference()))
        .telecom(singletonList(contactPoint()))
        .availableTime(singletonList(availableTime()))
        .notAvailable(singletonList(notAvailable()))
        .availabilityExceptions("Mondays")
        .endpoint(singletonList(reference()))
        .build();
  }
}
