package gov.va.api.health.dstu3.api.resources;

import static gov.va.api.health.dstu3.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.dstu3.api.bundle.AbstractBundle;
import gov.va.api.health.dstu3.api.bundle.BundleLink;
import gov.va.api.health.dstu3.api.samples.SamplePractitionerRoles;
import java.util.Collections;
import org.junit.Test;

public class PractitionerRoleTest {
  private final SamplePractitionerRoles data = SamplePractitionerRoles.get();

  @Test
  public void bundlerCanBuildMedicationBundles() {
    PractitionerRole.Entry entry =
        PractitionerRole.Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://practitionerRole.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://practitionerRole.com/1"))
                        .build()))
            .resource(data.practitionerRole())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();

    PractitionerRole.Bundle bundle =
        PractitionerRole.Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://practitionerRole.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void practitionerRole() {
    assertRoundTrip(data.practitionerRole());
  }
}
