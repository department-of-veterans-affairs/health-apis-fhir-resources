package gov.va.api.health.stu3.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.stu3.api.Fhir;
import gov.va.api.health.stu3.api.bundle.AbstractBundle;
import gov.va.api.health.stu3.api.bundle.AbstractEntry;
import gov.va.api.health.stu3.api.bundle.BundleLink;
import gov.va.api.health.stu3.api.datatypes.Address;
import gov.va.api.health.stu3.api.datatypes.CodeableConcept;
import gov.va.api.health.stu3.api.datatypes.Coding;
import gov.va.api.health.stu3.api.datatypes.ContactPoint;
import gov.va.api.health.stu3.api.datatypes.Identifier;
import gov.va.api.health.stu3.api.datatypes.Period;
import gov.va.api.health.stu3.api.datatypes.Signature;
import gov.va.api.health.stu3.api.datatypes.SimpleResource;
import gov.va.api.health.stu3.api.elements.BackboneElement;
import gov.va.api.health.stu3.api.elements.Element;
import gov.va.api.health.stu3.api.elements.Extension;
import gov.va.api.health.stu3.api.elements.Meta;
import gov.va.api.health.stu3.api.elements.Narrative;
import gov.va.api.health.stu3.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(
  description = "http://www.fhir.org/guides/argonaut/pd/StructureDefinition-argo-location.html"
)
public class Location implements DomainResource {
  @NotBlank String resourceType;

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid List<SimpleResource> contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  @Valid List<Identifier> identifier;

  Status status;

  Coding operationalStatus;

  String name;

  List<String> alias;

  String description;

  Mode mode;

  @Valid CodeableConcept type;

  @Valid List<ContactPoint> telecom;

  @Valid LocationAddress address;

  @Valid CodeableConcept physicalType;

  @Valid Position position;

  @Valid Reference managingOrganization;

  @Valid Reference partOf;

  @Valid List<Reference> endpoint;

  public enum Mode {
    instance,
    kind
  }

  public enum Status {
    active,
    suspended,
    inactive
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Location.Bundle.BundleBuilder.class)
  public static class Bundle extends AbstractBundle<Entry> {
    @Builder
    public Bundle(
        @NotBlank String resourceType,
        @Pattern(regexp = Fhir.ID) String id,
        @Valid Meta meta,
        @Pattern(regexp = Fhir.URI) String implicitRules,
        @Pattern(regexp = Fhir.CODE) String language,
        @NotNull BundleType type,
        @Min(0) Integer total,
        @Valid List<BundleLink> link,
        @Valid List<Location.Entry> entry,
        @Valid Signature signature) {
      super(resourceType, id, meta, implicitRules, language, type, total, link, entry, signature);
    }
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Location.Entry.EntryBuilder.class)
  @Schema(name = "LocationEntry")
  public static class Entry extends AbstractEntry<Location> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Location resource,
        @Valid Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class LocationAddress implements Element {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;
    Address.AddressUse use;
    Address.AddressType type;
    @NotNull String text;
    @NotEmpty List<String> line;
    String city;
    String district;
    String state;
    String postalCode;
    String country;
    @Valid Period period;
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Position implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotNull double longitude;

    @NotNull double latitude;

    double altitude;
  }
}
