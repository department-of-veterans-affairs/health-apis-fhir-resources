package gov.va.api.health.r4.api.samples;

import gov.va.api.health.r4.api.resources.Questionnaire;
import gov.va.api.health.r4.api.resources.Questionnaire.QuestionnaireItemOperator;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleQuestionnaires {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Questionnaire questionnaire() {
    return Questionnaire.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(List.of(resource()))
        .extension(List.of(extension(), extension()))
        .modifierExtension(List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
        .url("http://hl7.org/fhir/Questionnaire/3141")
        .identifier(List.of(identifier()))
        .version("3.14")
        .name("Cancer Quality Forum Questionnaire 2012")
        .title("Cancer Quality Forum Questionnaire 2012")
        .derivedFrom(List.of("Patient"))
        .status(Questionnaire.PublicationStatus.draft)
        .experimental(true)
        .subjectType(List.of("Patient"))
        .description("Sample Questionnaire Description")
        .date("2012-01")
        .publisher("abc")
        .contact(List.of(contactDetail()))
        .description("description")
        .useContext(List.of(usageContext()))
        .jurisdiction(List.of(codeableConcept()))
        .purpose("purpose")
        .copyright("copyright")
        .approvalDate("2012-01")
        .lastReviewDate("2012-01")
        .effectivePeriod(period())
        .code(List.of(coding()))
        .item(
            List.of(
                Questionnaire.Item.builder()
                    .id("1")
                    .extension(List.of(extension(), extension()))
                    .modifierExtension(
                        List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
                    .linkId("1")
                    .definition("definition")
                    .code(List.of(coding()))
                    .prefix("prefix")
                    .text("text")
                    .type(Questionnaire.QuestionnaireItemType.group)
                    .enableBehavior(Questionnaire.EnableWhenBehavior.all)
                    .required(true)
                    .repeats(false)
                    .readOnly(false)
                    .maxLength(2000)
                    .answerValueSet("answers")
                    .answerOption(
                        List.of(
                            Questionnaire.AnswerOption.builder()
                                .valueString("x")
                                .initialSelected(false)
                                .build()))
                    .initial(List.of(Questionnaire.Initial.builder().valueString("x").build()))
                    .item(
                        List.of(
                            Questionnaire.Item.builder()
                                .linkId("1.1")
                                .code(List.of(coding()))
                                .prefix("1")
                                .type(Questionnaire.QuestionnaireItemType.choice)
                                .answerValueSet("http://hl7.org/fhir/ValueSet/yesnodontknow")
                                .item(
                                    List.of(
                                        Questionnaire.Item.builder()
                                            .linkId("1.1.1")
                                            .code(List.of(coding()))
                                            .type(Questionnaire.QuestionnaireItemType.group)
                                            .enableWhen(
                                                List.of(
                                                    Questionnaire.EnableWhen.builder()
                                                        .question("1.1")
                                                        .operator(QuestionnaireItemOperator.equals)
                                                        .answerCoding(coding())
                                                        .build()))
                                            .item(
                                                List.of(
                                                    Questionnaire.Item.builder()
                                                        .linkId("1.1.1.1")
                                                        .code(List.of(coding()))
                                                        .prefix("1.1")
                                                        .type(
                                                            Questionnaire.QuestionnaireItemType
                                                                .choice)
                                                        .answerValueSet(
                                                            "http://hl7.org/fhir/ValueSet/yesnodontknow")
                                                        .item(
                                                            List.of(
                                                                Questionnaire.Item.builder()
                                                                    .linkId("1.1.1.1.1")
                                                                    .code(List.of(coding()))
                                                                    .prefix("1.1.1")
                                                                    .type(
                                                                        Questionnaire
                                                                            .QuestionnaireItemType
                                                                            .choice)
                                                                    .answerValueSet(
                                                                        "http://hl7.org/fhir/ValueSet/yesnodontknow")
                                                                    .build(),
                                                                Questionnaire.Item.builder()
                                                                    .linkId("1.1.1.1.2")
                                                                    .code(List.of(coding()))
                                                                    .prefix("1.1.2")
                                                                    .type(
                                                                        Questionnaire
                                                                            .QuestionnaireItemType
                                                                            .choice)
                                                                    .answerValueSet(
                                                                        "http://hl7.org/fhir/ValueSet/yesnodontknow")
                                                                    .build()))
                                                        .build(),
                                                    Questionnaire.Item.builder()
                                                        .linkId("1.1.1.2")
                                                        .code(List.of(coding()))
                                                        .prefix("1.2")
                                                        .type(
                                                            Questionnaire.QuestionnaireItemType
                                                                .choice)
                                                        .answerValueSet(
                                                            "http://hl7.org/fhir/ValueSet/yesnodontknow")
                                                        .build()))
                                            .build()))
                                .build()))
                    .build(),
                Questionnaire.Item.builder()
                    .id("2")
                    .extension(List.of(extension(), extension()))
                    .modifierExtension(
                        List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
                    .linkId("2")
                    .definition("definition")
                    .code(List.of(coding()))
                    .prefix("prefix")
                    .text("text")
                    .type(Questionnaire.QuestionnaireItemType.group)
                    .enableBehavior(Questionnaire.EnableWhenBehavior.all)
                    .required(true)
                    .repeats(false)
                    .readOnly(false)
                    .maxLength(2000)
                    .answerValueSet("answers")
                    .item(
                        List.of(
                            Questionnaire.Item.builder()
                                .linkId("2.1")
                                .code(List.of(coding()))
                                .type(Questionnaire.QuestionnaireItemType.group)
                                .item(
                                    List.of(
                                        Questionnaire.Item.builder()
                                            .linkId("2.1.2")
                                            .code(List.of(coding()))
                                            .type(Questionnaire.QuestionnaireItemType.choice)
                                            .build()))
                                .build()))
                    .build()))
        .build();
  }
}
