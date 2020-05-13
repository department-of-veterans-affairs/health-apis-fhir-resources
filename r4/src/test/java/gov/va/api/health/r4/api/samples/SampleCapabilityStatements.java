package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.CapabilityStatement;
import gov.va.api.health.r4.api.resources.CapabilityStatement.CapabilityResource;
import gov.va.api.health.r4.api.resources.CapabilityStatement.ConditionalDelete;
import gov.va.api.health.r4.api.resources.CapabilityStatement.ConditionalRead;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Document;
import gov.va.api.health.r4.api.resources.CapabilityStatement.DocumentMode;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Implementation;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Kind;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Messaging;
import gov.va.api.health.r4.api.resources.CapabilityStatement.MessagingEndpoint;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Operation;
import gov.va.api.health.r4.api.resources.CapabilityStatement.ReferencePolicy;
import gov.va.api.health.r4.api.resources.CapabilityStatement.ResourceInteraction;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Rest;
import gov.va.api.health.r4.api.resources.CapabilityStatement.RestInteraction;
import gov.va.api.health.r4.api.resources.CapabilityStatement.RestMode;
import gov.va.api.health.r4.api.resources.CapabilityStatement.SearchParam;
import gov.va.api.health.r4.api.resources.CapabilityStatement.SearchParamType;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Security;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Software;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Status;
import gov.va.api.health.r4.api.resources.CapabilityStatement.SupportedMessage;
import gov.va.api.health.r4.api.resources.CapabilityStatement.SupportedMessageMode;
import gov.va.api.health.r4.api.resources.CapabilityStatement.SystemRestfulInteraction;
import gov.va.api.health.r4.api.resources.CapabilityStatement.TypeRestfulInteraction;
import gov.va.api.health.r4.api.resources.CapabilityStatement.Versioning;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleCapabilityStatements {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public CapabilityStatement capability() {
    return CapabilityStatement.builder()
        .id("c1")
        .resourceType("Conformance")
        .implicitRules("https://example.com")
        .language("en")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .url("http://example.com")
        .version("1")
        .name("conformin' norman")
        .title("urgent care capability")
        .status(Status.active)
        .experimental(true)
        .date("2000-01-01T00:00:00-00:00")
        .publisher("random house")
        .contact(singletonList(contactDetail()))
        .description("words words")
        .useContext(singletonList(usageContext()))
        .jurisdiction(singletonList(codeableConcept()))
        .purpose("words words")
        .copyright("Alphonso, Lord of the Mangos")
        .kind(Kind.capability)
        .instantiates(singletonList("http://example.com"))
        .imports(singletonList("http://example.com"))
        .software(software())
        .implementation(implementation())
        .fhirVersion("R4")
        .format(singletonList("R4"))
        .patchFormat(singletonList("R4"))
        .rest(singletonList(rest()))
        .messaging(singletonList(messaging()))
        .document(singletonList(document()))
        .build();
  }

  public CapabilityResource capabilityResource() {
    return CapabilityResource.builder()
        .type("CODE")
        .profile("http://example.com")
        .supportedProfile(singletonList("http://example.com"))
        .documentation("words words")
        .interaction(singletonList(resourceInteraction()))
        .versioning(Versioning.no_version)
        .readHistory(true)
        .updateCreate(false)
        .conditionalCreate(true)
        .conditionalRead(ConditionalRead.not_supported)
        .conditionalUpdate(false)
        .conditionalDelete(ConditionalDelete.not_supported)
        .referencePolicy(singletonList(ReferencePolicy.enforced))
        .searchInclude(singletonList("indlude dem"))
        .searchRevInclude(singletonList("include dem too"))
        .searchParam(singletonList(searchParam()))
        .operation(singletonList(operation()))
        .build();
  }

  public Document document() {
    return Document.builder()
        .mode(DocumentMode.consumer)
        .documentation("words words")
        .profile("http://example.com")
        .build();
  }

  public MessagingEndpoint endpoint() {
    return MessagingEndpoint.builder().protocol(coding()).address("http://example.com").build();
  }

  public Implementation implementation() {
    return Implementation.builder()
        .description("words words")
        .url("http://example.com")
        .custodian(reference())
        .build();
  }

  public Messaging messaging() {
    return Messaging.builder()
        .endpoint(singletonList(endpoint()))
        .reliableCache(0)
        .documentation("words words")
        .supportedMessage(singletonList(supportedMessage()))
        .build();
  }

  public Operation operation() {
    return Operation.builder()
        .name("Jimmy")
        .definition("http://example.com")
        .documentation("words words")
        .build();
  }

  public ResourceInteraction resourceInteraction() {
    return ResourceInteraction.builder()
        .code(TypeRestfulInteraction.patch)
        .documentation("words words")
        .build();
  }

  public Rest rest() {
    return Rest.builder()
        .mode(RestMode.client)
        .documentation("words words")
        .security(security())
        .resource(singletonList(capabilityResource()))
        .interaction(singletonList(restInteraction()))
        .searchParam(searchParam())
        .operation(operation())
        .compartment("words words")
        .build();
  }

  public RestInteraction restInteraction() {
    return RestInteraction.builder()
        .code(SystemRestfulInteraction.batch)
        .documentation("words words")
        .build();
  }

  public SearchParam searchParam() {
    return SearchParam.builder()
        .name("Jimmy")
        .definition("http://example.com")
        .type(SearchParamType.composite)
        .documentation("words words")
        .build();
  }

  public Security security() {
    return Security.builder()
        .cors(true)
        .service(singletonList(codeableConcept()))
        .description("words words")
        .build();
  }

  public Software software() {
    return Software.builder()
        .name("Jimmy")
        .version("1")
        .releaseDate("2000-01-01T00:00:00-00:00")
        .build();
  }

  public SupportedMessage supportedMessage() {
    return SupportedMessage.builder()
        .mode(SupportedMessageMode.receiver)
        .definition("http://example.com")
        .build();
  }
}
