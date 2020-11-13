package gov.va.api.health.r4.api.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.SimpleResource;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ZeroOrOneOf;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
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

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Schema(
    description = "https://www.hl7.org/fhir/R4/questionnaireresponse.html",
    example =
        "${r4.questionnaireResponse"
            + ":gov.va.api.health.r4.api.swaggerexamples.SwaggerQuestionnaireResponse"
            + "#questionnaireResponse}")
public class QuestionnaireResponse implements DomainResource {
  @NotBlank @Builder.Default String resourceType = "QuestionnaireResponse";

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

  @Valid Identifier identifier;

  @Valid List<Reference> basedOn;

  @Valid List<Reference> partOf;

  @Pattern(regexp = Fhir.CANONICAL)
  String questionnaire;

  @NotNull Status status;

  @Valid Reference subject;

  @Valid Reference encounter;

  @Pattern(regexp = Fhir.DATETIME)
  String authored;

  @Valid Reference author;

  @Valid Reference source;

  @Valid List<Item> item;

  public enum Status {
    @JsonProperty("in-progress")
    in_progress,
    completed,
    amended,
    @JsonProperty("entered-in-error")
    entered_in_error,
    stopped
  }

  @Data
  @Builder
  @Schema(name = "QuestionnaireResponseItemAnswer")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @ZeroOrOneOf(
      fields = {
        "valueBoolean",
        "valueDecimal",
        "valueInteger",
        "valueDate",
        "valueDateTime",
        "valueTime",
        "valueString",
        "valueUri",
        "valueAttachment",
        "valueCoding",
        "valueQuantity",
        "valueReference"
      },
      message = "Only one value field should be specified")
  public static class Answer implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    Boolean valueBoolean;

    BigDecimal valueDecimal;

    Integer valueInteger;

    @Pattern(regexp = Fhir.DATE)
    String valueDate;

    @Pattern(regexp = Fhir.DATETIME)
    String valueDateTime;

    @Pattern(regexp = Fhir.TIME)
    String valueTime;

    String valueString;

    @Pattern(regexp = Fhir.URI)
    String valueUri;

    @Valid Attachment valueAttachment;

    @Valid Coding valueCoding;

    @Valid Quantity valueQuantity;

    @Valid Reference valueReference;

    @Valid List<Item> item;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = QuestionnaireResponse.Bundle.BundleBuilder.class)
  @Schema(
      name = "QuestionnaireResponseBundle",
      example =
          "${r4.questionnaireResponseBundle:gov.va.api.health.r4.api.swaggerexamples."
              + "SwaggerQuestionnaireResponse#questionnaireResponseBundle}")
  public static class Bundle extends AbstractBundle<QuestionnaireResponse.Entry> {
    /** Bundle builder. */
    @Builder
    public Bundle(
        @NotBlank String resourceType,
        @Pattern(regexp = Fhir.ID) String id,
        @Valid Meta meta,
        @Pattern(regexp = Fhir.URI) String implicitRules,
        @Pattern(regexp = Fhir.CODE) String language,
        @Valid Identifier identifier,
        @NotNull BundleType type,
        @Pattern(regexp = Fhir.INSTANT) String timestamp,
        @Min(0) Integer total,
        @Valid List<BundleLink> link,
        @Valid List<QuestionnaireResponse.Entry> entry,
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
  @JsonDeserialize(builder = QuestionnaireResponse.Entry.EntryBuilder.class)
  @Schema(name = "QuestionnaireResponseEntry")
  public static class Entry extends AbstractEntry<QuestionnaireResponse> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid QuestionnaireResponse resource,
        @Valid Search search,
        @Valid Request request,
        @Valid Response response) {
      super(id, extension, modifierExtension, link, fullUrl, resource, search, request, response);
    }
  }

  @Data
  @Builder
  @Schema(name = "QuestionnaireResponseItem")
  @AllArgsConstructor
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  public static class Item implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank String linkId;

    @Pattern(regexp = Fhir.URI)
    String definition;

    String text;

    @Valid List<Answer> answer;

    @Valid List<Item> item;
  }
}
