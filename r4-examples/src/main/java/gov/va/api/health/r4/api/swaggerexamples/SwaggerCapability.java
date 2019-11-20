package gov.va.api.health.r4.api.swaggerexamples;

import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.ContactDetail;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.ContactPoint.ContactPointSystem;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.resources.Capability;
import gov.va.api.health.r4.api.resources.Capability.CapabilityResource;
import gov.va.api.health.r4.api.resources.Capability.Kind;
import gov.va.api.health.r4.api.resources.Capability.ResourceInteraction;
import gov.va.api.health.r4.api.resources.Capability.Rest;
import gov.va.api.health.r4.api.resources.Capability.RestMode;
import gov.va.api.health.r4.api.resources.Capability.SearchParam;
import gov.va.api.health.r4.api.resources.Capability.SearchParamType;
import gov.va.api.health.r4.api.resources.Capability.Security;
import gov.va.api.health.r4.api.resources.Capability.Software;
import gov.va.api.health.r4.api.resources.Capability.Status;
import gov.va.api.health.r4.api.resources.Capability.TypeRestfulInteraction;
import java.util.Arrays;
import java.util.Collections;

public class SwaggerCapability {

  /**
   * An example Capability.
   *
   * @return an example Capability.
   */
  public static Capability capability() {
    return Capability.builder()
        .id("health-api-application")
        .resourceType("Capability")
        .version("2.0.0")
        .name("API Management Platform | Health API")
        .status(Status.draft)
        .date("2019-08-08T19:29:29Z")
        .publisher("Department of Veterans Affairs")
        .contact(
            Collections.singletonList(
                ContactDetail.builder()
                    .name("John Doe")
                    .telecom(
                        Collections.singletonList(
                            ContactPoint.builder()
                                .system(ContactPointSystem.email)
                                .value("john.doe@va.gov")
                                .build()))
                    .build()))
        .description("Read and search support.")
        .kind(Kind.instance)
        .software(Software.builder().name("health-api").build())
        .fhirVersion("4.0.0")
        .format(Arrays.asList("application/json+fhir", "application/json", "application/fhir+json"))
        .rest(
            Collections.singletonList(
                Rest.builder()
                    .mode(RestMode.server)
                    .security(
                        Security.builder()
                            .extension(
                                Collections.singletonList(
                                    Extension.builder()
                                        .url(
                                            "http://fhir-registry.smarthealthit.org/StructureDefinition/oauth-uris")
                                        .extension(
                                            Arrays.asList(
                                                Extension.builder()
                                                    .url("token")
                                                    .valueUri("https://example.com/oauth2/token")
                                                    .build(),
                                                Extension.builder()
                                                    .url("authorize")
                                                    .valueUri(
                                                        "https://example.com/oauth2/authorization")
                                                    .build()))
                                        .build()))
                            .cors("true")
                            .service(
                                Collections.singletonList(
                                    CodeableConcept.builder()
                                        .coding(
                                            Collections.singletonList(
                                                Coding.builder()
                                                    .system(
                                                        "https://www.hl7.org/fhir/valueset-restful-security-service.html")
                                                    .code("SMART-on-FHIR")
                                                    .display("SMART-on-FHIR")
                                                    .build()))
                                        .build()))
                            .description("http://docs.smarthealthit.org/")
                            .build())
                    .resource(
                        Collections.singletonList(
                            CapabilityResource.builder()
                                .type("Patient")
                                .profile("https://www.hl7.org/fhir/r4/patient.html")
                                .interaction(
                                    Arrays.asList(
                                        ResourceInteraction.builder()
                                            .code(TypeRestfulInteraction.search_type)
                                            .documentation(
                                                "Implemented per specification. See http://hl7.org/fhir/R4/http.html")
                                            .build(),
                                        ResourceInteraction.builder()
                                            .code(TypeRestfulInteraction.read)
                                            .documentation(
                                                "Implemented per specification. See http://hl7.org/fhir/R4/http.html")
                                            .build()))
                                .searchParam(
                                    Collections.singletonList(
                                        SearchParam.builder()
                                            .name("patient")
                                            .type(SearchParamType.reference)
                                            .build()))
                                .build()))
                    .build()))
        .build();
  }
}
