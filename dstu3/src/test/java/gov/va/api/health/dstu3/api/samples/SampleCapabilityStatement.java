package gov.va.api.health.dstu3.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.dstu3.api.datatypes.ContactDetail;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.AcceptUnknown;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.DeleteCode;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.Document;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.DocumentMode;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.Kind;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.Messaging;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.MessagingEndpoint;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.MessagingEvent;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.MessagingEventCategory;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.MessagingEventMode;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.ResourceInteraction;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.ResourceInteractionCode;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.Rest;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.RestInteraction;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.RestInteractionCode;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.RestMode;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.RestOperation;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.RestResource;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.RestResourceVersion;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.RestTransactionMode;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.SearchParam;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.SearchParamModifier;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.SearchParamType;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.SecurityCertificate;
import gov.va.api.health.dstu3.api.resources.CapabilityStatement.Status;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@SuppressWarnings({"unused", "WeakerAccess"})
@NoArgsConstructor(staticName = "get")
public class SampleCapabilityStatement {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public CapabilityStatement capabilityStatement() {
    return CapabilityStatement.builder()
        .id("c1")
        .resourceType("CapabilityStatement")
        .implicitRules("https://example.com")
        .language("en")
        .text(narrative())
        .contained(simpleResourceList())
        .extension(extensionList())
        .modifierExtension(extensionList())
        .url("http://example.com")
        .version("1")
        .name("conformin' norman")
        .status(Status.active)
        .experimental(true)
        .publisher("random house")
        .contact(singletonList(ContactDetail.builder().build()))
        .date("2005-01-21T07:57:00.000Z")
        .description("words words")
        .copyright("Alphonso, Lord of the Mangos")
        .kind(Kind.capability)
        .software(software())
        .implementation(implementation())
        .fhirVersion("argonaut")
        .acceptUnknown(AcceptUnknown.no)
        .format(singletonList("json"))
        .profile(referenceList())
        .rest(restList())
        .messaging(messagingList())
        .document(documentList())
        .build();
  }

  public ContactDetail contact() {
    return ContactDetail.builder()
        .id("contact1")
        .extension(extensionList())
        .name("Jimmy")
        .telecom(contactPointList())
        .build();
  }

  public List<ContactDetail> contactList() {
    return singletonList(contact());
  }

  private Document document() {
    return CapabilityStatement.Document.builder()
        .id("doc1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .mode(DocumentMode.consumer)
        .documentation("so many words")
        .profile(reference())
        .build();
  }

  private List<Document> documentList() {
    return singletonList(document());
  }

  public CapabilityStatement.Implementation implementation() {
    return CapabilityStatement.Implementation.builder()
        .id("implementation1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .description("cool beans")
        .url("http://example.com")
        .build();
  }

  public Messaging messaging() {
    return CapabilityStatement.Messaging.builder()
        .id("doc1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .endpoint(messagingEndpointList())
        .reliableCache(2)
        .documentation("finally done")
        .event(messagingEventList())
        .build();
  }

  public MessagingEndpoint messagingEndpoint() {
    return CapabilityStatement.MessagingEndpoint.builder()
        .id("end1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .protocol(coding())
        .address("http://example.com")
        .build();
  }

  public List<MessagingEndpoint> messagingEndpointList() {
    return singletonList(messagingEndpoint());
  }

  public MessagingEvent messagingEvent() {
    return CapabilityStatement.MessagingEvent.builder()
        .id("doc1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .code(coding())
        .category(MessagingEventCategory.Consequence)
        .mode(MessagingEventMode.sender)
        .focus("blur")
        .request(reference())
        .response(reference())
        .documentation("so many words")
        .build();
  }

  public List<MessagingEvent> messagingEventList() {
    return singletonList(messagingEvent());
  }

  public List<Messaging> messagingList() {
    return singletonList(messaging());
  }

  public CapabilityStatement.ResourceInteraction resourceInteraction() {
    return CapabilityStatement.ResourceInteraction.builder()
        .id("contact1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .code(ResourceInteractionCode.create)
        .documentation("so long")
        .build();
  }

  private List<ResourceInteraction> resourceInteractionList() {
    return singletonList(resourceInteraction());
  }

  public CapabilityStatement.Rest rest() {
    return CapabilityStatement.Rest.builder()
        .id("contact1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .mode(RestMode.server)
        .documentation("words words")
        .security(restSecurity())
        .resource(restResourceList())
        .interaction(restInteractionList())
        .transactionMode(RestTransactionMode.not_supported)
        .searchParam(searchParamList())
        .operation(restOperationList())
        .compartment(singletonList("compartments!"))
        .build();
  }

  public CapabilityStatement.RestInteraction restInteraction() {
    return CapabilityStatement.RestInteraction.builder()
        .id("contact1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .code(RestInteractionCode.search_system)
        .documentation("omg so long")
        .build();
  }

  private List<RestInteraction> restInteractionList() {
    return singletonList(restInteraction());
  }

  private List<Rest> restList() {
    return singletonList(rest());
  }

  private RestOperation restOperation() {
    return CapabilityStatement.RestOperation.builder()
        .id("contact1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .name("STUFF")
        .definition(reference())
        .build();
  }

  private List<RestOperation> restOperationList() {
    return singletonList(restOperation());
  }

  private RestResource restResource() {
    return CapabilityStatement.RestResource.builder()
        .id("restresource1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .type("Thing")
        .profile(reference())
        .interaction(resourceInteractionList())
        .versioning(RestResourceVersion.no_version)
        .readHistory(true)
        .updateCreate(true)
        .conditionalCreate(true)
        .conditionalUpdate(true)
        .conditionalDelete(DeleteCode.not_supported)
        .searchInclude(singletonList("ok"))
        .searchRevInclude(singletonList("meh"))
        .searchParam(searchParamList())
        .build();
  }

  public List<RestResource> restResourceList() {
    return singletonList(restResource());
  }

  public CapabilityStatement.RestSecurity restSecurity() {
    return CapabilityStatement.RestSecurity.builder()
        .id("contact1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .cors(true)
        .service(codeableConceptList())
        .description("wordy words")
        .certificate(restSecurityCertificateList())
        .build();
  }

  public CapabilityStatement.SecurityCertificate restSecurityCertificate() {
    return CapabilityStatement.SecurityCertificate.builder()
        .id("contact1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .type("stuff")
        .blob("TH38L089")
        .build();
  }

  private List<SecurityCertificate> restSecurityCertificateList() {
    return singletonList(restSecurityCertificate());
  }

  public CapabilityStatement.SearchParam searchParam() {
    return CapabilityStatement.SearchParam.builder()
        .id("contact1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .name("thing")
        .definition("http://example.com")
        .type(SearchParamType.string)
        .documentation("this resource is yuge")
        .target(singletonList("json"))
        .modifier(singletonList(SearchParamModifier.not_in))
        .chain(singletonList("cha-cha-chain-of-fools"))
        .build();
  }

  private List<SearchParam> searchParamList() {
    return singletonList(searchParam());
  }

  public CapabilityStatement.Software software() {
    return CapabilityStatement.Software.builder()
        .id("s1")
        .extension(extensionList())
        .modifierExtension(extensionList())
        .name("Virus.exe")
        .version("2.3")
        .releaseDate("2005-01-21T07:57:00.000Z")
        .build();
  }
}
