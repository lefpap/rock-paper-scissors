package com.lefpap.player.strategy;

import com.lefpap.player.PlayerChoice;

import java.util.Random;

/**
 * A choice strategy that selects a random choice for the player.
 *
 * <p>
 * This implementation uses a {@link Random} instance to randomly
 * select a value from the {@link PlayerChoice} enum.
 * </p>
 */
public class RandomChoiceStrategy implements PlayerChoiceStrategy {

    private final Random rnd;

    /**
     * Constructs a new {@code RandomChoiceStrategy} with the specified {@link Random} instance.
     *
     * @param rnd the random number generator to use for selecting choices
     */
    public RandomChoiceStrategy(Random rnd) {
        this.rnd = rnd;
    }

    /**
     * Makes a random choice for the player.
     *
     * @return a randomly selected {@link PlayerChoice}
     */
    @Override
    public PlayerChoice makeChoice() {
        return PlayerChoice.random(rnd);
    }
}
