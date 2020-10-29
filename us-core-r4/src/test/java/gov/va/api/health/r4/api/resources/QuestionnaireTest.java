package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType.searchset;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SampleAppointments;
import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import org.junit.Test;

public class QuestionnaireTest {
  private final SampleAppointments data = SampleAppointments.get();

  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void appointment() {
    assertRoundTrip(data.appointment());
  }

  @Test
  public void bundlerCanBuildAppointmentBundles() {
    Appointment.Entry entry =
        Appointment.Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://appointment.com")
            .id("123")
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://appointment/1"))
                        .build()))
            .resource(data.appointment())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Appointment.Bundle bundle =
        Appointment.Bundle.builder()
            .entry(singletonList(entry))
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://appointment.com/2"))
                        .build()))
            .type(searchset)
            .signature(data.signature())
            .build();
    assertRoundTrip(bundle);
  }
}
