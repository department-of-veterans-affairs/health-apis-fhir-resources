package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType.searchset;
import static java.util.Collections.singletonList;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SampleQuestionnaires;
import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import org.junit.Test;

public class QuestionnaireTest {
  private final SampleQuestionnaires data = SampleQuestionnaires.get();

  //private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void questionnaire() {
    assertRoundTrip(data.questionnaire());
  }

  @Test
  public void bundlerCanBuildQuestionnaireBundles() {
    Questionnaire.Entry entry =
        Questionnaire.Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://questionnaire.com")
            .id("123")
            .link(
                singletonList(
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
            .entry(singletonList(entry))
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://questionnaire.com/2"))
                        .build()))
            .type(searchset)
            .signature(data.signature())
            .build();
    assertRoundTrip(bundle);
  }
}
