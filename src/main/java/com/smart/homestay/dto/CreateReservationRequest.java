package com.smart.homestay.dto;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateReservationRequest(
        @NotNull Long userId,
        @NotNull Long roomId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        List<FacilityItem> facilities
) {}