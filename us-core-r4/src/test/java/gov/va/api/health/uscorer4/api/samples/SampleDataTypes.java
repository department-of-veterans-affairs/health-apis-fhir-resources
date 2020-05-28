package gov.va.api.health.uscorer4.api.samples;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.bundle.AbstractEntry.HttpVerb;
import gov.va.api.health.r4.api.bundle.AbstractEntry.Request;
import gov.va.api.health.r4.api.bundle.AbstractEntry.Response;
import gov.va.api.health.r4.api.bundle.AbstractEntry.Search;
import gov.va.api.health.r4.api.bundle.AbstractEntry.SearchMode;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Address.AddressType;
import gov.va.api.health.r4.api.datatypes.Address.AddressUse;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.ContactDetail;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.ContactPoint.ContactPointSystem;
import gov.va.api.health.r4.api.datatypes.ContactPoint.ContactPointUse;
import gov.va.api.health.r4.api.datatypes.Duration;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.HumanName.NameUse;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Identifier.IdentifierUse;
import gov.va.api.health.r4.api.datatypes.Money;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Range;
import gov.va.api.health.r4.api.datatypes.Ratio;
import gov.va.api.health.r4.api.datatypes.SampledData;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleQuantity;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.datatypes.Timing;
import gov.va.api.health.r4.api.datatypes.Timing.Repeat;
import gov.va.api.health.r4.api.datatypes.Timing.Repeat.EventTime;
import gov.va.api.health.r4.api.datatypes.Timing.Repeat.UnitOfTime;
import gov.va.api.health.r4.api.datatypes.UsageContext;
import gov.va.api.health.r4.api.elements.Dosage;
import gov.va.api.health.r4.api.elements.Dosage.DoseAndRate;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Narrative.NarrativeStatus;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.r4.api.resources.OperationOutcome.Issue;
import gov.va.api.health.r4.api.resources.OperationOutcome.Issue.IssueSeverity;
import java.math.BigDecimal;
import java.util.Arrays;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "get")
public class SampleDataTypes {
  public Address address() {
    return Address.builder()
        .id("1234")
        .extension(singletonList(extension()))
        .use(AddressUse.home)
        .type(AddressType.both)
        .text("Hello")
        .line(Arrays.asList("hello", "goodbye"))
        .city("Hello City")
        .district("Hello District")
        .state("Hello State")
        .postalCode("12345")
        .country("Hello Country")
        .period(period())
        .build();
  }

  public Annotation annotation() {
    return Annotation.builder()
        .authorReference(reference())
        .time("2017-01-01T00:00:00.000Z")
        .text("# annotation")
        .build();
  }

  public Attachment attachment() {
    return Attachment.builder()
        .contentType("HelloType")
        .language("HelloLanguage")
        .data("SSBqdXN0IGF0ZSBhIHBlYW51dAo=")
        .url("http://HelloUrl.com")
        .size(1)
        .hash("SSBqdXN0IGF0ZSBhIHBlYW51dAo=")
        .title("HelloTitle")
        .creation("2000-01-01T00:00:00-00:00")
        .build();
  }

  public CodeableConcept codeableConcept() {
    return CodeableConcept.builder().coding(singletonList(coding())).text("code text test").build();
  }

  public Coding coding() {
    return Coding.builder()
        .system("http://HelloSystem.com")
        .version("Hello Version")
        .code("Hello Code")
        .display("Hello Display")
        .userSelected(true)
        .build();
  }

  public ContactDetail contactDetail() {
    return ContactDetail.builder()
        .name("Hello name")
        .telecom(singletonList(contactPoint()))
        .build();
  }

  public ContactPoint contactPoint() {
    return ContactPoint.builder()
        .system(ContactPointSystem.other)
        .value("HelloValue")
        .use(ContactPointUse.home)
        .rank(1)
        .period(period())
        .build();
  }

  public CodeableConcept details() {
    return CodeableConcept.builder().coding(singletonList(coding())).text("HelloText").build();
  }

  public Dosage dosage() {
    return Dosage.builder()
        .sequence(1)
        .text("text")
        .additionalInstruction(singletonList(codeableConcept()))
        .patientInstruction("patient instruction")
        .timing(timing())
        .asNeededCodeableConcept(codeableConcept())
        .site(codeableConcept())
        .route(codeableConcept())
        .method(codeableConcept())
        .doseAndRate(singletonList(doseAndRate()))
        .maxDosePerPeriod(ratio())
        .maxDosePerAdministration(simpleQuantity())
        .maxDosePerLifetime(simpleQuantity())
        .build();
  }

  public DoseAndRate doseAndRate() {
    return DoseAndRate.builder()
        .type(codeableConcept())
        .doseRange(range())
        .rateRange(range())
        .build();
  }

  public Duration duration() {
    return Duration.builder().value(new BigDecimal("1.0")).code("code").unit("a").build();
  }

  public Extension extension() {
    return Extension.builder().url("http://HelloUrl.com").valueCoding(coding()).build();
  }

  public Extension extensionWithContactDetail() {
    return Extension.builder().valueContactDetail(contactDetail()).build();
  }

  public Extension extensionWithQuantity() {
    return Extension.builder()
        .url("http://HelloUrl.com")
        .valueQuantity(
            Quantity.builder()
                .code("Q")
                .comparator(">=")
                .id("Q1")
                .unit("things")
                .system("http://example.com")
                .value(new BigDecimal("1.0"))
                .build())
        .build();
  }

  public Extension extensionWithRatio() {
    return Extension.builder().url("http://HelloUrl.com").valueRatio(ratio()).build();
  }

  public Extension extensionWithUsageContext() {
    return Extension.builder().valueUsageContext(usageContext()).build();
  }

  public HumanName humanName() {
    return HumanName.builder()
        .use(NameUse.anonymous)
        .text("HelloText")
        .family("HelloFamily")
        .given(singletonList("HelloGiven"))
        .prefix(singletonList("HelloPrefix"))
        .suffix(singletonList("HelloSuffix"))
        .period(period())
        .build();
  }

  public Identifier identifier() {
    return Identifier.builder()
        .id("5678")
        .use(IdentifierUse.official)
        .use(IdentifierUse.official)
        .extension(singletonList(extension()))
        .build();
  }

  public Issue issue() {
    return Issue.builder()
        .severity(IssueSeverity.error)
        .code("HelloCode")
        .details(details())
        .diagnostics("HelloDiagnostics")
        .location(singletonList("HelloLocation"))
        .expression(singletonList("HelloExpression"))
        .build();
  }

  public Meta meta() {
    return Meta.builder()
        .versionId("1111")
        .lastUpdated("2000-01-01T00:00:00-00:00")
        .profile(singletonList("http://HelloProfile.com"))
        .security(singletonList(coding()))
        .tag(singletonList(coding()))
        .build();
  }

  public Money money() {
    return Money.builder().value(new BigDecimal("11.11")).currency("USD").build();
  }

  public Narrative narrative() {
    return Narrative.builder().status(NarrativeStatus.additional).div("<p>HelloDiv<p>").build();
  }

  public Period period() {
    return Period.builder()
        .id("5678")
        .extension(
            singletonList(Extension.builder().url("http://example.com").valueInteger(1).build()))
        .start("2000-01-01T00:00:00-00:00")
        .end("2001-01-01T00:00:00-00:00")
        .build();
  }

  public Quantity quantity() {
    return Quantity.builder().value(new BigDecimal("11.11")).unit("HelloUnit").build();
  }

  public Range range() {
    return Range.builder().high(simpleQuantity()).low(simpleQuantity()).build();
  }

  public Ratio ratio() {
    return Ratio.builder()
        .id("R1")
        .denominator(Quantity.builder().value(new BigDecimal("1.0")).build())
        .numerator(Quantity.builder().value(new BigDecimal("2.0")).build())
        .build();
  }

  public Reference reference() {
    return Reference.builder().reference("HelloReference").display("HelloDisplay").build();
  }

  public Request request() {
    return Request.builder()
        .id("request1")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .method(HttpVerb.GET)
        .url("http://example.com")
        .ifNoneMatch("ok")
        .ifModifiedSince("also ok")
        .ifMatch("really ok")
        .ifNoneExist("meh, ok.")
        .build();
  }

  public SimpleResource resource() {
    return SimpleResource.builder()
        .id("1111")
        .meta(meta())
        .implicitRules("http://HelloRules.com")
        .language("Hello Language")
        .build();
  }

  public Response response() {
    return Response.builder()
        .id("response1")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .status("single")
        .location("http://example.com")
        .etag("you're it")
        .lastModified("2005-01-21T07:57:00Z")
        .build();
  }

  public SampledData sampledData() {
    return SampledData.builder()
        .id("1234")
        .extension(singletonList(extension()))
        .origin(simpleQuantity())
        .period(new BigDecimal("10.234"))
        .factor(new BigDecimal("2.5"))
        .lowerLimit(new BigDecimal("1.0"))
        .upperLimit(new BigDecimal("10.0"))
        .dimensions(5)
        .data("HelloData")
        .build();
  }

  public Search search() {
    return Search.builder()
        .id("search1")
        .mode(SearchMode.match)
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .score(new BigDecimal("0.5"))
        .build();
  }

  public Signature signature() {
    return Signature.builder()
        .type(singletonList(coding()))
        .when("now")
        .who(reference())
        .onBehalfOf(reference())
        .targetFormat("YO")
        .sigFormat("YO")
        .data("00000")
        .build();
  }

  public SimpleQuantity simpleQuantity() {
    return SimpleQuantity.builder()
        .value(new BigDecimal("11.11"))
        .unit("HelloUnit")
        .system("http://example.com")
        .build();
  }

  public Timing timing() {
    return Timing.builder()
        .event(singletonList("2017-01-01T00:00:00.000Z"))
        .repeat(
            Repeat.builder()
                .boundsPeriod(period())
                .count(1)
                .countMax(10)
                .duration(new BigDecimal("0.5"))
                .durationMax(new BigDecimal("1.0"))
                .durationUnit(UnitOfTime.a)
                .frequency(1)
                .frequencyMax(10)
                .period(new BigDecimal("0.5"))
                .periodMax(new BigDecimal("1.0"))
                .periodUnit(UnitOfTime.wk)
                .timeOfDay(singletonList("11:00:00"))
                .when(singletonList(EventTime.AC))
                .offset(1)
                .build())
        .code(codeableConcept())
        .build();
  }

  public Extension usCoreEthnicityExtension() {
    return Extension.builder()
        .url("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity")
        .extension(
            asList(
                Extension.builder()
                    .url("ombCategory")
                    .valueCoding(
                        Coding.builder()
                            .system("https://www.hl7.org/fhir/us/core/CodeSystem-cdcrec.html")
                            .code("2186-5")
                            .display("Not Hispanic or Latino")
                            .build())
                    .build(),
                Extension.builder().url("text").valueString("Not Hispanic or Latino").build()))
        .build();
  }

  public UsageContext usageContext() {
    return UsageContext.builder().code(coding()).valueRange(range()).build();
  }
}
