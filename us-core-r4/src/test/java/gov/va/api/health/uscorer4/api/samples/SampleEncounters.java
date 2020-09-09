package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.uscorer4.api.resources.Encounter;
import gov.va.api.health.uscorer4.api.resources.Encounter.ClassHistory;
import gov.va.api.health.uscorer4.api.resources.Encounter.Diagnosis;
import gov.va.api.health.uscorer4.api.resources.Encounter.EncounterClass;
import gov.va.api.health.uscorer4.api.resources.Encounter.Hospitalization;
import gov.va.api.health.uscorer4.api.resources.Encounter.Location;
import gov.va.api.health.uscorer4.api.resources.Encounter.Participant;
import gov.va.api.health.uscorer4.api.resources.Encounter.Status;
import gov.va.api.health.uscorer4.api.resources.Encounter.StatusHistory;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

/**
 * This class provides data structures that are populated with dummy values, suitable for testing
 * serialization.
 */
@NoArgsConstructor(staticName = "get")
public class SampleEncounters {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public ClassHistory classHistory() {
    return ClassHistory.builder()
        .id("0000")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .encounterClass(EncounterClass.pre_admission)
        .period(period())
        .build();
  }

  public Diagnosis diagnosis() {
    return Diagnosis.builder()
        .id("1111")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .condition(reference())
        .use(codeableConcept())
        .rank(1)
        .build();
  }

  public Encounter encounter() {
    return Encounter.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://HelloRules.com")
        .language("Hello Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(extension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .status(Status.arrived)
        .statusHistory(singletonList(statusHistory()))
        .encounterClass(EncounterClass.ambulatory)
        .classHistory(classHistory())
        .type(codeableConceptList())
        .serviceType(codeableConceptList())
        .priority(codeableConceptList())
        .subject(reference())
        .episodeOfCare(referenceList())
        .basedOn(referenceList())
        .participant(singletonList(participant()))
        .appointment(reference())
        .period(period())
        .length(duration())
        .reason(codeableConceptList())
        .account(reference())
        .hospitalization(hospitalization())
        .location(singletonList(location()))
        .serviceProvider(reference())
        .partOf(reference())
        .build();
  }

  public Hospitalization hospitalization() {
    return Hospitalization.builder()
        .id("2222")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .origin(reference())
        .admitSource(codeableConcept())
        .reAdmission(codeableConcept())
        .dietPreference(codeableConceptList())
        .specialCourtesy(codeableConceptList())
        .specialArrangement(codeableConceptList())
        .destination(reference())
        .dischargeDisposition(codeableConcept())
        .build();
  }

  public Location location() {
    return Location.builder()
        .id("3333")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .location(reference())
        .status(Location.Status.planned)
        .physicalType(codeableConceptList())
        .period(period())
        .build();
  }

  public Participant participant() {
    return Participant.builder()
        .id("4444")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .type(codeableConceptList())
        .period(period())
        .individual(reference())
        .build();
  }

  public StatusHistory statusHistory() {
    return StatusHistory.builder()
        .id("5555")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .status(Status.planned)
        .period(period())
        .type(codeableConceptList())
        .build();
  }
}
