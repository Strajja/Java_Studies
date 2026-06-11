package com.strajja.database4.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strajja.database4.TestDataUtil;
import com.strajja.database4.domain.dto.BookDto;
import com.strajja.database4.domain.entities.AuthorEntity;
import com.strajja.database4.domain.entities.BookEntity;
import com.strajja.database4.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    @Test
    public void testThatUpdateBookReturnsHttpStatus200() throws Exception {
        BookEntity bookEntity=TestDataUtil.createTestBookEntity(null);
        BookEntity createdBook=bookService.createBook(bookEntity.getIsbn(),bookEntity);

        BookDto bookDto=TestDataUtil.createTestBookDto(null);
        bookDto.setIsbn(createdBook.getIsbn());

        String result=objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+createdBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(result)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity bookEntity=TestDataUtil.createTestBookEntity(null);
        BookEntity createdBook=bookService.createBook(bookEntity.getIsbn(),bookEntity);

        BookDto bookDto=TestDataUtil.createTestBookDto(null);
//        bookDto.setIsbn(createdBook.getIsbn());

        String result=objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+createdBook.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(result)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(createdBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );

    }

    @Test
    public void testThatParitalUpdateBookReturnsHttpStatus200() throws Exception {
        BookEntity bookEntity=TestDataUtil.createTestBookEntity(null);
        BookEntity createdBook=bookService.createBook(bookEntity.getIsbn(),bookEntity);

        BookDto bookDto=TestDataUtil.createTestBookDto(null);
        bookDto.setTitle("UPDATED");

        String result=objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+createdBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatParitalUpdateBookReturnsUpdatedBook() throws Exception {
        BookEntity bookEntity=TestDataUtil.createTestBookEntity(null);
        BookEntity createdBook=bookService.createBook(bookEntity.getIsbn(),bookEntity);

        BookDto bookDto=TestDataUtil.createTestBookDto(null);
        bookDto.setTitle("UPDATED");

        String result=objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/"+createdBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(createdBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatDeleteBookReturnHttpStatus204ForNonExistingBook() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteBookReturnHttpStatus204ForExistingBook() throws Exception {

        BookEntity bookEntity=TestDataUtil.createTestBookEntity(null);
        BookEntity createdBook=bookService.createBook(bookEntity.getIsbn(),bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

}
