package gov.va.api.health.r4.api;

import gov.va.api.health.r4.api.resources.OperationOutcome;
import gov.va.api.health.r4.api.resources.RelatedPerson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface RelatedPersonApi {

    @Operation(
            summary = "RelatedPerson Read",
            description = "https://www.hl7.org/fhir/relatedperson.html",
            tags = {"RelatedPerson"})
    @GET
    @Path("RelatedPerson/{id}")
    @ApiResponse(
            responseCode = "200",
            description = "Record found",
            content =
            @Content(
                    mediaType = "application/fhir+json",
                    schema = @Schema(implementation = RelatedPerson.class)))
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
    RelatedPerson relatedpersonRead(@Parameter(in = ParameterIn.PATH, name = "id", required = true) String id);

    @Operation(
            summary = "RelatedPerson Search",
            description = "https://www.hl7.org/fhir/R4/relatedperson.html",
            tags = {"RelatedPerson"})
    @GET
    @Path("RelatedPerson")
    @ApiResponse(
            responseCode = "200",
            description = "Record found",
            content =
            @Content(
                    mediaType = "application/fhir+json",
                    schema = @Schema(implementation = RelatedPerson.Bundle.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content =
            @Content(
                    mediaType = "application/fhir+json",
                    schema = @Schema(implementation = OperationOutcome.class)))
    @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content =
            @Content(
                    mediaType = "application/fhir+json",
                    schema = @Schema(implementation = OperationOutcome.class)))
    RelatedPerson.Bundle relatedpersonSearch(
            @Parameter(in = ParameterIn.QUERY, required = true, name = "patient") String id,
            @Parameter(in = ParameterIn.QUERY, name = "page") @DefaultValue("1") int page,
            @Parameter(in = ParameterIn.QUERY, name = "_count") @DefaultValue("30") int count);
}
