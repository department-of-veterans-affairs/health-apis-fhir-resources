package gov.va.api.health.uscorer4.api.samples;

import gov.va.api.health.uscorer4.api.resources.Location;
import gov.va.api.health.uscorer4.api.resources.Location.HoursOfOperation;
import gov.va.api.health.uscorer4.api.resources.Location.Mode;
import gov.va.api.health.uscorer4.api.resources.Location.Position;
import gov.va.api.health.uscorer4.api.resources.Location.Status;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleLocations {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Location location() {
    return Location.builder()
        .status(Status.active)
        .name("My Name")
        .alias(List.of("My", "Alias"))
        .hoursOfOperation(
            HoursOfOperation.builder()
                .allDay(true)
                .openingTime("00:00:00")
                .closingTime("00:00:00")
                .daysOfWeek(List.of(Location.DaysOfWeek.mon, Location.DaysOfWeek.fri))
                .build())
        .description("Description")
        .availabilityExceptions("Closed every other monday")
        .mode(Mode.instance)
        .type(List.of(dataTypes.codeableConcept()))
        .telecom(Collections.singletonList(dataTypes.contactPoint()))
        .address(dataTypes.address())
        .physicalType(dataTypes.codeableConcept())
        .position(
            Position.builder()
                .latitude(new BigDecimal(3.0))
                .longitude(new BigDecimal(2.0))
                .altitude(new BigDecimal(1.0))
                .build())
        .managingOrganization(dataTypes.reference())
        .partOf(dataTypes.reference())
        .endpoint(List.of(dataTypes.reference()))
        .build();
  }
}
