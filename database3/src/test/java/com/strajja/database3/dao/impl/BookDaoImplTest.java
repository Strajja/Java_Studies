package com.strajja.database3.dao.impl;


import com.strajja.database3.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl bookDao;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = Book.builder()
                .isbn("1235-1235-1235")
                .title("Nice")
                .authorId(5l)
                .build();

        bookDao.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn,title,author_id) VALUES (?,?,? )"),
                eq("1235-1235-1235"),
                eq("Nice"),
                eq(5l)
        );

    }
    @Test
    public void testThatFindOneBookGeneratesCorrectSql(){

        bookDao.find("1235-1235-1235");

        verify(jdbcTemplate)
    }
}
