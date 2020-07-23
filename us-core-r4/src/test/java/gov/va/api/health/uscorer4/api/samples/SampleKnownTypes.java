package gov.va.api.health.uscorer4.api.samples;

import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.ContactDetail;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.Duration;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
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
import gov.va.api.health.r4.api.datatypes.UsageContext;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Reference;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "get")
public class SampleKnownTypes {
  public Map<String, Supplier<?>> knownStringTypes() {
    Map<String, Supplier<?>> suppliers = new HashMap<>();
    suppliers.put("", () -> "hello");
    suppliers.put(Fhir.BASE64, () -> "SSBqdXN0IGF0ZSBhIHBlYW51dAo=");
    suppliers.put(Fhir.CODE, () -> "code");
    suppliers.put(Fhir.URI, () -> "http://example.com");
    suppliers.put(Fhir.DATE, () -> "2005-01-21");
    suppliers.put(Fhir.DATETIME, () -> "2005-01-21T07:57:00Z");
    suppliers.put(Fhir.ID, () -> "id");
    suppliers.put(Fhir.INSTANT, () -> "2005-01-21T07:57:00.000Z");
    suppliers.put(Fhir.OID, () -> "urn:oid:0.1");
    suppliers.put(Fhir.STRING, () -> "hello");
    suppliers.put(Fhir.TIME, () -> "07:57:00.000");
    suppliers.put(Fhir.XHTML, () -> "<div>html</div>");
    return suppliers;
  }

  public Map<Class<?>, Supplier<?>> knownTypes() {
    SampleDataTypes dataTypes = SampleDataTypes.get();
    Map<Class<?>, Supplier<?>> suppliers = new HashMap<>();
    suppliers.put(Integer.class, () -> 1);
    suppliers.put(Boolean.class, () -> true);
    suppliers.put(Double.class, () -> 1.0);
    suppliers.put(BigDecimal.class, () -> new BigDecimal("1.0"));
    suppliers.put(Address.class, dataTypes::address);
    suppliers.put(Attachment.class, dataTypes::attachment);
    suppliers.put(CodeableConcept.class, dataTypes::codeableConcept);
    suppliers.put(Coding.class, dataTypes::coding);
    suppliers.put(ContactDetail.class, dataTypes::contactDetail);
    suppliers.put(ContactPoint.class, dataTypes::contactPoint);
    suppliers.put(Duration.class, dataTypes::duration);
    suppliers.put(Extension.class, dataTypes::extension);
    suppliers.put(HumanName.class, dataTypes::humanName);
    suppliers.put(Identifier.class, dataTypes::identifier);
    suppliers.put(Instant.class, () -> Instant.parse("2020-01-20T21:36:00Z"));
    suppliers.put(Money.class, dataTypes::money);
    suppliers.put(Period.class, dataTypes::period);
    suppliers.put(Quantity.class, dataTypes::quantity);
    suppliers.put(Range.class, dataTypes::range);
    suppliers.put(Ratio.class, dataTypes::ratio);
    suppliers.put(SampledData.class, dataTypes::sampledData);
    suppliers.put(Signature.class, dataTypes::signature);
    suppliers.put(SimpleQuantity.class, dataTypes::simpleQuantity);
    suppliers.put(Reference.class, dataTypes::reference);
    suppliers.put(SimpleResource.class, dataTypes::resource);
    suppliers.put(Timing.class, dataTypes::timing);
    suppliers.put(UsageContext.class, dataTypes::usageContext);
    return suppliers;
  }
}
