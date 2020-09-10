package gov.va.api.health.dstu2.api.resources;

import static gov.va.api.health.dstu2.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.dstu2.api.bundle.AbstractBundle;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.samples.SamplePractitioners;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class PractitionerTest {
  private final SamplePractitioners data = SamplePractitioners.get();

  @Test
  public void bundlerCanBuildMedicationBundles() {
    Practitioner.Entry entry =
        Practitioner.Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://practitioner.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://practitioner.com/1"))
                        .build()))
            .resource(data.practitioner())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();

    Practitioner.Bundle bundle =
        Practitioner.Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://practitioner.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void practitioner() {
    assertRoundTrip(data.practitioner());
  }
}
