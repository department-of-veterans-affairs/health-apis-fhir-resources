package gov.va.api.health.deidentification.core;

/** This interface will be used to generate a deidentified ID given the original resources id. */
public interface DeidentifiedIdGenerator {

  String generateIdFrom(String identifier);
}
