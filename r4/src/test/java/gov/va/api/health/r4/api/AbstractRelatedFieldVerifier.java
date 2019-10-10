package gov.va.api.health.r4.api;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.autoconfig.configuration.JacksonConfig;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.ContactDetail;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.Duration;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Money;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Range;
import gov.va.api.health.r4.api.datatypes.Ratio;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.datatypes.UsageContext;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.samples.SampleDataTypes;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
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
  @Getter private final Map<Class<?>, Supplier<?>> knownTypes = createKnownTypes();
  /**
   * Used to create sample String values for fields based on the Pattern regex constraint on the
   * field.
   */
  @Getter private final Map<String, Supplier<?>> stringTypes = createKnownStringTypes();

  /**
   * The fieldSelector predicate will be checked for each field declared in the sample's class. A
   * true return value will include the field in the group, so setting and unsetting.
   */
  protected AbstractRelatedFieldVerifier(T sample, Predicate<String> fieldSelector) {
    this.sample = sample;
    fields =
        Arrays.stream(sample.getClass().getDeclaredFields())
            .map(Field::getName)
            .filter(fieldSelector)
            .collect(toList());
  }

  private static Map<String, Supplier<?>> createKnownStringTypes() {
    Map<String, Supplier<?>> suppliers = new HashMap<>();
    suppliers.put("", () -> "hello");
    suppliers.put(Fhir.BOOLEAN, () -> "true");
    suppliers.put(Fhir.BASE64, () -> "SSBqdXN0IGF0ZSBhIHBlYW51dAo=");
    suppliers.put(Fhir.CODE, () -> "code");
    suppliers.put(Fhir.URI, () -> "http://example.com");
    suppliers.put(Fhir.DATE, () -> "2005-01-21");
    suppliers.put(Fhir.DATETIME, () -> "2005-01-21T07:57:00Z");
    suppliers.put(Fhir.DECIMAL, () -> "0.03");
    suppliers.put(Fhir.ID, () -> "id");
    suppliers.put(Fhir.INSTANT, () -> "2005-01-21T07:57:00.000Z");
    suppliers.put(Fhir.INTEGER, () -> "10");
    suppliers.put(Fhir.OID, () -> "urn:oid:0.1");
    suppliers.put(Fhir.POSITIVE_INT, () -> "1");
    suppliers.put(Fhir.STRING, () -> "hello");
    suppliers.put(Fhir.TIME, () -> "07:57:00.000");
    suppliers.put(Fhir.UNSIGNED_INT, () -> "0");
    suppliers.put(Fhir.XHTML, () -> "<div>html</div>");
    return suppliers;
  }

  private static Map<Class<?>, Supplier<?>> createKnownTypes() {
    SampleDataTypes dataTypes = SampleDataTypes.get();
    Map<Class<?>, Supplier<?>> suppliers = new HashMap<>();
    suppliers.put(Address.class, dataTypes::address);
    suppliers.put(Attachment.class, dataTypes::attachment);
    suppliers.put(CodeableConcept.class, dataTypes::codeableConcept);
    suppliers.put(Coding.class, dataTypes::coding);
    suppliers.put(ContactDetail.class, dataTypes::contactDetail);
    suppliers.put(ContactPoint.class, dataTypes::contactPoint);
    suppliers.put(Duration.class, dataTypes::duration);
    suppliers.put(Extension.class, dataTypes::extension);
    suppliers.put(HumanName.class, dataTypes::humanName);
    suppliers.put(Identifier.class, dataTypes::identifier);
    suppliers.put(Money.class, dataTypes::money);
    suppliers.put(Period.class, dataTypes::period);
    suppliers.put(Quantity.class, dataTypes::quantity);
    suppliers.put(Range.class, dataTypes::range);
    suppliers.put(Ratio.class, dataTypes::ratio);
    suppliers.put(Signature.class, dataTypes::signature);
    suppliers.put(SimpleQuantity.class, dataTypes::simpleQuantity);
    suppliers.put(Reference.class, dataTypes::reference);
    suppliers.put(SimpleResource.class, dataTypes::resource);
    suppliers.put(UsageContext.class, dataTypes::usageContext);
    return suppliers;
  }

  /** Validate and verify the given number of problems are reported. */
  @SneakyThrows
  protected void assertProblems(int count) {
    Set<ConstraintViolation<T>> problems =
        Validation.buildDefaultValidatorFactory().getValidator().validate(sample);
    if (problems.size() == count) {
      return;
    }
    log.info(
        JacksonConfig.createMapper().writerWithDefaultPrettyPrinter().writeValueAsString(sample));
    problems.forEach(p -> log.error("{}", p));
    assertThat(problems.size()).isEqualTo(count);
  }

  private Supplier<?> enumSupplier(Class<? extends Enum<?>> type) {
    return () -> type.getEnumConstants()[0];
  }

  @SneakyThrows
  protected Field field(String name) {
    Field field = sample.getClass().getDeclaredField(name);
    assertThat(field).withFailMessage("Cannot determine field type: " + name).isNotNull();
    return field;
  }

  /** Set the field to an automatically determined value based on it's type. */
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
    assertThat(supplier)
        .withFailMessage("Unknown value type for field: " + name + " type: " + field.getType())
        .isNotNull();
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
