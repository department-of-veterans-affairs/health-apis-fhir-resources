package gov.va.api.health.r4.api.bundle;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.autoconfig.configuration.JacksonConfig;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.resources.Immunization;
import gov.va.api.health.r4.api.resources.Location;
import gov.va.api.health.r4.api.resources.Patient;
import gov.va.api.health.r4.api.resources.Resource;
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
import java.util.stream.Stream;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MixedBundleTest {
  static Stream<Arguments> roundTripResources() {
    return Stream.of(
        Arguments.of(SampleAllergyIntolerances.get().allergyIntolerance()),
        Arguments.of(SampleAppointments.get().appointment()),
        Arguments.of(SampleCapabilityStatements.get().capability()),
        Arguments.of(SampleClaims.get().claim()),
        Arguments.of(SampleConditions.get().condition()),
        Arguments.of(SampleCoverages.get().coverageWithValueMoney()),
        Arguments.of(SampleCoverageEligibilityResponses.get().coverageEligibilityResponse()),
        Arguments.of(SampleDevices.get().device()),
        Arguments.of(SampleDiagnosticReports.get().diagnosticReport()),
        Arguments.of(SampleEncounters.get().encounter()),
        Arguments.of(SampleExplanationOfBenefits.get().explanationOfBenefit()),
        Arguments.of(SampleImmunizations.get().immunization()),
        Arguments.of(SampleLocations.get().location()),
        Arguments.of(SampleMedications.get().medication()),
        Arguments.of(SampleMedicationRequests.get().medicationRequest()),
        Arguments.of(SampleObservations.get().observation()),
        Arguments.of(SampleDataTypes.get().operationOutcome()),
        Arguments.of(SamplePatients.get().patient()),
        Arguments.of(SamplePractitioners.get().practitioner()),
        Arguments.of(SamplePractitionerRoles.get().practitionerRole()),
        Arguments.of(SampleProcedures.get().procedure()),
        Arguments.of(SampleQuestionnaires.get().questionnaire()),
        Arguments.of(SampleQuestionnaireResponses.get().questionnaireResponse()),
        Arguments.of(SampleRelatedPersons.get().relatedPerson()),
        Arguments.of(SampleTerminologyCapabilities.get().terminologyCapabilities()));
  }

  @Test
  @SneakyThrows
  void exception_invalidResourceType() {
    var r = SamplePatients.get().patient();
    r.resourceType("NotARealResource");
    var mixedBundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build();
    ObjectMapper mapper = new JacksonConfig().objectMapper();
    assertThatExceptionOfType(JsonMappingException.class)
        .isThrownBy(
            () ->
                mapper.readValue(
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mixedBundle),
                    MixedBundle.class));
  }

  @Test
  @SneakyThrows
  void exception_missingResourceType() {
    Patient pat = SamplePatients.get().patient().resourceType(null);
    MixedBundle bundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(pat).build())).build();
    assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() -> assertRoundTrip(bundle));
  }

  @Test
  @SneakyThrows
  void exception_unknownResource() {
    FooResource foo = FooResource.builder().build();
    // Solo resource can round-trip
    assertRoundTrip(foo);
    // FooResource is not in the resources package, so it won't be found
    MixedBundle bundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(foo).build())).build();
    assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() -> assertRoundTrip(bundle));
  }

  @MethodSource
  @ParameterizedTest
  void roundTripResources(Resource r) {
    assertRoundTrip(
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build());
  }

  @Test
  void roundTrip_multiple() {
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
