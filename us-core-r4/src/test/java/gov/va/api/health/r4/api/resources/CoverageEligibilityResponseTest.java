package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType.searchset;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Bundle;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Entry;
import gov.va.api.health.r4.api.samples.SampleCoverageEligibilityResponses;
import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import gov.va.api.health.validation.api.ExactlyOneOfExtensionVerifier;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import org.junit.jupiter.api.Test;

public class CoverageEligibilityResponseTest {

  private final SampleCoverageEligibilityResponses data = SampleCoverageEligibilityResponses.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildCoverageEligibilityResponseBundles() {
    Entry entry =
        Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://coverage-eligibility-response.com")
            .id("123")
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://coverage-eligibility-response/1"))
                        .build()))
            .resource(data.coverageEligibilityResponse())
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
                        .url(("http://coverage-eligibility-response.com/2"))
                        .build()))
            .type(searchset)
            .signature(data.signature())
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void coverageEligibilityResponse() {
    assertRoundTrip(data.coverageEligibilityResponse());
    assertRoundTrip(data.coverageEligibilityResponseWithDataAbsentReason());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.coverageEligibilityResponse())
        .fieldPrefix("serviced")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.benefit())
        .fieldPrefix("allowed")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.benefit())
        .fieldPrefix("used")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ExactlyOneOfExtensionVerifier.builder()
        .sample(data.coverageEligibilityResponse())
        .field("request")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .extensionClass(Extension.class)
        .build()
        .verify();
    ExactlyOneOfExtensionVerifier.builder()
        .sample(data.coverageEligibilityResponseWithDataAbsentReason())
        .field("request")
        .extensionClass(Extension.class)
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ExactlyOneOfExtensionVerifier.builder()
        .sample(data.insurance())
        .field("coverage")
        .extensionClass(Extension.class)
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ExactlyOneOfExtensionVerifier.builder()
        .sample(data.insuranceWithDataAbsentReason())
        .field("coverage")
        .extensionClass(Extension.class)
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
