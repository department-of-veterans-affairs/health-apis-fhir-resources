# Argonaut

All models required to build Argonaut resources and datatypes are housed here.
Find more information about this specification [here](https://www.fhir.org/guides/argonaut/r2/).

### Within this module:
  * Java models for all Argonaut supported resources (below)
  * Tests for each Java model.

Argonaut resources use DSTU2 datatypes - accessed here with a dependency to DSTU2 module.

Because validation logic is shared among all FHIR versions, it has been pulled out into its own [module](../validation).

### Supported Resources:
  * [Allergy Intolerance](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-allergyintolerance.html)
  * [Condition](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-condition.html)
  * [Diagnostic Report](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-diagnosticreport.html)
  * [Immunization](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-immunization.html)
  * [Medication](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-medication.html)
  * [Medication Order](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-medicationorder.html)
  * [Medication Statement](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-medicationstatement.html)
  * [Observation](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-observationresults.html)
  * [Patient](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-patient.html)
  * [Procedure](https://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-procedure.html)

