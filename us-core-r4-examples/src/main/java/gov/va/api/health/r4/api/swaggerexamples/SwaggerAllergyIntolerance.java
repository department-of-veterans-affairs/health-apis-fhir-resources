package gov.va.api.health.r4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.AllergyIntolerance;

public final class SwaggerAllergyIntolerance {
  /** Example AllergyIntolerance. */
  public static AllergyIntolerance allergyIntolerance() {
    return AllergyIntolerance.builder()
        .id("I2-6F9A021B07D553C88CCEB49A694D4AD9")
        .onsetDateTime("1995-04-30T01:15:52Z")
        .patient(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/r4/v0/Patient/1017283148V813263")
                .display("Mr. Aurelio227 Cruickshank494")
                .build())
        .clinicalStatus(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .system(
                                "http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical")
                            .code("active")
                            .build()))
                .build())
        .verificationStatus(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .system(
                                "http://terminology.hl7.org/CodeSystem/allergyintolerance-verification")
                            .code("confirmed")
                            .build()))
                .build())
        .type(AllergyIntolerance.Type.allergy)
        .category(asList(AllergyIntolerance.Category.food))
        .note(
            asList(
                Annotation.builder()
                    .time("1995-04-30T01:15:52Z")
                    .text("Allergy to peanuts")
                    .build()))
        .reaction(
            asList(
                AllergyIntolerance.Reaction.builder()
                    .manifestation(
                        asList(
                            CodeableConcept.builder()
                                .coding(
                                    asList(
                                        Coding.builder()
                                            .display("Inflammation of Skin")
                                            .system("urn:oid:2.16.840.1.113883.6.233")
                                            .code("2000001")
                                            .build()))
                                .text("Inflammation of Skin")
                                .build()))
                    .build()))
        .build();
  }

  /** Example bundle. */
  public static AllergyIntolerance.Bundle allergyIntoleranceBundle() {
    return AllergyIntolerance.Bundle.builder()
        .type(AbstractBundle.BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/r4/v0/AllergyIntolerance?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/r4/v0/AllergyIntolerance?patient=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(BundleLink.LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/r4/v0/AllergyIntolerance?patient=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                AllergyIntolerance.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/r4/v0/AllergyIntolerance/I2-6F9A021B07D553C88CCEB49A694D4AD9")
                    .resource(allergyIntolerance())
                    .search(
                        AbstractEntry.Search.builder().mode(AbstractEntry.SearchMode.match).build())
                    .build()))
        .build();
  }
}
