package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SamplePractitioners;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

public class PractitionerTest {
  private final SamplePractitioners data = SamplePractitioners.get();

  @Test
  public void bundlerCanBuildPractitionerBundles() {
    Practitioner.Entry entry =
        Practitioner.Entry.builder()
            .extension(singletonList(data.extension()))
            .fullUrl("http://practitioner.com")
            .id("1234")
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://practitioner.com/1"))
                        .build()))
            .resource(data.practitioner())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();
    Practitioner.Bundle bundle =
        Practitioner.Bundle.builder()
            .entry(singletonList(entry))
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://practitioner.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void practitioner() {
    assertRoundTrip(data.practitioner());
  }

  @Test
  public void validationFailsGivenBadIdentifier() {
    Practitioner prac = data.practitioner();
    assertThat(violationsOf(prac)).isEmpty();
    prac.identifier().stream()
        .forEach(
            i -> {
              i.value(null);
              i.system(null);
            });
    assertThat(violationsOf(prac)).isNotEmpty();
  }

  @Test
  public void validationFailsGivenBadName() {
    Practitioner prac = data.practitioner();
    assertThat(violationsOf(prac)).isEmpty();
    prac.name().stream().forEach(n -> n.family(null));
    assertThat(violationsOf(prac)).isNotEmpty();
  }

  @Test
  public void validationPassesGivenGoodIdentifier() {
    Practitioner prac = data.practitioner();
    assertThat(violationsOf(prac)).isEmpty();
    prac.identifier().stream()
        .forEach(
            i -> {
              i.value("value");
              i.system("system");
            });
    assertThat(violationsOf(prac)).isEmpty();
  }

  @Test
  public void validationPassesGivenGoodName() {
    Practitioner prac = data.practitioner();
    assertThat(violationsOf(prac)).isEmpty();
    prac.name().stream().forEach(n -> n.family("Smith"));
    assertThat(violationsOf(prac)).isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
