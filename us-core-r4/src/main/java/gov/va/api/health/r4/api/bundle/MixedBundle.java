package gov.va.api.health.r4.api.bundle;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.elements.Meta;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "https://hl7.org/fhir/R4/bundle.html")
public final class MixedBundle extends AbstractBundle<MixedEntry> {
  /** Builder constructor. */
  @Builder
  public MixedBundle(
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
      @Valid List<MixedEntry> entry,
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
