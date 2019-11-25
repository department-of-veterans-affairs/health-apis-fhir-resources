package gov.va.api.health.dstu2.api.swaggerexamples;

import static java.util.Arrays.asList;

import gov.va.api.health.dstu2.api.datatypes.CodeableConcept;
import gov.va.api.health.dstu2.api.resources.OperationOutcome;

public class SwaggerOperationOutcome {

  /**
   * An example OperationOutcome.
   *
   * @return an example OperationOutcome.
   */
  public static OperationOutcome operationOutcome() {
    return OperationOutcome.builder()
        .resourceType("OperationOutcome")
        .issue(
            asList(
                OperationOutcome.Issue.builder()
                    .severity(OperationOutcome.Issue.IssueSeverity.error)
                    .code("forbidden")
                    .details(
                        CodeableConcept.builder()
                            .text("Token not allowed access to this patient.")
                            .build())
                    .build()))
        .build();
  }
}
