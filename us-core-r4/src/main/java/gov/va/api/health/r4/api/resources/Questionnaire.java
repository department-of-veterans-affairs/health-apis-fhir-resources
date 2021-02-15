package gov.va.api.health.r4.api.resources;

import static org.apache.commons.lang3.StringUtils.defaultString;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gov.va.api.health.r4.api.Fhir;
import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.AbstractEntry;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Attachment;
import gov.va.api.health.r4.api.datatypes.CodeableConcept;
import gov.va.api.health.r4.api.datatypes.Coding;
import gov.va.api.health.r4.api.datatypes.ContactDetail;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.datatypes.Period;
import gov.va.api.health.r4.api.datatypes.Quantity;
import gov.va.api.health.r4.api.datatypes.Signature;
import gov.va.api.health.r4.api.datatypes.UsageContext;
import gov.va.api.health.r4.api.elements.BackboneElement;
import gov.va.api.health.r4.api.elements.Extension;
import gov.va.api.health.r4.api.elements.Meta;
import gov.va.api.health.r4.api.elements.Narrative;
import gov.va.api.health.r4.api.elements.Reference;
import gov.va.api.health.validation.api.ExactlyOneOf;
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
    description = "https://www.hl7.org/fhir/R4/questionnaire.html",
    example =
        "${r4.questionnaire"
            + ":gov.va.api.health.r4.api.swaggerexamples.SwaggerQuestionnaire#questionnaire}")
public class Questionnaire implements DomainResource {
  @NotBlank @Builder.Default String resourceType = "Questionnaire";

  @Pattern(regexp = Fhir.ID)
  String id;

  @Valid Meta meta;

  @Pattern(regexp = Fhir.URI)
  String implicitRules;

  @Pattern(regexp = Fhir.CODE)
  String language;

  @Valid Narrative text;

  @Valid List<Resource> contained;

  @Valid List<Extension> extension;

  @Valid List<Extension> modifierExtension;

  @Pattern(regexp = Fhir.URI)
  String url;

  @Valid List<Identifier> identifier;

  String version;

  String name;

  String title;

  List<@Pattern(regexp = Fhir.CANONICAL) String> derivedFrom;

  @NotNull PublicationStatus status;

  Boolean experimental;

  List<@Pattern(regexp = Fhir.CODE) String> subjectType;

  @Pattern(regexp = Fhir.DATETIME)
  String date;

  String publisher;

  @Valid List<ContactDetail> contact;

  @Pattern(regexp = Fhir.MARKDOWN)
  String description;

  @Valid List<UsageContext> useContext;

  @Valid List<CodeableConcept> jurisdiction;

  @Pattern(regexp = Fhir.MARKDOWN)
  String purpose;

  @Pattern(regexp = Fhir.MARKDOWN)
  String copyright;

  @Pattern(regexp = Fhir.DATE)
  String approvalDate;

  @Pattern(regexp = Fhir.DATE)
  String lastReviewDate;

  @Valid Period effectivePeriod;

  @Valid List<Coding> code;

  @Valid List<Item> item;

  public enum EnableWhenBehavior {
    all,
    any
  }

  public enum PublicationStatus {
    draft,
    active,
    retired,
    unknown
  }

  public enum QuestionnaireItemOperator {
    exists,
    @JsonProperty("=")
    equals,
    @JsonProperty("!=")
    notEquals,
    @JsonProperty(">")
    greaterThan,
    @JsonProperty("<")
    lessThan,
    @JsonProperty(">=")
    greaterOrEquals,
    @JsonProperty("<=")
    lessOrEquals
  }

  public enum QuestionnaireItemType {
    group,
    display,
    question,
    @JsonProperty("boolean")
    bool,
    decimal,
    integer,
    date,
    dateTime,
    time,
    string,
    text,
    url,
    choice,
    @JsonProperty("open-choice")
    open_choice,
    attachment,
    reference,
    quantity
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "QuestionnaireItemAnswerOption")
  @ExactlyOneOf(
      fields = {
        "valueInteger",
        "valueDate",
        "valueTime",
        "valueString",
        "valueCoding",
        "valueReference"
      },
      message = "Exactly one value field must be specified")
  public static class AnswerOption implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    Integer valueInteger;

    @Pattern(regexp = Fhir.DATE)
    String valueDate;

    @Pattern(regexp = Fhir.TIME)
    String valueTime;

    String valueString;

    @Valid Coding valueCoding;

    @Valid Reference valueReference;

    Boolean initialSelected;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Questionnaire.Bundle.BundleBuilder.class)
  @Schema(
      name = "QuestionnaireBundle",
      example =
          "${r4.questionnaireBundle:gov.va.api.health.r4.api.swaggerexamples."
              + "SwaggerQuestionnaire#questionnaireBundle}")
  public static class Bundle extends AbstractBundle<Questionnaire.Entry> {
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
        @Valid List<Questionnaire.Entry> entry,
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

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "QuestionnaireItemEnableWhen")
  @ExactlyOneOf(
      fields = {
        "answerBoolean",
        "answerDecimal",
        "answerInteger",
        "answerDate",
        "answerDateTime",
        "answerTime",
        "answerString",
        "answerCoding",
        "answerQuantity",
        "answerReference"
      },
      message = "Exactly one answer field must be specified")
  public static class EnableWhen implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank String question;

    @NotNull QuestionnaireItemOperator operator;

    Boolean answerBoolean;

    BigDecimal answerDecimal;

    Integer answerInteger;

    @Pattern(regexp = Fhir.DATE)
    String answerDate;

    @Pattern(regexp = Fhir.DATETIME)
    String answerDateTime;

    @Pattern(regexp = Fhir.TIME)
    String answerTime;

    String answerString;

    @Valid Coding answerCoding;

    @Valid Quantity answerQuantity;

    @Valid Reference answerReference;
  }

  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @JsonDeserialize(builder = Questionnaire.Entry.EntryBuilder.class)
  @Schema(name = "QuestionnaireEntry")
  public static class Entry extends AbstractEntry<Questionnaire> {
    @Builder
    public Entry(
        @Pattern(regexp = Fhir.ID) String id,
        @Valid List<Extension> extension,
        @Valid List<Extension> modifierExtension,
        @Valid List<BundleLink> link,
        @Pattern(regexp = Fhir.URI) String fullUrl,
        @Valid Questionnaire resource,
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
  @Schema(name = "QuestionnaireItemInitial")
  @ExactlyOneOf(
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
      message = "Exactly one value field must be specified")
  public static class Initial implements BackboneElement {
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
  }

  @Data
  @Builder
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @AllArgsConstructor
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
  @Schema(name = "QuestionnaireItem")
  public static class Item implements BackboneElement {
    @Pattern(regexp = Fhir.ID)
    String id;

    @Valid List<Extension> extension;

    @Valid List<Extension> modifierExtension;

    @NotBlank String linkId;

    @Pattern(regexp = Fhir.URI)
    String definition;

    @Valid List<Coding> code;

    String prefix;

    String text;

    @NotNull QuestionnaireItemType type;

    @Valid List<EnableWhen> enableWhen;

    EnableWhenBehavior enableBehavior;

    Boolean required;

    Boolean repeats;

    Boolean readOnly;

    Integer maxLength;

    @Pattern(regexp = Fhir.CANONICAL)
    String answerValueSet;

    @Valid List<AnswerOption> answerOption;

    @Valid List<Initial> initial;

    @Valid List<Item> item;
  }
}
