package gov.va.api.health.r4.api;

import gov.va.api.health.r4.api.resources.CoverageEligibilityResponse;
import gov.va.api.health.r4.api.resources.OperationOutcome;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface CoverageEligibilityResponseApi {

  @Operation(
      summary = "Get detailed Coverage Eligibility for a Veteran",
      description =
          "The CoverageEligibilityResponse resource provides eligibility and plan details. "
              + "It combines key information from a payor as to whether a Coverage is in-force, "
              + "and optionally the nature of the Policy benefit details as well as the ability "
              + "for the insurer to indicate whether the insurance provides benefits for requested "
              + "types of services."
              + "<br><br>"
              + "**FOR MORE INFO:** [https://www.hl7.org/fhir/R4/coverageeligibilityresponse.html]"
              + "(https://www.hl7.org/fhir/R4/coverageeligibilityresponse.html)",
      tags = {"CoverageEligibilityResponse"})
  @GET
  @Path("CoverageEligibilityResponse")
  @ApiResponse(
      responseCode = "200",
      description = "Record found",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = CoverageEligibilityResponse.Bundle.class)))
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class)))
  CoverageEligibilityResponse.Bundle coverageEligibilityResponseSearch(
      @Parameter(in = ParameterIn.QUERY, name = "patient") String id,
      @Parameter(in = ParameterIn.QUERY, name = "page") @DefaultValue("1") int page,
      @Parameter(in = ParameterIn.QUERY, name = "_count") @DefaultValue("30") int count);
}
