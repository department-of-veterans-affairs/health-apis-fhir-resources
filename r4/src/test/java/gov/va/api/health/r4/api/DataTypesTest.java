package gov.va.api.health.r4.api;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.OperationOutcome;
import gov.va.api.health.r4.api.samples.SampleDataTypes;
import gov.va.api.health.r4.api.samples.SampleKnownTypes;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import java.util.Arrays;
import org.junit.Test;

public class DataTypesTest {

  private final SampleDataTypes data = SampleDataTypes.get();
  private final SampleKnownTypes types = SampleKnownTypes.get();

  @Test
  public void operationOutcome() {
    assertRoundTrip(
        OperationOutcome.builder()
            .id("4321")
            .meta(data.meta())
            .implicitRules("http://HelloRules.com")
            .language("Hello Language")
            .text(data.narrative())
            .contained(singletonList(data.resource()))
            .modifierExtension(
                Arrays.asList(
                    data.extension(),
                    data.extensionWithQuantity(),
                    data.extensionWithRatio(),
                    data.extensionWithUsageContext(),
                    data.extensionWithContactDetail()))
            .issue(singletonList(data.issue()))
            .build());
  }

  @Test
  public void relatedGroups() {
    ZeroOrOneOfVerifier.builder()
        .sample(data.annotation().authorReference(null).authorString("string"))
        .fieldPrefix("author")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(data.annotation().authorReference(data.reference()).authorString(null))
        .fieldPrefix("author")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(data.dosage().asNeededBoolean(null).asNeededCodeableConcept(data.codeableConcept()))
        .fieldPrefix("asNeeded")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(data.dosage().asNeededBoolean(true).asNeededCodeableConcept(null))
        .fieldPrefix("asNeeded")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(
            data.dosage().doseAndRate().get(0).doseRange(null).doseQuantity(data.simpleQuantity()))
        .fieldPrefix("dose")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(data.dosage().doseAndRate().get(0).doseRange(data.range()).doseQuantity(null))
        .fieldPrefix("dose")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(
            data.dosage()
                .doseAndRate()
                .get(0)
                .rateRatio(null)
                .rateRange(null)
                .rateQuantity(data.simpleQuantity()))
        .fieldPrefix("rate")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(
            data.dosage()
                .doseAndRate()
                .get(0)
                .rateRatio(null)
                .rateRange(data.range())
                .rateQuantity(null))
        .fieldPrefix("rate")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(
            data.dosage()
                .doseAndRate()
                .get(0)
                .rateRatio(data.ratio())
                .rateRange(null)
                .rateQuantity(null))
        .fieldPrefix("rate")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(
            data.dosage()
                .timing()
                .repeat()
                .boundsDuration(null)
                .boundsRange(null)
                .boundsPeriod(data.period()))
        .fieldPrefix("bounds")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(
            data.dosage()
                .timing()
                .repeat()
                .boundsDuration(null)
                .boundsRange(data.range())
                .boundsPeriod(null))
        .fieldPrefix("bounds")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();

    ZeroOrOneOfVerifier.builder()
        .sample(
            data.dosage()
                .timing()
                .repeat()
                .boundsDuration(data.duration())
                .boundsRange(null)
                .boundsPeriod(null))
        .fieldPrefix("bounds")
        .knownTypes(types.knownTypes())
        .stringTypes(types.knownStringTypes())
        .build()
        .verify();
  }
}
