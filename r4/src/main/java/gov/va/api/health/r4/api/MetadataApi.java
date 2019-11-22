package gov.va.api.health.r4.api;

import gov.va.api.health.r4.api.resources.Capability;
import gov.va.api.health.r4.api.resources.OperationOutcome;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface MetadataApi {
  @Operation(
    summary = "Capability",
    description = "http://hl7.org/fhir/R4/capabilitystatement.html",
    tags = "Metadata"
  )
  @GET
  @Path("metadata")
  @ApiResponse(
    responseCode = "200",
    description = "Record found",
    content =
        @Content(
          mediaType = "application/json+fhir",
          schema = @Schema(implementation = Capability.class),
          examples =
              @ExampleObject(
                value =
                    "${r4.capability:gov.va.api.health.r4.api.swaggerexamples.SwaggerCapability#capability}"
              )
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
  @ApiResponse(
    responseCode = "400",
    description = "Bad request",
    content =
        @Content(
          mediaType = "application/json+fhir",
          schema = @Schema(implementation = OperationOutcome.class)
        )
  )
  Capability metadata();
}
