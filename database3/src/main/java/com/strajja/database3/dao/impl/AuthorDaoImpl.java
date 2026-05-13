package com.strajja.database3.dao.impl;

import com.strajja.database3.dao.AuthorDao;
import com.strajja.database3.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {

    private JdbcTemplate template;

    public AuthorDaoImpl(final JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void create(Author author) {
        template.update(
                "INSERT INTO authors (id,name,age) VALUES (?,?,?)",
                author.getId(), author.getName(), author.getAge()
        );
    }
}
