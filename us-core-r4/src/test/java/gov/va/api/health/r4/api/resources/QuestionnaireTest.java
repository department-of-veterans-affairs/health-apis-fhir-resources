package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SampleQuestionnaires;
import java.util.List;
import javax.validation.Validation;
import org.junit.jupiter.api.Test;

public class QuestionnaireTest {
  private final SampleQuestionnaires data = SampleQuestionnaires.get();

  @Test
  public void bundlerCanBuildQuestionnaireBundles() {
    Questionnaire.Entry entry =
        Questionnaire.Entry.builder()
            .extension(List.of(data.extension()))
            .fullUrl("http://questionnaire.com")
            .id("123")
            .link(
                List.of(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://questionnaire/1"))
                        .build()))
            .resource(data.questionnaire())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Questionnaire.Bundle bundle =
        Questionnaire.Bundle.builder()
            .entry(List.of(entry))
            .link(
                List.of(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://questionnaire.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .signature(data.signature())
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void questionnaire() {
    assertRoundTrip(data.questionnaire());
  }

  @Test
  public void valid() {
    assertThat(
            Validation.buildDefaultValidatorFactory().getValidator().validate(data.questionnaire()))
        .isEmpty();
  }
}
