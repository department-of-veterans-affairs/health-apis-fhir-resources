package gov.va.api.health.dstu2.api.resources;

import static gov.va.api.health.dstu2.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.resources.Encounter.Bundle;
import gov.va.api.health.dstu2.api.resources.Encounter.Entry;
import gov.va.api.health.dstu2.api.samples.SampleEncounters;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class EncounterTest {
  private final SampleEncounters data = SampleEncounters.get();

  @Test
  public void bundlerCanBuildEncounterBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://encounter.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://encounter.com/1"))
                        .build()))
            .resource(data.encounter())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();

    Bundle bundle =
        Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://encounter.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void encounter() {
    assertRoundTrip(data.encounter());
  }
}
