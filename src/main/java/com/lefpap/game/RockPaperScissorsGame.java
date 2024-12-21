package com.lefpap.game;

import com.lefpap.gamestate.RoundResult;
import com.lefpap.player.PlayerChoice;
import com.lefpap.player.PlayerIndex;
import com.lefpap.gamestate.GameState;
import com.lefpap.gamestate.Round;
import com.lefpap.player.Player;
import com.lefpap.player.strategy.InputChoiceStrategy;
import com.lefpap.player.strategy.RandomChoiceStrategy;

import java.util.*;

/**
 * Implements the game loop for a Rock-Paper-Scissors game.
 *
 * <p>
 * This class provides the logic for initializing the game, handling
 * player interactions, and managing the game loop until a winner is determined.
 * </p>
 */
public class RockPaperScissorsGame extends Game {

    private static final String BORDER_LINE = "-".repeat(20);
    private static final Random RND = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int SCORE_TO_WIN = 3;

    private Player playerOne;
    private Player playerTwo;
    private GameState gameState;

    /**
     * Initializes the game by setting up players and the game state.
     */
    @Override
    public void init() {
        playerOne = new Player("Human", new InputChoiceStrategy(SCANNER, "Human"));
        playerTwo = new Player("Computer", new RandomChoiceStrategy(RND));
        gameState = new GameState(SCORE_TO_WIN);
    }

    /**
     * Executes a single iteration of the game loop.
     *
     * <p>
     * This method collects player choices, plays a round, and updates
     * the game state accordingly.
     * </p>
     */
    @Override
    public void loop() {
        PlayerChoice playerOneChoice = playerOne.makeChoice();
        PlayerChoice playerTwoChoice = playerTwo.makeChoice();
        gameState.playRound(playerOneChoice, playerTwoChoice);
    }

    /**
     * Displays the game introduction before the loop starts.
     */
    @Override
    public void onBeforeLoop() {
        System.out.println("""
            █▀█ █▀█ █▀▀ █▄▀
            █▀▄ █▄█ █▄▄ █░█
            
            █▀█ ▄▀█ █▀█ █▀▀ █▀█
            █▀▀ █▀█ █▀▀ ██▄ █▀▄
            
            █▀ █▀▀ █ █▀ █▀ █▀█ █▀█ █▀
            ▄█ █▄▄ █ ▄█ ▄█ █▄█ █▀▄ ▄█
            """);

        System.out.println("Press any key to start...");
        SCANNER.nextLine();
        System.out.println(BORDER_LINE);
    }

    /**
     * Displays the scoreboard and round number at the start of each loop iteration.
     */
    @Override
    public void onStartOfEachLoop() {
        System.out.printf("Round %d%n", gameState.getCurrentRound().map(Round::getIndex).orElse(0) + 1);
        System.out.println();
        System.out.printf(
            """
            Scoreboard:
            %s: %d | %s: %d
            """,
            playerOne.name(),
            gameState.getPlayerScore(PlayerIndex.PLAYER_ONE),
            playerTwo.name(),
            gameState.getPlayerScore(PlayerIndex.PLAYER_TWO)
        );
        System.out.println();
    }

    /**
     * Displays the details of the current round and prompts the user to continue or quit.
     */
    @Override
    public void onEndOfEachLoop() {
        // Print round details
        Round currentRound = gameState.getCurrentRound().orElseThrow();
        System.out.printf(
            """
            Round Details:
            %s chose: %s
            %s chose: %s
            %s%n
            """,
            playerOne.name(), currentRound.getPlayerChoice(PlayerIndex.PLAYER_ONE),
            playerTwo.name(), currentRound.getPlayerChoice(PlayerIndex.PLAYER_TWO),
            formatRoundResult(currentRound.getResult())
        );

        // Print prompt to continue or exit
        System.out.println();
        System.out.print("Press any key to continue... (q)uit to exit       :");
        String input = SCANNER.nextLine();
        if ("q".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
            System.out.println("Quiting game...");
            System.exit(0);
        }

        System.out.println(BORDER_LINE);
    }

    /**
     * Displays the winner and the final round when the game ends.
     */
    @Override
    public void onAfterLoop() {
        Player winner = gameState.getWinner()
            .map(this::convertIndexToPlayer)
            .orElseThrow();

        Round finalRound = gameState.getCurrentRound().orElseThrow();
        System.out.printf("The winner is %s on round %d%n", winner.name(), finalRound.getIndex());
    }

    /**
     * Checks if the game is over by determining if a winner exists.
     *
     * @return {@code true} if a winner has been determined, {@code false} otherwise
     */
    @Override
    public boolean isGameOver() {
        return gameState.getWinner().isPresent();
    }

    /**
     * Converts a player index to the corresponding player.
     *
     * @param playerIndex the player index
     * @return the {@link Player} corresponding to the index
     */
    private Player convertIndexToPlayer(PlayerIndex playerIndex) {
        return switch (playerIndex) {
            case PLAYER_ONE -> playerOne;
            case PLAYER_TWO -> playerTwo;
        };
    }

    /**
     * Converts a {@link RoundResult} into a human-readable message.
     *
     * @param roundResult the result of the round
     * @return a formatted string describing the round result
     */
    private String formatRoundResult(RoundResult roundResult) {
        return switch (roundResult) {
            case PLAYER_ONE_WINS -> "%s wins this round!".formatted(playerOne.name());
            case PLAYER_TWO_WINS -> "%s wins this round!".formatted(playerTwo.name());
            case DRAW -> "It's a draw!";
        };
    }
}
