package gov.va.api.health.r4.api.resources;

import gov.va.api.health.r4.api.RoundTrip;
import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.resources.AllergyIntolerance.Bundle;
import gov.va.api.health.r4.api.resources.AllergyIntolerance.Entry;
import gov.va.api.health.r4.api.samples.SampleAllergyIntolerances;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class AllergyIntoleranceTest {
  private final SampleAllergyIntolerances data = SampleAllergyIntolerances.get();

  @Test
  public void allergyIntolerance() {
    RoundTrip.assertRoundTrip(data.allergyIntolerance());
  }

  @Test
  public void bundlerCanBuildAllergyIntoleranceBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://AllergyIntolerance.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://AllergyIntolerance.com/1"))
                        .build()))
            .resource(data.allergyIntolerance())
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
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://AllergyIntolerance.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();

    RoundTrip.assertRoundTrip(bundle);
  }
}
