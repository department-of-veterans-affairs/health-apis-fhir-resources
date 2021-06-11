package gov.va.api.health.stu3.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.stu3.api.resources.Organization;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleOrganizations {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Organization organization() {
    return Organization.builder()
        .id("2222")
        .meta(meta())
        .implicitRules("http://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(Organization.OrganizationIdentifier.builder().build()))
        .active(true)
        .type(singletonList(codeableConcept()))
        .name("Hello Name")
        .telecom(singletonList(contactPoint()))
        .address(singletonList(Organization.OrganizationAddress.builder().build()))
        .partOf(reference())
        .contact(
            singletonList(
                Organization.OrganizationContact.builder()
                    .purpose(codeableConcept())
                    .name(humanName())
                    .telecom(singletonList(contactPoint()))
                    .address(address())
                    .build()))
        .build();
  }
}
