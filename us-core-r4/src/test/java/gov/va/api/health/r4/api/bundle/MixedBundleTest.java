package gov.va.api.health.r4.api.bundle;

import static gov.va.api.health.autoconfig.configuration.JacksonConfig.createMapper;
import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.resources.AllergyIntolerance;
import gov.va.api.health.r4.api.resources.Appointment;
import gov.va.api.health.r4.api.resources.CapabilityStatement;
import gov.va.api.health.r4.api.resources.Claim;
import gov.va.api.health.r4.api.resources.Condition;
import gov.va.api.health.r4.api.resources.Coverage;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse;
import gov.va.api.health.r4.api.resources.Device;
import gov.va.api.health.r4.api.resources.DiagnosticReport;
import gov.va.api.health.r4.api.resources.Encounter;
import gov.va.api.health.r4.api.resources.ExplanationOfBenefit;
import gov.va.api.health.r4.api.resources.Immunization;
import gov.va.api.health.r4.api.resources.Location;
import gov.va.api.health.r4.api.resources.Medication;
import gov.va.api.health.r4.api.resources.MedicationRequest;
import gov.va.api.health.r4.api.resources.Observation;
import gov.va.api.health.r4.api.resources.OperationOutcome;
import gov.va.api.health.r4.api.resources.Patient;
import gov.va.api.health.r4.api.resources.Practitioner;
import gov.va.api.health.r4.api.resources.PractitionerRole;
import gov.va.api.health.r4.api.resources.Procedure;
import gov.va.api.health.r4.api.resources.Questionnaire;
import gov.va.api.health.r4.api.resources.QuestionnaireResponse;
import gov.va.api.health.r4.api.resources.RelatedPerson;
import gov.va.api.health.r4.api.resources.TerminologyCapabilities;
import gov.va.api.health.r4.api.samples.SampleAllergyIntolerances;
import gov.va.api.health.r4.api.samples.SampleAppointments;
import gov.va.api.health.r4.api.samples.SampleCapabilityStatements;
import gov.va.api.health.r4.api.samples.SampleClaims;
import gov.va.api.health.r4.api.samples.SampleConditions;
import gov.va.api.health.r4.api.samples.SampleCoverageEligibilityResponses;
import gov.va.api.health.r4.api.samples.SampleCoverages;
import gov.va.api.health.r4.api.samples.SampleDataTypes;
import gov.va.api.health.r4.api.samples.SampleDevices;
import gov.va.api.health.r4.api.samples.SampleDiagnosticReports;
import gov.va.api.health.r4.api.samples.SampleEncounters;
import gov.va.api.health.r4.api.samples.SampleExplanationOfBenefits;
import gov.va.api.health.r4.api.samples.SampleImmunizations;
import gov.va.api.health.r4.api.samples.SampleLocations;
import gov.va.api.health.r4.api.samples.SampleMedicationRequests;
import gov.va.api.health.r4.api.samples.SampleMedications;
import gov.va.api.health.r4.api.samples.SampleObservations;
import gov.va.api.health.r4.api.samples.SamplePatients;
import gov.va.api.health.r4.api.samples.SamplePractitionerRoles;
import gov.va.api.health.r4.api.samples.SamplePractitioners;
import gov.va.api.health.r4.api.samples.SampleProcedures;
import gov.va.api.health.r4.api.samples.SampleQuestionnaireResponses;
import gov.va.api.health.r4.api.samples.SampleQuestionnaires;
import gov.va.api.health.r4.api.samples.SampleRelatedPersons;
import gov.va.api.health.r4.api.samples.SampleTerminologyCapabilities;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class MixedBundleTest {
  @Test
  void _mixed() {
    String path = "/mixed-bundle.json";
    _roundTrip(path, MixedBundle.class);
  }

  @SneakyThrows
  private <T> void _roundTrip(String path, Class<T> clazz) {
    T response = createMapper().readValue(getClass().getResourceAsStream(path), clazz);
    assertThat(response).isExactlyInstanceOf(clazz);
    String actual = createMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response);
    String expected =
        createMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(createMapper().readTree(getClass().getResourceAsStream(path)));
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void allergyIntolerance() {
    AllergyIntolerance r = SampleAllergyIntolerances.get().allergyIntolerance();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void appoointment() {
    Appointment r = SampleAppointments.get().appointment();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void capabilityStatement() {
    CapabilityStatement r = SampleCapabilityStatements.get().capability();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void claim() {
    Claim r = SampleClaims.get().claim();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void condition() {
    Condition r = SampleConditions.get().condition();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void coverage() {
    Coverage r = SampleCoverages.get().coverageWithValueMoney();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void coverageEligResponse() {
    CoverageEligibilityResponse r =
        SampleCoverageEligibilityResponses.get().coverageEligibilityResponse();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void device() {
    Device r = SampleDevices.get().device();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void diagnosticReport() {
    DiagnosticReport r = SampleDiagnosticReports.get().diagnosticReport();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void encounter() {
    Encounter r = SampleEncounters.get().encounter();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void explanationOfBenefit() {
    ExplanationOfBenefit r = SampleExplanationOfBenefits.get().explanationOfBenefit();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void immunization() {
    Immunization r = SampleImmunizations.get().immunization();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  public void location() {
    Location r = SampleLocations.get().location();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void medication() {
    Medication r = SampleMedications.get().medication();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void medicationRequest() {
    MedicationRequest r = SampleMedicationRequests.get().medicationRequest();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void observation() {
    Observation r = SampleObservations.get().observation();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void operationOutcome() {
    SampleDataTypes data = SampleDataTypes.get();
    OperationOutcome r =
        OperationOutcome.builder()
            .id("4321")
            .meta(data.meta())
            .implicitRules("http://HelloRules.com")
            .language("Hello Language")
            .text(data.narrative())
            .contained(singletonList(data.resource()))
            .modifierExtension(
                Arrays.asList(
                    data.extension(),
                    data.extensionWithQuantity(),
                    data.extensionWithRatio(),
                    data.extensionWithUsageContext(),
                    data.extensionWithContactDetail()))
            .issue(singletonList(data.issue()))
            .build();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  public void patient() {
    Patient r = SamplePatients.get().patient();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void practitioner() {
    Practitioner r = SamplePractitioners.get().practitioner();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void practitionerRole() {
    PractitionerRole r = SamplePractitionerRoles.get().practitionerRole();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void procedure() {
    Procedure r = SampleProcedures.get().procedure();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void questionnaire() {
    Questionnaire r = SampleQuestionnaires.get().questionnaire();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void questionnaireResponse() {
    QuestionnaireResponse r = SampleQuestionnaireResponses.get().questionnaireResponse();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void relatedPerson() {
    RelatedPerson r = SampleRelatedPersons.get().relatedPerson();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void simpleResource() {
    SimpleResource r = SampleDataTypes.get().resource();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void terminologyCapabilities() {
    TerminologyCapabilities r = SampleTerminologyCapabilities.get().terminologyCapabilities();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }
}
