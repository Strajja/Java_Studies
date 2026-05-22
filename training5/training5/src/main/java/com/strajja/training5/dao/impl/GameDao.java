package com.strajja.training5.dao.impl;

import com.strajja.training5.domain.Game;

import java.util.List;
import java.util.Optional;

public interface GameDao {

    public void create(Game game);

    public Optional<Game> findOne(long id);

    public void setTitle(long id, String title);

    public List<Game> findAll();

    public void delete(long id);
}
