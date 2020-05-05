package gov.va.api.health.argonaut.api.resources;

import static gov.va.api.health.argonaut.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.argonaut.api.resources.Patient.Bundle;
import gov.va.api.health.argonaut.api.resources.Patient.Entry;
import gov.va.api.health.argonaut.api.samples.SampleKnownTypes;
import gov.va.api.health.argonaut.api.samples.SamplePatients;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import java.util.Collections;
import org.junit.Test;

public class PatientTest {
  private final SamplePatients data = SamplePatients.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildPatientBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://patient.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://patient.com/1"))
                        .build()))
            .resource(data.patient())
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
                        .url(("http://patient.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void patient() {
    assertRoundTrip(data.patient());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.patient())
        .fieldPrefix("deceased")
        .stringTypes(types.knownStringTypes())
        .knownTypes(types.knownTypes())
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.patient())
        .stringTypes(types.knownStringTypes())
        .knownTypes(types.knownTypes())
        .fieldPrefix("multipleBirth")
        .build()
        .verify();
  }
}
