package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SampleDevices;
import org.junit.jupiter.api.Test;

public class DeviceTest {
  private final SampleDevices samples = SampleDevices.get();

  @Test
  public void bundlerCanBuildDeviceBundles() {
    Device.Bundle bundle =
        Device.Bundle.builder()
            .type(BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://device.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    Device.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://device.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://device.com/entry")
                                    .build()))
                        .resource(samples.device())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void device() {
    assertRoundTrip(samples.device());
  }
}
