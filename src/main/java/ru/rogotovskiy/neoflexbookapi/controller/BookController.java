package ru.rogotovskiy.neoflexbookapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rogotovskiy.neoflexbookapi.dto.CreateBookRequest;
import ru.rogotovskiy.neoflexbookapi.service.BookService;

@RestController
@Validated
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody @Valid CreateBookRequest request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    @PostMapping("/{bookId}/take")
    public ResponseEntity<?> takeBook(@PathVariable Long bookId, @RequestParam Long userId) {
        return ResponseEntity.ok(bookService.takeBook(bookId, userId));
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.returnBook(bookId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserBooks(@PathVariable Long userId) {
        return ResponseEntity.ok(bookService.getUserBooks(userId));
    }

}
