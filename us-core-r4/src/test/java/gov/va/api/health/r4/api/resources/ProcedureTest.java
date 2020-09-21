package gov.va.api.health.r4.api.resources;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.RoundTrip;
import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import gov.va.api.health.r4.api.samples.SampleProcedures;
import gov.va.api.health.validation.api.ExactlyOneOfVerifier;
import org.junit.jupiter.api.Test;

public class ProcedureTest {
  private final SampleProcedures samples = SampleProcedures.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildProcedureBundles() {
    Procedure.Bundle bundle =
        Procedure.Bundle.builder()
            .type(BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url("http://procedure.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    Procedure.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://procedure.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://procedure.com/entry")
                                    .build()))
                        .resource(samples.procedure())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    RoundTrip.assertRoundTrip(bundle);
  }

  @Test
  public void procedure() {
    RoundTrip.assertRoundTrip(samples.procedure());
  }

  @Test
  public void relatedGroupsTest() {
    ExactlyOneOfVerifier.builder()
        .sample(samples.procedure())
        .fieldPrefix("performed")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
