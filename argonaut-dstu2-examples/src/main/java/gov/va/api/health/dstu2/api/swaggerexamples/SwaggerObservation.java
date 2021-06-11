package gov.va.api.health.dstu2.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.Search;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.datatypes.Coding;
import gov.va.api.health.dstu2.api.datatypes.Quantity;
import gov.va.api.health.dstu2.api.elements.Reference;
import gov.va.api.health.dstu2.api.resources.Observation;

public class SwaggerObservation {
  /**
   * An example Observation.
   *
   * @return an example Observation.
   */
  public static Observation observation() {
    return Observation.builder()
        .id("7889e577-88d6-5e6f-8a4d-fb6988b7b3c1")
        .status(Observation.Status._final)
        .category(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .system("http://hl7.org/fhir/observation-category")
                            .code("laboratory")
                            .display("Laboratory")
                            .build()))
                .build())
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
                .reference("https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .effectiveDateTime("2017-04-24T01:15:52Z")
        .issued("2017-04-24T01:15:52Z")
        .valueQuantity(
            Quantity.builder()
                .value(10.226877417360429)
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
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Observation.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Observation/7889e577-88d6-5e6f-8a4d-fb6988b7b3c1")
                    .resource(
                        Observation.builder()
                            .id("7889e577-88d6-5e6f-8a4d-fb6988b7b3c1")
                            .status(Observation.Status._final)
                            .category(
                                CodeableConcept.builder()
                                    .coding(
                                        asList(
                                            Coding.builder()
                                                .system("http://hl7.org/fhir/observation-category")
                                                .code("laboratory")
                                                .display("Laboratory")
                                                .build()))
                                    .build())
                            .code(
                                CodeableConcept.builder()
                                    .coding(
                                        asList(
                                            Coding.builder()
                                                .system("http://loinc.org")
                                                .code("32623-1")
                                                .display("Platelet mean volume in Blood by ")
                                                .build()))
                                    .build())
                            .subject(
                                Reference.builder()
                                    .reference(
                                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                                    .display("Mr. Aurelio227 Cruickshank494")
                                    .build())
                            .effectiveDateTime("2017-04-24T01:15:52Z")
                            .issued("2017-04-24T01:15:52Z")
                            .valueQuantity(
                                Quantity.builder()
                                    .value(10.226877417360429)
                                    .unit("fL")
                                    .system("http://unitsofmeasure.org")
                                    .code("fL")
                                    .build())
                            .build())
                    .search(Search.builder().mode(SearchMode.match).build())
                    .build()))
        .build();
  }
}
