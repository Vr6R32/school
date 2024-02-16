package pl.kowalkowski.api.infrastructure.invoice.model;

import java.math.BigDecimal;


public record SummaryTotalPaymentHours(BigDecimal totalPayment, long totalBillableHours) {}