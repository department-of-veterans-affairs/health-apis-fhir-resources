package gov.va.api.health.fhir.api;

import java.time.Instant;
import javax.xml.datatype.DatatypeFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FhirDateTime {
  /**
   * Attempt to parse the given dateTime string. Returns null if the given string is null or empty,
   * throws a IllegalArgumentException if the date cannot be parsed, otherwise returns an Instant.
   */
  @SneakyThrows
  public static Instant parseDateTime(String dateTime) {
    if (StringUtils.isBlank(dateTime)) {
      return null;
    }
    return DatatypeFactory.newInstance()
        .newXMLGregorianCalendar(dateTime)
        .toGregorianCalendar()
        .toInstant();
  }
}
