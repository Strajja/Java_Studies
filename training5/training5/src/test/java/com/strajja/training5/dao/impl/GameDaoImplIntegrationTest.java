package com.strajja.training5.dao.impl;

import com.strajja.training5.domain.Game;
import com.strajja.training5.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GameDaoImplIntegrationTest {

    @Autowired
    private  GameDao underTest;



    @Test
    public void testThatGameCanBeCreatedAndRecalled(){
        Game game = TestDataUtil.createGameTest();
        underTest.create(game);

        Optional<Game> result= underTest.findOne(5L);

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Test Game");


    }

    @Test
    public void testThatGameCanBeUpdatedAndRecalled(){

        Game game = TestDataUtil.createGameTest();
        underTest.create(game);
        underTest.setTitle(5L, "Witcher 3");

        Optional<Game> result= underTest.findOne(5L);

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Witcher 3");
    }

    @Test
    public void testThatGamesCanBeCreatedAndRecalled(){
        Game game = TestDataUtil.createGameTest();
        underTest.create(game);
        Game game2 = TestDataUtil.createGameTest2();
        underTest.create(game2);

        List<Game> result= underTest.findAll();

        assertThat(result)
                .hasSize(2)
                .contains(game, game2);
    }

    @Test
    public void testThatGamesCanBeDeleted(){
        Game game = TestDataUtil.createGameTest();
        underTest.create(game);

        underTest.delete(5L);

        Optional<Game> result= underTest.findOne(5L);

        assertThat(result).isEmpty();
    }
}
