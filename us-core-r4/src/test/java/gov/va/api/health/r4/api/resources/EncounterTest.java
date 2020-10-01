package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import static gov.va.api.health.r4.api.bundle.BundleLink.LinkRelation;
import static gov.va.api.health.r4.api.resources.Encounter.Bundle;
import static gov.va.api.health.r4.api.resources.Encounter.Entry;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.datatypes.Identifier;
import gov.va.api.health.r4.api.samples.SampleEncounters;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class EncounterTest {
  private final SampleEncounters data = SampleEncounters.get();

  @Test
  public void bundlerCanBuildEncounterBundles() {
    Entry entry =
        Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://encounter.com")
            .id("123")
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://encounter.com/1"))
                        .build()))
            .resource(data.encounter())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Bundle bundle =
        Bundle.builder()
            .entry(singletonList(entry))
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(LinkRelation.self)
                        .url(("http://encounter.com/2"))
                        .build()))
            .type(BundleType.searchset)
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void encounter() {
    assertRoundTrip(data.encounter());
  }

  @Test
  public void validationFailsGivenBadIdentifier() {
    assertThat(violationsOf(data.encounter().identifier(singletonList(data.identifier()))))
        .isNotEmpty();
  }

  @Test
  public void validationFailsGivenIdentifierWithMissingSystem() {

    List<Identifier> invalidIdentifier =
        singletonList(Identifier.builder().system(null).value("123456").build());

    assertThat(violationsOf(data.encounter().identifier(invalidIdentifier))).isNotEmpty();
  }

  @Test
  public void validationFailsGivenIdentifierWithMissingValue() {
    List<Identifier> invalidIdentifier =
        singletonList(
            Identifier.builder()
                .system("http://www.acme.com/identifiers/patient")
                .value(null)
                .build());

    assertThat(violationsOf(data.encounter().identifier(invalidIdentifier))).isNotEmpty();
  }

  @Test
  public void validationPassesGivenGoodIdentifier() {
    List<Identifier> validIdentifier =
        singletonList(
            Identifier.builder()
                .system("http://www.acme.com/identifiers/patient")
                .value("123456")
                .build());
    assertThat(violationsOf(data.encounter().identifier(validIdentifier))).isEmpty();
  }

  @Test
  public void validationPassesGivenNullIdentifier() {
    assertThat(violationsOf(data.encounter().identifier(null))).isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
