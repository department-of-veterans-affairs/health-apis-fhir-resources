package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.HealthcareService;
import gov.va.api.health.r4.api.resources.HealthcareService.AvailableTime;
import gov.va.api.health.r4.api.resources.HealthcareService.AvailableTime.DaysOfWeek;
import gov.va.api.health.r4.api.resources.HealthcareService.Eligibility;
import gov.va.api.health.r4.api.resources.HealthcareService.NotAvailable;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleHealthcareServices {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public AvailableTime availableTime() {
    return AvailableTime.builder()
        .daysOfWeek(Arrays.asList(DaysOfWeek.mon, DaysOfWeek.fri))
        .allDay("false")
        .availableStartTime("03:00:00")
        .availableEndTime("05:00:00")
        .build();
  }

  public Eligibility eligibility() {
    return Eligibility.builder().code(codeableConcept()).comment("# comment").build();
  }

  public HealthcareService healthcareService() {
    return HealthcareService.builder()
        .id("1234")
        .resourceType("HealthcareService")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .active("true")
        .providedBy(reference())
        .category(singletonList(codeableConcept()))
        .type(singletonList(codeableConcept()))
        .specialty(singletonList(codeableConcept()))
        .location(singletonList(reference()))
        .name("name")
        .comment("comment")
        .extraDetails("# extra details")
        .photo(attachment())
        .telecom(singletonList(contactPoint()))
        .coverageArea(singletonList(reference()))
        .serviceProvisionCode(singletonList(codeableConcept()))
        .eligibility(singletonList(eligibility()))
        .program(singletonList(codeableConcept()))
        .characteristic(singletonList(codeableConcept()))
        .communication(singletonList(codeableConcept()))
        .referralMethod(singletonList(codeableConcept()))
        .appointmentRequired("true")
        .availableTime(singletonList(availableTime()))
        .notAvailable(singletonList(notAvailable()))
        .availabilityExceptions("available exceptions")
        .endpoint(singletonList(reference()))
        .build();
  }

  public NotAvailable notAvailable() {
    return NotAvailable.builder().description("description").during(period()).build();
  }
}
