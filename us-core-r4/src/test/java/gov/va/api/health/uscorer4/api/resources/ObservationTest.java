package gov.va.api.health.uscorer4.api.resources;

import gov.va.api.health.r4.api.bundle.AbstractBundle;
import gov.va.api.health.r4.api.bundle.BundleLink;
import gov.va.api.health.uscorer4.api.samples.SampleKnownTypes;
import gov.va.api.health.uscorer4.api.samples.SampleObservations;
import gov.va.api.health.validation.api.ZeroOrOneOfVerifier;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;

import static gov.va.api.health.uscorer4.api.RoundTrip.assertRoundTrip;
import static org.assertj.core.api.Assertions.assertThat;

public class ObservationTest {

    private final SampleObservations data = SampleObservations.get();
    private final SampleKnownTypes types = SampleKnownTypes.get();

    @Test
    public void bundlerCanBuildObservationBundles() {
        Observation.Entry entry =
                Observation.Entry.builder()
                        .extension(Collections.singletonList(data.extension()))
                        .fullUrl("http://observation.com")
                        .id("123")
                        .link(
                                Collections.singletonList(
                                        BundleLink.builder()
                                                .relation(BundleLink.LinkRelation.self)
                                                .url(("http://observation.com/1"))
                                                .build()))
                        .resource(data.observation())
                        .search(data.search())
                        .request(data.request())
                        .response(data.response())
                        .build();

        Observation.Bundle bundle =
                Observation.Bundle.builder()
                        .entry(Collections.singletonList(entry))
                        .link(
                                Collections.singletonList(
                                        BundleLink.builder()
                                                .relation(BundleLink.LinkRelation.self)
                                                .url(("http://observation.com/2"))
                                                .build()))
                        .type(AbstractBundle.BundleType.searchset)
                        .build();

        assertRoundTrip(bundle);
    }

    @Test
    public void observation() {
        assertRoundTrip(data.observation());
    }

    @Test
    public void relatedGroups() {
        ZeroOrOneOfVerifier.builder()
                .sample(data.observation())
                .fieldPrefix("effective")
                .knownTypes(types.knownTypes())
                .stringTypes(types.knownStringTypes())
                .build()
                .verify();
        ZeroOrOneOfVerifier.builder()
                .sample(data.observation())
                .fieldPrefix("value")
                .knownTypes(types.knownTypes())
                .stringTypes(types.knownStringTypes())
                .build()
                .verify();
    }

    @Test
    public void validationFailsGivenBadCategory() {
        assertThat(violationsOf(data.observation().category(data.codeableConcept()))).isNotEmpty();
    }

    @Test
    public void validationFailsGivenNoCategory() {
        assertThat(violationsOf(data.observation().category(null))).isNotEmpty();
    }

    @Test
    public void validationPassesGivenGoodCategory() {
        assertThat(
                violationsOf(
                        data.observation()
                                .category()
                                .coding()
                                .get(0)
                                .code("http://hl7.org/fhir/observation-category")))
                .isEmpty();
    }

    private <T> Set<ConstraintViolation<T>> violationsOf(T object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator().validate(object);
    }
}
