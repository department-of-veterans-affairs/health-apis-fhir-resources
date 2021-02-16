package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import gov.va.api.health.r4.api.samples.SampleParameters;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class ParametersTest {
  private final SampleParameters data = SampleParameters.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  void roundTrip() {
    assertRoundTrip(data.parameters());
  }

  @Test
  void validationFailsGivenBadParameter() {
    var bad = data.parameters();
    bad.parameter().get(0).valueInteger(5);
    assertThat(violationsOf(bad)).isNotEmpty();
  }

  @Test
  void validationPassesGivenGoodParameter() {
    assertThat(violationsOf(data.parameters())).isEmpty();
    assertThat(violationsOf(data.parametersBoolean())).isEmpty();
    assertThat(violationsOf(data.parametersString())).isEmpty();
    var tmp = data.parametersResource();
    assertThat(violationsOf(tmp)).isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
