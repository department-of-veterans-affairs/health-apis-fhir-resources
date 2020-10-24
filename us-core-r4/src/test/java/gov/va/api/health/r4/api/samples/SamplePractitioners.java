package gov.va.api.health.r4.api.samples;

import gov.va.api.health.r4.api.resources.Practitioner;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@NoArgsConstructor(staticName = "get")
public class SamplePractitioners {

  @Delegate SampleDataTypes dataTypes = SampleDataTypes.get();

  public Practitioner practitioner() {
    return Practitioner.builder()
        .id("1234")
        .meta(meta())
        .implicitRules("http://GoodnightRules.com")
        .language("Goodnight Language")
        .text(narrative())
        .contained(Collections.singletonList(resource()))
        .extensions(Collections.singletonList(extension()))
        .modifierExtensions(Collections.singletonList(extension()))
        .identifier(Collections.singletonList(identifier().system("system").value("value")))
        .active(true)
        .name(List.of(humanName().given(List.of("John")).family("Smith")))
        .telecom(List.of(contactPoint(), contactPoint()))
        .address(List.of(address(), address()))
        .gender(Practitioner.GenderCode.male)
        .birthDate("1970/09/09")
        .build();
  }
}
