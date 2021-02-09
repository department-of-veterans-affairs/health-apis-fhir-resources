package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.MixedBundle;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.Meta;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
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

    static final Map<String, Class<? extends Resource>> CLASS_MAP =
        Map.ofEntries(
            Map.entry("AllergyIntolerance", AllergyIntolerance.class),
            Map.entry("Appointment", Appointment.class),
            Map.entry("Bundle", MixedBundle.class),
            Map.entry("CapabilityStatement", CapabilityStatement.class),
            Map.entry("Claim", Claim.class),
            Map.entry("Condition", Condition.class),
            Map.entry("Coverage", Coverage.class),
            Map.entry("CoverageEligibilityResponse", CoverageEligibilityResponse.class),
            Map.entry("Device", Device.class),
            Map.entry("DiagnosticReport", DiagnosticReport.class),
            Map.entry("Encounter", Encounter.class),
            Map.entry("ExplanationOfBenefit", ExplanationOfBenefit.class),
            Map.entry("Immunization", Immunization.class),
            Map.entry("Location", Location.class),
            Map.entry("Medication", Medication.class),
            Map.entry("MedicationRequest", MedicationRequest.class),
            Map.entry("Observation", Observation.class),
            Map.entry("OperationOutcome", OperationOutcome.class),
            Map.entry("Organization", Organization.class),
            Map.entry("Patient", Patient.class),
            Map.entry("Practitioner", Practitioner.class),
            Map.entry("PractitionerRole", PractitionerRole.class),
            Map.entry("Procedure", Procedure.class),
            Map.entry("Questionnaire", Questionnaire.class),
            Map.entry("QuestionnaireResponse", QuestionnaireResponse.class),
            Map.entry("RelatedPerson", RelatedPerson.class),
            Map.entry("SimpleResource", SimpleResource.class),
            Map.entry("TerminologyCapabilities", TerminologyCapabilities.class));

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
      Class<? extends Resource> clazz = CLASS_MAP.get(type);
      if (clazz != null) {
        return (Resource) mapper.readValue(root.toString(), clazz);
      }
      throw new IllegalStateException("Unknown resource type: " + type);
    }
  }
}
