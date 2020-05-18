package gov.va.api.health.fhir.api;

import static java.util.stream.Collectors.toList;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class FhirVersionTest {

  @Test
  void sighsHeavily() {
    Stream.of(FhirVersion.values()).map(FhirVersion::toString).collect(toList());
  }
}
