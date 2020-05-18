package gov.va.api.health.uscorer4.api.resources;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.uscorer4.api.samples.SampleImmunizations;
import org.junit.jupiter.api.Test;

import static gov.va.api.health.uscorer4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

public class ImmunizationTest {
  private final SampleImmunizations samples = SampleImmunizations.get();

  @Test
  void bundlerCanBuildImmunizationBundles() {
    Immunization.Bundle bundle =
        Immunization.Bundle.builder()
            .type(AbstractBundle.BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://immunization.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    Immunization.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://immunization.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://immunization.com/entry")
                                    .build()))
                        .resource(samples.immunization())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  void immunization() {
    assertRoundTrip(samples.immunization());
  }
}
