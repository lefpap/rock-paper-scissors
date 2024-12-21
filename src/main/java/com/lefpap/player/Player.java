package com.lefpap.player;

import com.lefpap.player.strategy.PlayerChoiceStrategy;

/**
 * Represents a player in the game.
 *
 * <p>
 * A player has a name and uses a {@link PlayerChoiceStrategy} to make choices during the game.
 * The choice strategy allows flexibility in defining how a player's choices are determined,
 * such as through random selection or user input.
 * </p>
 */
public class Player {

    private final String name;
    private PlayerChoiceStrategy choiceStrategy;

    /**
     * Constructs a new player with the specified name and choice strategy.
     *
     * @param name the name of the player
     * @param choiceStrategy the strategy used by the player to make choices
     */
    public Player(String name, PlayerChoiceStrategy choiceStrategy) {
        this.name = name;
        this.choiceStrategy = choiceStrategy;
    }

    /**
     * Makes a choice for this player using the configured {@link PlayerChoiceStrategy}.
     *
     * @return the choice made by the player
     */
    public PlayerChoice makeChoice() {
        return choiceStrategy.makeChoice();
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String name() {
        return name;
    }

    /**
     * Sets the players {@link PlayerChoiceStrategy}.
     *
     * @param choiceStrategy the strategy to set
     */
    public void setChoiceStrategy(PlayerChoiceStrategy choiceStrategy) {
        this.choiceStrategy = choiceStrategy;
    }
}
