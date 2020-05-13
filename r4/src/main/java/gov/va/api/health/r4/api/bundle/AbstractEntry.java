package gov.va.api.health.r4.api.bundle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.resources.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "https://hl7.org/fhir/R4/bundle.html")
public abstract class AbstractEntry<T extends Resource> implements BackboneElement {
  @Pattern(regexp = Fhir.ID)
  protected String id;

  @Valid protected List<Extension> extension;
  @Valid protected List<Extension> modifierExtension;
  @Valid protected List<BundleLink> link;

  @Pattern(regexp = Fhir.URI)
  protected String fullUrl;

  @Valid protected T resource;
  @Valid Search search;
  @Valid Request request;
  @Valid Response response;

  @SuppressWarnings("unused")
  public enum SearchMode {
    match,
    include,
    outcome
  }

  @SuppressWarnings("unused")
  public enum HttpVerb {
    GET,
    HEAD,
    POST,
    PUT,
    DELETE,
    PATCH
  }

  @Data
  @Builder
  @Schema(description = "https://hl7.org/fhir/R4/bundle.html")
  public static class Request implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    protected final String id;

    @Valid protected final List<Extension> extension;
    @Valid protected final List<Extension> modifierExtension;

    @NotNull HttpVerb method;

    @NotBlank
    @Pattern(regexp = Fhir.URI)
    String url;

    @Pattern(regexp = Fhir.STRING)
    String ifNoneMatch;

    @Pattern(regexp = Fhir.INSTANT)
    String ifModifiedSince;

    @Pattern(regexp = Fhir.STRING)
    String ifMatch;

    @Pattern(regexp = Fhir.STRING)
    String ifNoneExist;
  }

  @Data
  @Builder
  @Schema(description = "https://hl7.org/fhir/R4/bundle.html")
  public static class Response implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    protected final String id;

    @Valid protected final List<Extension> extension;
    @Valid protected final List<Extension> modifierExtension;

    @Pattern(regexp = Fhir.STRING)
    @NotBlank
    String status;

    @Pattern(regexp = Fhir.URI)
    String location;

    @Pattern(regexp = Fhir.STRING)
    String etag;

    @Pattern(regexp = Fhir.INSTANT)
    String lastModified;

    @Valid Resource outcome;
  }

  @Data
  @Builder
  @Schema(description = "https://hl7.org/fhir/R4/bundle.html")
  public static class Search implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    final String id;

    @Valid List<Extension> extension;
    @Valid List<Extension> modifierExtension;
    SearchMode mode;

    @Min(0)
    @Max(1)
    BigDecimal score;
  }
}
