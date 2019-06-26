package gov.va.api.health.stu3.api.resources;

import gov.va.api.health.stu3.api.RoundTrip;
import gov.va.api.health.stu3.api.bundle.AbstractBundle;
import gov.va.api.health.stu3.api.bundle.BundleLink;
import gov.va.api.health.stu3.api.samples.SampleLocations;
import java.util.Collections;
import org.junit.Test;

public class LocationTest {
  private final SampleLocations data = SampleLocations.get();

  @Test
  public void bundlerCanBuildLocationBundles() {
    Location.Entry entry =
        Location.Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://location.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://location.com/1"))
                        .build()))
            .resource(data.location())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();

    Location.Bundle bundle =
        Location.Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://location.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();

    RoundTrip.assertRoundTrip(bundle);
  }

  @Test
  public void location() {
    RoundTrip.assertRoundTrip(data.location());
  }
}
