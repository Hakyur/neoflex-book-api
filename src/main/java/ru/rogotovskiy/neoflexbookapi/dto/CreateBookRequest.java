package ru.rogotovskiy.neoflexbookapi.dto;

public record CreateBookRequest(
        String title,
        String author
) {
}
