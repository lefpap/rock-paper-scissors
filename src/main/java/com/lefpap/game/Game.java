package com.lefpap.game;

/**
 * Abstract base class representing the framework for a game.
 * <p>
 * This class provides a structured game loop with hooks for customization.
 * Subclasses are expected to define the core logic of the game by implementing
 * the abstract methods {@link #init()}, {@link #loop()}, and {@link #isGameOver()}.
 * Optional hooks are provided to allow additional behavior at specific points
 * during the game lifecycle.
 * </p>
 */
public abstract class Game {

    /**
     * Starts and manages the game loop.
     *
     * <p>
     * This method initializes the game, executes any pre-loop logic,
     * and then enters the main game loop. The loop runs until {@link #isGameOver()}
     * returns {@code true}. During each iteration, hooks for per-loop logic
     * are executed before and after the main game logic.
     * </p>
     */
    public void run() {
        init();
        onBeforeLoop();
        while (!isGameOver()) {
            onStartOfEachLoop();
            loop();
            onEndOfEachLoop();
        }
        onAfterLoop();
    }

    /**
     * Initializes the game.
     *
     * <p>
     * This method is called once before the game loop begins and is
     * used to set up the initial state of the game. Subclasses must
     * provide an implementation to initialize game-specific resources.
     * </p>
     */
    public abstract void init();

    /**
     * Executes the main logic for a single iteration of the game loop.
     *
     * <p>
     * Subclasses must implement this method to define what happens
     * during each iteration of the game loop.
     * </p>
     */
    public abstract void loop();

    /**
     * Determines whether the game is over.
     *
     * <p>
     * This method is called during each iteration of the game loop to
     * check if the game should terminate. Subclasses must provide an
     * implementation to define the conditions for ending the game.
     * </p>
     *
     * @return {@code true} if the game is over, {@code false} otherwise
     */
    public abstract boolean isGameOver();

    /**
     * Hook for behavior to execute before the main game loop begins.
     *
     * <p>
     * Subclasses can override this method to define additional logic
     * that should run once after {@link #init()} and before the game loop starts.
     * By default, this method does nothing.
     * </p>
     */
    public void onBeforeLoop() {
        // Do nothing by default
    }

    /**
     * Hook for behavior to execute at the start of each loop iteration.
     *
     * <p>
     * Subclasses can override this method to define logic that should
     * run at the start of each iteration of the game loop. By default, this
     * method does nothing.
     * </p>
     */
    public void onStartOfEachLoop() {
        // Do nothing by default
    }

    /**
     * Hook for behavior to execute at the end of each loop iteration.
     *
     * <p>
     * Subclasses can override this method to define logic that should
     * run at the end of each iteration of the game loop. By default, this
     * method does nothing.
     * </p>
     */
    public void onEndOfEachLoop() {
        // Do nothing by default
    }

    /**
     * Hook for behavior to execute after the game loop ends.
     *
     * <p>
     * Subclasses can override this method to define cleanup or other
     * post-game logic that should run once after the game loop terminates.
     * By default, this method does nothing.
     * </p>
     */
    public void onAfterLoop() {
        // Do nothing by default
    }
}
