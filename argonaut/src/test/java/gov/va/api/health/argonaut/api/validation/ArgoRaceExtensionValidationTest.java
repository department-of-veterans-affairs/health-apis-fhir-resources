package gov.va.api.health.argonaut.api.validation;

import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.argonaut.api.samples.SampleExtensions;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class ArgoRaceExtensionValidationTest {
  private final SampleExtensions data = SampleExtensions.get();

  @Test
  public void patientWithMultipleOptionalRaceExtensionIsValid() {
    assertThat(violationsOf(data.patientWithMultipleOptionalRaceExtension())).isEmpty();
  }

  @Test
  public void patientWithNoRequiredRaceExtensionIsNotValid() {
    assertThat(violationsOf(data.patientWithNoRequiredRaceExtension())).isNotEmpty();
  }

  @Test
  public void patientWithNullRaceExtensionIsValid() {
    assertThat(violationsOf(data.patientWithNullRaceExtension())).isEmpty();
  }

  @Test
  public void patientWithSingleOptionalRaceExtensionIsValid() {
    assertThat(violationsOf(data.patientWithSingleOptionalRaceExtension())).isEmpty();
  }

  @Test
  public void patientWithSingleRequiredRaceExtensionIsValid() {
    assertThat(violationsOf(data.patientWithSingleRequiredRaceExtension())).isEmpty();
  }

  @Test
  public void patientWithTooManyOptionalRaceExtensionIsNotValid() {
    assertThat(violationsOf(data.patientWithTooManyOptionalRaceExtension())).isNotEmpty();
  }

  @Test
  public void patientWithTooManyRequiredRaceExtensionIsNotValid() {
    assertThat(violationsOf(data.patientWithTooManyRequiredRaceExtension())).isNotEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
