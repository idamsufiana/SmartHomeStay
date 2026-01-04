package com.smart.homestay.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FacilityItem(
        @NotNull Long facilityId,
        @Min(1) int qty
) {}
