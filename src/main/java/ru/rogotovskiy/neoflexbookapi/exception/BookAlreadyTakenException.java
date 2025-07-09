package ru.rogotovskiy.neoflexbookapi.exception;

public class BookAlreadyTakenException extends RuntimeException {
    public BookAlreadyTakenException(String message) {
        super(message);
    }
}
