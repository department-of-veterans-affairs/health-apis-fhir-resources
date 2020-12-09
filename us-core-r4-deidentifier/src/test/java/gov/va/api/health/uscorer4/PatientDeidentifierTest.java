package gov.va.api.health.uscorer4;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import gov.va.api.health.deidentification.core.DeidentifiedIdGenerator;
import gov.va.api.health.deidentification.core.SyntheticData;
import gov.va.api.health.deidentification.core.SyntheticName;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.resources.Patient;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PatientDeidentifierTest {
  SyntheticData syntheticData = mock(SyntheticData.class);

  DeidentifiedIdGenerator idGenerator = mock(DeidentifiedIdGenerator.class);

  /**
   * Let's mock the synthesis process. We only want to test that the deidentifier is acting
   * correctly on each patient field.
   */
  @Test
  void apply() {
    var patientSample = R4PatientSample.create();
    when(syntheticData.synthesizeName(anyLong()))
        .thenReturn(SyntheticName.builder().first("Jon").last("Jones").build());
    when(syntheticData.synthesizeDate("1970-11-14")).thenReturn("1970-10-01");
    when(syntheticData.synthesizeDateTime("2001-03-03T15:08:09Z"))
        .thenReturn("2001-02-01T00:00:00Z");
    when(idGenerator.generateIdFrom(Mockito.anyString())).thenReturn("WHOISIT");
    assertThat(deidentifier().apply(patientSample)).isEqualTo(deidentified(patientSample));
  }

  Patient deidentified(Patient patient) {
    // Remove
    patient.identifier(null);
    patient.telecom(null);
    patient.address(null);
    patient.photo(null);
    patient.contact(null);
    // Synthesize
    patient.id("WHOISIT");
    patient.name(
        List.of(
            HumanName.builder().given(List.of("Jon")).family("Jones").text("Jon Jones").build()));
    patient.birthDate("1970-10-01");
    patient.deceasedDateTime("2001-02-01T00:00:00Z");
    return patient;
  }

  PatientDeidentifier deidentifier() {
    return PatientDeidentifier.builder()
        .syntheticData(syntheticData)
        .idGenerator(idGenerator)
        .build();
  }

  @Test
  void sanitizeMultipleBirthBoolean() {
    assertThat(deidentifier().sanitizeMultipleBirthBoolean(true, null)).isTrue();
    assertThat(deidentifier().sanitizeMultipleBirthBoolean(false, null)).isFalse();
    assertThat(deidentifier().sanitizeMultipleBirthBoolean(null, 2)).isTrue();
    assertThat(deidentifier().sanitizeMultipleBirthBoolean(null, 0)).isFalse();
    assertThat(deidentifier().sanitizeMultipleBirthBoolean(null, -1)).isFalse();
  }

  public static class R4PatientSample {
    public static Patient create() {
      return Patient.builder()
          .id("2000163")
          .resourceType("Patient")
          .extension(
              asList(
                  Extension.builder()
                      .url("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race")
                      .extension(
                          asList(
                              Extension.builder()
                                  .url("ombCategory")
                                  .valueCoding(
                                      Coding.builder()
                                          .system(
                                              "https://www.hl7.org/fhir/us/core/CodeSystem-cdcrec.html")
                                          .code("2016-3")
                                          .display("White")
                                          .build())
                                  .build(),
                              Extension.builder().url("text").valueString("White").build()))
                      .build(),
                  Extension.builder()
                      .url("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity")
                      .extension(
                          asList(
                              Extension.builder()
                                  .url("ombCategory")
                                  .valueCoding(
                                      Coding.builder()
                                          .system(
                                              "https://www.hl7.org/fhir/us/core/CodeSystem-cdcrec.html")
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
                      .url("http://hl7.org/fhir/us/core/StructureDefinition/us-core-birthsex")
                      .valueCode("M")
                      .build()))
          .identifier(
              asList(
                  Identifier.builder()
                      .use(Identifier.IdentifierUse.usual)
                      .type(
                          CodeableConcept.builder()
                              .coding(
                                  Arrays.asList(
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
                                  Arrays.asList(
                                      Coding.builder()
                                          .system("http://hl7.org/fhir/v2/0203")
                                          .code("SB")
                                          .build()))
                              .build())
                      .system("http://hl7.org/fhir/sid/us-ssn")
                      .value("999-61-4803")
                      .build()))
          .name(
              Arrays.asList(
                  HumanName.builder()
                      .use(HumanName.NameUse.usual)
                      .text("Mr. Aurelio227 Cruickshank494")
                      .family("Cruickshank494")
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
          .birthDate("1970-11-14")
          .deceasedDateTime("2001-03-03T15:08:09Z")
          .address(
              Arrays.asList(
                  Address.builder()
                      .line(asList("909 Rohan Highlands"))
                      .city("Mesa")
                      .state("Arizona")
                      .postalCode("85120")
                      .build()))
          .maritalStatus(
              CodeableConcept.builder()
                  .coding(
                      Arrays.asList(
                          Coding.builder()
                              .system("http://hl7.org/fhir/R4/v3/NullFlavor/cs.html")
                              .code("UNK")
                              .display("unknown")
                              .build()))
                  .build())
          .build();
    }
  }
}
