package gov.va.api.health.uscorer4.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.uscorer4.api.resources.AllergyIntolerance;

public final class SwaggerAllergyIntolerance {
  /** Example AllergyIntolerance. */
  public static AllergyIntolerance allergyIntolerance() {
    return AllergyIntolerance.builder()
        .resourceType("AllergyIntolerance")
        .id("6f9a021b-07d5-53c8-8cce-b49a694d4ad9")
        .onsetDateTime("1995-04-30T01:15:52Z")
        .patient(
            Reference.builder()
                .reference("https://sandbox-api.va.gov/services/r4/v0/Patient/2000163")
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
        .category(AllergyIntolerance.Category.food)
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
        .resourceType("Bundle")
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
                        "https://sandbox-api.va.gov/services/r4/v0/AllergyIntolerance/6f9a021b-07d5-53c8-8cce-b49a694d4ad9")
                    .resource(
                        AllergyIntolerance.builder()
                            .resourceType("AllergyIntolerance")
                            .id("e2019e0c-fa38-596d-b966-9b86926959a7")
                            .onsetDateTime("1995-04-30T01:15:52Z")
                            .patient(
                                Reference.builder()
                                    .reference(
                                        "https://sandbox-api.va.gov/services/r4/v0/Patient/2000163")
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
                            .note(
                                asList(
                                    Annotation.builder()
                                        .time("1995-04-30T01:15:52Z")
                                        .text("Allergy to bee venom")
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
                                                                .display("Sneezing and Coughing")
                                                                .system("urn:oid:2.16.840.1.233")
                                                                .code("2000004")
                                                                .build()))
                                                    .text("Sneezing and Coughing")
                                                    .build()))
                                        .build()))
                            .build())
                    .search(
                        AbstractEntry.Search.builder().mode(AbstractEntry.SearchMode.match).build())
                    .build()))
        .build();
  }
}
