package com.strajja.database4.controllers;

import com.strajja.database4.domain.dto.BookDto;
import com.strajja.database4.domain.entities.BookEntity;
import com.strajja.database4.mappers.Mapper;
import com.strajja.database4.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private BookService bookService;

    private Mapper<BookEntity, BookDto> bookMapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> createBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto
            ){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.createBook(isbn,bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(savedBookEntity), HttpStatus.CREATED);
    }

    @GetMapping(path="/books")
    public List<BookDto> getAllBooks(){
        List<BookEntity> books=bookService.findAll();
        return books
                .stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn){
        Optional<BookEntity> foundBook=bookService.findOne(isbn);

        return foundBook.map(bookEntity -> {
            return new ResponseEntity<>(bookMapper.mapTo(bookEntity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> fullUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto
    ){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean BookExists=bookService.isExists(isbn);
        BookEntity savedBookEntity=bookService.createBook(isbn,bookEntity);
        BookDto updatedBookDto=bookMapper.mapTo(savedBookEntity);

        if(BookExists){
            return new ResponseEntity<>(updatedBookDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(updatedBookDto, HttpStatus.CREATED);
        }
    }

    @PatchMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto
    ){

        boolean BookExists=bookService.isExists(isbn);

        if(!BookExists){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity=bookService.partialUpdate(isbn,bookEntity);
        BookDto updatedBookDto=bookMapper.mapTo(savedBookEntity);
        return new ResponseEntity<>(updatedBookDto, HttpStatus.OK);
    }
}
