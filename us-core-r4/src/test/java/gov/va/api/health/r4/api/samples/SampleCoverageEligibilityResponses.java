package gov.va.api.health.r4.api.samples;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.DataAbsentReason;
import gov.va.api.health.r4.api.DataAbsentReason.Reason;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Benefit;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.CoverageEligibilityResponseError;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Insurance;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Item;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Outcome;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Purpose;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Status;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleCoverageEligibilityResponses {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Benefit benefit() {
    return Benefit.builder()
        .type(codeableConcept())
        .allowedString("Allowed String")
        .usedMoney(money())
        .build();
  }

  public CoverageEligibilityResponse coverageEligibilityResponse() {
    return CoverageEligibilityResponse.builder()
        .id("1324")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(asList(extension(), extension()))
        .modifierExtension(asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .status(Status.active)
        .purpose(singletonList(Purpose.benefits))
        .patient(reference())
        .servicedPeriod(period())
        .created("2015-04-15T04:00:00Z")
        .requestor(reference())
        .request(reference())
        .outcome(Outcome.complete)
        .disposition("Hello disposition")
        .insurer(reference())
        .insurance(singletonList(insurance()))
        .preAuthRef("Hello preAuthRef")
        .form(codeableConcept())
        .error(singletonList(error()))
        .build();
  }

  public CoverageEligibilityResponse coverageEligibilityResponseWithDataAbsentReason() {
    return coverageEligibilityResponse()
        ._request(DataAbsentReason.of(Reason.unsupported))
        .request(null);
  }

  public CoverageEligibilityResponseError error() {
    return CoverageEligibilityResponseError.builder().code(codeableConcept()).build();
  }

  public Insurance insurance() {
    return Insurance.builder()
        .coverage(reference())
        .inforce(true)
        .benefitPeriod(period())
        .item(singletonList(item()))
        .build();
  }

  public Insurance insuranceWithDataAbsentReason() {
    return insurance().coverage(null)._coverage(DataAbsentReason.of(Reason.unsupported));
  }

  public Item item() {
    return Item.builder()
        .category(codeableConcept())
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .provider(reference())
        .excluded(false)
        .name("Hello name")
        .description("Hello description")
        .network(codeableConcept())
        .unit(codeableConcept())
        .term(codeableConcept())
        .benefit(singletonList(benefit()))
        .authorizationRequired(true)
        .authorizationSupporting(singletonList(codeableConcept()))
        .authorizationUrl("http://example.com")
        .build();
  }
}
