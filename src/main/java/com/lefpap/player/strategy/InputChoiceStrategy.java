package com.lefpap.player.strategy;

import com.lefpap.player.PlayerChoice;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A choice strategy that prompts the player to input their choice.
 *
 * <p>
 * This implementation uses a {@link Scanner} to read input from the console
 * and validates the input against the available choices in {@link PlayerChoice}.
 * </p>
 */
public class InputChoiceStrategy implements PlayerChoiceStrategy {

    private final Scanner scanner;
    private final String playerName;

    /**
     * Constructs a new {@code InputChoiceStrategy} with the specified {@link Scanner}
     * and the player's name.
     *
     * @param scanner the scanner used to read input from the console
     * @param playerName the name of the player (used for personalized prompts)
     */
    public InputChoiceStrategy(Scanner scanner, String playerName) {
        this.playerName = playerName;
        this.scanner = scanner;
    }


    /**
     * Prompts the player to input their choice.
     *
     * <p>
     * The method continuously prompts the player until a valid choice
     * is entered. The valid choices are displayed in the prompt as their
     * names and aliases.
     * </p>
     *
     * @return the player's choice as a {@link PlayerChoice}
     */
    @Override
    public PlayerChoice makeChoice() {
        Optional<PlayerChoice> playerChoice;
        do {
            System.out.printf("%s make your choice [%s]     :", playerName, formatChoices());
            playerChoice = parsePlayerInput(scanner.nextLine().trim());
        } while (playerChoice.isEmpty());

        // Add some space for better readability
        System.out.println();

        return playerChoice.get();
    }

    /**
     * Validates and parses the player's input into a {@link PlayerChoice}.
     *
     * <p>
     * If the input is invalid (e.g., blank or not a valid choice), an appropriate
     * message is displayed, and {@link Optional#empty()} is returned.
     * </p>
     *
     * @param input the player's input
     * @return an {@link Optional} containing the corresponding {@link PlayerChoice},
     *         or {@link Optional#empty()} if the input is invalid
     */
    private Optional<PlayerChoice> parsePlayerInput(String input) {
        if (input.isBlank()) {
            System.out.println("No input entered, please type a choice.");
            return Optional.empty();
        }

        Optional<PlayerChoice> playerChoice = PlayerChoice.of(input);
        if (playerChoice.isEmpty()) {
            System.out.printf("Invalid player choice: %s%n", input);
            return Optional.empty();
        }

        return playerChoice;
    }

    /**
     * Formats the available choices as a string for display in the prompt.
     *
     * @return a string representation of available choices with their aliases
     */
    private String formatChoices() {
        return Arrays.stream(PlayerChoice.values())
            .map(choice -> "%s (%s)".formatted(choice.name(), choice.alias()))
            .collect(Collectors.joining(", "));
    }
}
