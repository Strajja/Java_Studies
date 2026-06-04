package com.strajja.books;

import com.strajja.books.domain.Book;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

public class JacksonTest {

    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() {
        ObjectMapper objectMapper = new ObjectMapper();

        Book book = Book.builder()
                .isbn("isbn")
                .author("author")
                .title("title")
                .yearPublished("yearPublished")
                .build();


    }
}
