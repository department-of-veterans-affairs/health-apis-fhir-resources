package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SamplePractitionerRoles;
import org.junit.Test;

public class PractitionerRoleTest {
  private final SamplePractitionerRoles samples = SamplePractitionerRoles.get();

  @Test
  public void bundlerCanBuildPractitionerRoleBundles() {
    PractitionerRole.Bundle bundle =
        PractitionerRole.Bundle.builder()
            .type(BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://practitionerrole.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    PractitionerRole.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://practitionerrole.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://practitionerrole.com/entry")
                                    .build()))
                        .resource(samples.practitionerRole())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void practitionerRole() {
    assertRoundTrip(samples.practitionerRole());
  }
}
