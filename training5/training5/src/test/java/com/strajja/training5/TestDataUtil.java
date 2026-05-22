package com.strajja.training5;

import com.strajja.training5.domain.Game;

public class TestDataUtil {

public TestDataUtil(){}

    public static Game createGameTest(){
    return Game.builder()
            .id(5L)
            .title("Test Game")
            .genre("RPG")
            .releaseYear(2026)
            .build();
    }

    public static Game createGameTest2(){
    return Game.builder()
            .id(6L)
            .title("Test Game2")
            .genre("Singleplayer")
            .releaseYear(2025)
            .build();
    }
}
