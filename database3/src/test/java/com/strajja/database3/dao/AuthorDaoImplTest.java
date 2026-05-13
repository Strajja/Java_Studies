package com.strajja.database3.dao;

import com.strajja.database3.dao.impl.AuthorDaoImpl;
import com.strajja.database3.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl authorDao;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = Author.builder()
                .id(5l)
                .name("Strajja")
                .age(22)
                .build();
        authorDao.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors(id, name,age) VALUES(?,?,?)"),
                eq(5l), eq("Strajja"), eq(22)
        );
    }
}
