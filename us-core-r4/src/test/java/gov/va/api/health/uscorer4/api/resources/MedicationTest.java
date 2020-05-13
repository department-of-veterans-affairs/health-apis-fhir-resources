package gov.va.api.health.uscorer4.api.resources;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.uscorer4.api.samples.SampleMedications;
import org.junit.jupiter.api.Test;

import static gov.va.api.health.uscorer4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

public class MedicationTest {
  private final SampleMedications samples = SampleMedications.get();

  @Test
  void bundlerCanBuildMedicationBundles() {
    Medication.Bundle bundle =
        Medication.Bundle.builder()
            .type(AbstractBundle.BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://medication.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    Medication.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://medication.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://medication.com/entry")
                                    .build()))
                        .resource(samples.medication())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  void medication() {
    assertRoundTrip(samples.medication());
  }
}
