package gov.va.api.health.dstu3.api.datatypes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Schema(description = "http://hl7.org/fhir/STU3/datatypes.html#SampledData")
public class SampledData {
  @Valid @NotNull SimpleQuantity origin;
  @NotNull double period;
  double factor;
  double lowerLimit;
  double upperLimit;

  @NotNull
  @Min(1)
  Integer dimensions;

  @NotBlank String data;
}
