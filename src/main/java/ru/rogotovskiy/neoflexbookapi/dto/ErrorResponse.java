package ru.rogotovskiy.neoflexbookapi.dto;

import java.time.LocalDateTime;

public record ErrorResponse (
        String message,
        LocalDateTime timestamp
) {
}
