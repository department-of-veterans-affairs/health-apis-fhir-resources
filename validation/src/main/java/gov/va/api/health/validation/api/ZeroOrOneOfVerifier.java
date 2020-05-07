package gov.va.api.health.validation.api;

import static java.util.Collections.emptyList;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * This class will verify fields with a given prefix are properly configured in the same ZeroOrOneOf
 * group. This class will find related fields with the same prefix and systematically test different
 * combinations to ensure they are validated as expected.
 */
@Slf4j
public class ZeroOrOneOfVerifier<T> extends AbstractRelatedFieldVerifier<T> {
  /** The prefix of the related fields. */
  private String fieldPrefix;

  /** Creates a customizable verifier with different prefixes, and known types. */
  @Builder
  ZeroOrOneOfVerifier(
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
    /* Make sure the sample is valid before we mess it up. */
    assertProblems(0);
    /* Make sure we are valid if no fields are set. */
    unsetFields();
    assertProblems(0);
    /* Make sure setting any two fields is not ok. */
    log.info("{} fields in group {}: {}", sample.getClass().getSimpleName(), fieldPrefix, fields());
    Preconditions.checkState(fields().size() > 1, "Not enough fields in group: " + fieldPrefix);
    String anchor = fields().get(0);
    for (int i = 1; i < fields().size(); i++) {
      unsetFields();
      setField(anchor);
      setField(fields().get(i));
      assertProblems(1);
    }
  }
}
