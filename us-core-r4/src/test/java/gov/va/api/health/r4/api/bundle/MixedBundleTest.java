package gov.va.api.health.r4.api.bundle;

import static gov.va.api.health.autoconfig.configuration.JacksonConfig.createMapper;
import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.samples.SamplePatients;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class MixedBundleTest {
  @Test
  void mixed() {
    String path = "/mixed-bundle.json";
    roundTrip(path, MixedBundle.class);
  }

  @Test
  public void patient() {
    SamplePatients data = SamplePatients.get();
    MixedBundle bundle =
        MixedBundle.builder()
            .entry(
                List.of(
                    MixedEntry.builder()
                        .extension(List.of(data.extension()))
                        .fullUrl("http://patient.com")
                        .id("123")
                        .link(
                            List.of(
                                BundleLink.builder()
                                    .relation(LinkRelation.self)
                                    .url(("http://patient.com/1"))
                                    .build()))
                        .resource(data.patient())
                        .search(data.search())
                        .request(data.request())
                        .response(data.response())
                        .build()))
            .link(
                List.of(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://patient.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();
    assertRoundTrip(bundle);
  }

  @SneakyThrows
  private <T> void roundTrip(String path, Class<T> clazz) {
    T response = createMapper().readValue(getClass().getResourceAsStream(path), clazz);
    assertThat(response).isExactlyInstanceOf(clazz);
    String actual = createMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response);
    String expected =
        createMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(createMapper().readTree(getClass().getResourceAsStream(path)));
    assertThat(actual).isEqualTo(expected);
  }
  // Location
  // Immunization
  // SimpleResource
  // AllergyIntolerance
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
