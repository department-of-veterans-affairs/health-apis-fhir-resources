package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Narrative.NarrativeStatus;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.Coverage;
import gov.va.api.health.r4.api.resources.Coverage.CoverageClass;
import gov.va.api.health.r4.api.resources.Coverage.Status;
import java.util.Arrays;
import java.util.Collections;

public class SwaggerCoverage {
  /**
   * An example Coverage.
   *
   * @return an example Coverage.
   */
  public static Coverage coverage() {
    return Coverage.builder()
        .id("9876B1")
        .text(
            Narrative.builder()
                .status(NarrativeStatus.generated)
                .div(
                    "<div xmlns=\"http://www.w3.org/1999/xhtml\">A human-readable rendering of the coverage</div>")
                .build())
        .identifier(
            Collections.singletonList(
                Identifier.builder()
                    .system("http://benefitsinc.com/certificate")
                    .value("12345")
                    .build()))
        .status(Status.active)
        .type(
            CodeableConcept.builder()
                .coding(
                    Collections.singletonList(
                        Coding.builder()
                            .system("http://terminology.hl7.org/CodeSystem/v3-ActCode")
                            .code("EHCPOL")
                            .display("extended healthcare")
                            .build()))
                .build())
        .policyHolder(
            Reference.builder().reference("http://benefitsinc.com/FHIR/Organization/CBI35").build())
        .subscriber(Reference.builder().reference("Patient/4").build())
        .beneficiary(Reference.builder().reference("Patient/4").build())
        .dependent("0")
        .relationship(
            CodeableConcept.builder()
                .coding(Collections.singletonList(Coding.builder().code("self").build()))
                .build())
        .period(Period.builder().start("2011-05-23").end("2012-05-23").build())
        .payor(Collections.singletonList(Reference.builder().reference("Organization/2").build()))
        .coverageClass(
            Arrays.asList(
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("group")
                                        .build()))
                            .build())
                    .value("CB135")
                    .name("Corporate Baker's Inc. Local #35")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("subgroup")
                                        .build()))
                            .build())
                    .value("123")
                    .name("Trainee Part-time Benefits")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("plan")
                                        .build()))
                            .build())
                    .value("B37FC")
                    .name("Full Coverage: Medical, Dental, Pharmacy, Vision, EHC")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("subplan")
                                        .build()))
                            .build())
                    .value("P7")
                    .name("Includes afterlife benefits")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("class")
                                        .build()))
                            .build())
                    .value("SILVER")
                    .name("Silver: Family Plan spouse only")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("subclass")
                                        .build()))
                            .build())
                    .value("Tier2")
                    .name("Low deductable, max $20 copay")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("sequence")
                                        .build()))
                            .build())
                    .value("9")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("rxid")
                                        .build()))
                            .build())
                    .value("MDF12345")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("rxbin")
                                        .build()))
                            .build())
                    .value("987654")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("rxgroup")
                                        .build()))
                            .build())
                    .value("M35PT")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("rxpcn")
                                        .build()))
                            .build())
                    .value("234516")
                    .build(),
                CoverageClass.builder()
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                Collections.singletonList(
                                    Coding.builder()
                                        .system(
                                            "http://terminology.hl7.org/CodeSystem/coverage-class")
                                        .code("sequence")
                                        .build()))
                            .build())
                    .value("9")
                    .build()))
        .build();
  }

  /**
   * An example Bundle.
   *
   * @return an example Bundle.
   */
  public static Coverage.Bundle coverageBundle() {
    return Coverage.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Coverage?patient=1008679665V880686&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Coverage?patient=1008679665V880686&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/fhir/v0/r4/Coverage?patient=1008679665V880686&page=1&_count=15")
                    .build()))
        .entry(asList(Coverage.Entry.builder().resource(coverage()).build()))
        .build();
  }
}
