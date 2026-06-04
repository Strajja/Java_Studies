package com.strajja.books.controllers;

import lombok.Builder;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.strajja.books.domain.Book;

@RestController
@Log
public class BookController {

    @GetMapping(path="/books")
    public Book retrieveBook() {
        return Book.builder()
                .isbn("0000-1111-2222")
                .title("Book Title")
                .author("Author")
                .yearPublished("1990")
                .build();
    }

    @PostMapping(path="/books")
    public Book createBook(@RequestBody Book book) {
        log.info("Got book: "+book.toString());
        return book;
    }
}
