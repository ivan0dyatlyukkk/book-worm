package org.diatliuk.bookstore.service;

import java.util.List;
import org.diatliuk.bookstore.dto.BookDto;
import org.diatliuk.bookstore.dto.BookSearchParametersDto;
import org.diatliuk.bookstore.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> getAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto bookDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto params);
}
