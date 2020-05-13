package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.uscorer4.api.resources.Medication;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleMedications {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Medication.Batch batch() {
    return Medication.Batch.builder()
        .id("806")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .lotNumber("9995")
        .expirationDate("2015-04-15T04:00:00Z")
        .build();
  }

  public Medication.Ingredient ingredient() {
    return Medication.Ingredient.builder()
        .id("509")
        .extension(singletonList(extension()))
        .itemCodeableConcept(codeableConcept())
        .itemReference(reference())
        .build();
  }

  public Medication medication() {
    return Medication.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .code(codeableConcept())
        .status(Medication.Status.entered_in_error)
        .manufacturer(reference())
        .form(codeableConcept())
        .amount(ratio())
        .ingredient(singletonList(ingredient()))
        .batch(singletonList(batch()))
        .build();
  }
}
