package gov.va.api.health.deidentification.core;

/**
 * The SyntheticData interface defines the set of FHIR PII fields that must be deidentified.
 * Implementations will define the details of deidentification. For example, class path resource
 * based implementation exists in ResourceBasedSyntheticData.java
 */
public interface SyntheticData {

  String synthesizeDate(String rawBirthdate);

  String synthesizeDateTime(String rawDateTime);

  SyntheticName synthesizeName(long seed);
}
