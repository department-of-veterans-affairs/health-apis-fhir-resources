package gov.va.api.health.stu3.api.datatypes;

import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.stu3.api.RoundTrip;
import gov.va.api.health.stu3.api.samples.SampleDataTypes;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import org.junit.jupiter.api.Test;

public class SignatureTest {

  public SampleDataTypes data = SampleDataTypes.get();

  @Test
  public void signature() {
    RoundTrip.assertRoundTrip(data.signature());
  }

  @Test
  public void signatureMissingBothWhoUriAndWhoReferenceIsInvalid() {
    Signature psuedoSignature =
        Signature.builder()
            .id("1234")
            .type(SampleDataTypes.get().codingList())
            .when("2000-01-01T00:00:00-00:00")
            .contentType("contentTypeTest")
            .blob("aGVsbG8=")
            .build();
    Set<ConstraintViolation<Signature>> problems =
        Validation.buildDefaultValidatorFactory().getValidator().validate(psuedoSignature);
    assertThat(problems.size()).isEqualTo(1);
  }

  @Test
  public void signatureWithBothWhoUriAndWhoReferenceIsInvalid() {
    Signature psuedoSignature =
        Signature.builder()
            .id("1234")
            .type(SampleDataTypes.get().codingList())
            .when("2000-01-01T00:00:00-00:00")
            .whoUri("123456")
            .whoReference(SampleDataTypes.get().reference())
            .contentType("contentTypeTest")
            .blob("aGVsbG8=")
            .build();
    Set<ConstraintViolation<Signature>> problems =
        Validation.buildDefaultValidatorFactory().getValidator().validate(psuedoSignature);
    assertThat(problems.size()).isEqualTo(1);
  }

  @Test
  public void signatureWithOnlyWhoReferenceIsValid() {
    Signature psuedoSignature =
        Signature.builder()
            .id("1234")
            .type(SampleDataTypes.get().codingList())
            .when("2000-01-01T00:00:00-00:00")
            .whoReference(SampleDataTypes.get().reference())
            .contentType("contentTypeTest")
            .blob("aGVsbG8=")
            .build();
    Set<ConstraintViolation<Signature>> problems =
        Validation.buildDefaultValidatorFactory().getValidator().validate(psuedoSignature);
    assertThat(problems.size()).isEqualTo(0);
  }

  @Test
  public void signatureWithOnlyWhoUriIsValid() {
    Signature psuedoSignature =
        Signature.builder()
            .id("1234")
            .type(SampleDataTypes.get().codingList())
            .when("2000-01-01T00:00:00-00:00")
            .whoUri("123456")
            .contentType("contentTypeTest")
            .blob("aGVsbG8=")
            .build();
    Set<ConstraintViolation<Signature>> problems =
        Validation.buildDefaultValidatorFactory().getValidator().validate(psuedoSignature);
    assertThat(problems.size()).isEqualTo(0);
  }
}
