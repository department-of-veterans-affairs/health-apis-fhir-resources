package gov.va.api.health.uscorer4;

import gov.va.api.health.deidentification.core.DeidentifiedIdGenerator;
import gov.va.api.health.deidentification.core.SyntheticData;
import gov.va.api.health.deidentification.core.SyntheticName;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.uscorer4.api.resources.Patient;
import java.util.List;
import java.util.function.Function;
import lombok.Builder;

@Builder
public class PatientDeidentifier implements Function<Patient, Patient> {
  private final SyntheticData syntheticData;

  private final DeidentifiedIdGenerator idGenerator;

  /** Anonymize a Patient Record. */
  public Patient apply(Patient resource) {
    String anonymizedId = idGenerator.generateIdFrom(resource.id());
    /*
     * Lets get a repeatable seed from our patient record, so that we can create replicable
     * Synthetic data. For our seed, we will strip the V out of the ICN, and use the resulting long.
     * If we cannot parse the ICN, we create our seed from the string hash.
     */
    long idBasedSeed = Integer.toUnsignedLong(anonymizedId.hashCode());
    return Patient.builder()
        .id(anonymizedId)
        .resourceType(resource.resourceType())
        .meta(resource.meta())
        .implicitRules(resource.implicitRules())
        .language(resource.language())
        .text(resource.text())
        .contained(resource.contained())
        .extension(resource.extension())
        .modifierExtension(resource.modifierExtension())
        // Remove .identifier[]
        .active(resource.active())
        .name(sanitizeHumanName(resource.name(), idBasedSeed))
        // Remove .telecom[]
        .gender(resource.gender())
        .birthDate(syntheticData.synthesizeDate(resource.birthDate()))
        .deceasedBoolean(resource.deceasedBoolean())
        .deceasedDateTime(syntheticData.synthesizeDateTime(resource.deceasedDateTime()))
        // Remove .address[]
        .maritalStatus(resource.maritalStatus())
        .multipleBirthBoolean(
            sanitizeMultipleBirthBoolean(
                resource.multipleBirthBoolean(), resource.multipleBirthInteger()))
        // Remove .photo[]
        // Remove .contact[]
        .communication(resource.communication())
        .generalPractitioner(resource.generalPractitioner())
        .managingOrganization(resource.managingOrganization())
        .link(resource.link())
        .build();
  }

  /** Transform a Synthetic name to an R4 HumanName. */
  List<HumanName> sanitizeHumanName(List<HumanName> identifiableNames, long seed) {
    SyntheticName deidentifiedName = syntheticData.synthesizeName(seed);
    return List.of(
        HumanName.builder()
            .given(List.of(deidentifiedName.first()))
            .family(deidentifiedName.last())
            .text(deidentifiedName.first() + " " + deidentifiedName.last())
            .build());
  }

  /**
   * MBI and MBB are a one of choice. If MBI is provided, anonymization must drop it, and instead
   * provide the MBB corresponding the value.
   */
  Boolean sanitizeMultipleBirthBoolean(Boolean multipleBirthBoolean, Integer multipleBirthInteger) {
    if (multipleBirthInteger != null) {
      return multipleBirthInteger > 0;
    }
    return multipleBirthBoolean;
  }
}
