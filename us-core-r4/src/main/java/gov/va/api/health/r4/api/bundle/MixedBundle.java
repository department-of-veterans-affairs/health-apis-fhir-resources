package gov.va.api.health.r4.api.bundle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.resources.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonDeserialize(builder = MixedBundle.MixedBundleBuilder.class)
@Schema(description = "https://hl7.org/fhir/R4/bundle.html")
public class MixedBundle extends AbstractBundle<MixedEntry> {

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
            "Bundle",
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
