package gov.va.api.health.r4.api.samples;

import static gov.va.api.health.r4.api.resources.RelatedPerson.Gender.male;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.RelatedPerson;
import gov.va.api.health.r4.api.resources.RelatedPerson.Communication;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleRelatedPersons {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Communication communication() {
    return Communication.builder().language(codeableConcept()).preferred(false).build();
  }

  public RelatedPerson relatedPerson() {
    return RelatedPerson.builder()
        .resourceType("RelatedPerson")
        .id("1234")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .active(true)
        .patient(reference())
        .relationship(singletonList(codeableConcept()))
        .name(singletonList(humanName()))
        .telecom(singletonList(contactPoint()))
        .gender(male)
        .birthDate("1998-09-10")
        .address(singletonList(address()))
        .photo(singletonList(attachment()))
        .period(period())
        .communication(singletonList(communication()))
        .build();
  }
}
