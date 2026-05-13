package com.strajja.database3.dao.impl;

import com.strajja.database3.dao.AuthorDao;
import com.strajja.database3.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> results=template.query(
                "SELECT id, name, age FROM authors WHERE id=? LIMIT 1",
                new AuthorMapper(), authorId
        );
        return results.stream().findFirst();
    }

    public static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
