package ru.rogotovskiy.neoflexbookapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rogotovskiy.neoflexbookapi.client.AccountClient;
import ru.rogotovskiy.neoflexbookapi.dto.BookDto;
import ru.rogotovskiy.neoflexbookapi.dto.CreateBookRequest;
import ru.rogotovskiy.neoflexbookapi.exception.BookAlreadyTakenException;
import ru.rogotovskiy.neoflexbookapi.exception.BookNotFoundException;
import ru.rogotovskiy.neoflexbookapi.mapper.BookMapper;
import ru.rogotovskiy.neoflexbookapi.model.Book;
import ru.rogotovskiy.neoflexbookapi.model.enums.BookStatus;
import ru.rogotovskiy.neoflexbookapi.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AccountClient accountClient;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }


    public BookDto getBookById(Long bookId) {
        return bookMapper.toDto(getBook(bookId));
    }

    protected Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(
                () -> new BookNotFoundException("Книга с id = %d не найдена".formatted(bookId))
        );
    }

    public BookDto createBook(CreateBookRequest request) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(request)));
    }

    public BookDto takeBook(Long bookId, Long userId) {
        Book book = getBook(bookId);

        if (book.getStatus() == BookStatus.TAKEN) {
            throw new BookAlreadyTakenException("Книга с id = %d уже взята".formatted(bookId));
        }

        accountClient.getAccount(userId);

        book.setStatus(BookStatus.TAKEN);
        book.setUserId(userId);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public BookDto returnBook(Long bookId) {
        Book book = getBook(bookId);

        book.setStatus(BookStatus.AVAILABLE);
        book.setUserId(null);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public List<BookDto> getUserBooks(Long userId) {
        return bookRepository.findAllByUserId(userId).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
