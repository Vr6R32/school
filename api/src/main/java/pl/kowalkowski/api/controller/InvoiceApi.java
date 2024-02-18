package pl.kowalkowski.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceSchoolDTO;

import java.time.Month;
import java.util.UUID;

interface InvoiceApi {

    @Operation(summary = "Get Invoice for School by ID and Period",
            description = "Returns an Invoice summary for a school based on provided ID, month, and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Invoice for School"),
            @ApiResponse(responseCode = "400", description = "School not found"),
            @ApiResponse(responseCode = "400", description = "Attendances not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    InvoiceResponse<InvoiceSchoolDTO> getInvoiceForSchoolByIdAndPeriod(
            @Parameter(description = "ID of the school") @RequestParam UUID schoolId,
            @Parameter(description = "Month for the invoice") @RequestParam Month month,
            @Parameter(description = "Year for the invoice") @RequestParam int year);

    @Operation(summary = "Get Invoice for Parent by ID and Period",
            description = "Returns an Invoice summary for a parent based on provided ID, month, and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Invoice for Parent"),
            @ApiResponse(responseCode = "400", description = "Parent not found"),
            @ApiResponse(responseCode = "400", description = "Attendances not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    InvoiceResponse<InvoiceParentDTO> getInvoiceForParentByIdAndPeriod(
            @Parameter(description = "ID of the parent") @RequestParam UUID parentId,
            @Parameter(description = "Month for the invoice") @RequestParam Month month,
            @Parameter(description = "Year for the invoice") @RequestParam int year);
}