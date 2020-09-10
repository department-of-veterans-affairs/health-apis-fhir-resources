package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;

import gov.va.api.health.r4.api.ExactlyOneOfVerifier;
import gov.va.api.health.r4.api.ZeroOrOneOfVerifier;
import gov.va.api.health.r4.api.samples.SampleMedicationRequests;
import org.junit.jupiter.api.Test;

public class MedicationRequestTest {

  private final SampleMedicationRequests data = SampleMedicationRequests.get();

  @Test
  public void medicationRequest() {
    assertRoundTrip(data.medicationRequest());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.medicationRequest().reportedBoolean(null).reportedReference(data.reference()))
        .fieldPrefix("reported")
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(data.medicationRequest().reportedBoolean(true).reportedReference(null))
        .fieldPrefix("reported")
        .build()
        .verify();

    ExactlyOneOfVerifier.builder()
        .sample(
            data.medicationRequest()
                .medicationCodeableConcept(null)
                .medicationReference(data.reference()))
        .fieldPrefix("medication")
        .build()
        .verify();

    ExactlyOneOfVerifier.builder()
        .sample(
            data.medicationRequest()
                .medicationCodeableConcept(data.codeableConcept())
                .medicationReference(null))
        .fieldPrefix("medication")
        .build()
        .verify();

    ExactlyOneOfVerifier.builder()
        .sample(
            data.substitution().allowedBoolean(null).allowedCodeableConcept(data.codeableConcept()))
        .fieldPrefix("allowed")
        .build()
        .verify();

    ExactlyOneOfVerifier.builder()
        .sample(data.substitution().allowedBoolean(true).allowedCodeableConcept(null))
        .fieldPrefix("allowed")
        .build()
        .verify();
  }
}
