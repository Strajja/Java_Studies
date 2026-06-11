package com.strajja.database4.services;

import com.strajja.database4.domain.entities.BookEntity;
import com.strajja.database4.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

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
    public Page<BookEntity> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public Boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);

        return bookRepository.findById(isbn).map(BookExists->{
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(BookExists::setTitle);
            return bookRepository.save(bookEntity);
        }).orElseThrow(()->new RuntimeException("Book not found"));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }


}
