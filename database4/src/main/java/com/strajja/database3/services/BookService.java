package com.strajja.database3.services;


import com.strajja.database3.domain.entities.BookEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public interface BookService {

    BookEntity createBook(String isbn,BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);
}
