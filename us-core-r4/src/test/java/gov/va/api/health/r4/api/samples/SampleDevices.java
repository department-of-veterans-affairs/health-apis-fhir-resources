package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.Device;
import gov.va.api.health.r4.api.resources.Device.DeviceNameType;
import gov.va.api.health.r4.api.resources.Device.Status;
import gov.va.api.health.r4.api.resources.Device.UdiEntryType;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleDevices {
  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Device device() {
    return Device.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .text(narrative())
        .contained(singletonList(resource()))
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .identifier(singletonList(identifier()))
        .definition(reference())
        .udiCarrier(deviceIdentifier())
        .status(Status.active)
        .statusReason(singletonList(codeableConcept()))
        .distinctIdentifier("Blah")
        .manufacturer("BOSTON SCIENTIFIC")
        .manufactureDate("2015-04-15T04:00:00Z")
        .expirationDate("2045-04-15T04:00:00Z")
        .lotNumber("1")
        .serialNumber("SERIAL-1234")
        .deviceName(singletonList(deviceName()))
        .modelNumber("12345")
        .partNumber("12346")
        .type(codeableConcept())
        .specialization(singletonList(specialization()))
        .version(singletonList(version()))
        .property(singletonList(property()))
        .patient(reference())
        .owner(reference())
        .contact(singletonList(contactPoint()))
        .location(reference())
        .url("http://DeviceIssuer.com")
        .note(singletonList(annotation()))
        .safety(singletonList(codeableConcept()))
        .parent(reference())
        .build();
  }

  public Device.DeviceIdentifier deviceIdentifier() {
    return Device.DeviceIdentifier.builder()
        .id("999")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .deviceIdentifier("SomeDeviceId")
        .issuer("http://DeviceIssuer.com")
        .jurisdiction("http://DeviceJurisdiction.com")
        .carrierAidc( // DeviceAIDCValue
            "RGV2aWNlQUlEQ1ZhbHVl")
        .carrierHrf("CarrierHRF")
        .entryType(UdiEntryType.unknown)
        .build();
  }

  public Device.DeviceName deviceName() {
    return Device.DeviceName.builder()
        .id("998")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .name("PACEMAKER")
        .type(DeviceNameType.user_friendly_name)
        .build();
  }

  public Device.Property property() {
    return Device.Property.builder()
        .id("996")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .type(codeableConcept())
        .valueQuantity(singletonList(quantity()))
        .valueCode(singletonList(codeableConcept()))
        .build();
  }

  public Device.Specialization specialization() {
    return Device.Specialization.builder()
        .id("997")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .systemType(codeableConcept())
        .version("1")
        .build();
  }

  public Device.Version version() {
    return Device.Version.builder()
        .id("996")
        .extension(singletonList(extension()))
        .modifierExtension(singletonList(extension()))
        .type(codeableConcept())
        .component(identifier())
        .value("1")
        .build();
  }
}
