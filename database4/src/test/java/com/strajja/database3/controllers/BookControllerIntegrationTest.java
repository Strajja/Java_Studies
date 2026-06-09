package com.strajja.database3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strajja.database3.TestDataUtil;
import com.strajja.database3.domain.dto.AuthorDto;
import com.strajja.database3.domain.dto.BookDto;
import com.strajja.database3.domain.entities.AuthorEntity;
import com.strajja.database3.domain.entities.BookEntity;
import com.strajja.database3.mappers.Mapper;
import com.strajja.database3.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {


    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private BookService bookService;

    @Autowired
    public BookControllerIntegrationTest(ObjectMapper objectMapper, MockMvc mockMvc, BookService bookService) {
        this.objectMapper =new ObjectMapper();
        this.bookService = bookService;
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsHttp201Created() throws Exception {
//        AuthorEntity author=TestDataUtil.createTestAuthor();
//        BookEntity bookEntity = TestDataUtil.createTestBook(author);
//
//        String result=objectMapper.writeValueAsString(bookEntity);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(result)
//        ).andExpect(
//                MockMvcResultMatchers.status().isCreated()
//        );

        BookDto bookDto = TestDataUtil.createTestBookDto(null);
        String createdBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createdBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsSavedBook() throws Exception {

        AuthorEntity author=TestDataUtil.createTestAuthor();
        BookEntity bookEntity = TestDataUtil.createTestBook(author);

        String result=objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author.id").value(1)
        );
    }

    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {

        AuthorEntity author=TestDataUtil.createTestAuthor();
        BookEntity bookEntity=TestDataUtil.createTestBook(author    );
        bookService.createBook(bookEntity.getIsbn(),bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].author.id").isNumber()
        );

    }

    @Test
    public void testThatGetBooksReturnsHttpStatus200WhenBookExists() throws Exception {

        AuthorEntity author=TestDataUtil.createTestAuthor();
        BookEntity bookEntity=TestDataUtil.createTestBook(author);
        bookService.createBook(bookEntity.getIsbn(),bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatGetBooksReturnsHttpStatus404WhenBookNotExists() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

    }

    @Test
    public void testThatGetBooksReturnsBookWhenBookExists() throws Exception {

        AuthorEntity author=TestDataUtil.createTestAuthor();
        BookEntity bookEntity=TestDataUtil.createTestBook(author);
        bookService.createBook(bookEntity.getIsbn(),bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/1235-1235-1235")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("1235-1235-1235")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Nice")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author.id").value(1)
        );

    }

}
