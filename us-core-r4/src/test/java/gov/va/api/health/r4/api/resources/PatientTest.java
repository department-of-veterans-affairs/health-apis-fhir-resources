package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.resources.Patient.Bundle;
import gov.va.api.health.r4.api.resources.Patient.Entry;
import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import gov.va.api.health.r4.api.samples.SamplePatients;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.SAME_THREAD)
public class PatientTest {
  private final SamplePatients data = SamplePatients.get();

  private final SampleKnownTypes types = SampleKnownTypes.get();

  private static <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }

  @Test
  void bundlerCanBuildPatientBundles() {
    Entry entry =
        Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://patient.com")
            .id("123")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://patient.com/1"))
                        .build()))
            .resource(data.patient())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Bundle bundle =
        Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://patient.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  void patient() {
    assertRoundTrip(data.patient());
  }

  @Test
  void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.patient())
        .fieldPrefix("deceased")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
    ZeroOrOneOfVerifier.builder()
        .sample(data.patient())
        .fieldPrefix("multipleBirth")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }

  @Test
  void validationFailsGivenNoIdentifiers() {
    var patient = data.patient();
    assertThat(violationsOf(patient)).isEmpty();
    patient.identifier(List.of());
    assertThat(violationsOf(patient)).isNotEmpty();
    patient.identifier(null);
    assertThat(violationsOf(patient)).isNotEmpty();
  }

  @Test
  void validationPassesWhenConfiguredWithMinSizeOfZero() {
    var patient = data.patient();
    var save = Patient.IDENTIFIER_MIN_SIZE.get();
    try {
      Patient.IDENTIFIER_MIN_SIZE.set(0);
      assertThat(violationsOf(patient.identifier(null))).isEmpty();
      assertThat(violationsOf(patient.identifier(List.of()))).isEmpty();
    } finally {
      Patient.IDENTIFIER_MIN_SIZE.set(save);
    }
  }
}
