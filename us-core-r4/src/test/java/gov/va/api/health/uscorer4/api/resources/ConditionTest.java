package gov.va.api.health.uscorer4.api.resources;

import static gov.va.api.health.uscorer4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.uscorer4.api.samples.SampleConditions;
import org.junit.Test;

public class ConditionTest {
  private final SampleConditions samples = SampleConditions.get();

  @Test
  public void bundlerCanBuildConditionBundles() {
    Condition.Bundle bundle =
        Condition.Bundle.builder()
            .type(AbstractBundle.BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://condition.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    Condition.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://condition.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://condition.com/entry")
                                    .build()))
                        .resource(samples.condition())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void condition() {
    assertRoundTrip(samples.condition());
  }
}
