package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType.searchset;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.ExactlyOneOfVerifier;
import gov.va.api.health.r4.api.ZeroOrOneOfVerifier;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Bundle;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit.Entry;
import gov.va.api.health.r4.api.samples.SampleExplanationOfBenefits;
import org.junit.Test;

public class ExplanationOfBenefitsTest {

  private final SampleExplanationOfBenefits data = SampleExplanationOfBenefits.get();

  @Test
  public void bundlerCanBuildExplanationOfBenefitBundles() {
    Entry entry =
        Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://localhost")
            .id("123")
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://localhost/1"))
                        .build()))
            .resource(data.explanationOfBenefit())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Bundle bundle =
        Bundle.builder()
            .entry(singletonList(entry))
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://localhost/2"))
                        .build()))
            .type(searchset)
            .signature(data.signature())
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void explanationOfBenefits() {
    assertRoundTrip(data.explanationOfBenefit());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfo())
        .fieldPrefix("timing")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfo())
        .fieldPrefix("value")
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.diagnosis())
        .fieldPrefix("diagnosis")
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.procedure())
        .fieldPrefix("procedure")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder().sample(data.accident()).fieldPrefix("location").build().verify();
    ZeroOrOneOfVerifier.builder().sample(data.item()).fieldPrefix("serviced").build().verify();
    ZeroOrOneOfVerifier.builder().sample(data.item()).fieldPrefix("location").build().verify();
    ZeroOrOneOfVerifier.builder().sample(data.addItem()).fieldPrefix("serviced").build().verify();
    ZeroOrOneOfVerifier.builder().sample(data.addItem()).fieldPrefix("location").build().verify();
    ZeroOrOneOfVerifier.builder().sample(data.financial()).fieldPrefix("allowed").build().verify();
    ZeroOrOneOfVerifier.builder().sample(data.financial()).fieldPrefix("used").build().verify();
  }
}
