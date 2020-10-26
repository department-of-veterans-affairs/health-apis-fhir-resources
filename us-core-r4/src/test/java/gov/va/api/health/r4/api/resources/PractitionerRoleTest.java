package gov.va.api.health.r4.api.resources;

import static gov.va.api.health.r4.api.RoundTrip.assertRoundTrip;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.AbstractBundle.BundleType;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.r4.api.samples.SamplePractitionerRoles;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.Test;

public class PractitionerRoleTest {
  private final SamplePractitionerRoles samples = SamplePractitionerRoles.get();

  @Test
  public void bundlerCanBuildPractitionerRoleBundles() {
    PractitionerRole.Bundle bundle =
        PractitionerRole.Bundle.builder()
            .type(BundleType.searchset)
            .link(
                singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url("http://practitionerrole.com/bundle")
                        .build()))
            .entry(
                singletonList(
                    PractitionerRole.Entry.builder()
                        .extension(singletonList(samples.extension()))
                        .fullUrl("http://practitionerrole.com")
                        .id("1234")
                        .link(
                            singletonList(
                                BundleLink.builder()
                                    .relation(BundleLink.LinkRelation.self)
                                    .url("http://practitionerrole.com/entry")
                                    .build()))
                        .resource(samples.practitionerRole())
                        .search(samples.search())
                        .request(samples.request())
                        .response(samples.response())
                        .build()))
            .build();
    assertRoundTrip(bundle);
  }

  @Test
  public void practitionerRole() {
    assertRoundTrip(samples.practitionerRole());
  }

  @Test
  public void validationFailsGivenBadContactPoint() {
    assertThat(
            violationsOf(
                samples
                    .practitionerRole()
                    .telecom(singletonList(samples.contactPoint().system(null)))))
        .isNotEmpty();

    // One good one bad
    assertThat(
            violationsOf(
                samples
                    .practitionerRole()
                    .telecom(asList(samples.contactPoint(), samples.contactPoint().system(null)))))
        .isNotEmpty();
  }

  @Test
  public void validationPassesGivenGoodContactPoint() {
    assertThat(
            violationsOf(samples.practitionerRole().telecom(singletonList(samples.contactPoint()))))
        .isEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
