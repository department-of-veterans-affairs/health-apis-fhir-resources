package gov.va.api.health.r4.api;

import gov.va.api.health.r4.api.resources.ExplanationOfBenefit;
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

public interface ExplanationOfBenefitApi {

  @Operation(
      summary = "ExplanationOfBenefit Read",
      description = "https://www.hl7.org/fhir/explanationofbenefit.html",
      tags = {"ExplanationOfBenefit"})
  @GET
  @Path("ExplanationOfBenefit/{id}")
  @ApiResponse(
      responseCode = "200",
      description = "Record found",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = ExplanationOfBenefit.class)))
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Not found",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class)))
  ExplanationOfBenefit explanationOfBenefitRead(
      @Parameter(in = ParameterIn.PATH, name = "id", required = true) String id);

  @Operation(
      summary = "Get detailed Explanation of Benefit for a Veteran",
      description = "https://www.hl7.org/fhir/explanationofbenefit.html",
      tags = {"ExplanationOfBenefit"})
  @GET
  @Path("ExplanationOfBenefit")
  @ApiResponse(
      responseCode = "200",
      description = "Record found",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = ExplanationOfBenefit.Bundle.class)))
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class)))
  ExplanationOfBenefit.Bundle explanationOfBenefitSearch(
      @Parameter(in = ParameterIn.QUERY, required = true, name = "patient") String id,
      @Parameter(in = ParameterIn.QUERY, name = "page") @DefaultValue("1") int page,
      @Parameter(in = ParameterIn.QUERY, name = "_count") @DefaultValue("15") int count);
}
