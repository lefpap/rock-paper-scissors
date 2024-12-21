package com.lefpap.gamestate;

import com.lefpap.player.PlayerChoice;
import com.lefpap.player.PlayerIndex;

import java.util.Objects;

/**
 * Represents a single round in the game.
 *
 * <p>
 * The {@code Round} class captures the choices made by both players,
 * determines the result of the round, and assigns a unique index to the round.
 * </p>
 */
public class Round {
    private final int index;
    private final PlayerChoice playerOneChoice;
    private final PlayerChoice playerTwoChoice;
    private final RoundResult result;

    /**
     * Constructs a new {@code Round} with the specified index and player choices.
     *
     * <p>The result of the round is automatically resolved based on the
     * choices of the players.</p>
     *
     * @param index the index of the round (e.g., 1 for the first round)
     * @param playerOneChoice the choice made by Player One
     * @param playerTwoChoice the choice made by Player Two
     */
    public Round(int index, PlayerChoice playerOneChoice, PlayerChoice playerTwoChoice) {
        this.index = index;
        this.playerOneChoice = playerOneChoice;
        this.playerTwoChoice = playerTwoChoice;

        this.result = resolveRoundResult();
    }

    /**
     * Resolves the result of the round based on the players' choices.
     *
     * <p>The rules are:
     * <ul>
     *   <li>If both players make the same choice, the result is {@link RoundResult#DRAW}.</li>
     *   <li>Otherwise, the result is determined by the {@link PlayerChoice#beats(PlayerChoice)} method.</li>
     * </ul>
     * </p>
     *
     * @return the result of the round
     */
    private RoundResult resolveRoundResult() {
        if (Objects.equals(playerOneChoice, playerTwoChoice)) {
            return RoundResult.DRAW;
        }

        return playerOneChoice.beats(playerTwoChoice)
            ? RoundResult.PLAYER_ONE_WINS
            : RoundResult.PLAYER_TWO_WINS;
    }

    /**
     * Gets the index of the round.
     *
     * @return the index of the round
     */
    public int getIndex() {
        return index;
    }

    /**
     * Retrieves the choice made by the specified player in this round.
     *
     * @param playerIndex the index of the player whose choice is to be retrieved
     * @return the {@link PlayerChoice} made by the specified player
     */
    public PlayerChoice getPlayerChoice(PlayerIndex playerIndex) {
        return switch (playerIndex) {
            case PLAYER_ONE -> playerOneChoice;
            case PLAYER_TWO -> playerTwoChoice;
        };
    }

    /**
     * Gets the result of the round.
     *
     * @return the result of the round as a {@link RoundResult}
     */
    public RoundResult getResult() {
        return result;
    }
}
