package gov.va.api.health.argonaut.api;

import gov.va.api.health.argonaut.api.resources.Observation;
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

public interface ObservationApi {
  @Operation(
    summary = "Observation Read",
    description =
        "http://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-observationresults.html",
    tags = {"Observation"}
  )
  @GET
  @Path("Observation/{id}")
  @ApiResponse(
    responseCode = "200",
    description = "Record found",
    content =
        @Content(
          mediaType = "application/fhir+json",
          schema = @Schema(implementation = Observation.class)
        )
  )
  @ApiResponse(
    responseCode = "400",
    description = "Bad request",
    content =
        @Content(
          mediaType = "application/json+fhir",
          schema = @Schema(implementation = OperationOutcome.class)
        )
  )
  @ApiResponse(
    responseCode = "404",
    description = "Not found",
    content =
        @Content(
          mediaType = "application/json+fhir",
          schema = @Schema(implementation = OperationOutcome.class)
        )
  )
  Observation observationRead(
      @Parameter(
            in = ParameterIn.PATH,
            name = "id",
            required = true,
            description =
                "The logical id of the resource, as used in the URL for the resource."
                    + " Once assigned, this value never changes."
          )
          String id);

  @Operation(
    summary = "Observation Search",
    description =
        "http://www.fhir.org/guides/argonaut/r2/StructureDefinition-argo-observationresults.html",
    tags = {"Observation"}
  )
  @GET
  @Path("Observation")
  @ApiResponse(
    responseCode = "200",
    description = "Record found",
    content =
        @Content(
          schema = @Schema(implementation = Observation.Bundle.class),
          mediaType = "application/fhir+json"
        )
  )
  @ApiResponse(
    responseCode = "400",
    description = "Bad request",
    content =
        @Content(
          mediaType = "application/json+fhir",
          schema = @Schema(implementation = OperationOutcome.class)
        )
  )
  @ApiResponse(
    responseCode = "404",
    description = "Not found",
    content =
        @Content(
          mediaType = "application/json+fhir",
          schema = @Schema(implementation = OperationOutcome.class)
        )
  )
  Observation.Bundle observationSearch(
      @Parameter(
            in = ParameterIn.QUERY,
            required = true,
            name = "patient",
            description =
                "The Integration Control Number (ICN) assigned by the Master Veteran Index (MVI)"
                    + " that refers to the patient whose characteristics (direct or indirect)"
                    + " are described by the observation "
                    + "and into whose record the observation is placed"
          )
          String id,
      @Parameter(
            in = ParameterIn.QUERY,
            name = "page",
            description = "The page number of the search result."
          )
          @DefaultValue("1")
          int page,
      @Parameter(
            in = ParameterIn.QUERY,
            name = "_count",
            description =
                "The number of resources that should be returned in a single page. "
                    + "The maximum count size is 20."
          )
          @DefaultValue("15")
          int count);
}
