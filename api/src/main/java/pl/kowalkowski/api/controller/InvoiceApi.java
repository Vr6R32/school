package pl.kowalkowski.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kowalkowski.api.infrastructure.invoice.model.ClientType;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

interface InvoiceApi {

    @Operation(summary = "Get Invoice for School or Parent by ID and Period",
            description = "Returns an Invoice summary for specified client type based on provided ID, month, and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Invoice for specified client"),
            @ApiResponse(responseCode = "400", description = "School not found"),
            @ApiResponse(responseCode = "400", description = "Parent not found"),
            @ApiResponse(responseCode = "400", description = "Unsupported client type"),
            @ApiResponse(responseCode = "400", description = "Attendances not found"),
    })
    @GetMapping
    InvoiceResponse<InvoiceDTO> generateClientSummaryInvoice(
            @Parameter(description = "ID of the client") @RequestParam UUID clientId,
            @Parameter(description = "Month for the invoice") @RequestParam Month month,
            @Parameter(description = "Year for the invoice") @RequestParam Year year,
            @Parameter(description = "ClientType school or parent") @RequestParam ClientType clientType);
}