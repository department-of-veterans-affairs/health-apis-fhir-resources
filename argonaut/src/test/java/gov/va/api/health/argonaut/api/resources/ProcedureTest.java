package gov.va.api.health.argonaut.api.resources;

import static gov.va.api.health.argonaut.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.argonaut.api.ExactlyOneOfExtensionVerifier;
import gov.va.api.health.argonaut.api.resources.Procedure.Bundle;
import gov.va.api.health.argonaut.api.resources.Procedure.Entry;
import gov.va.api.health.argonaut.api.samples.SampleKnownTypes;
import gov.va.api.health.argonaut.api.samples.SampleProcedures;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.validation.api.ExactlyOneOfVerifier;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import java.util.Collections;
import org.junit.Test;

public class ProcedureTest {
  private final SampleProcedures data = SampleProcedures.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildProcedureBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://procedure.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://procedure.com/1"))
                        .build()))
            .resource(data.procedure())
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
                        .url(("http://procedure.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void patient() {
    assertRoundTrip(data.procedure());
  }

  @Test
  public void relatedFields() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.procedure())
        .fieldPrefix("reason")
        .omission("reasonNotPerformed")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ExactlyOneOfExtensionVerifier.builder()
        .sample(data.procedure())
        .field("status")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.procedure())
        .fieldPrefix("performed")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
