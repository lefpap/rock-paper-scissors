package com.lefpap.gamestate;

import com.lefpap.player.PlayerChoice;
import com.lefpap.player.PlayerIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    void test_roundResult() {
        // Player One wins
        Round round = new Round(1, PlayerChoice.ROCK, PlayerChoice.SCISSORS);
        assertEquals(RoundResult.PLAYER_ONE_WINS, round.getResult());

        // Player Two wins
        round = new Round(2, PlayerChoice.ROCK, PlayerChoice.PAPER);
        assertEquals(RoundResult.PLAYER_TWO_WINS, round.getResult());

        // Draw
        round = new Round(3, PlayerChoice.ROCK, PlayerChoice.ROCK);
        assertEquals(RoundResult.DRAW, round.getResult());
    }

    @Test
    void test_getPlayerChoice() {
        Round round = new Round(1, PlayerChoice.ROCK, PlayerChoice.PAPER);
        assertEquals(PlayerChoice.ROCK, round.getPlayerChoice(PlayerIndex.PLAYER_ONE));
        assertEquals(PlayerChoice.PAPER, round.getPlayerChoice(PlayerIndex.PLAYER_TWO));
    }
}