package gov.va.api.health.stu3.api.samples;

import gov.va.api.health.stu3.api.datatypes.Coding;
import gov.va.api.health.stu3.api.resources.Endpoint;
import gov.va.api.health.stu3.api.resources.Endpoint.Status;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleEndpoints {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Endpoint endpoint() {
    return Endpoint.builder()
        .status(Status.active)
        .name("My Name")
        .connectionType(Coding.builder().build())
        .address("address")
        .managingOrganization(dataTypes.reference())
        .build();
  }
}
