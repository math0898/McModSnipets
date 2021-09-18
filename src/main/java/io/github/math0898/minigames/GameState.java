package io.github.math0898.minigames;

/**
 * An enum describing the state a game is in at any particular time.
 *
 * @author Sugaku
 */
public enum GameState {

    /**
     * The Game is being setup and is not ready to go.
     */
    SETUP,

    /**
     * The game is set up but is waiting for players to join in order to start.
     */
    WAITING,

    /**
     * Enough players have joined to start the game, and it is currently counting down.
     */
    STARTING_SOON,

    /**
     * The game is in progress and cannot be joined.
     */
    IN_PROGRESS;
}
