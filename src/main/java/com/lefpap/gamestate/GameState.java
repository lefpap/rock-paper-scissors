package com.lefpap.gamestate;

import com.lefpap.player.PlayerChoice;
import com.lefpap.player.PlayerIndex;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * Manages the state of the game, including rounds, scores, and the winner.
 *
 * <p>
 * The {@code GameState} class tracks the progress of the game by maintaining
 * a history of rounds, a scoreboard, and determining when a player wins the game.
 * </p>
 */
public class GameState {
    private final int scoreToWin;
    private final Scoreboard scoreboard;
    private final Deque<Round> rounds;

    private PlayerIndex winner;

    /**
     * Constructs a new {@code GameState} with the specified winning score.
     *
     * @param scoreToWin the score a player must reach to win the game
     */
    public GameState(int scoreToWin) {
        this.scoreToWin = scoreToWin;

        // initialize game state
        this.scoreboard = new Scoreboard();
        this.rounds = new ArrayDeque<>();
    }

    /**
     * Retrieves the winner of the game, if one exists.
     *
     * @return an {@link Optional} containing the {@link PlayerIndex} of the winner,
     *         or an empty {@link Optional} if no winner has been determined
     */
    public Optional<PlayerIndex> getWinner() {
        return Optional.ofNullable(winner);
    }

    /**
     * Retrieves the current (most recent) round of the game.
     *
     * @return an {@link Optional} containing the current {@link Round},
     *         or an empty {@link Optional} if no rounds have been played
     */
    public Optional<Round> getCurrentRound() {
        return Optional.ofNullable(rounds.peekLast());
    }

    /**
     * Plays a round with the specified choices for Player One and Player Two.
     *
     * <p>
     * This method creates a new {@link Round}, updates the scoreboard based on the
     * result of the round, and checks if a player has won the game.
     * </p>
     *
     * @param playerOneChoice the choice made by Player One
     * @param playerTwoChoice the choice made by Player Two
     */
    public void playRound(PlayerChoice playerOneChoice, PlayerChoice playerTwoChoice) {
        Round round = new Round(rounds.size() + 1, playerOneChoice, playerTwoChoice);

        updateScoreboard(round.getResult());
        determineWinner();

        rounds.add(round);
    }

    /**
     * Retrieves the score of the specified player.
     *
     * @param playerIndex the index of the player whose score is to be retrieved
     * @return the score of the specified player
     */
    public int getPlayerScore(PlayerIndex playerIndex) {
        return scoreboard.getPlayerScore(playerIndex);
    }

    /**
     * Updates the scoreboard based on the result of the latest round.
     *
     * <p>
     * This method increments the score of the winning player if the round
     * result is not a draw.
     * </p>
     *
     * @param roundResult the result of the round
     */
    private void updateScoreboard(RoundResult roundResult) {
        // Update scoreboard
        switch (roundResult) {
            case PLAYER_ONE_WINS -> scoreboard.incrementPlayerScore(PlayerIndex.PLAYER_ONE);
            case PLAYER_TWO_WINS -> scoreboard.incrementPlayerScore(PlayerIndex.PLAYER_TWO);
            case DRAW -> {
                // Do nothing on draw
            }
        }
    }

    /**
     * Determines if a player has won the game.
     *
     * <p>
     * This method checks the scores of both players and sets the winner
     * if a player has reached the winning score.
     * </p>
     */
    private void determineWinner() {
        if (scoreboard.getPlayerScore(PlayerIndex.PLAYER_ONE) == scoreToWin) {
            this.winner = PlayerIndex.PLAYER_ONE;
        } else if (scoreboard.getPlayerScore(PlayerIndex.PLAYER_TWO) == scoreToWin) {
            this.winner = PlayerIndex.PLAYER_TWO;
        }
    }
}
