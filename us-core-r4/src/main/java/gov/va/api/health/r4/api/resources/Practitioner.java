package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Address;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.ContactPoint;
import gov.va.api.health.r4.api.datatypes.HumanName;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
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
import javax.validation.constraints.NotEmpty;
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
@Schema(description = " http://hl7.org/fhir/us/core/StructureDefinition-us-core-practitioner.html")
public class Practitioner implements Resource {
  @NotBlank @Builder.Default String resourceType = "Practitioner";

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid List<SimpleResource> contained;

  @Valid List<Extension> extensions;

  @Valid List<Extension> modifierExtensions;

  @Valid @NotEmpty List<Identifier> identifier;

  Boolean active;

  @Valid @NotEmpty List<HumanName> name;

  @Valid List<ContactPoint> telecom;

  @Valid List<Address> address;

  @Valid GenderCode gender;

  @Pattern(regexp = Fhir.DATE)
  String birthDate;

  @Valid List<Attachment> photo;

  @Valid List<Qualification> qualification;

  @Valid List<CodeableConcept> communication;

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "System and value of identifier must be specified")
  private boolean isValidIdentifierSystemAndValue() {
    if (identifier == null) {
      return false;
    }

    if (identifier.stream().filter(e -> "http://hl7.org/fhir/sid/us-npi".equals(e.system())).count()
        > 1) {
      return false;
    }

    return identifier.stream()
        .allMatch(
            e ->
                StringUtils.isNotEmpty(e.system())
                    && (StringUtils.isNotEmpty(e.value())
                        || "http://hl7.org/fhir/sid/us-npi".equals(e.system())));
  }

  @JsonIgnore
  @SuppressWarnings("unused")
  @AssertTrue(message = "Family name must be specified")
  private boolean isValidNameFamily() {
    if (name == null) {
      return false;
    }
    return name.stream().allMatch(e -> StringUtils.isNotEmpty(e.family()));
  }

  public enum GenderCode {
    male,
    female,
    other,
    unknown
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "PractitionerQualification")
  public static class Qualification implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @Valid List<Identifier> identifier;

    @Valid @NotNull CodeableConcept code;

    @Valid Period period;

    @Valid Reference issuer;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Practitioner.Bundle.BundleBuilder.class)
  @Schema(name = "PractitionerBundle")
  public static class Bundle extends AbstractBundle<Practitioner.Entry> {
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
        @Valid List<Practitioner.Entry> entry,
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
  @JsonDeserialize(builder = Practitioner.Entry.EntryBuilder.class)
  @Schema(name = "PractitionerEntry")
  public static class Entry extends AbstractEntry<Practitioner> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Practitioner resource,
        @Valid Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }
}
