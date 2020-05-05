package gov.va.api.health.argonaut.api.resources;

import static gov.va.api.health.argonaut.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.argonaut.api.resources.MedicationStatement.Bundle;
import gov.va.api.health.argonaut.api.resources.MedicationStatement.Entry;
import gov.va.api.health.argonaut.api.samples.SampleKnownTypes;
import gov.va.api.health.argonaut.api.samples.SampleMedicationStatements;
import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.validation.api.ExactlyOneOfVerifier;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import java.util.Collections;
import org.junit.Test;

public class MedicationStatementTest {
  private final SampleMedicationStatements data = SampleMedicationStatements.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildMedicationStatementBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://medicationstatement.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://medicationstatement.com/1"))
                        .build()))
            .resource(data.medicationStatement())
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
                        .url(("http://medicationstatement.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void medicationStatement() {
    assertRoundTrip(data.medicationStatement());
    assertRoundTrip(data.medicationStatementWithAlternateValues());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.medicationStatement())
        .fieldPrefix("reasonForUse")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.medicationStatement())
        .fieldPrefix("effective")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ExactlyOneOfVerifier.builder()
        .sample(data.medicationStatement())
        .fieldPrefix("medication")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
