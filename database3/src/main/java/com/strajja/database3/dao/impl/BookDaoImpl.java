package com.strajja.database3.dao.impl;

import com.strajja.database3.dao.BookDao;
import com.strajja.database3.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate template;

    public BookDaoImpl(JdbcTemplate template) {
        this.template = template;
    }

    public void create(Book book) {
        template.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId()
                );
    }
    @Override
    public Optional<Book> find(String isbn) {
        List<Book> results= template.query("SELECT isbn, title, author_id FROM books WHERE isbn=? LIMIT 1",
                new BookRowMapper(),
                isbn
        );
        return results.stream().findFirst();
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
           return  Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong(("author_id")))
                    .build();
        }
    }
}
