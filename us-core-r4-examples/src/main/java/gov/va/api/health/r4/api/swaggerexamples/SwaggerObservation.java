package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Observation;
import java.math.BigDecimal;

public class SwaggerObservation {
  /**
   * An example Observation.
   *
   * @return an example Observation.
   */
  public static Observation observation() {
    return Observation.builder()
        .id("I2-RRCTYID4OWJHGGFQ7S7YPH4G6XVBG6D7VORRKERYLTZD7VBQLCJQ0000")
        .status(Observation.ObservationStatus._final)
        .category(
            asList(
                CodeableConcept.builder()
                    .coding(
                        asList(
                            Coding.builder()
                                .system(
                                    "http://terminology.hl7.org/CodeSystem/observation-category")
                                .code("laboratory")
                                .display("Laboratory")
                                .build()))
                    .build()))
        .code(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .system("http://loinc.org")
                            .code("32623-1")
                            .display("Platelet mean volume [Entitic volume] in Blood by ")
                            .build()))
                .build())
        .subject(
            Reference.builder()
                .reference(
                    "https://sandbox-api.va.gov/services/argonaut/v0/Patient/1017283148V813263")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .effectiveDateTime("2017-04-24T01:15:52Z")
        .issued("2017-04-24T01:15:52Z")
        .valueQuantity(
            Quantity.builder()
                .value(new BigDecimal("10.226877417360429"))
                .unit("fL")
                .system("http://unitsofmeasure.org")
                .code("fL")
                .build())
        .build();
  }

  /**
   * An example Observation.Bundle.
   *
   * @return an example Observation.Bundle.
   */
  public static Observation.Bundle observationBundle() {
    return Observation.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Observation.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation/I2-RRCTYID4OWJHGGFQ7S7YPH4G6XVBG6D7VORRKERYLTZD7VBQLCJQ0000")
                    .resource(observation())
                    .search(
                        AbstractEntry.Search.builder().mode(AbstractEntry.SearchMode.match).build())
                    .build()))
        .build();
  }
}
