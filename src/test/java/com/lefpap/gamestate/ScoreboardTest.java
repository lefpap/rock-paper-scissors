package com.lefpap.gamestate;

import com.lefpap.player.PlayerIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setup() {
        scoreboard = new Scoreboard();
    }

    @Test
    void test_initialScoresAreZero() {
        assertEquals(0, scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(0, scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }

    @Test
    void test_incrementPlayerOneScore() {
        scoreboard.incrementPlayerScore(PlayerIndex.PLAYER_ONE);

        assertEquals(1, scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(0, scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }

    @Test
    void test_incrementPlayerTwoScore() {
        scoreboard.incrementPlayerScore(PlayerIndex.PLAYER_TWO);

        assertEquals(0, scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(1, scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }

    @Test
    void test_multipleScoreIncrements() {
        scoreboard.incrementPlayerScore(PlayerIndex.PLAYER_ONE);
        scoreboard.incrementPlayerScore(PlayerIndex.PLAYER_ONE);
        scoreboard.incrementPlayerScore(PlayerIndex.PLAYER_TWO);

        assertEquals(2, scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(1, scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }
}