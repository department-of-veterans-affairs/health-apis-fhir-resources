package gov.va.api.health.uscorer4.api.samples;

import gov.va.api.health.uscorer4.api.resources.Organization;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleOrganizations {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Organization organization() {
    return Organization.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(Collections.singletonList(resource()))
        .extension(Collections.singletonList(extension()))
        .modifierExtension(Collections.singletonList(extension()))
        .identifier(Collections.singletonList(identifier()))
        .active(true)
        .name("Goodnight Club")
        .telecom(List.of(contactPoint(), contactPoint()))
        .address(List.of(address(), address()))
        .build();
  }
}
