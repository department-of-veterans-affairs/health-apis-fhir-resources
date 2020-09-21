package gov.va.api.health.r4.api.elements;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Coding;
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
@Schema(description = "https://www.hl7.org/fhir/R4/resource.html#meta")
public class Meta implements Element {
  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid List<Extension> extension;

  @Pattern(regexp = Fhir.ID)
  String versionId;

  @Pattern(regexp = Fhir.INSTANT)
  String lastUpdated;

  @Pattern(regexp = Fhir.URI)
  String source;

  List<@Pattern(regexp = Fhir.URI) String> profile;

  @Valid List<Coding> security;

  @Valid List<Coding> tag;
}
