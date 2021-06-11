package gov.va.api.health.dstu2.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.Search;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.datatypes.Coding;
import gov.va.api.health.dstu2.api.elements.Reference;
import gov.va.api.health.dstu2.api.resources.Procedure;

public class SwaggerProcedure {
  /**
   * An example Procedure.
   *
   * @return an example Procedure.
   */
  public static Procedure procedure() {
    return Procedure.builder()
        .id("532070f1-cb7b-582e-9380-9e0ef27bc817")
        .subject(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .status(Procedure.Status.completed)
        .code(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .display("Documentation of current medications")
                            .system("http://www.ama-assn.org/go/cpt")
                            .code("XXXXX")
                            .build()))
                .build())
        .notPerformed(false)
        .performedDateTime("2017-04-24T01:15:52Z")
        .build();
  }

  /**
   * An example Procedure.Bundle.
   *
   * @return an example Procedure.Bundle.
   */
  public static Procedure.Bundle procedureBundle() {
    return Procedure.Bundle.builder()
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Procedure?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Procedure?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Procedure?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Procedure.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Procedure/532070f1-cb7b-582e-9380-9e0ef27bc817")
                    .resource(
                        Procedure.builder()
                            .id("532070f1-cb7b-582e-9380-9e0ef27bc817")
                            .subject(
                                Reference.builder()
                                    .reference(
                                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient/2000163")
                                    .display("Mr. Aurelio227 Cruickshank494")
                                    .build())
                            .status(Procedure.Status.completed)
                            .code(
                                CodeableConcept.builder()
                                    .coding(
                                        asList(
                                            Coding.builder()
                                                .display("Documentation of current medications")
                                                .system("http://www.ama-assn.org/go/cpt")
                                                .code("XXXXX")
                                                .build()))
                                    .build())
                            .notPerformed(false)
                            .performedDateTime("2017-04-24T01:15:52Z")
                            .build())
                    .search(Search.builder().mode(SearchMode.match).build())
                    .build()))
        .build();
  }
}
