package gov.va.api.health.r4.api.datatypes;

import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.elements.Reference;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class TriggerDefinitionTest {
  @Test
  void validationFailsGivenMultipleValues() {
    var bad =
        TriggerDefinition.builder()
            .timingTiming(Timing.builder().build())
            .timingReference(Reference.builder().build())
            .build();
    assertThat(violationsOf(bad)).isNotEmpty();
  }

  @Test
  void validationPassesGivenGoodDefinition() {
    var good = TriggerDefinition.builder().build();
    assertThat(violationsOf(good)).isEmpty();
    good.timingTiming(Timing.builder().build());
    assertThat(violationsOf(good)).isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
