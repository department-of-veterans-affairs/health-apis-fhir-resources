package gov.va.api.health.dstu2.api.resources;

import gov.va.api.health.dstu2.api.RoundTrip;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.elements.Extension;
import gov.va.api.health.dstu2.api.resources.Immunization.Bundle;
import gov.va.api.health.dstu2.api.resources.Immunization.Entry;
import gov.va.api.health.dstu2.api.samples.SampleImmunizations;
import gov.va.api.health.dstu2.api.samples.SampleKnownTypes;
import gov.va.api.health.validation.api.ExactlyOneOfExtensionVerifier;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class ImmunizationTest {
  private final SampleImmunizations data = SampleImmunizations.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildImmunizationBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://immunization.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://immunization.com/1"))
                        .build()))
            .resource(data.immunization())
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
                        .url(("http://immunization.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();

    RoundTrip.assertRoundTrip(bundle);

    AbstractEntry.Search.builder().build().id();
  }

  @Test
  public void immunization() {
    RoundTrip.assertRoundTrip(data.immunization());
    RoundTrip.assertRoundTrip(data.immunizationWithDataAbsentReasons());
  }

  @Test
  public void relatedFields() {
    ExactlyOneOfExtensionVerifier.builder()
        .sample(data.immunization())
        .field("status")
        .extensionClass(Extension.class)
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ExactlyOneOfExtensionVerifier.builder()
        .sample(data.immunization())
        .field("reported")
        .extensionClass(Extension.class)
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
