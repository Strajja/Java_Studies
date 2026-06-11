package com.strajja.database4.services;


import com.strajja.database4.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {

    BookEntity createBook(String isbn,BookEntity bookEntity);

    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    Boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
