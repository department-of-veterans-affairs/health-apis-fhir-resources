package gov.va.api.health.dstu2.api.resources;

import gov.va.api.health.dstu2.api.RoundTrip;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.resources.Condition.Bundle;
import gov.va.api.health.dstu2.api.samples.SampleConditions;
import gov.va.api.health.dstu2.api.samples.SampleKnownTypes;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class ConditionTest {
  private final SampleConditions data = SampleConditions.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildConditionBundles() {
    Condition.Entry entry =
        Condition.Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://condition.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://condition/1"))
                        .build()))
            .resource(data.condition())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();

    Bundle bundle =
        Condition.Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://condition.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();

    RoundTrip.assertRoundTrip(bundle);
  }

  @Test
  public void condition() {
    RoundTrip.assertRoundTrip(data.condition());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.condition())
        .fieldPrefix("onset")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.condition())
        .fieldPrefix("abatement")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
