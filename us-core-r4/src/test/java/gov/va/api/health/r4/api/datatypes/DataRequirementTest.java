package gov.va.api.health.r4.api.datatypes;

import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.elements.Reference;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class DataRequirementTest {
  @Test
  void dateFilterFailsValidationWithMultipleValues() {
    var bad =
        DataRequirement.DateFilter.builder()
            .valuePeriod(Period.builder().build())
            .valueDuration(Duration.builder().build())
            .build();
    assertThat(violationsOf(bad)).isNotEmpty();
  }

  @Test
  void dateFilterPassesValidationWithOneValueOrLess() {
    var good = DataRequirement.DateFilter.builder().path("some/path").build();
    assertThat(violationsOf(good)).isEmpty();
    good.valuePeriod(Period.builder().build());
    assertThat(violationsOf(good)).isEmpty();
  }

  @Test
  void filtersFailValidationWithInvalidNumberOfFields() {
    var codeBad = DataRequirement.CodeFilter.builder().build();
    assertThat(violationsOf(codeBad)).isNotEmpty();
    codeBad.path("some/path");
    codeBad.searchParam("something");
    assertThat(violationsOf(codeBad)).isNotEmpty();
    var dateBad = DataRequirement.DateFilter.builder().build();
    assertThat(violationsOf(dateBad)).isNotEmpty();
    dateBad.path("some/path");
    dateBad.searchParam("something");
    assertThat(violationsOf(dateBad)).isNotEmpty();
  }

  @Test
  void filtersPassValidationWithOneField() {
    var codeGood = DataRequirement.CodeFilter.builder().path("some/path").build();
    assertThat(violationsOf(codeGood)).isEmpty();
    var dateBad = DataRequirement.DateFilter.builder().path("some/path").build();
    assertThat(violationsOf(dateBad)).isEmpty();
  }

  @Test
  void subjectFailsValidationWithMoreThanOneSubject() {
    var bad =
        DataRequirement.Subject.builder()
            .subjectCodeableConcept(CodeableConcept.builder().build())
            .subjectReference(Reference.builder().build())
            .build();
    assertThat(violationsOf(bad)).isNotEmpty();
  }

  @Test
  void subjectPassesValidationOneSubjectOrLess() {
    var good = DataRequirement.Subject.builder().build();
    assertThat(violationsOf(good)).isEmpty();
    good.subjectReference(Reference.builder().build());
    assertThat(violationsOf(good)).isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
