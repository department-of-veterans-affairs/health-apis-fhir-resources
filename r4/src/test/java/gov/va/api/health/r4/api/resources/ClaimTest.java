package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType.searchset;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.ExactlyOneOfVerifier;
import gov.va.api.health.r4.api.ZeroOrOneOfVerifier;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.resources.Claim.Bundle;
import gov.va.api.health.r4.api.resources.Claim.Entry;
import gov.va.api.health.r4.api.samples.SampleClaims;
import org.junit.Test;

public class ClaimTest {

  private final SampleClaims data = SampleClaims.get();

  @Test
  public void bundlerCanBuildClaimBundles() {
    Entry entry =
        Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://localhost")
            .id("123")
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://localhost/1"))
                        .build()))
            .resource(data.claim())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Bundle bundle =
        Bundle.builder()
            .entry(singletonList(entry))
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://localhost/2"))
                        .build()))
            .type(searchset)
            .signature(data.signature())
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void claim() {
    assertRoundTrip(data.claim());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.accidentWithLocationAddress())
        .fieldPrefix("location")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.accidentWithLocationReference())
        .fieldPrefix("location")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfoWithTimingDateAndValueAttachment())
        .fieldPrefix("timing")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfoWithTimingPeriodAndValueAttachment())
        .fieldPrefix("timing")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfoWithTimingDateAndValueAttachment())
        .fieldPrefix("value")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfoWithTimingDateAndValueBoolean())
        .fieldPrefix("value")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfoWithTimingDateAndValueQuantity())
        .fieldPrefix("value")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfoWithTimingDateAndValueReference())
        .fieldPrefix("value")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.supportingInfoWithTimingDateAndValueString())
        .fieldPrefix("value")
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.diagnosisWithCodeableConcept())
        .fieldPrefix("diagnosis")
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.diagnosisWithReference())
        .fieldPrefix("diagnosis")
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.procedureWithCodeableConcept())
        .fieldPrefix("procedure")
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.procedureWithReference())
        .fieldPrefix("procedure")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.itemWithServicedDateAndLocationAddress())
        .fieldPrefix("serviced")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.itemWithServicedPeriodAndLocationAddress())
        .fieldPrefix("serviced")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.itemWithServicedDateAndLocationAddress())
        .fieldPrefix("location")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.itemWithServicedDateAndLocationCodeableConcept())
        .fieldPrefix("location")
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.itemWithServicedDateAndLocationReference())
        .fieldPrefix("location")
        .build()
        .verify();
  }
}
