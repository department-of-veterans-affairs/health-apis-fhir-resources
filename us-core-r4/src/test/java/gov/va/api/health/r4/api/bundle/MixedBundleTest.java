package gov.va.api.health.r4.api.bundle;

import static gov.va.api.health.autoconfig.configuration.JacksonConfig.createMapper;
import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.resources.AllergyIntolerance;
import gov.va.api.health.r4.api.resources.Immunization;
import gov.va.api.health.r4.api.resources.Location;
import gov.va.api.health.r4.api.resources.Patient;
import gov.va.api.health.r4.api.samples.SampleAllergyIntolerances;
import gov.va.api.health.r4.api.samples.SampleDataTypes;
import gov.va.api.health.r4.api.samples.SampleImmunizations;
import gov.va.api.health.r4.api.samples.SampleLocations;
import gov.va.api.health.r4.api.samples.SamplePatients;
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
  public void patient() {
    Patient r = SamplePatients.get().patient();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void simpleResource() {
    SimpleResource r = SampleDataTypes.get().resource();
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  // TODO
  // CapabilityStatement
  // Condition
  // Coverage
  // CoverageEligResponse
  // Device
  // DiagnosticReport
  // Encounter
  // ExplanationOfBenefit
  // Immunization/
  // Medication
  // MedicationRequest
  // Observation
  // Practitioner
  // PractitionerRole
  // Procedure
  // TerminologyCapabilities
  // Appointment
  // Claim
  // OperationOutcome
  // Questionnaire
  // QuestionnaireResponse
  // RelatedPerson
  // MixedBundle
  // PatientBundle, LocationBundle, ImmunizationBundle
}
