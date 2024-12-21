package com.lefpap.player.strategy;

import com.lefpap.player.PlayerChoice;

/**
 * Defines a strategy for making a choice in the game.
 *
 * <p>
 * Implementations of this interface determine how a player's choice
 * is made, whether through user input, random selection, or any other logic.
 * </p>
 */
public interface PlayerChoiceStrategy {

    /**
     * Makes a choice for the player.
     *
     * @return the player's choice
     */
    PlayerChoice makeChoice();
}
