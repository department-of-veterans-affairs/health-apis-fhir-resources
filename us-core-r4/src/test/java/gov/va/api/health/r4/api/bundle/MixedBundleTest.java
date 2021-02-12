package gov.va.api.health.r4.api.bundle;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.fasterxml.jackson.databind.JsonMappingException;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.resources.Immunization;
import gov.va.api.health.r4.api.resources.Location;
import gov.va.api.health.r4.api.resources.Patient;
import gov.va.api.health.r4.api.resources.Resource;
import gov.va.api.health.r4.api.samples.SampleImmunizations;
import gov.va.api.health.r4.api.samples.SampleLocations;
import gov.va.api.health.r4.api.samples.SamplePatients;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

public class MixedBundleTest {
  @Test
  void exception_invalidResourceType() {
    var r = SamplePatients.get().patient().resourceType("NotARealResource");
    var bundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build();
    assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() -> assertRoundTrip(bundle));
  }

  @Test
  void exception_missingResourceType() {
    Patient pat = SamplePatients.get().patient().resourceType(null);
    MixedBundle bundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(pat).build())).build();
    assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() -> assertRoundTrip(bundle));
  }

  @Test
  void exception_wrongPackage() {
    WrongPackage r = WrongPackage.builder().build();
    MixedBundle bundle =
        MixedBundle.builder().entry(List.of(MixedEntry.builder().resource(r).build())).build();
    assertThatExceptionOfType(JsonMappingException.class).isThrownBy(() -> assertRoundTrip(bundle));
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

  @Test
  void roundTrip_nestedBundles() {
    Patient.Bundle pat =
        Patient.Bundle.builder()
            .entry(
                List.of(Patient.Entry.builder().resource(SamplePatients.get().patient()).build()))
            .build();
    Immunization.Bundle im =
        Immunization.Bundle.builder()
            .entry(
                List.of(
                    Immunization.Entry.builder()
                        .resource(SampleImmunizations.get().immunization())
                        .build()))
            .build();
    Location.Bundle loc =
        Location.Bundle.builder()
            .entry(
                List.of(
                    Location.Entry.builder().resource(SampleLocations.get().location()).build()))
            .build();
    assertRoundTrip(
        MixedBundle.builder()
            .entry(
                List.of(
                    MixedEntry.builder().resource(pat).build(),
                    MixedEntry.builder().resource(im).build(),
                    MixedEntry.builder().resource(loc).build()))
            .build());
  }

  @Data
  @Builder
  private static final class WrongPackage implements Resource {
    @Builder.Default String resourceType = "WrongPackage";

    String id;

    Meta meta;

    String implicitRules;

    String language;
  }
}
