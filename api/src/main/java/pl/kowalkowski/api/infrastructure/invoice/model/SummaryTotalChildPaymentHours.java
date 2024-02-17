package pl.kowalkowski.api.infrastructure.invoice.model;

import java.math.BigDecimal;


public record SummaryTotalChildPaymentHours(BigDecimal totalPayment, long totalBillableHours) {}