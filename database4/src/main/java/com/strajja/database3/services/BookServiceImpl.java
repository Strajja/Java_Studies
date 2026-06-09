package com.strajja.database3.services;

import com.strajja.database3.domain.entities.BookEntity;
import com.strajja.database3.repositories.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn,BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> findAll() {
        Iterable<BookEntity> bookEntities = bookRepository.findAll();
        return StreamSupport
                .stream(bookEntities.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }


}
