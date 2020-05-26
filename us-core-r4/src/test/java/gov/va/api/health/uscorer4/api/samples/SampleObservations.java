package gov.va.api.health.uscorer4.api.samples;

import gov.va.api.health.uscorer4.api.resources.Observation;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import java.util.List;

import static java.util.Collections.singletonList;

@NoArgsConstructor(staticName = "get")
public class SampleObservations {
    @Delegate
    SampleDataTypes dataTypes = SampleDataTypes.get();

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
                .category(singletonList(observationCategorySliceCodeableConcept()))
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

    public Observation.ObservationCategorySliceCodeableConcept observationCategorySliceCodeableConcept(){
        return Observation.ObservationCategorySliceCodeableConcept.builder()
                .id("1234")
                .extension(singletonList(extension()))
                .coding(List.of(Observation.ObservationCategorySliceCoding.builder()
                        .id("1234")
                        .extension(singletonList(extension()))
                        .system("http://HelloSystem.com")
                        .version("Hello Version")
                        .code("Hello Code")
                        .display("Hello Display")
                        .userSelected(true)
                        .build()))
                .text("Best Test Text")
                .build();
    }
}
