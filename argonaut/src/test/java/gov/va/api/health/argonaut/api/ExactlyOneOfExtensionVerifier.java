package gov.va.api.health.argonaut.api;

import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.dstu2.api.elements.Extension;
import gov.va.api.health.validation.api.AbstractRelatedFieldVerifier;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * This class will verify fields with a field required by FHIR but not available for us is
 * represented by an ExactlyOneOf annotation that follows the name + _name convention where the
 * _name is an Extension object.
 */
@Slf4j
public class ExactlyOneOfExtensionVerifier<T> extends AbstractRelatedFieldVerifier<T> {
  private final Map<String, Supplier<?>> stringTypes;

  private final Map<Class<?>, Supplier<?>> knownTypes;

  /** The base of the related fields, e.g. status vs _status. */
  private String baseField;

  @Builder
  public ExactlyOneOfExtensionVerifier(
      T sample,
      String field,
      Map<Class<?>, Supplier<?>> knownTypes,
      Map<String, Supplier<?>> stringTypes) {
    super(sample, name -> name.equals(field) || name.equals("_" + field), knownTypes, stringTypes);
    baseField = field;
    this.stringTypes = stringTypes;
    this.knownTypes = knownTypes;
  }

  @Override
  public void verify() {
    log.info("Verifying {}", sample.getClass());
    String extensionField = "_" + baseField;
    assertThat(fields()).containsExactlyInAnyOrder(baseField, extensionField);
    /* Make sure the sample is valid before we mess it up. */
    assertProblems(0);
    /* Make sure we are valid if no fields are set. */
    unsetFields();
    assertProblems(1);
    setField(baseField);
    assertProblems(0);
    unsetFields();
    setField(extensionField);
    assertProblems(0);
    assertThat(field(extensionField).getType()).isEqualTo(Extension.class);
  }
}
