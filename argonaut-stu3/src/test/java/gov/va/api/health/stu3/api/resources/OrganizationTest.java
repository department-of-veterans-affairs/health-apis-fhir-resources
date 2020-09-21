package gov.va.api.health.stu3.api.resources;

import gov.va.api.health.stu3.api.RoundTrip;
import gov.va.api.health.stu3.api.bundle.AbstractBundle;
import gov.va.api.health.stu3.api.bundle.BundleLink;
import gov.va.api.health.stu3.api.resources.Organization.Bundle;
import gov.va.api.health.stu3.api.resources.Organization.Entry;
import gov.va.api.health.stu3.api.samples.SampleOrganizations;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class OrganizationTest {
  private final SampleOrganizations data = SampleOrganizations.get();

  @Test
  public void bundlerCanBuildOrganizationBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://organization.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://organization.com/1"))
                        .build()))
            .resource(data.organization())
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
                        .url(("http://organization.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();

    RoundTrip.assertRoundTrip(bundle);
  }

  @Test
  public void organization() {
    RoundTrip.assertRoundTrip(data.organization());
  }
}
