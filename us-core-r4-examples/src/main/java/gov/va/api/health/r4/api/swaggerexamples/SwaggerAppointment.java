package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Appointment;

public final class SwaggerAppointment {
  /** Example Appointment. */
  public static Appointment appointment() {
    return Appointment.builder()
        .id("I2-09PJ067B07D553B93JCEB49A0JP12QW9")
        .status(Appointment.AppointmentStatus.cancelled)
        .description("Scheduled Visit")
        .start("2006-06-30T17:00:00Z")
        .participant(
            asList(
                Appointment.Participant.builder()
                    .type(
                        asList(
                            CodeableConcept.builder()
                                .coding(
                                    asList(
                                        Coding.builder()
                                            .system("http://hl7.org/fhir/v3/ParticipationType")
                                            .code("PART")
                                            .display("Participation")
                                            .build()))
                                .build()))
                    .actor(
                        Reference.builder()
                            .reference(
                                "https://www.freedomstream.io/Argonaut/api/Patient/185601V825290")
                            .display("VETERAN,JOHN Q")
                            .build())
                    .required(Appointment.Required.required)
                    .status(Appointment.ParticipationStatus.accepted)
                    .build(),
                Appointment.Participant.builder()
                    .type(
                        asList(
                            CodeableConcept.builder()
                                .coding(
                                    asList(
                                        Coding.builder()
                                            .system("http://hl7.org/fhir/v3/ParticipationType")
                                            .code("PART")
                                            .display("Participation")
                                            .build()))
                                .build()))
                    .actor(
                        Reference.builder()
                            .reference(
                                "https://www.freedomstream.io/Argonaut/api/Location/71fed7fc-d060-5a35-bba9-e89fd7cdb4e6")
                            .display("ZZZTG SURG POD SCHEULLER COBI")
                            .build())
                    .required(Appointment.Required.required)
                    .status(Appointment.ParticipationStatus.accepted)
                    .build()))
        .build();
  }

  /** Example bundle. */
  public static Appointment.Bundle appointmentBundle() {
    return Appointment.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/r4/v0/Appointment?patient=185601V825290&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/r4/v0/Appointment?patient=185601V825290&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/r4/v0/Appointment?patient=185601V825290&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Appointment.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/r4/v0/Appointment/I2-09PJ067B07D553B93JCEB49A0JP12QW9")
                    .resource(appointment())
                    .search(
                        AbstractEntry.Search.builder().mode(AbstractEntry.SearchMode.match).build())
                    .build()))
        .build();
  }
}
