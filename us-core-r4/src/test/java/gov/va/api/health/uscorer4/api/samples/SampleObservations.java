package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.uscorer4.api.resources.Observation;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleObservations {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public CodeableConcept category() {
    return CodeableConcept.builder()
        .coding(
            singletonList(
                Coding.builder()
                    .system("http://terminology.hl7.org/CodeSystem/observation-category")
                    .code("laboratory")
                    .build()))
        .build();
  }

  public Observation.Component component() {
    return Observation.Component.builder()
        .id("1234")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .code(codeableConcept())
        .valuePeriod(period())
        .dataAbsentReason(codeableConcept())
        .interpretation(singletonList(codeableConcept()))
        .referenceRange(singletonList(referenceRange()))
        .build();
  }

  public Observation observation() {
    return Observation.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .basedOn(singletonList(reference()))
        .partOf(singletonList(reference()))
        .status(Observation.ObservationStatus.unknown)
        .category(singletonList(category()))
        .code(codeableConcept())
        .subject(reference())
        .focus(singletonList(reference()))
        .encounter(reference())
        .effectiveDateTime("2015-04-15T04:00:00Z")
        .issued("2015-04-15T04:00:00Z")
        .performer(singletonList(reference()))
        .valueQuantity(quantity())
        .dataAbsentReason(codeableConcept())
        .interpretation(singletonList(codeableConcept()))
        .note(singletonList(annotation()))
        .bodySite(codeableConcept())
        .method(codeableConcept())
        .specimen(reference())
        .device(reference())
        .referenceRange(singletonList(referenceRange()))
        .hasMember(singletonList(reference()))
        .derivedFrom(singletonList(reference()))
        .component(singletonList(component()))
        .build();
  }

  public Observation.ReferenceRange referenceRange() {
    return Observation.ReferenceRange.builder()
        .id("1234")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .low(simpleQuantity())
        .high(simpleQuantity())
        .type(codeableConcept())
        .appliesTo(singletonList(codeableConcept()))
        .age(range())
        .text("Best Test Text")
        .build();
  }
}
