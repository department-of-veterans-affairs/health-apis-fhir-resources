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

public class PatientTest {
  private final SamplePatients data = SamplePatients.get();

  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void bundlerCanBuildPatientBundles() {
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
  public void patient() {
    assertRoundTrip(data.patient());
  }

  @Test
  public void relatedGroups() {
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
  public void validationFailsGivenNoIdentifiers() {
    var patient = data.patient();
    assertThat(violationsOf(patient)).isEmpty();
    patient.identifier(List.of());
    assertThat(violationsOf(patient)).isNotEmpty();
    patient.identifier(null);
    assertThat(violationsOf(patient)).isNotEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
