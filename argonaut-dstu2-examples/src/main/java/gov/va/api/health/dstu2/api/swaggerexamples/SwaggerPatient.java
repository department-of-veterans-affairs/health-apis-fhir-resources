package gov.va.api.health.dstu2.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.dstu2.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.Search;
import gov.va.api.health.dstu2.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.dstu2.api.bundle.BundleLink;
import gov.va.api.health.dstu2.api.bundle.BundleLink.LinkRelation;
import gov.va.api.health.dstu2.api.datatypes.Address;
import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.datatypes.Coding;
import gov.va.api.health.dstu2.api.datatypes.ContactPoint;
import gov.va.api.health.dstu2.api.datatypes.HumanName;
import gov.va.api.health.dstu2.api.datatypes.Identifier;
import gov.va.api.health.dstu2.api.elements.Extension;
import gov.va.api.health.dstu2.api.resources.Patient;

public class SwaggerPatient {
  /**
   * An example Patient.
   *
   * @return an example Patient.
   */
  public static Patient patient() {
    return Patient.builder()
        .id("2000163")
        .extension(
            asList(
                Extension.builder()
                    .url("http://fhir.org/guides/argonaut/StructureDefinition/argo-race")
                    .extension(
                        asList(
                            Extension.builder()
                                .url("ombCategory")
                                .valueCoding(
                                    Coding.builder()
                                        .system("http://hl7.org/fhir/v3/Race")
                                        .code("2016-3")
                                        .display("White")
                                        .build())
                                .build(),
                            Extension.builder().url("text").valueString("White").build()))
                    .build(),
                Extension.builder()
                    .url("http://fhir.org/guides/argonaut/StructureDefinition/argo-ethnicity")
                    .extension(
                        asList(
                            Extension.builder()
                                .url("ombCategory")
                                .valueCoding(
                                    Coding.builder()
                                        .system("http://hl7.org/fhir/ValueSet/v3-Ethnicity")
                                        .code("2186-5")
                                        .display("Not Hispanic or Latino")
                                        .build())
                                .build(),
                            Extension.builder()
                                .url("text")
                                .valueString("Not Hispanic or Latino")
                                .build()))
                    .build(),
                Extension.builder()
                    .url("http://fhir.org/guides/argonaut/StructureDefinition/argo-birthsex")
                    .valueCode("M")
                    .build()))
        .identifier(
            asList(
                Identifier.builder()
                    .use(Identifier.IdentifierUse.usual)
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                asList(
                                    Coding.builder()
                                        .system("http://hl7.org/fhir/v2/0203")
                                        .code("MR")
                                        .build()))
                            .build())
                    .system("http://va.gov/mpi")
                    .value("2000163")
                    .build(),
                Identifier.builder()
                    .use(Identifier.IdentifierUse.official)
                    .type(
                        CodeableConcept.builder()
                            .coding(
                                asList(
                                    Coding.builder()
                                        .system("http://hl7.org/fhir/v2/0203")
                                        .code("SB")
                                        .build()))
                            .build())
                    .system("http://hl7.org/fhir/sid/us-ssn")
                    .value("999-61-4803")
                    .build()))
        .name(
            asList(
                HumanName.builder()
                    .use(HumanName.NameUse.usual)
                    .text("Mr. Aurelio227 Cruickshank494")
                    .family(asList("Cruickshank494"))
                    .given(asList("Aurelio227"))
                    .build()))
        .telecom(
            asList(
                ContactPoint.builder()
                    .system(ContactPoint.ContactPointSystem.phone)
                    .value("5555191065")
                    .use(ContactPoint.ContactPointUse.mobile)
                    .build(),
                ContactPoint.builder()
                    .system(ContactPoint.ContactPointSystem.email)
                    .value("Aurelio227.Cruickshank494@email.example")
                    .build()))
        .gender(Patient.Gender.male)
        .birthDate("1995-02-06")
        .deceasedBoolean(false)
        .address(
            asList(
                Address.builder()
                    .line(asList("909 Rohan Highlands"))
                    .city("Mesa")
                    .state("Arizona")
                    .postalCode("85120")
                    .build()))
        .maritalStatus(
            CodeableConcept.builder()
                .coding(
                    asList(
                        Coding.builder()
                            .system("http://hl7.org/fhir/v3/NullFlavor")
                            .code("UNK")
                            .display("unknown")
                            .build()))
                .build())
        .build();
  }

  /**
   * An example Patient.Bundle.
   *
   * @return an example Patient.Bundle.
   */
  public static Patient.Bundle patientBundle() {
    return Patient.Bundle.builder()
        .type(BundleType.searchset)
        .total(1)
        .link(
            asList(
                BundleLink.builder()
                    .relation(LinkRelation.self)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient?_id=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.first)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient?_id=1017283148V813263&page=1&_count=15")
                    .build(),
                BundleLink.builder()
                    .relation(LinkRelation.last)
                    .url(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient?_id=1017283148V813263&page=1&_count=15")
                    .build()))
        .entry(
            asList(
                Patient.Entry.builder()
                    .fullUrl(
                        "https://sandbox-api.va.gov/services/argonaut/v0/Patient/1017283148V813263")
                    .resource(
                        Patient.builder()
                            .id("2000163")
                            .extension(
                                asList(
                                    Extension.builder()
                                        .url(
                                            "http://fhir.org/guides/argonaut/StructureDefinition/argo-race")
                                        .extension(
                                            asList(
                                                Extension.builder()
                                                    .url("ombCategory")
                                                    .valueCoding(
                                                        Coding.builder()
                                                            .system("http://hl7.org/fhir/v3/Race")
                                                            .code("2016-3")
                                                            .display("White")
                                                            .build())
                                                    .build(),
                                                Extension.builder()
                                                    .url("text")
                                                    .valueString("White")
                                                    .build()))
                                        .build(),
                                    Extension.builder()
                                        .url(
                                            "http://fhir.org/guides/argonaut/StructureDefinition/argo-ethnicity")
                                        .extension(
                                            asList(
                                                Extension.builder()
                                                    .url("ombCategory")
                                                    .valueCoding(
                                                        Coding.builder()
                                                            .system(
                                                                "http://hl7.org/fhir/ValueSet/v3-Ethnicity")
                                                            .code("2186-5")
                                                            .display("Not Hispanic or Latino")
                                                            .build())
                                                    .build(),
                                                Extension.builder()
                                                    .url("text")
                                                    .valueString("Not Hispanic or Latino")
                                                    .build()))
                                        .build(),
                                    Extension.builder()
                                        .url(
                                            "http://fhir.org/guides/argonaut/StructureDefinition/argo-birthsex")
                                        .valueCode("M")
                                        .build()))
                            .identifier(
                                asList(
                                    Identifier.builder()
                                        .use(Identifier.IdentifierUse.usual)
                                        .type(
                                            CodeableConcept.builder()
                                                .coding(
                                                    asList(
                                                        Coding.builder()
                                                            .system("http://hl7.org/fhir/v2/0203")
                                                            .code("MR")
                                                            .build()))
                                                .build())
                                        .system("http://va.gov/mpi")
                                        .value("2000163")
                                        .build(),
                                    Identifier.builder()
                                        .use(Identifier.IdentifierUse.official)
                                        .type(
                                            CodeableConcept.builder()
                                                .coding(
                                                    asList(
                                                        Coding.builder()
                                                            .system("http://hl7.org/fhir/v2/0203")
                                                            .code("SB")
                                                            .build()))
                                                .build())
                                        .system("http://hl7.org/fhir/sid/us-ssn")
                                        .value("999-61-4803")
                                        .build()))
                            .name(
                                asList(
                                    HumanName.builder()
                                        .use(HumanName.NameUse.usual)
                                        .text("Mr. Aurelio227 Cruickshank494")
                                        .family(asList("Cruickshank494"))
                                        .given(asList("Aurelio227"))
                                        .build()))
                            .telecom(
                                asList(
                                    ContactPoint.builder()
                                        .system(ContactPoint.ContactPointSystem.phone)
                                        .value("5555191065")
                                        .use(ContactPoint.ContactPointUse.mobile)
                                        .build(),
                                    ContactPoint.builder()
                                        .system(ContactPoint.ContactPointSystem.email)
                                        .value("Aurelio227.Cruickshank494@email.example")
                                        .build()))
                            .gender(Patient.Gender.male)
                            .birthDate("1995-02-06")
                            .deceasedBoolean(false)
                            .address(
                                asList(
                                    Address.builder()
                                        .line(asList("909 Rohan Highlands"))
                                        .city("Mesa")
                                        .state("Arizona")
                                        .postalCode("85120")
                                        .build()))
                            .maritalStatus(
                                CodeableConcept.builder()
                                    .coding(
                                        asList(
                                            Coding.builder()
                                                .system("http://hl7.org/fhir/v3/NullFlavor")
                                                .code("UNK")
                                                .display("unknown")
                                                .build()))
                                    .build())
                            .build())
                    .search(Search.builder().mode(SearchMode.match).build())
                    .build()))
        .build();
  }
}
