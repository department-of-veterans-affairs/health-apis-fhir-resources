package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.ServiceRequest;
import gov.va.api.health.r4.api.resources.ServiceRequest.Intent;
import gov.va.api.health.r4.api.resources.ServiceRequest.Priority;
import gov.va.api.health.r4.api.resources.ServiceRequest.Status;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleServiceRequests {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public ServiceRequest serviceRequest() {
    return ServiceRequest.builder()
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
        .instantiatesCanonical(singletonList("canonical"))
        .instantiatesUri(singletonList("uri"))
        .basedOn(singletonList(reference()))
        .replaces(singletonList(reference()))
        .requisition(identifier())
        .status(Status.active)
        .intent(Intent.plan)
        .category(singletonList(codeableConcept()))
        .priority(Priority.asap)
        .doNotPerform("false")
        .code(codeableConcept())
        .orderDetail(singletonList(codeableConcept()))
        .quantityQuantity(quantity())
        .subject(reference())
        .encounter(reference())
        .occurrencePeriod(period())
        .asNeededBoolean("true")
        .authoredOn("2015-02-07T13:28:17-05:00")
        .requester(reference())
        .performerType(codeableConcept())
        .performer(singletonList(reference()))
        .locationCode(singletonList(codeableConcept()))
        .locationReference(singletonList(reference()))
        .reasonCode(singletonList(codeableConcept()))
        .reasonReference(singletonList(reference()))
        .insurance(singletonList(reference()))
        .supportingInfo(singletonList(reference()))
        .specimen(singletonList(reference()))
        .bodySite(singletonList(codeableConcept()))
        .note(singletonList(annotation()))
        .patientInstruction("patient instruction")
        .relevantHistory(singletonList(reference()))
        .build();
  }
}
