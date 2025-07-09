package ru.rogotovskiy.neoflexbookapi.dto;

import ru.rogotovskiy.neoflexbookapi.model.enums.BookStatus;

public record BookDto(
        Long id,
        String title,
        String author,
        BookStatus status,
        Long userId
) {
}
