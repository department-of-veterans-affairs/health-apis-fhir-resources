package gov.va.api.health.r4.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.Element;
import gov.va.api.health.r4.api.elements.Extension;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "https://www.hl7.org/fhir/R4/datatypes.html#Address")
public class Address implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  AddressUse use;
  AddressType type;

  @Pattern(regexp = Fhir.STRING)
  String text;

  List<@Pattern(regexp = Fhir.STRING) String> line;

  @Pattern(regexp = Fhir.STRING)
  String city;

  @Pattern(regexp = Fhir.STRING)
  String district;

  @Pattern(regexp = Fhir.STRING)
  String state;

  @Pattern(regexp = Fhir.STRING)
  String postalCode;

  @Pattern(regexp = Fhir.STRING)
  String country;

  @Valid Period period;

  @SuppressWarnings("unused")
  public enum AddressUse {
    home,
    work,
    temp,
    old,
    billing
  }

  @SuppressWarnings("unused")
  public enum AddressType {
    postal,
    physical,
    both
  }
}
