package pl.kowalkowski.api.infrastructure.school;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record SchoolDTO(UUID id, String name, BigDecimal hourPrice) {
}
