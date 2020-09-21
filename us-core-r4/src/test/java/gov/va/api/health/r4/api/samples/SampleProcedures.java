package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.Procedure;
import gov.va.api.health.r4.api.resources.Procedure.FocalDevice;
import gov.va.api.health.r4.api.resources.Procedure.Performer;
import gov.va.api.health.r4.api.resources.Procedure.Status;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleProcedures {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public FocalDevice focalDevice() {
    return FocalDevice.builder()
        .id("987")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .action(codeableConcept())
        .manipulated(reference())
        .build();
  }

  public Performer performer() {
    return Performer.builder()
        .id("987")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .actor(reference())
        .function(codeableConcept())
        .onBehalfOf(reference())
        .build();
  }

  public Procedure procedure() {
    return Procedure.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .instantiatesCanonical(singletonList(reference()))
        .instantiatesUri("http://InstantiateGoodnight.com")
        .basedOn(singletonList(reference()))
        .partOf(singletonList(reference()))
        .status(Status.completed)
        .statusReason(codeableConcept())
        .category(codeableConcept())
        .code(codeableConcept())
        .subject(reference())
        .encounter(reference())
        .performedDateTime("2015-04-15T04:00:00Z")
        .recorder(reference())
        .asserter(reference())
        .performer(singletonList(performer()))
        .location(reference())
        .reasonCode(singletonList(codeableConcept()))
        .reasonReference(singletonList(reference()))
        .bodySite(singletonList(codeableConcept()))
        .outcome(codeableConcept())
        .report(singletonList(reference()))
        .complication(singletonList(codeableConcept()))
        .complicationDetail(singletonList(reference()))
        .followUp(singletonList(codeableConcept()))
        .note(singletonList(annotation()))
        .focalDevice(singletonList(focalDevice()))
        .usedReference(singletonList(reference()))
        .usedCode(singletonList(codeableConcept()))
        .build();
  }
}
