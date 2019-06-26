package gov.va.api.health.dstu3.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.dstu3.api.elements.Meta;
import gov.va.api.health.dstu3.api.elements.Narrative;
import gov.va.api.health.dstu3.api.resources.Practitioner;
import gov.va.api.health.dstu3.api.resources.Practitioner.Gender;
import gov.va.api.health.dstu3.api.resources.Practitioner.Qualification;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SamplePractitioners {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Practitioner practitioner() {
    return Practitioner.builder()
        .resourceType("Practitioner")
        .id("2222")
        .meta(Meta.builder().build())
        .implicitRules("http://HelloRules.com")
        .language("Hello Language")
        .text(Narrative.builder().build())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(Practitioner.PractitionerIdentifier.builder().build()))
        .active(true)
        .name(Practitioner.PractitionerHumanName.builder().build())
        .telecom(singletonList(dataTypes.contactPoint()))
        .address(singletonList(dataTypes.address()))
        .gender(Gender.male)
        .birthDate("2000-10-01")
        .photo(singletonList(dataTypes.attachment()))
        .qualification(
            singletonList(
                Qualification.builder()
                    .identifier(singletonList(dataTypes.identifier()))
                    .code(dataTypes.codeableConcept())
                    .period(dataTypes.period())
                    .issuer(dataTypes.reference())
                    .build()))
        .communication(dataTypes.codeableConceptList())
        .build();
  }
}
