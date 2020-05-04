package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.uscorer4.api.resources.AllergyIntolerance;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public final class SampleAllergyIntolerances {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public AllergyIntolerance allergyIntolerance() {
    return AllergyIntolerance.builder()
        .id("2222")
        .meta(meta())
        .implicitRules("http://HelloRules.com")
        .language("Hello Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .clinicalStatus(codeableConcept())
        .verificationStatus(codeableConcept())
        .type(AllergyIntolerance.Type.allergy)
        .category(AllergyIntolerance.Category.food)
        .criticality(AllergyIntolerance.Criticality.high)
        .code(codeableConcept())
        .patient(reference())
        .encounter(reference())
        .onsetDateTime("2015-04-15T04:00:00Z")
        .recordedDate("2015-04-15T04:00:00Z")
        .recorder(reference())
        .asserter(reference())
        .lastOccurence("2015-04-15T04:00:00Z")
        .note(singletonList(annotation()))
        .reaction(singletonList(reaction()))
        .build();
  }

  public AllergyIntolerance.Reaction reaction() {
    return AllergyIntolerance.Reaction.builder()
        .id("1111")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .substance(codeableConcept())
        .manifestation(singletonList(codeableConcept()))
        .description("Test Reaction Description")
        .onset("2015-04-15T04:00:00Z")
        .severity(AllergyIntolerance.Severity.severe)
        .exposureRoute(codeableConcept())
        .note(singletonList(annotation()))
        .build();
  }
}
