package gov.va.api.health.r4.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Annotation;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
    description = "http://hl7.org/fhir/us/core/StructureDefinition-us-core-implantable-device.html",
    example = "${r4.device:gov.va.api.health.r4.api.swaggerexamples.SwaggerDevice#device}")
public class Device implements Resource {
  @NotBlank @Builder.Default String resourceType = "Device";

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid List<SimpleResource> contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  @Valid List<Identifier> identifier;

  @Valid Reference definition;

  @Valid DeviceIdentifier udiCarrier;

  Status status;

  @Valid List<CodeableConcept> statusReason;

  String distinctIdentifier;

  String manufacturer;

  @Pattern(regexp = Fhir.DATETIME)
  String manufactureDate;

  @Pattern(regexp = Fhir.DATETIME)
  String expirationDate;

  String lotNumber;

  String serialNumber;

  @Valid List<DeviceName> deviceName;

  String modelNumber;

  String partNumber;

  // This has a Binding of FHIRDeviceTypes(extensible), but there's a ton of them.
  @Valid @NotNull CodeableConcept type;

  @Valid List<Specialization> specialization;

  @Valid List<Version> version;

  @Valid List<Property> property;

  @Valid @NotNull Reference patient;

  @Valid Reference owner;

  @Valid List<ContactPoint> contact;

  @Valid Reference location;

  @Pattern(regexp = Fhir.URI)
  String url;

  @Valid List<Annotation> note;

  @Valid List<CodeableConcept> safety;

  @Valid Reference parent;

  public enum DeviceNameType {
    @JsonProperty("udi-label-name")
    udi_label_name,
    @JsonProperty("user-friendly-name")
    user_friendly_name,
    @JsonProperty("patient-reported-name")
    patient_reported_name,
    @JsonProperty("manufacturer-name")
    manufacturer_name,
    @JsonProperty("model-name")
    model_name,
    other
  }

  public enum Status {
    active,
    inactive,
    @JsonProperty("entered-in-error")
    entered_in_error,
    unknown
  }

  public enum UdiEntryType {
    barcode,
    rfid,
    manual,
    card,
    @JsonProperty("self-reported")
    self_reported,
    unknown
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Device.Bundle.BundleBuilder.class)
  @Schema(
      name = "DeviceBundle",
      example =
          "${r4.deviceBundle:gov.va.api.health."
              + "r4.api.swaggerexamples.SwaggerDevice#deviceBundle}")
  public static final class Bundle extends AbstractBundle<Entry> {
    /** Builder constructor. */
    @Builder
    public Bundle(
        @NotBlank String resourceType,
        @Pattern(regexp = Fhir.ID) String id,
        @Valid Meta meta,
        @Pattern(regexp = Fhir.URI) String implicitRules,
        @Pattern(regexp = Fhir.CODE) String language,
        @Valid Identifier identifier,
        @NotNull AbstractBundle.BundleType type,
        @Pattern(regexp = Fhir.INSTANT) String timestamp,
        @Min(0) Integer total,
        @Valid List<BundleLink> link,
        @Valid List<Device.Entry> entry,
        @Valid Signature signature) {
      super(
          defaultString(resourceType, "Bundle"),
          id,
          meta,
          implicitRules,
          language,
          identifier,
          type,
          timestamp,
          total,
          link,
          entry,
          signature);
    }
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @Schema(name = "DeviceEntry")
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Device.Entry.EntryBuilder.class)
  public static final class Entry extends AbstractEntry<Device> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Device resource,
        @Valid AbstractEntry.Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @Schema(name = "UniqueDeviceIdentifier")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class DeviceIdentifier implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank String deviceIdentifier;

    @Pattern(regexp = Fhir.URI)
    String issuer;

    @Pattern(regexp = Fhir.URI)
    String jurisdiction;

    @Pattern(regexp = Fhir.BASE64)
    @JsonProperty("carrierAIDC")
    String carrierAidc;

    @JsonProperty("carrierHRF")
    String carrierHrf;

    UdiEntryType entryType;
  }

  @Data
  @Builder
  @Schema(name = "DeviceName")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class DeviceName implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank String name;

    @NotNull DeviceNameType type;
  }

  @Data
  @Builder
  @Schema(name = "DeviceProperty")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Property implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid @NotNull CodeableConcept type;

    @Valid List<Quantity> valueQuantity;

    @Valid List<CodeableConcept> valueCode;
  }

  @Data
  @Builder
  @Schema(name = "DeviceSpecialization")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Specialization implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid @NotNull CodeableConcept systemType;

    String version;
  }

  @Data
  @Builder
  @Schema(name = "DeviceVersion")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Version implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept type;

    @Valid Identifier component;

    @NotBlank String value;
  }
}
