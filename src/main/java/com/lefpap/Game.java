package com.lefpap;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);

    private static final int SCORE_TO_WIN = 3;
    private static final Random rnd = new Random();

    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int currentRound = 1;

    public void run() {
        do {
            try {
                System.out.printf("Round %d%n", currentRound);
                System.out.printf("Score: |P1 %d| vs |P2 %d| Win at: %d%n", playerOneScore, playerTwoScore, SCORE_TO_WIN);

                System.out.print("P1 choice: ");
                String playerOneInput = scanner.nextLine().trim().toLowerCase();
                PlayerChoice playerOneChoice = inputToChoice(playerOneInput);

                PlayerChoice playerTwoChoice = randomChoice();
                System.out.printf("P2 choice: %s%n", playerTwoChoice.name());

                System.out.printf("P1 picked: %s | P2 picked: %s%n", playerOneChoice.name(), playerTwoChoice.name());
                RoundResult roundResult = resolveRoundResult(playerOneChoice, playerTwoChoice);

                switch (roundResult) {
                    case DRAW -> System.out.printf("Round %d is a draw!%n", currentRound);
                    case PLAYER_ONE_WINS -> {
                        System.out.printf("P1 wins round %d%n", currentRound);
                        playerOneScore++;
                    }
                    case PLAYER_TWO_WINS -> {
                        System.out.printf("P2 wins round %d%n", currentRound);
                        playerTwoScore++;
                    }
                }

                currentRound++;
                System.out.println("-".repeat(20));
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.err.println(ex.getMessage());
            }
        } while (!winnerExists());

        System.out.printf("The winner is %s on round %d 🎉%n", getWinnerName(), currentRound);
    }

    private boolean winnerExists() {
        return playerOneScore == SCORE_TO_WIN || playerTwoScore == SCORE_TO_WIN;
    }

    private String getWinnerName() {
        if (playerOneScore == SCORE_TO_WIN) {
            return "P1";
        }

        if (playerTwoScore == SCORE_TO_WIN) {
            return "P2";
        }

        throw new IllegalStateException("No winner exists!");
    }

    private PlayerChoice inputToChoice(String input) {
        return switch (input.toLowerCase()) {
            case "r", "rock" -> PlayerChoice.ROCK;
            case "p", "paper" -> PlayerChoice.PAPER;
            case "s", "scissors" -> PlayerChoice.SCISSORS;
            default -> throw new IllegalArgumentException("Invalid choice: %s, you have to type either [PAPER, ROCK, SCISSORS] or [r, p, s] case insensitive".formatted(input));
        };
    }

    private PlayerChoice randomChoice() {
        int totalChoices = PlayerChoice.values().length;
        return PlayerChoice.values()[rnd.nextInt(0, totalChoices)];
    }

    private RoundResult resolveRoundResult(PlayerChoice one, PlayerChoice two) {
        if (Objects.equals(one, two)) {
            return RoundResult.DRAW;
        }

        Optional<RoundResult> rockWinner = rockBeatsScissors(one, two);
        if (rockWinner.isPresent()) {
            return rockWinner.get();
        }

        Optional<RoundResult> scissorsWinner = scissorsBeatsPaper(one, two);
        if (scissorsWinner.isPresent()) {
            return scissorsWinner.get();
        }

        Optional<RoundResult> paperWinner = paperBeatsRock(one, two);
        if (paperWinner.isPresent()) {
            return paperWinner.get();
        }

        throw new IllegalStateException("An impossible case reached - Player 1: %s | Player 2: %s".formatted(one.name(), two.name()));
    }

    private Optional<RoundResult> rockBeatsScissors(PlayerChoice one, PlayerChoice two) {
        if (PlayerChoice.ROCK.equals(one) && PlayerChoice.SCISSORS.equals(two)) {
            return Optional.of(RoundResult.PLAYER_ONE_WINS);
        }
        if (PlayerChoice.ROCK.equals(two) && PlayerChoice.SCISSORS.equals(one)) {
            return Optional.of(RoundResult.PLAYER_TWO_WINS);
        }

        return Optional.empty();
    }

    private Optional<RoundResult> scissorsBeatsPaper(PlayerChoice one, PlayerChoice two) {
        if (PlayerChoice.SCISSORS.equals(one) && PlayerChoice.PAPER.equals(two)) {
            return Optional.of(RoundResult.PLAYER_ONE_WINS);
        }
        if (PlayerChoice.SCISSORS.equals(two) && PlayerChoice.PAPER.equals(one)) {
            return Optional.of(RoundResult.PLAYER_TWO_WINS);
        }

        return Optional.empty();
    }

    private Optional<RoundResult> paperBeatsRock(PlayerChoice one, PlayerChoice two) {
        if (PlayerChoice.PAPER.equals(one) && PlayerChoice.ROCK.equals(two)) {
            return Optional.of(RoundResult.PLAYER_ONE_WINS);
        }
        if (PlayerChoice.PAPER.equals(two) && PlayerChoice.ROCK.equals(one)) {
            return Optional.of(RoundResult.PLAYER_TWO_WINS);
        }

        return Optional.empty();
    }

    enum PlayerChoice {
        ROCK,
        PAPER,
        SCISSORS;
    }

    enum RoundResult {
        PLAYER_ONE_WINS,
        PLAYER_TWO_WINS,
        DRAW;
    }
}
