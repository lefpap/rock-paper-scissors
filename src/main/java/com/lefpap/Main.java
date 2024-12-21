package com.lefpap;


import com.lefpap.game.Game;
import com.lefpap.game.RockPaperScissorsGame;

public class Main {

    public static void main(String[] args) {
        Game game = new RockPaperScissorsGame();
        game.run();
    }
}