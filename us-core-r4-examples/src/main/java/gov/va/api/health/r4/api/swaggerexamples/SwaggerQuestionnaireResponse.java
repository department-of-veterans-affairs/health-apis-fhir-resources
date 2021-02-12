package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.QuestionnaireResponse;
import gov.va.api.health.r4.api.resources.QuestionnaireResponse.Status;
import java.util.List;

public class SwaggerQuestionnaireResponse {
  /**
   * An example QuestionnaireResponse.
   *
   * @return an example QuestionnaireResponse.
   */
  public static QuestionnaireResponse questionnaireResponse() {
    return QuestionnaireResponse.builder()
        .id("3141")
        .status(Status.completed)
        .subject(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/fhir/v0/r4/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .author(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/fhir/v0/r4/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .authored("2013-02-19T14:15:00Z")
        .item(
            List.of(
                QuestionnaireResponse.Item.builder()
                    .linkId("1")
                    .item(
                        List.of(
                            QuestionnaireResponse.Item.builder()
                                .linkId("1.1")
                                .answer(
                                    List.of(
                                        QuestionnaireResponse.Answer.builder()
                                            .valueCoding(
                                                Coding.builder()
                                                    .system(
                                                        "http://cancer.questionnaire.org/system/code/yesno")
                                                    .code("1")
                                                    .display("Yes")
                                                    .build())
                                            .build()))
                                .build(),
                            QuestionnaireResponse.Item.builder()
                                .linkId("1.2")
                                .answer(
                                    List.of(
                                        QuestionnaireResponse.Answer.builder()
                                            .valueCoding(
                                                Coding.builder()
                                                    .system(
                                                        "http://cancer.questionnaire.org/system/code/yesno")
                                                    .code("1")
                                                    .display("Yes")
                                                    .build())
                                            .build()))
                                .build()))
                    .build()))
        .build();
  }

  /**
   * An example QuestionnaireResponse.Bundle.
   *
   * @return an example QuestionnaireResponse.Bundle.
   */
  public static QuestionnaireResponse.Bundle questionnaireResponseBundle() {
    return QuestionnaireResponse.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/v0/QuestionnaireResponse?_id=3141&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/v0/QuestionnaireResponse?_id=3141&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/v0/QuestionnaireResponse?_id=3141&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                QuestionnaireResponse.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/v0/QuestionnaireResponse/3141")
                    .resource(questionnaireResponse())
                    .search(
                        AbstractEntry.Search.builder().mode(AbstractEntry.SearchMode.match).build())
                    .build()))
        .build();
  }
}
