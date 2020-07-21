package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.DataAbsentReason;
import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Insurance;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Item;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Outcome;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Purpose;
import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse.Status;

public class SwaggerCoverageEligibilityResponse {

  /**
   * Example CoverageEligibilityResponse.
   *
   * @return an example CoverageEligibilityResponse.
   */
  public static CoverageEligibilityResponse coverageEligibilityResponse() {
    return CoverageEligibilityResponse.builder()
        .id("1008679665V880686")
        .resourceType("CoverageEligibilityResponse")
        .identifier(
            singletonList(
                Identifier.builder()
                    .system("http://www.va.gov/FHIR/R4/coverageeligibilityresponse")
                    .value("1008679665V880686")
                    .build()))
        .status(Status.active)
        .purpose(singletonList(Purpose.discovery))
        .patient(Reference.builder().display("Patient/1008679665V880686").build())
        .created("2019-02-21T23:44:32.000-06:00")
        .request(null)
        ._request(DataAbsentReason.of(DataAbsentReason.Reason.unsupported))
        .outcome(Outcome.complete)
        .insurer(Reference.builder().display("Veterans Administration").build())
        .insurance(
            singletonList(
                Insurance.builder()
                    .coverage(null)
                    ._coverage(DataAbsentReason.of(DataAbsentReason.Reason.unsupported))
                    .benefitPeriod(Period.builder().start("2019-02-21T23:44:32.000-06:00").build())
                    .item(
                        singletonList(
                            Item.builder()
                                .category(
                                    CodeableConcept.builder()
                                        .coding(
                                            singletonList(
                                                Coding.builder()
                                                    .code("U")
                                                    .display("Urgent Care")
                                                    .build()))
                                        .build())
                                .build()))
                    .build()))
        .build();
  }

  /**
   * Example Bundle.
   *
   * @return an example bundle.
   */
  public static CoverageEligibilityResponse.Bundle coverageEligibilityResponseBundle() {
    return CoverageEligibilityResponse.Bundle.builder()
        .resourceType("Bundle")
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/CoverageEligibilityResponse?patient=1008679665V880686&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/CoverageEligibilityResponse?patient=1008679665V880686&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/CoverageEligibilityResponse?patient=1008679665V880686&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                CoverageEligibilityResponse.Entry.builder()
                    .resource(coverageEligibilityResponse())
                    .build()))
        .build();
  }
}
