package gov.va.api.health.uscorer4.api.resources;

import static gov.va.api.health.uscorer4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.uscorer4.api.samples.SampleOrganizations;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.junit.Test;

public class OrganizationTest {

  private final SampleOrganizations data = SampleOrganizations.get();

  @Test
  public void bundlerCanBuildOrganizationBundles() {
    Organization.Entry entry =
        Organization.Entry.builder()
            .extension(Collections.singletonList(data.extension()))
            .fullUrl("http://organization.com")
            .id("1234")
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://organization.com/1"))
                        .build()))
            .resource(data.organization())
            .search(data.search())
            .request(data.request())
            .response(data.response())
            .build();

    Organization.Bundle bundle =
        Organization.Bundle.builder()
            .entry(Collections.singletonList(entry))
            .link(
                Collections.singletonList(
                    BundleLink.builder()
                        .relation(BundleLink.LinkRelation.self)
                        .url(("http://organization.com/2"))
                        .build()))
            .type(AbstractBundle.BundleType.searchset)
            .build();

    assertRoundTrip(bundle);
  }

  @Test
  public void organization() {
    assertRoundTrip(data.organization());
  }

  @Test
  public void validationFailsGivenBadIdentifierSlice() {
    assertThat(
            violationsOf(
                data.organization()
                    .identifier(
                        List.of(
                            data.identifier().id("123").system("http://hl7.org/fhir/sid/us-npi"),
                            data.identifier().id("987").system("http://hl7.org/fhir/sid/us-npi")))))
        .isNotEmpty();
  }

  private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator().validate(object);
  }
}
