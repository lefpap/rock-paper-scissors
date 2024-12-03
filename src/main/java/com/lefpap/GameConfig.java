package com.lefpap;

import java.util.Map;

public final class GameConfig {
    public static final int SCORE_TO_WIN = 3;
    public static final String PLAYER_ONE_NAME = "P1";
    public static final String PLAYER_TWO_NAME = "P2";
    public static final String ROUND_LINE_SEPARATOR = "-".repeat(20);

    public static final Map<PlayerChoice, PlayerChoice> WINNING_RULES = Map.of(
        PlayerChoice.ROCK, PlayerChoice.SCISSORS,
        PlayerChoice.PAPER, PlayerChoice.ROCK,
        PlayerChoice.SCISSORS, PlayerChoice.PAPER
    );

    private GameConfig() {
        // Prevent instantiation
    }
}
