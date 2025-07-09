package ru.rogotovskiy.neoflexbookapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.rogotovskiy.neoflexbookapi.dto.BookDto;
import ru.rogotovskiy.neoflexbookapi.dto.CreateBookRequest;
import ru.rogotovskiy.neoflexbookapi.model.Book;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookDto toDto(Book book);
    Book toEntity(CreateBookRequest request);
}
