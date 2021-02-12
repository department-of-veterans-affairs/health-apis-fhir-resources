package gov.va.api.health.r4.api.datatypes;

import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.elements.Reference;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class UsageContextTest {
  @Test
  void validationFailsGivenManyValues() {
    var bad =
        UsageContext.builder()
            .id("x")
            .valueReference(Reference.builder().build())
            .valueRange(Range.builder().build())
            .build();
    assertThat(violationsOf(bad)).isNotEmpty();
  }

  @Test
  void validationFailsGivenNoValue() {
    var bad = UsageContext.builder().id("x").build();
    assertThat(violationsOf(bad)).isNotEmpty();
  }

  @Test
  void validationPassesGivenOneValue() {
    var good = UsageContext.builder().id("x").valueReference(Reference.builder().build());
    assertThat(violationsOf(good)).isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
