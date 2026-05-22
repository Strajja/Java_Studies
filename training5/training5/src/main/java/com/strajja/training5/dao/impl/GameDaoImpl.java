package com.strajja.training5.dao.impl;

import com.strajja.training5.domain.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class GameDaoImpl implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Game.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .genre(rs.getString("genre"))
                    .releaseYear(rs.getInt("release_year"))
                    .build();
        }
    }

    @Override
    public void create(Game game){

        jdbcTemplate.update(
                "INSERT INTO games (id, title, genre, release_year) VALUES (?, ?, ?, ?)",
                game.getId(), game.getTitle(), game.getGenre(), game.getReleaseYear()
        );
    }

    @Override
    public Optional<Game> findOne(long id) {
        List<Game> result= jdbcTemplate.query("SELECT id, title, genre, release_year FROM games WHERE id = ? LIMIT 1",
                new GameMapper(), id);
        return result.stream().findFirst();
    }

    @Override
    public void setTitle(long id, String title) {

        jdbcTemplate.update("UPDATE games SET title = ? WHERE id = ?", title, id);

    }

    @Override
    public List<Game> findAll() {
        return jdbcTemplate.query("SELECT id, title, genre, release_year FROM games",
                new GameMapper());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(
                "DELETE FROM games WHERE id=?",
                id
        );
    }


}
