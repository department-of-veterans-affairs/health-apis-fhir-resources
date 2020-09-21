package gov.va.api.health.r4.api;

import gov.va.api.health.r4.api.resources.Immunization;
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

public interface ImmunizationApi {
  @Operation(
      summary = "Immunization Read",
      description =
          "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-immunization.html",
      tags = {"Immunization"})
  @GET
  @Path("Immunization/{id}")
  @ApiResponse(
      responseCode = "200",
      description = "Record found",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = Immunization.class)))
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
  Immunization immunizationRead(
      @Parameter(
              in = ParameterIn.PATH,
              name = "id",
              required = true,
              description =
                  "The logical id of the resource. Once assigned, this value never changes.")
          String id);

  @Operation(
      summary = "Immunization Search",
      description =
          "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-immunization.html",
      tags = {"Immunization"})
  @GET
  @Path("Immunization")
  @ApiResponse(
      responseCode = "200",
      description = "Records Found",
      content =
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = Immunization.Bundle.class)))
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
  Immunization.Bundle immunizationSearch(
      @Parameter(
              in = ParameterIn.QUERY,
              required = true,
              name = "patient",
              description =
                  " The Integration Control Number (ICN) assigned by the "
                      + "Master Veteran Index (MVI) that indicates "
                      + "the patient who the immunization record is "
                      + "associated with.")
          String id,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "page",
              description = "The page number being requested.")
          @DefaultValue("1")
          int page,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "_count",
              description =
                  "The number of resources that should be returned in a single page."
                      + " The maximum count size is 100.")
          @DefaultValue("15")
          int count);
}
