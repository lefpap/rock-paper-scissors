package com.lefpap.gamestate;

import com.lefpap.player.PlayerIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        scoreboard.updateScores(RoundResult.PLAYER_ONE_WINS);

        assertEquals(1, scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(0, scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }

    @Test
    void test_incrementPlayerTwoScore() {
        scoreboard.updateScores(RoundResult.PLAYER_TWO_WINS);

        assertEquals(0, scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(1, scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }

    @Test
    void test_multipleScoreIncrements() {
        scoreboard.updateScores(RoundResult.PLAYER_ONE_WINS);
        scoreboard.updateScores(RoundResult.PLAYER_ONE_WINS);
        scoreboard.updateScores(RoundResult.PLAYER_TWO_WINS);

        assertEquals(2, scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(1, scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }
}