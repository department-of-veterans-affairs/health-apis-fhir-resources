package gov.va.api.health.r4.api.bundle;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.autoconfig.configuration.JacksonConfig;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.Meta;
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
import gov.va.api.health.r4.api.resources.Resource;
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
import java.util.List;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;
import org.junit.jupiter.api.Test;

public class MixedBundleTest {
  @Test
  void allergyIntolerance() {
    AllergyIntolerance r = SampleAllergyIntolerances.get().allergyIntolerance();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void appointment() {
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
  void location() {
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
  @SneakyThrows
  void missingResourceType() {
    Patient pat = SamplePatients.get().patient();
    pat.resourceType(null);
    ObjectMapper mapper = new JacksonConfig().objectMapper();
    Patient evilTwin =
        mapper.readValue(
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pat), Patient.class);
    assertThat(evilTwin).isEqualTo(SamplePatients.get().patient());
    MixedBundle bundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(pat).build())).build();
    String bundleJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bundle);
    assertThatExceptionOfType(JsonMappingException.class)
        .isThrownBy(() -> mapper.readValue(bundleJson, MixedBundle.class));
  }

  @Test
  void multiple() {
    Patient pat = SamplePatients.get().patient();
    Immunization im = SampleImmunizations.get().immunization();
    Location loc = SampleLocations.get().location();
    assertRoundTrip(
        MixedBundle.builder()
            .entry(
                List.of(
                    MixedEntry.builder().resource(pat).build(),
                    MixedEntry.builder().resource(im).build(),
                    MixedEntry.builder().resource(loc).build()))
            .build());
  }

  @Test
  void observation() {
    Observation r = SampleObservations.get().observation();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void operationOutcome() {
    OperationOutcome r = SampleDataTypes.get().operationOutcome();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void patient() {
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

  @Test
  @SneakyThrows
  void unknownResource() {
    FooResource foo = FooResource.builder().build();
    ObjectMapper mapper = new JacksonConfig().objectMapper();
    FooResource evilTwin =
        mapper.readValue(
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(foo), FooResource.class);
    assertThat(evilTwin).isEqualTo(foo);
    MixedBundle bundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(foo).build())).build();
    String bundleJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bundle);
    assertThatExceptionOfType(JsonMappingException.class)
        .isThrownBy(() -> mapper.readValue(bundleJson, MixedBundle.class));
  }

  @Value
  @Builder
  @JsonDeserialize(builder = FooResource.FooResourceBuilder.class)
  private static final class FooResource implements Resource {
    @Builder.Default String resourceType = "FooResource";

    String id;

    Meta meta;

    String implicitRules;

    String language;
  }
}
