package gov.va.api.health.r4.api.samples;

import gov.va.api.health.r4.api.resources.QuestionnaireResponse;
import gov.va.api.health.r4.api.resources.QuestionnaireResponse.Status;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleQuestionnaireResponses {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public QuestionnaireResponse.Answer invalidAnswer() {
    return QuestionnaireResponse.Answer.builder()
        .id("99")
        .extension(List.of(extension(), extension()))
        .modifierExtension(List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
        .build();
  }

  public List<QuestionnaireResponse.Item> items() {
    return List.of(
        QuestionnaireResponse.Item.builder()
            .id("1")
            .extension(List.of(extension(), extension()))
            .modifierExtension(List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
            .linkId("1")
            .definition("definition")
            .text("text")
            .answer(
                List.of(
                    QuestionnaireResponse.Answer.builder()
                        .id("11")
                        .extension(List.of(extension(), extension()))
                        .modifierExtension(
                            List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
                        .valueString("a string answer")
                        .build()))
            .build(),
        QuestionnaireResponse.Item.builder()
            .id("2")
            .extension(List.of(extension(), extension()))
            .modifierExtension(List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
            .linkId("2")
            .definition("definition")
            .text("text")
            .answer(
                List.of(
                    QuestionnaireResponse.Answer.builder()
                        .id("21")
                        .extension(List.of(extension(), extension()))
                        .modifierExtension(
                            List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
                        .valueInteger(42)
                        .build()))
            .build());
  }

  public QuestionnaireResponse questionnaireResponse() {
    return QuestionnaireResponse.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(List.of(resource()))
        .extension(List.of(extension(), extension()))
        .modifierExtension(List.of(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(identifier())
        .basedOn(List.of(reference()))
        .partOf(List.of(reference()))
        .questionnaire("http://hl7.org/fhir/Questionnaire/3141")
        .status(Status.completed)
        .subject(reference())
        .encounter(reference())
        .authored("2015-04-15T04:00:00Z")
        .author(reference())
        .source(reference())
        .item(items())
        .build();
  }
}
