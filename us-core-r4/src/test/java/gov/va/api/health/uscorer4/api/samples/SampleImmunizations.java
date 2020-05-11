package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.uscorer4.api.resources.Immunization;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleImmunizations {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Immunization.Education education() {
    return Immunization.Education.builder()
        .id("654")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .documentType("GoodNightType")
        .reference("http://GoodNightType.com")
        .presentationDate("2015-04-15T04:00:00Z")
        .publicationDate("2015-04-15T04:00:00Z")
        .build();
  }

  public Immunization immunization() {
    return Immunization.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .status(Immunization.Status.entered_in_error)
        .statusReason(codeableConcept())
        .vaccineCode(codeableConcept())
        .patient(reference())
        .encounter(reference())
        .occurrenceDateTime("2015-04-15T04:00:00Z")
        .recorded("2015-04-15T04:00:00Z")
        .primarySource(Boolean.FALSE)
        .reportOrigin(codeableConcept())
        .location(reference())
        .manufacturer(reference())
        .lotNumber("1")
        .expirationDate("2015-04-15T04:00:00Z")
        .site(codeableConcept())
        .route(codeableConcept())
        .doesQuantity(simpleQuantity())
        .performer(singletonList(performer()))
        .note(singletonList(annotation()))
        .reasonCode(codeableConcept())
        .reasonReference(reference())
        .isSubpotent(Boolean.TRUE)
        .subpotentReason(singletonList(codeableConcept()))
        .education(singletonList(education()))
        .programEligibility(singletonList(codeableConcept()))
        .fundingSource(codeableConcept())
        .reaction(singletonList(reaction()))
        .protocolApplied(singletonList(protocolApplied()))
        .build();
  }

  public Immunization.Performer performer() {
    return Immunization.Performer.builder()
        .id("987")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .actor(reference())
        .function(codeableConcept())
        .build();
  }

  public Immunization.ProtocolApplied protocolApplied() {
    return Immunization.ProtocolApplied.builder()
        .id("0")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .series("GoodNightSeries")
        .authority(reference())
        .targetDisease(singletonList(codeableConcept()))
        .seriesDosesPositiveInt("1")
        .doseNumberString("2")
        .build();
  }

  public Immunization.Reaction reaction() {
    return Immunization.Reaction.builder()
        .id("321")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .date("2015-04-15T04:00:00Z")
        .detail(reference())
        .reported(Boolean.FALSE)
        .build();
  }
}
