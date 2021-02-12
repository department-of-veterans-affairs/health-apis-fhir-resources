package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.Claim;
import gov.va.api.health.r4.api.resources.Claim.Accident;
import gov.va.api.health.r4.api.resources.Claim.CareTeam;
import gov.va.api.health.r4.api.resources.Claim.Diagnosis;
import gov.va.api.health.r4.api.resources.Claim.Insurance;
import gov.va.api.health.r4.api.resources.Claim.Item;
import gov.va.api.health.r4.api.resources.Claim.Item.Detail;
import gov.va.api.health.r4.api.resources.Claim.Item.Detail.SubDetail;
import gov.va.api.health.r4.api.resources.Claim.Payee;
import gov.va.api.health.r4.api.resources.Claim.Procedure;
import gov.va.api.health.r4.api.resources.Claim.Related;
import gov.va.api.health.r4.api.resources.Claim.Status;
import gov.va.api.health.r4.api.resources.Claim.SupportingInfo;
import gov.va.api.health.r4.api.resources.Claim.Use;
import java.math.BigDecimal;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleClaims {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  /*
   * Accident is missing optional 0..1 location[x] field
   */
  public Accident accident() {
    return Accident.builder().date("1905-08-23").type(codeableConcept()).build();
  }

  public Accident accidentWithLocationAddress() {
    return accident().locationAddress(address());
  }

  public Accident accidentWithLocationReference() {
    return accident().locationReference(reference());
  }

  public CareTeam careTeam() {
    return CareTeam.builder()
        .sequence(1)
        .provider(reference())
        .responsible(true)
        .role(codeableConcept())
        .qualification(codeableConcept())
        .build();
  }

  public Claim claim() {
    return Claim.builder()
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
        .status(Status.active)
        .type(codeableConcept())
        .subType(codeableConcept())
        .use(Use.claim)
        .patient(reference())
        .billablePeriod(period())
        .created("2017-01-01T00:00:00.000Z")
        .enterer(reference())
        .insurer(reference())
        .provider(reference())
        .priority(codeableConcept())
        .fundsReserve(codeableConcept())
        .related(singletonList(related()))
        .prescription(reference())
        .originalPrescription(reference())
        .payee(payee())
        .referral(reference())
        .facility(reference())
        .careTeam(singletonList(careTeam()))
        .supportingInfo(
            Arrays.asList(
                supportingInfo(),
                supportingInfoWithTimingDate(),
                supportingInfoWithTimingDateAndValueAttachment(),
                supportingInfoWithTimingDateAndValueQuantity(),
                supportingInfoWithTimingDateAndValueReference(),
                supportingInfoWithTimingDateAndValueString(),
                supportingInfoWithTimingDateAndValueBoolean(),
                supportingInfoWithTimingPeriod(),
                supportingInfoWithTimingPeriodAndValueAttachment(),
                supportingInfoWithTimingPeriodAndValueQuantity(),
                supportingInfoWithTimingPeriodAndValueReference(),
                supportingInfoWithTimingPeriodAndValueString(),
                supportingInfoWithTimingPeriodAndValueBoolean()))
        .diagnosis(Arrays.asList(diagnosisWithCodeableConcept(), diagnosisWithReference()))
        .procedure(Arrays.asList(procedureWithCodeableConcept(), procedureWithReference()))
        .insurance(singletonList(insurance()))
        .accident(accidentWithLocationAddress())
        .item(
            Arrays.asList(
                item(),
                itemWithServicedDateAndLocationAddress(),
                itemWithServicedDateAndLocationCodeableConcept(),
                itemWithServicedDateAndLocationReference(),
                itemWithServicedPeriodAndLocationAddress(),
                itemWithServicedPeriodAndLocationCodeableConcept(),
                itemWithServicedPeriodAndLocationReference()))
        .total(money())
        .build();
  }

  public Detail detail() {
    return Detail.builder()
        .sequence(1)
        .revenue(codeableConcept())
        .category(codeableConcept())
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .programCode(singletonList(codeableConcept()))
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor(new BigDecimal("1.0"))
        .net(money())
        .udi(singletonList(reference()))
        .subDetail(singletonList(subDetail()))
        .build();
  }

  public Diagnosis diagnosisWithCodeableConcept() {
    return incompleteDiagnosis().diagnosisCodeableConcept(codeableConcept());
  }

  public Diagnosis diagnosisWithReference() {
    return incompleteDiagnosis().diagnosisReference(reference());
  }

  /*
   * Diagnosis is missing required 1..1 diagnosis[x] field
   */
  private Diagnosis incompleteDiagnosis() {
    return Diagnosis.builder()
        .sequence(1)
        .type(singletonList(codeableConcept()))
        .onAdmission(codeableConcept())
        .packageCode(codeableConcept())
        .build();
  }

  /*
   * Procedure is missing required 1..1 procedure[x] field
   */
  private Procedure incompleteProcedure() {
    return Procedure.builder()
        .sequence(1)
        .type(singletonList(codeableConcept()))
        .date("2017-01-01T00:00:00.000Z")
        .udi(singletonList(reference()))
        .build();
  }

  public Insurance insurance() {
    return Insurance.builder()
        .sequence(1)
        .focal(true)
        .identifier(identifier())
        .coverage(reference())
        .businessArrangement("business arrangement")
        .preAuthRef(singletonList("pre auth ref"))
        .claimResponse(reference())
        .build();
  }

  /*
   * Item is missing optional serviced[x] and location[x] fields
   */
  public Item item() {
    return Item.builder()
        .sequence(1)
        .careTeamSequence(singletonList(1))
        .diagnosisSequence(singletonList(1))
        .procedureSequence(singletonList(1))
        .informationSequence(singletonList(1))
        .revenue(codeableConcept())
        .category(codeableConcept())
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .programCode(singletonList(codeableConcept()))
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor(new BigDecimal("1.0"))
        .net(money())
        .udi(singletonList(reference()))
        .bodySite(codeableConcept())
        .subSite(singletonList(codeableConcept()))
        .encounter(singletonList(reference()))
        .detail(singletonList(detail()))
        .build();
  }

  public Item itemWithServicedDateAndLocationAddress() {
    return item().servicedDate("1905-08-23").locationAddress(address());
  }

  public Item itemWithServicedDateAndLocationCodeableConcept() {
    return item().servicedDate("1905-08-23").locationCodeableConcept(codeableConcept());
  }

  public Item itemWithServicedDateAndLocationReference() {
    return item().servicedDate("1905-08-23").locationReference(reference());
  }

  public Item itemWithServicedPeriodAndLocationAddress() {
    return item().servicedPeriod(period()).locationAddress(address());
  }

  public Item itemWithServicedPeriodAndLocationCodeableConcept() {
    return item().servicedPeriod(period()).locationCodeableConcept(codeableConcept());
  }

  public Item itemWithServicedPeriodAndLocationReference() {
    return item().servicedPeriod(period()).locationReference(reference());
  }

  public Payee payee() {
    return Payee.builder().type(codeableConcept()).party(reference()).build();
  }

  public Procedure procedureWithCodeableConcept() {
    return incompleteProcedure().procedureCodeableConcept(codeableConcept());
  }

  public Procedure procedureWithReference() {
    return incompleteProcedure().procedureReference(reference());
  }

  public Related related() {
    return Related.builder()
        .claim(reference())
        .relationship(codeableConcept())
        .reference(identifier())
        .build();
  }

  public SubDetail subDetail() {
    return SubDetail.builder()
        .sequence(1)
        .revenue(codeableConcept())
        .category(codeableConcept())
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .programCode(singletonList(codeableConcept()))
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor(new BigDecimal("1.0"))
        .net(money())
        .udi(singletonList(reference()))
        .build();
  }

  /*
   * SupportingInfo is missing optional 0..1 timing[x] and 0..1 value[x] fields
   */
  public SupportingInfo supportingInfo() {
    return SupportingInfo.builder()
        .sequence(1)
        .category(codeableConcept())
        .code(codeableConcept())
        .reason(codeableConcept())
        .build();
  }

  public SupportingInfo supportingInfoWithTimingDate() {
    return supportingInfo().timingDate("1905-08-23");
  }

  public SupportingInfo supportingInfoWithTimingDateAndValueAttachment() {
    return supportingInfoWithTimingDate().valueAttachment(attachment());
  }

  public SupportingInfo supportingInfoWithTimingDateAndValueBoolean() {
    return supportingInfoWithTimingDate().valueBoolean(true);
  }

  public SupportingInfo supportingInfoWithTimingDateAndValueQuantity() {
    return supportingInfoWithTimingDate().valueQuantity(quantity());
  }

  public SupportingInfo supportingInfoWithTimingDateAndValueReference() {
    return supportingInfoWithTimingDate().valueReference(reference());
  }

  public SupportingInfo supportingInfoWithTimingDateAndValueString() {
    return supportingInfoWithTimingDate().valueString("value");
  }

  public SupportingInfo supportingInfoWithTimingPeriod() {
    return supportingInfo().timingPeriod(period());
  }

  public SupportingInfo supportingInfoWithTimingPeriodAndValueAttachment() {
    return supportingInfoWithTimingPeriod().valueAttachment(attachment());
  }

  public SupportingInfo supportingInfoWithTimingPeriodAndValueBoolean() {
    return supportingInfoWithTimingPeriod().valueBoolean(true);
  }

  public SupportingInfo supportingInfoWithTimingPeriodAndValueQuantity() {
    return supportingInfoWithTimingPeriod().valueQuantity(quantity());
  }

  public SupportingInfo supportingInfoWithTimingPeriodAndValueReference() {
    return supportingInfoWithTimingPeriod().valueReference(reference());
  }

  public SupportingInfo supportingInfoWithTimingPeriodAndValueString() {
    return supportingInfoWithTimingPeriod().valueString("value");
  }
}
