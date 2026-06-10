package com.strajja.database4.services;


import com.strajja.database4.domain.entities.BookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {

    BookEntity createBook(String isbn,BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    Boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);
}
