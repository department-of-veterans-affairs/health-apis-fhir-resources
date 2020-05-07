package gov.va.api.health.validation.api;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExactlyOneOfVerifier<T> extends AbstractRelatedFieldVerifier<T> {
  private String fieldPrefix;

  /** Creates a customizable verifier with different prefixes, and known types. */
  @Builder
  ExactlyOneOfVerifier(
      T sample,
      String fieldPrefix,
      Collection<?> omissions,
      Map<Class<?>, Supplier<?>> knownTypes,
      Map<String, Supplier<?>> stringTypes) {
    super(
        sample,
        name ->
            name.startsWith(fieldPrefix)
                && !Optional.ofNullable(omissions).orElse(emptyList()).contains(name),
        knownTypes,
        stringTypes);
    this.fieldPrefix = fieldPrefix;
  }

  @Override
  public void verify() {
    log.info("Verifying {}", sample.getClass());
    assertProblems(0);
    /* Must have at least 1 field set. */
    unsetFields();
    assertProblems(1);
    /* Make sure setting any two fields is not ok. */
    log.info("{} fields in group {}: {}", sample.getClass().getSimpleName(), fieldPrefix, fields());
    assertThat(fields().size())
        .withFailMessage("Not enough fields in group: " + fieldPrefix)
        .isGreaterThan(1);
    String anchor = fields().get(0);
    for (int i = 1; i < fields().size(); i++) {
      unsetFields();
      setField(anchor);
      setField(fields().get(i));
      assertProblems(1);
    }
  }
}
