package com.lefpap.gamestate;

import com.lefpap.player.Player;
import com.lefpap.player.PlayerChoice;
import com.lefpap.player.PlayerIndex;
import com.lefpap.player.strategy.ForceChoiceStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState gameState;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setup() {
        gameState = new GameState(3);
        playerOne = new Player("P1", new ForceChoiceStrategy(PlayerChoice.ROCK));
        playerTwo = new Player("P2", new ForceChoiceStrategy(PlayerChoice.ROCK));
    }

    @Test
    void test_initialState() {
        assertTrue(gameState.getWinner().isEmpty());
        assertTrue(gameState.getCurrentRound().isEmpty());
        assertEquals(0, gameState.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(0, gameState.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }

    @Test
    void test_playRound_updatesState() {

        // Given
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.SCISSORS));

        // Player One wins the round
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());

        assertEquals(1, gameState.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(0, gameState.getPlayerScore(PlayerIndex.PLAYER_TWO));

        // Verify round details
        Round round = gameState.getCurrentRound().orElseThrow();
        assertEquals(PlayerChoice.ROCK, round.getPlayerChoice(PlayerIndex.PLAYER_ONE));
        assertEquals(PlayerChoice.SCISSORS, round.getPlayerChoice(PlayerIndex.PLAYER_TWO));
        assertEquals(RoundResult.PLAYER_ONE_WINS, round.getResult());
    }

    @Test
    void test_winnerDetermination() {

        // Round 1: Player One wins
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.SCISSORS));
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());
        assertTrue(gameState.getWinner().isEmpty());

        // Round 2: Player Two wins
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.PAPER));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.SCISSORS));
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());
        assertTrue(gameState.getWinner().isEmpty());

        // Round 3: Player One wins
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.PAPER));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());
        assertTrue(gameState.getWinner().isEmpty());

        // Round 4: Player One wins
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.SCISSORS));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.PAPER));
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());
        assertTrue(gameState.getWinner().isPresent());

        // Assert Player One is the winner
        assertEquals(PlayerIndex.PLAYER_ONE, gameState.getWinner().get());
    }

    @Test
    void test_draw_doesNot_updateScores() {
        // Given
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));

        // Play a draw round
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());

        assertEquals(0, gameState.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(0, gameState.getPlayerScore(PlayerIndex.PLAYER_TWO));
        assertTrue(gameState.getWinner().isEmpty());
    }

    @Test
    void test_multipleRounds() {

        // Round 1: Player One wins
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.SCISSORS));
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());
        assertEquals(1, gameState.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(0, gameState.getPlayerScore(PlayerIndex.PLAYER_TWO));

        // Round 2: Player Two wins
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.SCISSORS));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());
        assertEquals(1, gameState.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(1, gameState.getPlayerScore(PlayerIndex.PLAYER_TWO));

        // Round 3: Draw
        playerOne.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        playerTwo.setChoiceStrategy(new ForceChoiceStrategy(PlayerChoice.ROCK));
        gameState.playRound(playerOne.makeChoice(), playerTwo.makeChoice());
        assertEquals(1, gameState.getPlayerScore(PlayerIndex.PLAYER_ONE));
        assertEquals(1, gameState.getPlayerScore(PlayerIndex.PLAYER_TWO));
    }

}