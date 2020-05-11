package gov.va.api.health.r4.api.samples;

import static java.util.Collections.singletonList;

import gov.va.api.health.r4.api.resources.CapabilityStatement;
import gov.va.api.health.r4.api.resources.TerminologyCapabilities;
import java.util.Arrays;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SampleTerminologyCapabilities {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public List<TerminologyCapabilities.CodeSystem> codeSystem() {
    return singletonList(
        TerminologyCapabilities.CodeSystem.builder()
            .version(version())
            .subsumption("true")
            .build());
  }

  public TerminologyCapabilities.Implementation implementation() {
    return TerminologyCapabilities.Implementation.builder()
        .description("words words")
        .url("http://example.com")
        .build();
  }

  public TerminologyCapabilities.Software software() {
    return TerminologyCapabilities.Software.builder().name("Jimmy").version("1").build();
  }

  public TerminologyCapabilities terminologyCapabilities() {
    return TerminologyCapabilities.builder()
        .id("c1")
        .resourceType("Conformance")
        .implicitRules("https://example.com")
        .language("en")
        .url("http://example.com")
        .version("1")
        .name("conformin' norman")
        .title("urgent care capability")
        .status(CapabilityStatement.Status.active)
        .experimental("true")
        .date("2000-01-01T00:00:00-00:00")
        .publisher("random house")
        .contact(singletonList(contactDetail()))
        .description("words words")
        .useContext(singletonList(usageContext()))
        .jurisdiction(singletonList(codeableConcept()))
        .purpose("words words")
        .copyright("Alphonso, Lord of the Mangos")
        .kind(CapabilityStatement.Kind.capability)
        .software(software())
        .implementation(implementation())
        .lockedDate("true")
        .codeSystem(codeSystem())
        .build();
  }

  public List<TerminologyCapabilities.Version> version() {
    return singletonList(
        TerminologyCapabilities.Version.builder()
            .isDefault("true")
            .compositional("true")
            .property(Arrays.asList("version_property"))
            .build());
  }
}
