package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.ExplanationOfBenefit;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Accident;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.AddItem;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.AddItemDetail;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.AddItemSubDetail;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Adjudication;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.BenefitBalance;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.CareTeam;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Diagnosis;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Financial;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Insurance;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Item;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.ItemDetail;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.ItemSubDetail;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Outcome;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Payee;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Payment;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Procedure;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.ProcessNote;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Related;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Status;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.SupportingInfo;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Total;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Type;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Use;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleExplanationOfBenefits {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Accident accident() {
    return Accident.builder()
        .date("2017-01-01")
        .type(codeableConcept())
        .locationAddress(address())
        .build();
  }

  public AddItem addItem() {
    return AddItem.builder()
        .itemSequence(singletonList("1"))
        .detailSequence(singletonList("1"))
        .subDetailSequence(singletonList("1"))
        .provider(singletonList(reference()))
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .programCode(singletonList(codeableConcept()))
        .servicedPeriod(period())
        .locationCodeableConcept(codeableConcept())
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor("10.0")
        .net(money())
        .bodySite(codeableConcept())
        .subSite(singletonList(codeableConcept()))
        .noteNumber(singletonList("1"))
        .adjudication(singletonList(adjudication()))
        .detail(singletonList(addItemDetail()))
        .build();
  }

  public AddItemDetail addItemDetail() {
    return AddItemDetail.builder()
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor("10.0")
        .net(money())
        .noteNumber(singletonList("1"))
        .adjudication(singletonList(adjudication()))
        .subDetail(singletonList(addItemSubDetail()))
        .build();
  }

  public AddItemSubDetail addItemSubDetail() {
    return AddItemSubDetail.builder()
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor("10.0")
        .net(money())
        .noteNumber(singletonList("1"))
        .adjudication(singletonList(adjudication()))
        .build();
  }

  public Adjudication adjudication() {
    return Adjudication.builder()
        .category(codeableConcept())
        .reason(codeableConcept())
        .amount(money())
        .value("10.0")
        .build();
  }

  public BenefitBalance benefitBalance() {
    return BenefitBalance.builder()
        .category(codeableConcept())
        .excluded(true)
        .name("name")
        .description("description")
        .network(codeableConcept())
        .unit(codeableConcept())
        .term(codeableConcept())
        .financial(singletonList(financial()))
        .build();
  }

  public CareTeam careTeam() {
    return CareTeam.builder()
        .sequence("1")
        .provider(reference())
        .responsible(false)
        .role(codeableConcept())
        .qualification(codeableConcept())
        .build();
  }

  public ItemDetail detail() {
    return ItemDetail.builder()
        .sequence("1")
        .revenue(codeableConcept())
        .category(codeableConcept())
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .programCode(singletonList(codeableConcept()))
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor("10.0")
        .net(money())
        .udi(singletonList(reference()))
        .noteNumber(singletonList("1"))
        .adjudication(singletonList(adjudication()))
        .subDetail(singletonList(itemSubDetail()))
        .build();
  }

  public Diagnosis diagnosis() {
    return Diagnosis.builder()
        .sequence("1")
        .diagnosisCodeableConcept(codeableConcept())
        .type(singletonList(codeableConcept()))
        .onAdmission(codeableConcept())
        .packageCode(codeableConcept())
        .build();
  }

  public ExplanationOfBenefit explanationOfBenefit() {
    return ExplanationOfBenefit.builder()
        .id("1234")
        .resourceType("CoverageEligibilityRequest")
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
        .fundsReserveRequested(codeableConcept())
        .fundsReserve(codeableConcept())
        .related(singletonList(related()))
        .prescription(reference())
        .originalPrescription(reference())
        .payee(payee())
        .referral(reference())
        .facility(reference())
        .claim(reference())
        .claimResponse(reference())
        .outcome(Outcome.complete)
        .disposition("disposition")
        .preAuthRef(singletonList("preAuthRef"))
        .preAuthRefPeriod(singletonList(period()))
        .careTeam(singletonList(careTeam()))
        .supportingInfo(singletonList(supportingInfo()))
        .diagnosis(singletonList(diagnosis()))
        .procedure(singletonList(procedure()))
        .precedence("1")
        .insurance(singletonList(insurance()))
        .accident(accident())
        .item(singletonList(item()))
        .addItem(singletonList(addItem()))
        .adjudication(singletonList(adjudication()))
        .total(singletonList(total()))
        .payment(payment())
        .formCode(codeableConcept())
        .form(attachment())
        .processNote(singletonList(processNote()))
        .benefitPeriod(period())
        .benefitBalance(singletonList(benefitBalance()))
        .build();
  }

  public Financial financial() {
    return Financial.builder()
        .type(codeableConcept())
        .allowedMoney(money())
        .usedMoney(money())
        .build();
  }

  public Insurance insurance() {
    return Insurance.builder()
        .focal(false)
        .coverage(reference())
        .preAuthRef(singletonList("preAuthRef"))
        .build();
  }

  public Item item() {
    return Item.builder()
        .sequence("1")
        .careTeamSequence(singletonList("1"))
        .diagnosisSequence(singletonList("1"))
        .procedureSequence(singletonList("1"))
        .informationSequence(singletonList("1"))
        .revenue(codeableConcept())
        .category(codeableConcept())
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .programCode(singletonList(codeableConcept()))
        .servicedPeriod(period())
        .locationReference(reference())
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor("10.0")
        .net(money())
        .udi(singletonList(reference()))
        .bodySite(codeableConcept())
        .subSite(singletonList(codeableConcept()))
        .encounter(singletonList(reference()))
        .noteNumber(singletonList("1"))
        .adjudication(singletonList(adjudication()))
        .detail(singletonList(detail()))
        .build();
  }

  public ItemSubDetail itemSubDetail() {
    return ItemSubDetail.builder()
        .sequence("1")
        .revenue(codeableConcept())
        .category(codeableConcept())
        .productOrService(codeableConcept())
        .modifier(singletonList(codeableConcept()))
        .programCode(singletonList(codeableConcept()))
        .quantity(simpleQuantity())
        .unitPrice(money())
        .factor("10.0")
        .net(money())
        .udi(singletonList(reference()))
        .noteNumber(singletonList("1"))
        .adjudication(singletonList(adjudication()))
        .build();
  }

  public Payee payee() {
    return Payee.builder().type(codeableConcept()).party(reference()).build();
  }

  public Payment payment() {
    return Payment.builder()
        .type(codeableConcept())
        .adjustment(money())
        .adjustmentReason(codeableConcept())
        .date("2017-01-01T00:00:00.000Z")
        .amount(money())
        .identifier(identifier())
        .build();
  }

  public Procedure procedure() {
    return Procedure.builder()
        .sequence("1")
        .type(singletonList(codeableConcept()))
        .date("2017-01-01T00:00:00.000Z")
        .procedureCodeableConcept(codeableConcept())
        .udi(singletonList(reference()))
        .build();
  }

  public ProcessNote processNote() {
    return ProcessNote.builder()
        .number("1")
        .type(Type.display)
        .text("text")
        .language(codeableConcept())
        .build();
  }

  public Related related() {
    return Related.builder()
        .claim(reference())
        .relationship(codeableConcept())
        .reference(identifier())
        .build();
  }

  public SupportingInfo supportingInfo() {
    return SupportingInfo.builder()
        .sequence("1")
        .category(codeableConcept())
        .code(codeableConcept())
        .timingPeriod(period())
        .valueBoolean(false)
        .reason(coding())
        .build();
  }

  public Total total() {
    return Total.builder().category(codeableConcept()).amount(money()).build();
  }
}
