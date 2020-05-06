package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.uscorer4.api.resources.Condition;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleConditions {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Condition condition() {
    return Condition.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .clinicalStatus(codeableConcept())
        .verificationStatus(codeableConcept())
        .category(singletonList(codeableConcept()))
        .severity(codeableConcept())
        .bodySite(singletonList(codeableConcept()))
        .subject(reference())
        .encounter(reference())
        .onsetDateTime("2015-04-15T04:00:00Z")
        .abatementDateTime("2015-04-15T04:00:00Z")
        .recordedDate("2015-04-15T04:00:00Z")
        .recorder(reference())
        .asserter(reference())
        .stage(singletonList(stage()))
        .evidence(singletonList(evidence()))
        .note(singletonList(annotation()))
        .build();
  }

  public Condition.Evidence evidence() {
    return Condition.Evidence.builder()
        .id("9012")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .code(singletonList(codeableConcept()))
        .detail(singletonList(reference()))
        .build();
  }

  public Condition.Stage stage() {
    return Condition.Stage.builder()
        .id("5678")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .summary(codeableConcept())
        .assessment(singletonList(reference()))
        .type(codeableConcept())
        .build();
  }
}
