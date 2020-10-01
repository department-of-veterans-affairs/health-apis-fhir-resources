package gov.va.api.health.deidentification.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SaltedType5UuidGeneratorTest {

  @Test
  void differentIcnsGenerateDifferentUuids() {
    DeidentifiedIdGenerator generator = idGenerator("Patient");
    /*
     * Make sure the different UUIDs are generated for the different ICNs
     */
    String firstGeneratedUuid = generator.generateIdFrom("12345V67890");
    String secondGeneratedUuid = generator.generateIdFrom("12345V67891");
    assertThat(firstGeneratedUuid).isNotEqualTo(secondGeneratedUuid);
  }

  SaltedType5UuidGenerator idGenerator(String resource) {
    return SaltedType5UuidGenerator.builder()
        .resource(resource)
        .saltKey("SALTYBRAH")
        .seed("3e7636e4-6e18-58df-8ee8-69f4d9770c3e")
        .build();
  }

  @Test
  void uuidIsConsistentlyGenerated() {
    String icnToUuid = "12345V67890";
    DeidentifiedIdGenerator generator = idGenerator("Patient");
    /*
     * Make sure the same UUID is generated for the same ICN multiple times
     */
    String firstGeneratedUuid = generator.generateIdFrom(icnToUuid);
    String secondGeneratedUuid = generator.generateIdFrom(icnToUuid);
    assertThat(firstGeneratedUuid).isEqualTo(secondGeneratedUuid);
  }

  @Test
  void uuidIsConsistentlyGeneratedForDifferentInstances() {
    String icnToUuid = "12345V67890";
    /*
     * Make sure the same UUID is generated for the same ICN multiple times with different instances of the generator
     */
    String firstGeneratedUuid = idGenerator("Patient").generateIdFrom(icnToUuid);
    String secondGeneratedUuid = idGenerator("Patient").generateIdFrom(icnToUuid);
    assertThat(firstGeneratedUuid).isEqualTo(secondGeneratedUuid);
    String wrongGeneratedUuid = idGenerator("AlmostPatient").generateIdFrom(icnToUuid);
    assertThat(wrongGeneratedUuid).isNotEqualTo(firstGeneratedUuid);
  }
}
