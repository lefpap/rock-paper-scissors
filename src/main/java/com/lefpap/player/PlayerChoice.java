package com.lefpap.player;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Enum representing the possible choices a player can make in the game.
 */
public enum PlayerChoice {
    ROCK("r"),
    PAPER("p"),
    SCISSORS("x");

    private final String alias;

    PlayerChoice(String alias) {
        this.alias = alias;
    }

    /**
     * Parses a string into a {@code PlayerChoice}.
     *
     * <p>
     * The input can match either the name or the alias of a choice.
     * For example, "ROCK" or "r" will return {@link #ROCK}.
     * </p>
     *
     * @param value the string representation of the choice
     * @return an {@link Optional} containing the corresponding {@code PlayerChoice},
     *         or an empty {@link Optional} if no match is found
     */
    public static Optional<PlayerChoice> of(String value) {
        return Arrays.stream(PlayerChoice.values())
            .filter(isMatchingNameOrAlias(value))
            .findAny();
    }

    /**
     * Returns a random {@code PlayerChoice}.
     *
     * @param rnd the random number generator to use
     * @return a randomly selected {@code PlayerChoice}
     */
    public static PlayerChoice random(Random rnd) {
        PlayerChoice[] choices = values();
        return choices[rnd.nextInt(choices.length)];
    }

    /**
     * Determines if this choice beats the specified {@code other} choice.
     *
     * @param other the other choice to compare against
     * @return {@code true} if this choice beats the {@code other} choice, {@code false} otherwise
     */
    public boolean beats(PlayerChoice other) {
        return switch (this) {
            case ROCK -> SCISSORS.equals(other);
            case PAPER -> ROCK.equals(other);
            case SCISSORS -> PAPER.equals(other);
        };
    }

    /**
     * Gets the alias of this choice.
     *
     * @return the alias of this choice
     */
    public String alias() {
        return alias;
    }

    private static Predicate<PlayerChoice> isMatchingNameOrAlias(String value) {
        return choice -> choice.alias.equalsIgnoreCase(value) || choice.name().equalsIgnoreCase(value);
    }
}
