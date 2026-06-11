package com.strajja.database4.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.strajja.database4.TestDataUtil;
import com.strajja.database4.domain.dto.AuthorDto;
import com.strajja.database4.domain.entities.AuthorEntity;
import com.strajja.database4.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
public class AuthorControllerIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, AuthorService authorService)
    {
        this.authorService = authorService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        authorEntity.setId(null);
        String result =objectMapper.writeValueAsString(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        authorEntity.setId(null);
        String result =objectMapper.writeValueAsString(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Strajja")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("22")
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {

        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();

        authorService.createAuthor(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Strajja")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value("22")
        );

    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExists() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        authorService.createAuthor(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/"+authorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatusNotFoundWhenAuthorNotExists() throws Exception {
//        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
//        authorService.createAuthor(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenExists() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        authorService.createAuthor(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Strajja")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value("22")
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenAuthorNotExists() throws Exception {
        AuthorDto authorDto= TestDataUtil.createTestAuthorDto();
        String result =objectMapper.writeValueAsString(authorDto);


        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpsStatus200AuthorWhenExists() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        AuthorEntity savedAuthor= authorService.createAuthor(authorEntity);

        AuthorDto authorDto= TestDataUtil.createTestAuthorDto();
        String result =objectMapper.writeValueAsString(authorDto);


        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        AuthorEntity savedAuthor= authorService.createAuthor(authorEntity);

        AuthorDto authorFullUpdate= TestDataUtil.createTestAuthorD();
        authorFullUpdate.setId(savedAuthor.getId());
        String result =objectMapper.writeValueAsString(authorFullUpdate);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Mica")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(26)
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus200Ok() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        AuthorEntity savedAuthor= authorService.createAuthor(authorEntity);

        AuthorDto authorFullUpdate= TestDataUtil.createTestAuthorD();
        authorFullUpdate.setName("UPDATED");
        String result =objectMapper.writeValueAsString(authorFullUpdate);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        AuthorEntity savedAuthor= authorService.createAuthor(authorEntity);

        AuthorDto authorFullUpdate= TestDataUtil.createTestAuthorD();
        authorFullUpdate.setName("UPDATED");
        String result =objectMapper.writeValueAsString(authorFullUpdate);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthor.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorFullUpdate.getAge())
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingAuthor() throws Exception {

        AuthorEntity authorEntity= TestDataUtil.createTestAuthor();
        AuthorEntity savedAuthor= authorService.createAuthor(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/"+savedAuthor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForNoExistingAuthor() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }


}
