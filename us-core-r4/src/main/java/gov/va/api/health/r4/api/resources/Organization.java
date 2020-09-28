package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.CarinBlueButton;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
    description =
        "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-organization.html",
    example =
        "${r4.organization:gov.va.api.health."
            + "r4.api.swaggerexamples.SwaggerOrganization#organization}")
public class Organization implements Resource {
  // Ancestors
  @NotBlank @Builder.Default String resourceType = "Organization";

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

  // Organization
  /* An identifier slice can contain any number of identifiers,
  but at most there can be one Identifier NPI slice,
  and at most there can be one Identifier CLIA slice.
  */
  @CarinBlueButton(
      note =
          "Carin includes an additional slice definition for Identifier:TIN with cardinality ..*."
              + "The Identifier.type field's cardinality changes cardinality to 1..1. "
              + "The Identifier:NPI slice's Type.Coding changes cardinality to 1..*. "
              + "The Identifier:NPI slice's Type.Coding.Code changes cardinality to 1..1.")
  @Valid
  List<Identifier> identifier;

  @NotNull Boolean active;

  @Valid List<CodeableConcept> type;

  @NotNull String name;

  @Valid List<String> alias;

  @Valid List<ContactPoint> telecom;

  @Valid List<Address> address;

  @Valid Reference partOf;

  @Valid Contact contact;

  @Valid List<Reference> endpoint;

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "At most one IdentifierClia can be specified.")
  private boolean isIdentifierCliaSliceValid() {
    if (identifier == null) {
      return true;
    }
    return identifier.stream()
            .filter(
                e ->
                    StringUtils.isNotBlank(e.system())
                        && e.system().equals("urn:oid:2.16.840.1.113883.4.7"))
            .count()
        <= 1;
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "At most one IdentifierNpi can be specified.")
  private boolean isIdentifierNpiSliceValid() {
    if (identifier == null) {
      return true;
    }
    return identifier.stream()
            .filter(
                e ->
                    StringUtils.isNotBlank(e.system())
                        && e.system().equals("http://hl7.org/fhir/sid/us-npi"))
            .count()
        <= 1;
  }

  @Data
  @Builder
  @Schema(name = "OrganizationContact")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Contact implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid CodeableConcept purpose;

    @Valid HumanName name;

    @Valid List<ContactPoint> telecom;

    @Valid Address address;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Organization.Bundle.BundleBuilder.class)
  @Schema(
      name = "OrganizationBundle",
      example =
          "${r4.organizationBundle:"
              + "gov.va.api.health.r4.api.swaggerexamples."
              + "SwaggerOrganization#organizationBundle}")
  public static class Bundle extends AbstractBundle<Entry> {
    /** Builder constructor. */
    @Builder
    public Bundle(
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
        @Valid List<Organization.Entry> entry,
        @Valid Signature signature) {
      super(
          resourceType,
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

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Organization.Entry.EntryBuilder.class)
  @Schema(name = "OrganizationEntry")
  public static class Entry extends AbstractEntry<Organization> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Organization resource,
        @Valid Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }
}
