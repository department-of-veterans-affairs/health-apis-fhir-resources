package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.Meta;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Pattern;
import lombok.SneakyThrows;

@Schema(description = "https://www.hl7.org/fhir/R4/resource.html")
@JsonDeserialize(using = Resource.ResourceDeserializer.class)
public interface Resource {
  @Pattern(regexp = Fhir.STRING)
  String id();

  @Pattern(regexp = Fhir.STRING)
  String implicitRules();

  @Pattern(regexp = Fhir.STRING)
  String language();

  Meta meta();

  public final class ResourceDeserializer extends StdDeserializer<Resource> {
    public ResourceDeserializer() {
      this(null);
    }

    public ResourceDeserializer(Class<?> vc) {
      super(vc);
    }

    @Override
    @SneakyThrows
    public Resource deserialize(JsonParser jp, DeserializationContext ctxt) {
      ObjectMapper mapper = (ObjectMapper) jp.getCodec();
      ObjectNode root = mapper.readTree(jp);
      String type = root.get("resourceType").asText();
      if (type.equals("Patient")) {
        return mapper.readValue(root.toString(), Patient.class);
      } else if (type.equals("Location")) {
        return mapper.readValue(root.toString(), Location.class);
      } else if (type.equals("Immunization")) {
        return mapper.readValue(root.toString(), Immunization.class);
      } else if (type.equals("SimpleResource")) {
        return mapper.readValue(root.toString(), SimpleResource.class);
      }
      throw new IllegalStateException();

      // AllergyIntolerance
      // CapabilityStatement
      // Condition
      // Coverage
      // CoverageEligResponse
      // Device
      // DiagnosticReport
      // Encounter
      // ExplanationOfBenefit
      // Immunization/
      // Medication
      // MedicationRequest
      // Observation
      // Practitioner
      // PractitionerRole
      // Procedure
      // TerminologyCapabilities
      // Appointment
      // Claim
      // OperationOutcome
      // Questionnaire
      // QuestionnaireResponse
      // RelatedPerson

      // Bundles
      // PatientBundle, LocationBundle, ImmunizationBundle
    }
  }
}
