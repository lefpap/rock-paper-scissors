package com.lefpap.player;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlayerChoiceTest {

    @ParameterizedTest
    @CsvSource({
        "ROCK, r",
        "PAPER, p",
        "SCISSORS, x"
    })
    void test_of_validInputs(String name, String alias) {
        // Test both the name and alias for each PlayerChoice
        PlayerChoice expectedChoice = PlayerChoice.valueOf(name);

        assertEquals(expectedChoice, PlayerChoice.of(name).orElseThrow());
        assertEquals(expectedChoice, PlayerChoice.of(alias).orElseThrow());
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "123", "", "rockx"})
    void test_of_invalidInputs(String invalidInput) {
        // Test invalid inputs to ensure no match is found
        assertTrue(PlayerChoice.of(invalidInput).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "ROCK, SCISSORS, true",
        "ROCK, PAPER, false",
        "PAPER, ROCK, true",
        "PAPER, SCISSORS, false",
        "SCISSORS, PAPER, true",
        "SCISSORS, ROCK, false",
        "ROCK, ROCK, false",
        "PAPER, PAPER, false",
        "SCISSORS, SCISSORS, false"
    })
    void test_beats(String playerOneInput, String playerTwoInput, boolean expectedResult) {
        // Test the beats method for all combinations
        PlayerChoice playerOneChoice = PlayerChoice.valueOf(playerOneInput);
        PlayerChoice playerTwoChoice = PlayerChoice.valueOf(playerTwoInput);

        assertEquals(expectedResult, playerOneChoice.beats(playerTwoChoice),
            () -> "%s should%s beat %s".formatted(
                playerOneChoice,
                expectedResult ? "" : " not",
                playerTwoChoice
            )
        );
    }
}