package gov.va.api.health.validation.api;

import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.google.common.base.Preconditions.checkState;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * This is a base for creating verifier tests for groups of related fields, such as ZeroOrOneOf. It
 * provides a foundation for setting and unsetting groups of fields. Values are determined by type
 * the type of field.
 */
@Slf4j
public abstract class AbstractRelatedFieldVerifier<T> {
  /** A valid sample. We will mutate this throughout the test. */
  protected final T sample;

  /** The determined list of related fields based on the prefix. */
  @Getter private final List<String> fields;

  /** Used to create sample values for fields based on the field type. */
  @Getter private final Map<Class<?>, Supplier<?>> knownTypes;

  /**
   * Used to create sample String values for fields based on the Pattern regex constraint on the
   * field.
   */
  @Getter private final Map<String, Supplier<?>> stringTypes;

  /**
   * The fieldSelector predicate will be checked for each field declared in the sample's class. A
   * true return value will include the field in the group, so setting and unsetting.
   */
  protected AbstractRelatedFieldVerifier(
      T sample,
      Predicate<String> fieldSelector,
      Map<Class<?>, Supplier<?>> knownTypes,
      Map<String, Supplier<?>> stringTypes) {
    this.sample = sample;
    fields =
        Arrays.stream(sample.getClass().getDeclaredFields())
            .map(Field::getName)
            .filter(fieldSelector)
            .collect(toList());
    this.knownTypes = knownTypes;
    this.stringTypes = stringTypes;
  }

  /** Validate and verify the given number of problems are reported. */
  @SneakyThrows
  protected void assertProblems(int count) {
    Set<ConstraintViolation<T>> problems =
        Validation.buildDefaultValidatorFactory().getValidator().validate(sample);
    if (problems.size() == count) {
      return;
    }
    log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(sample));
    problems.forEach(p -> log.error("{}", p));
    checkState(problems.size() == count);
  }

  private Supplier<?> enumSupplier(Class<? extends Enum<?>> type) {
    return () -> type.getEnumConstants()[0];
  }

  @SneakyThrows
  protected Field field(String name) {
    Field field = sample.getClass().getDeclaredField(name);
    checkState(field != null, "Cannot determine field type: " + name);
    return field;
  }

  /** Set the field to an automatically determined value based on it's type. */
  @SuppressWarnings("unchecked")
  protected void setField(String name) {
    Field field = field(name);
    Supplier<?> supplier;
    if (String.class.equals(field.getType())) {
      Pattern pattern = field.getAnnotation(Pattern.class);
      String stringType = "";
      if (pattern != null) {
        stringType = pattern.regexp();
      }
      supplier = stringTypes().get(stringType);
    } else if (Enum.class.isAssignableFrom(field.getType())) {
      supplier = enumSupplier((Class<? extends Enum<?>>) field.getType());
    } else {
      supplier = knownTypes().get(field.getType());
    }
    checkState(
        supplier != null, "Unknown value type for field: " + name + " type: " + field.getType());
    setField(name, supplier.get());
  }

  /** Set the field to given value. */
  @SneakyThrows
  protected void setField(String field, Object value) {
    log.trace("Setting {} to {}", field, value);
    setter(field).invoke(sample, value);
  }

  /** Finds the getter method of the property provided in order to access the value. */
  @SneakyThrows
  private Method setter(String name) {
    Method setter = null;
    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(sample.getClass(), name);
    if (pd != null) {
      setter = pd.getWriteMethod();
    }
    if (setter == null) {
      setter = BeanUtils.findMethod(sample.getClass(), name, field(name).getType());
    }
    if (setter == null) {
      throw new IllegalArgumentException(
          "Cannot find Java bean property or fluent setter: "
              + sample.getClass().getName()
              + "."
              + name);
    }
    return setter;
  }

  /** Unset all related fields. */
  protected void unsetFields() {
    fields.forEach(field -> setField(field, null));
  }

  /**
   * Implementations should verify the combinations of setting and unsetting fields. Use {@link
   * #unsetFields()}, {@link #setField(String)}, and/or {@link #setField(String, Object)} along with
   * {@link #assertProblems(int)} to check classes.
   */
  public abstract void verify();
}
