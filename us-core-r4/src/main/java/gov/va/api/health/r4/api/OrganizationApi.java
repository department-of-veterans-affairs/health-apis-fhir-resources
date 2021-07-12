package gov.va.api.health.r4.api;

import gov.va.api.health.r4.api.resources.OperationOutcome;
import gov.va.api.health.r4.api.resources.Organization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface OrganizationApi {
  @Operation(
      summary = "Organization Read",
      description =
          "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-organization.html",
      tags = {"Organization"})
  @GET
  @Path("Organization/{id}")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Record found",
        content =
            @Content(
                mediaType = "application/fhir+json",
                schema = @Schema(implementation = Organization.class))),
    @ApiResponse(
        responseCode = "400",
        description = "Bad request",
        content =
            @Content(
                mediaType = "application/fhir+json",
                schema = @Schema(implementation = OperationOutcome.class))),
    @ApiResponse(
        responseCode = "401",
        description = "Unauthorized",
        content = {
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class))
        }),
    @ApiResponse(
        responseCode = "403",
        description = "Forbidden",
        content = {
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class))
        }),
    @ApiResponse(
        responseCode = "404",
        description = "Not found",
        content =
            @Content(
                mediaType = "application/fhir+json",
                schema = @Schema(implementation = OperationOutcome.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Server Error",
        content = {
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class))
        })
  })
  Organization organizationRead(
      @Parameter(
              in = ParameterIn.PATH,
              name = "id",
              required = true,
              description =
                  "The logical id of the resource. Once assigned, this value never changes.",
              example = "I3-87aih87KJHaMAuy89PKA90AOIJL897QHJKNaPZha9jLJKHAO8hkja")
          String id);

  @Operation(
      summary = "Organization Search",
      description =
          "https://build.fhir.org/ig/HL7/US-Core-R4/StructureDefinition-us-core-organization.html",
      tags = {"Organization"})
  @GET
  @Path("Organization")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Record found",
        content =
            @Content(
                mediaType = "application/fhir+json",
                schema = @Schema(implementation = Organization.Bundle.class))),
    @ApiResponse(
        responseCode = "400",
        description = "Bad request",
        content =
            @Content(
                mediaType = "application/fhir+json",
                schema = @Schema(implementation = OperationOutcome.class))),
    @ApiResponse(
        responseCode = "401",
        description = "Unauthorized",
        content = {
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class))
        }),
    @ApiResponse(
        responseCode = "403",
        description = "Forbidden",
        content = {
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class))
        }),
    @ApiResponse(
        responseCode = "404",
        description = "Not found",
        content =
            @Content(
                mediaType = "application/fhir+json",
                schema = @Schema(implementation = OperationOutcome.class))),
    @ApiResponse(
        responseCode = "500",
        description = "Server Error",
        content = {
          @Content(
              mediaType = "application/fhir+json",
              schema = @Schema(implementation = OperationOutcome.class))
        })
  })
  Organization.Bundle organizationSearch(
      @Parameter(
              in = ParameterIn.QUERY,
              name = "_id",
              description =
                  "The logical id of the resource. Once assigned, this value never changes.",
              example = "I3-87aih87KJHaMAuy89PKA90AOIJL897QHJKNaPZha9jLJKHAO8hkja")
          String id,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "identifier",
              description =
                  "The logical identifier of the resource. Once assigned, this value "
                      + "never changes.",
              example = "I3-87aih87KJHaMAuy89PKA90AOIJL897QHJKNaPZha9jLJKHAO8hkja")
          String identifier,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "address",
              description =
                  "Indicates the physical location of the organization "
                      + "expressed using postal conventions "
                      + "(as opposed to GPS or other location definition formats).",
              example = "2360+E+PERSHING+BLVD")
          String address,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "address-city",
              description = "Indicates the geographical city where the organization resides.",
              example = "CHEYENNE")
          String addressCity,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "address-state",
              description = "Indicates the geographical state where the organization resides.",
              example = "WY")
          String addressState,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "address-postalcode",
              description =
                  "Indicates the postal code that designates the region"
                      + " where the organization resides.",
              example = "82001-5356")
          String addressPostalCode,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "name",
              description = "Indicates the name of the organization as it is referenced by humans.",
              example = "CHEYENNE+VA+MEDICAL")
          String name,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "page",
              description = "The page number of the search result.",
              example = "2")
          @DefaultValue("1")
          int page,
      @Parameter(
              in = ParameterIn.QUERY,
              name = "_count",
              description =
                  "The number of resources that should be returned in a single page."
                      + " The maximum count size is 100.",
              example = "30")
          @DefaultValue("30")
          int count);
}
