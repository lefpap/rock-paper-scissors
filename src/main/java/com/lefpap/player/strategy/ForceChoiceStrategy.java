package com.lefpap.player.strategy;

import com.lefpap.player.PlayerChoice;

/**
 * A strategy that always returns a predefined choice.
 *
 * <p>
 * This strategy is designed for testing purposes, allowing the simulation
 * of specific scenarios by forcing a player to make a fixed choice.
 * </p>
 */
public class ForceChoiceStrategy implements PlayerChoiceStrategy {
    private final PlayerChoice forcedChoice;

    /**
     * Constructs a new {@code ForceChoiceStrategy} with the specified choice.
     *
     * @param forcedChoice the choice that this strategy will always return
     */
    public ForceChoiceStrategy(PlayerChoice forcedChoice) {
        this.forcedChoice = forcedChoice;
    }

    @Override
    public PlayerChoice makeChoice() {
        return forcedChoice;
    }
}
