package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.DataAbsentReason;
import gov.va.api.health.r4.api.resources.MedicationRequest;
import gov.va.api.health.r4.api.resources.MedicationRequest.DispenseRequest;
import gov.va.api.health.r4.api.resources.MedicationRequest.DispenseRequest.InitialFill;
import gov.va.api.health.r4.api.resources.MedicationRequest.Substitution;
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
        .numberOfRepeatsAllowed(41)
        .quantity(simpleQuantity())
        .expectedSupplyDuration(duration())
        .performer(reference())
        .build();
  }

  public MedicationRequest medicationRequest() {
    return MedicationRequest.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .status(MedicationRequest.Status.active)
        .statusReason(codeableConcept())
        .intent(MedicationRequest.Intent.original_order)
        .category(singletonList(codeableConcept()))
        .priority(MedicationRequest.Priority.asap)
        .doNotPerform(true)
        .reportedBoolean(true)
        .medicationCodeableConcept(codeableConcept())
        .subject(reference())
        .encounter(reference())
        .supportingInformation(singletonList(reference()))
        .authoredOn("2015-04-15T04:00:00Z")
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

  public MedicationRequest medicationRequestWithDataAbsentReason() {
    return medicationRequest()
        ._requester(DataAbsentReason.of(DataAbsentReason.Reason.unknown))
        .requester(null);
  }

  public Substitution substitution() {
    return Substitution.builder().allowedBoolean(true).reason(codeableConcept()).build();
  }
}
