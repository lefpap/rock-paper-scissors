package com.lefpap;

import java.util.*;

import static com.lefpap.GameConfig.PLAYER_ONE_NAME;
import static com.lefpap.GameConfig.PLAYER_TWO_NAME;
import static com.lefpap.GameConfig.ROUND_LINE_SEPARATOR;
import static com.lefpap.GameConfig.SCORE_TO_WIN;
import static com.lefpap.GameConfig.WINNING_RULES;

public class Game {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RND = new Random();

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int currentRound = 1;

    public void run() {
        while (getWinnerName().isEmpty()) {
            System.out.printf("Round %d%n", currentRound);
            System.out.printf("Score: |%s %d| vs |%s %d| Win at: %d%n", PLAYER_ONE_NAME, playerOneScore, PLAYER_TWO_NAME, playerTwoScore, SCORE_TO_WIN);

            PlayerChoice playerOneChoice = getInputChoice(PLAYER_ONE_NAME);
            PlayerChoice playerTwoChoice = getRandomChoice(PLAYER_TWO_NAME);

            System.out.printf("%s %s VS %s %s%n", PLAYER_ONE_NAME, playerOneChoice.name(), PLAYER_TWO_NAME, playerTwoChoice.name());
            RoundResult roundResult = resolveRound(playerOneChoice, playerTwoChoice);

            updateScores(roundResult);
            printRoundResult(roundResult);
            currentRound++;

            System.out.println(ROUND_LINE_SEPARATOR);
            System.out.println("Press any key to continue, or type 'quit/q' to quit.");
            String input = SCANNER.nextLine().trim();
            if ("q".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
                System.out.println("Game aborted!");
                return;
            }
        }

        System.out.printf("The winner is %s on round %d 🎉%n", getWinnerName().orElseThrow(), currentRound);
    }

    private Optional<String> getWinnerName() {
        if (playerOneScore >= SCORE_TO_WIN) return Optional.of(PLAYER_ONE_NAME);
        if (playerTwoScore >= SCORE_TO_WIN) return Optional.of(PLAYER_TWO_NAME);
        return Optional.empty();
    }

    private PlayerChoice getInputChoice(String playerName) {
        while (true) {
            System.out.printf("%s choice: ", playerName);
            String choice = SCANNER.nextLine().trim();
            try {
                return switch (choice.toLowerCase()) {
                    case "r", "rock" -> PlayerChoice.ROCK;
                    case "p", "paper" -> PlayerChoice.PAPER;
                    case "s", "scissors" -> PlayerChoice.SCISSORS;
                    default ->
                        throw new InvalidPlayerChoiceException("Invalid choice! Please type [PAPER, ROCK, SCISSORS] or [r, p, s].");
                };
            } catch (InvalidPlayerChoiceException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private PlayerChoice getRandomChoice(String playerName) {
        int totalChoices = PlayerChoice.values().length;
        PlayerChoice choice = PlayerChoice.values()[RND.nextInt(0, totalChoices)];
        System.out.printf("%s choice: %s%n", playerName, choice.name());
        return choice;
    }

    private RoundResult resolveRound(PlayerChoice one, PlayerChoice two) {
        if (one.equals(two)) {
            return RoundResult.DRAW;
        }
        return WINNING_RULES.get(one).equals(two) ? RoundResult.PLAYER_ONE_WINS : RoundResult.PLAYER_TWO_WINS;
    }

    private void updateScores(RoundResult result) {
        if (result == RoundResult.PLAYER_ONE_WINS) {
            playerOneScore++;
        } else if (result == RoundResult.PLAYER_TWO_WINS) {
            playerTwoScore++;
        }
    }

    private void printRoundResult(RoundResult result) {
        switch (result) {
            case DRAW -> System.out.printf("Round %d is a draw!%n", currentRound);
            case PLAYER_ONE_WINS -> System.out.printf("%s wins round %d%n", PLAYER_ONE_NAME, currentRound);
            case PLAYER_TWO_WINS -> System.out.printf("%s wins round %d%n", PLAYER_TWO_NAME, currentRound);
        }
    }
}
