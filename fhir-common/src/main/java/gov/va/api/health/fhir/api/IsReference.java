package gov.va.api.health.fhir.api;

/**
 * This common set of attributes across DSTU2, STU3, and R4 reference is used for processing that
 * spans all versions of FHIR.
 */
public interface IsReference {
  String display();

  IsReference display(String display);

  String id();

  IsReference id(String id);

  String reference();

  IsReference reference(String reference);
}
