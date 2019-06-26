package gov.va.api.health.stu3.api.samples;

import gov.va.api.health.stu3.api.resources.Location;
import java.util.Collections;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleLocations {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Location location() {
    return Location.builder()
        .status(Location.Status.active)
        .name("My Name")
        .description("Description")
        .mode(Location.Mode.instance)
        .type(dataTypes.codeableConcept())
        .telecom(Collections.singletonList(dataTypes.contactPoint()))
        .address(Location.LocationAddress.builder().build())
        .physicalType(dataTypes.codeableConcept())
        .position(Location.Position.builder().latitude(3.0).longitude(2.0).altitude(1.0).build())
        .managingOrganization(dataTypes.reference())
        .partOf(dataTypes.reference())
        .build();
  }
}
