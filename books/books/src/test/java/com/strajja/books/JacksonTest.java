package com.strajja.books;

import com.strajja.books.domain.Book;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class JacksonTest {

    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject(){
        ObjectMapper objectMapper = new ObjectMapper();

        Book book = Book.builder()
                .isbn("isbn")
                .author("author")
                .title("title")
                .yearPublished("yearPublished")
                .build();


        String result=objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"isbn\",\"title\":\"title\",\"author\":\"author\",\"year\":\"yearPublished\"}");

    }

    @Test
    public void testThatObjectMapperCanCreateJsonFromObject(){
        ObjectMapper objectMapper = new ObjectMapper();

        String book="{\"isbn\":\"isbn\",\"title\":\"title\",\"author\":\"author\",\"year\":\"yearPublished\"}";

        Book result=objectMapper.readValue(book, Book.class);

        Book test = Book.builder()
                .isbn("isbn")
                .author("author")
                .title("title")
                .yearPublished("yearPublished")
                .build();

        assertThat(result).isEqualTo(test);
    }
}
