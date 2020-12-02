package gov.va.api.health.dstu2.api;

import gov.va.api.health.dstu2.api.resources.MedicationDispense;
import gov.va.api.health.dstu2.api.resources.OperationOutcome;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface MedicationDispenseApi {
  @Operation(
      summary = "Medication Dispense Read",
      description = "https://www.hl7.org/fhir/DSTU2/medicationdispense.html",
      tags = {"Medication Dispense"})
  @GET
  @Path("MedicationDispense/{id}")
  @ApiResponse(
      responseCode = "200",
      description = "Record found",
      content =
          @Content(
              mediaType = "application/json+fhir",
              schema = @Schema(implementation = MedicationDispense.class)))
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content =
          @Content(
              mediaType = "application/json+fhir",
              schema = @Schema(implementation = OperationOutcome.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Not found",
      content =
          @Content(
              mediaType = "application/json+fhir",
              schema = @Schema(implementation = OperationOutcome.class)))
  MedicationDispense medicationDispenseRead(
      @Parameter(
              in = ParameterIn.PATH,
              name = "id",
              required = true,
              description =
                  "The logical id of the resource. Once assigned, this value never changes.")
          String id);

  @Operation(
      summary = "Medication Dispense Search",
      description = "https://www.hl7.org/fhir/DSTU2/medicationdispense.html",
      tags = {"Medication Dispense"})
  @GET
  @Path("MedicationDispense")
  @ApiResponse(
      responseCode = "200",
      description = "Record found",
      content =
          @Content(
              mediaType = "application/json+fhir",
              schema = @Schema(implementation = MedicationDispense.Bundle.class)))
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content =
          @Content(
              mediaType = "application/json+fhir",
              schema = @Schema(implementation = OperationOutcome.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Not found",
      content =
          @Content(
              mediaType = "application/json+fhir",
              schema = @Schema(implementation = OperationOutcome.class)))
  MedicationDispense.Bundle medicationDispenseSearch(
      @Parameter(
              in = ParameterIn.QUERY,
              name = "patient",
              description =
                  "The Integration Control Number (ICN) assigned by the Master Veteran Index (MVI)"
                      + " that refers to the person to whom the medication will be given.")
          String id,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "page",
              description = "The page number of the search result.")
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
