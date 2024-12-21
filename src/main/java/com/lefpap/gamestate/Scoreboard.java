package com.lefpap.gamestate;

import com.lefpap.player.PlayerIndex;

/**
 * Tracks and manages the scores for the players in the game.
 *
 * <p>
 * The {@code Scoreboard} class maintains separate scores for Player One
 * and Player Two. It provides methods to retrieve and increment scores
 * for each player based on their {@link PlayerIndex}.
 * </p>
 */
public class Scoreboard {

    private int playerOneScore;
    private int playerTwoScore;

    /**
     * Retrieves the score of the specified player.
     *
     * @param playerIndex the index of the player whose score is to be retrieved
     * @return the score of the specified player
     * @throws IllegalArgumentException if the provided {@code playerIndex} is invalid
     */
    public int getPlayerScore(PlayerIndex playerIndex) {
        return switch (playerIndex) {
            case PLAYER_ONE -> playerOneScore;
            case PLAYER_TWO -> playerTwoScore;
            default -> throw new IllegalArgumentException("Invalid player index: %s".formatted(playerIndex));
        };
    }

    /**
     * Increments the score of the specified player by 1.
     *
     * @param playerIndex the index of the player whose score is to be incremented
     * @throws IllegalArgumentException if the provided {@code playerIndex} is invalid
     */
    public void incrementPlayerScore(PlayerIndex playerIndex) {
        switch (playerIndex) {
            case PLAYER_ONE -> playerOneScore++;
            case PLAYER_TWO -> playerTwoScore++;
            default -> throw new IllegalArgumentException("Invalid player index: %s".formatted(playerIndex));
        }
    }
}
