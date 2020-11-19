package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SampleQuestionnaireResponses;
import java.util.List;
import javax.validation.Validation;
import org.junit.jupiter.api.Test;

public class QuestionnaireResponseTest {
  private final SampleQuestionnaireResponses data = SampleQuestionnaireResponses.get();

  @Test
  public void bundlerCanBuildQuestionnaireResponseBundles() {
    QuestionnaireResponse.Entry entry =
        QuestionnaireResponse.Entry.builder()
            .extension(List.of(data.extension()))
            .fullUrl("http://questionnaireresponse.com")
            .id("123")
            .link(
                List.of(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://questionnaireresponse/1")
                        .build()))
            .resource(data.questionnaireResponse())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    QuestionnaireResponse.Bundle bundle =
        QuestionnaireResponse.Bundle.builder()
            .entry(List.of(entry))
            .link(
                List.of(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://questionnaireresponse.com/2")
                        .build()))
            .type(BundleType.searchset)
            .signature(data.signature())
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void invalid() {
    QuestionnaireResponse response = data.questionnaireResponse();
    response.item().get(0).answer(List.of(data.invalidAnswer()));
    assertThat(Validation.buildDefaultValidatorFactory().getValidator().validate(response))
        .isNotEmpty();
  }

  @Test
  public void questionnaireResponse() {
    assertRoundTrip(data.questionnaireResponse());
  }

  @Test
  public void valid() {
    assertThat(
            Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(data.questionnaireResponse()))
        .isEmpty();
  }
}
