package gov.va.api.health.dstu3.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.dstu3.api.datatypes.Identifier;
import gov.va.api.health.dstu3.api.elements.Meta;
import gov.va.api.health.dstu3.api.elements.Narrative;
import gov.va.api.health.dstu3.api.resources.PractitionerRole;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SamplePractitionerRoles {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public PractitionerRole practitionerRole() {
    return PractitionerRole.builder()
        .resourceType("PractitionerRole")
        .id("2222")
        .meta(Meta.builder().build())
        .implicitRules("http://HelloRules.com")
        .language("Hello Language")
        .text(Narrative.builder().build())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(Identifier.builder().build()))
        .active(true)
        .telecom(singletonList(PractitionerRole.PractitionerContactPoint.builder().build()))
        .build();
  }
}
