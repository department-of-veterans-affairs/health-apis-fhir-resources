package gov.va.api.health.uscorer4.api.resources;

import static gov.va.api.health.uscorer4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.uscorer4.api.samples.SampleKnownTypes;
import gov.va.api.health.uscorer4.api.samples.SampleMedicationRequests;
import gov.va.api.health.validation.api.ExactlyOneOfVerifier;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import org.junit.jupiter.api.Test;

public class MedicationRequestTest {
  private final SampleMedicationRequests samples = SampleMedicationRequests.get();

  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  void bundlerCanBuildMedicationRequestBundles() {
    MedicationRequest.Bundle bundle =
        MedicationRequest.Bundle.builder()
            .type(AbstractBundle.BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://medicationrequest.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    MedicationRequest.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://medicationrequest.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://medicationrequest.com/entry")
                                    .build()))
                        .resource(samples.medicationRequest())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  void exactlyOneOfTest() {
    ExactlyOneOfVerifier.builder()
        .sample(samples.medicationRequest())
        .fieldPrefix("medication")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }

  @Test
  void medicationRequest() {
    assertRoundTrip(samples.medicationRequest());
  }

  @Test
  void zeroOrOneOfTest() {
    ZeroOrOneOfVerifier.builder()
        .sample(samples.medicationRequest())
        .fieldPrefix("reported")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
