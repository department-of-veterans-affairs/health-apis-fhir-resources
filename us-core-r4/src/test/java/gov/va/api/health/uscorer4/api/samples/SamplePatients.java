package gov.va.api.health.uscorer4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.uscorer4.api.resources.Patient;
import gov.va.api.health.uscorer4.api.resources.Patient.Communication;
import gov.va.api.health.uscorer4.api.resources.Patient.Gender;
import gov.va.api.health.uscorer4.api.resources.Patient.Link;
import gov.va.api.health.uscorer4.api.resources.Patient.PatientContact;
import gov.va.api.health.uscorer4.api.resources.Patient.Type;
import java.util.Arrays;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SamplePatients {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Communication communication() {
    return Communication.builder().language(codeableConcept()).preferred(true).build();
  }

  public Link link() {
    return Link.builder().other(reference()).type(Type.seealso).build();
  }

  public Patient patient() {
    return Patient.builder()
        .id("1324")
        .resourceType("Coverage")
        .meta(meta())
        .implicitRules("https://HelloRules.com")
        .language("Hello language")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(Arrays.asList(usCoreEthnicityExtension(), extension()))
        .modifierExtension(
            Arrays.asList(extension(), extensionWithQuantity(), extensionWithRatio()))
        .identifier(singletonList(identifier()))
        .active(true)
        .name(singletonList(humanName()))
        .telecom(singletonList(contactPoint()))
        .gender(Gender.unknown)
        .birthDate("2000-01-01")
        .deceasedBoolean(true)
        .address(singletonList(address()))
        .maritalStatus(codeableConcept())
        .multipleBirthInteger(2)
        .photo(singletonList(attachment()))
        .contact(singletonList(patientContact()))
        .communication(singletonList(communication()))
        .generalPractitioner(singletonList(reference()))
        .managingOrganization(reference())
        .link(singletonList(link()))
        .build();
  }

  public PatientContact patientContact() {
    return PatientContact.builder()
        .relationship(singletonList(codeableConcept()))
        .name(humanName())
        .telecom(singletonList(contactPoint()))
        .address(address())
        .gender(Gender.unknown)
        .organization(reference())
        .period(period())
        .build();
  }
}
