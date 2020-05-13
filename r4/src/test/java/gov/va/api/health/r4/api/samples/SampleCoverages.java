package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.Coverage;
import gov.va.api.health.r4.api.resources.Coverage.CostToBeneficiary;
import gov.va.api.health.r4.api.resources.Coverage.CoverageClass;
import gov.va.api.health.r4.api.resources.Coverage.Exception;
import gov.va.api.health.r4.api.resources.Coverage.Status;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleCoverages {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public CostToBeneficiary costToBeneficiaryWithValueMoney() {
    return CostToBeneficiary.builder()
        .type(codeableConcept())
        .valueMoney(money())
        .exception(singletonList(exception()))
        .build();
  }

  public CostToBeneficiary costToBeneficiaryWithValueQuantity() {
    return CostToBeneficiary.builder()
        .type(codeableConcept())
        .valueQuantity(simpleQuantity())
        .exception(singletonList(exception()))
        .build();
  }

  public CoverageClass coverageClass() {
    return CoverageClass.builder()
        .type(codeableConcept())
        .value("Hello value")
        .name("Hello name")
        .build();
  }

  public Coverage coverageWithValueMoney() {
    return Coverage.builder()
        .id("1324")
        .resourceType("Coverage")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .status(Status.active)
        .type(codeableConcept())
        .policyHolder(reference())
        .subscriber(reference())
        .subscriberId("Hello subscriberId")
        .beneficiary(reference())
        .dependent("Hello dependent")
        .relationship(codeableConcept())
        .period(period())
        .payor(singletonList(reference()))
        .coverageClass(singletonList(coverageClass()))
        .order(1)
        .network("Hello network")
        .costToBeneficiary(singletonList(costToBeneficiaryWithValueMoney()))
        .subrogation(true)
        .contract(singletonList(reference()))
        .build();
  }

  public Coverage coverageWithValueQuanitity() {
    return Coverage.builder()
        .id("1324")
        .resourceType("Coverage")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .status(Status.active)
        .type(codeableConcept())
        .policyHolder(reference())
        .subscriber(reference())
        .subscriberId("Hello subscriberId")
        .beneficiary(reference())
        .dependent("Hello dependent")
        .relationship(codeableConcept())
        .period(period())
        .payor(singletonList(reference()))
        .coverageClass(singletonList(coverageClass()))
        .order(1)
        .network("Hello network")
        .costToBeneficiary(singletonList(costToBeneficiaryWithValueQuantity()))
        .subrogation(true)
        .contract(singletonList(reference()))
        .build();
  }

  public Exception exception() {
    return Exception.builder().type(codeableConcept()).period(period()).build();
  }
}
