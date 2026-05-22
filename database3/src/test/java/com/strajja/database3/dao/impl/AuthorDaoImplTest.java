package com.strajja.database3.dao.impl;

import com.strajja.database3.TestDataUtil;
import com.strajja.database3.dao.impl.AuthorDaoImpl.AuthorMapper;
import com.strajja.database3.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.mockito.ArgumentMatchers.any;
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
        Author author = TestDataUtil.createTestAuthor();

        authorDao.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors(id, name,age) VALUES(?,?,?)"),
                eq(5l), eq("Strajja"), eq(22)
        );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSql() {
        authorDao.findOne(5l);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id=? LIMIT 1"),
                ArgumentMatchers.<AuthorMapper>any(),
                eq(5l)
        );
    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        authorDao.find();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors "),
                ArgumentMatchers.<AuthorDaoImpl.AuthorMapper>any()
                );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthor();
        authorDao.update(5L, author);

        verify(jdbcTemplate).update(
                "UPDATE authors SET id=?, name =?, age=? WHERE id=?",
                5L, "Strajja", 22, 5L
        );
    }
}
