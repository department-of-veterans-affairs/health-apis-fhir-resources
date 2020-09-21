package gov.va.api.health.stu3.api.resources;

import gov.va.api.health.stu3.api.RoundTrip;
import gov.va.api.health.stu3.api.bundle.AbstractBundle;
import gov.va.api.health.stu3.api.bundle.BundleLink;
import gov.va.api.health.stu3.api.samples.SampleEndpoints;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class EndpointTest {

  private final SampleEndpoints data = SampleEndpoints.get();

  @Test
  public void Endpoint() {
    RoundTrip.assertRoundTrip(data.endpoint());
  }

  @Test
  public void bundlerCanBuildEndpointBundles() {
    Endpoint.Entry entry =
        Endpoint.Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://Endpoint.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://Endpoint.com/1"))
                        .build()))
            .resource(data.endpoint())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Endpoint.Bundle bundle =
        Endpoint.Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://Endpoint.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();
    RoundTrip.assertRoundTrip(bundle);
  }
}
