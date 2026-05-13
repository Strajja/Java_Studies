package com.strajja.database3.dao.impl;

import com.strajja.database3.dao.BookDao;
import com.strajja.database3.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

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

    }
}
