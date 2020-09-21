package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.MedicationRequest;
import gov.va.api.health.r4.api.resources.MedicationRequest.DispenseRequest;
import gov.va.api.health.r4.api.resources.MedicationRequest.DispenseRequest.InitialFill;
import gov.va.api.health.r4.api.resources.MedicationRequest.Intent;
import gov.va.api.health.r4.api.resources.MedicationRequest.Priority;
import gov.va.api.health.r4.api.resources.MedicationRequest.Status;
import gov.va.api.health.r4.api.resources.MedicationRequest.Substitution;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleMedicationRequests {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public DispenseRequest dispenseRequest() {
    return DispenseRequest.builder()
        .initialFill(InitialFill.builder().quantity(simpleQuantity()).duration(duration()).build())
        .dispenseInterval(duration())
        .validityPeriod(period())
        .numberOfRepeatsAllowed("1")
        .quantity(simpleQuantity())
        .expectedSupplyDuration(duration())
        .performer(reference())
        .build();
  }

  public MedicationRequest medicationRequest() {
    return MedicationRequest.builder()
        .id("1234")
        .resourceType("Claim")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .status(Status.entered_in_error)
        .statusReason(codeableConcept())
        .intent(Intent.original_order)
        .category(singletonList(codeableConcept()))
        .priority(Priority.urgent)
        .doNotPerform(true)
        .reportedBoolean(true)
        .medicationCodeableConcept(codeableConcept())
        .subject(reference())
        .encounter(reference())
        .supportingInformation(singletonList(reference()))
        .authoredOn("2015-02-07T13:28:17-05:00")
        .requester(reference())
        .performer(reference())
        .performerType(codeableConcept())
        .recorder(reference())
        .reasonCode(singletonList(codeableConcept()))
        .reasonReference(singletonList(reference()))
        .instantiatesCanonical(singletonList("canonical"))
        .instantiatesUri(singletonList("uri"))
        .basedOn(singletonList(reference()))
        .groupIdentifier(identifier())
        .courseOfTherapyType(codeableConcept())
        .insurance(singletonList(reference()))
        .note(singletonList(annotation()))
        .dosageInstruction(singletonList(dosage()))
        .dispenseRequest(dispenseRequest())
        .substitution(substitution())
        .priorPrescription(reference())
        .detectedIssue(singletonList(reference()))
        .eventHistory(singletonList(reference()))
        .build();
  }

  public Substitution substitution() {
    return Substitution.builder().allowedBoolean(true).reason(codeableConcept()).build();
  }
}
