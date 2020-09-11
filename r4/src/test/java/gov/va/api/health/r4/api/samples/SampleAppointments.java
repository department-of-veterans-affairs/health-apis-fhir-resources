package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.Appointment;
import gov.va.api.health.r4.api.resources.Appointment.AppointmentStatus;
import gov.va.api.health.r4.api.resources.Appointment.Participant;
import gov.va.api.health.r4.api.resources.Appointment.ParticipationStatus;
import gov.va.api.health.r4.api.resources.Appointment.Required;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleAppointments {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Appointment appointment() {
    return Appointment.builder()
        .resourceType("Appointment")
        .id("1234")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .status(AppointmentStatus.booked)
        .cancelationReason(codeableConcept())
        .serviceCategory(singletonList(codeableConcept()))
        .serviceType(singletonList(codeableConcept()))
        .specialty(singletonList(codeableConcept()))
        .appointmentType(codeableConcept())
        .reasonCode(singletonList(codeableConcept()))
        .reasonReference(singletonList(reference()))
        .priority(1)
        .description("Sample Appointment Description")
        .supportingInformation(singletonList(reference()))
        .start("2015-02-07T13:28:17.239+02:00")
        .end("2015-02-07T15:28:17.239+02:00")
        .minutesDuration(120)
        .slot(singletonList(reference()))
        .created("2015-02-07T13:28:17-05:00")
        .comment("Sample Appointment Comment")
        .patientInstruction("Sample Appt Patient Instruction")
        .basedOn(singletonList(reference()))
        .participant(singletonList(participant()))
        .requestedPeriod(singletonList(period()))
        .build();
  }

  public Participant participant() {
    return Participant.builder()
        .type(singletonList(codeableConcept()))
        .actor(reference())
        .required(Required.required)
        .status(ParticipationStatus.accepted)
        .period(period())
        .build();
  }
}
