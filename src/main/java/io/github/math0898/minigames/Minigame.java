package io.github.math0898.minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * The abstract class which describes a minigame. It handles basic functionality such as when players join and leave the
 * world along with checking for win conditions.
 *
 * @author Sugaku
 */
public abstract class Minigame implements Listener {

    /**
     * The world that this minigame has full control over. With an exception for players with an admin permission all
     * players in this world are considered... well players of the game.
     */
    private final World world;

    /**
     * A list of players which are currently in this instance.
     */
    private final ArrayList<Player> players = new ArrayList<>();

    /**
     * A pointer which holds the current game state.
     */
    private GameState currentState = GameState.SETUP;

    /**
     * A pointer to the maximum player size of the minigame instance.
     */
    private final int MAX_PLAYERS = main.plugin.getConfig().getInt("games.max-players", 30);

    /**
     * A pointer to the minimum player size of the minigame instance.
     */
    private final int MIN_PLAYERS = main.plugin.getConfig().getInt("games.min-players", 9);

    /**
     * The default constructor for a Minigame just requiring a world.
     */
    public Minigame (World world) {
        this.world = world;
        Bukkit.getScheduler().runTaskLater(main.plugin, this::gameLoop, 20);
    }

    /**
     * Returns what the line of a sign should be for this minigame instance.
     *
     * @param line The line of the sign to return.
     */
    public String getSign (int line) {
        switch (line) {
            case 1: return ChatColor.BLACK + "" + ChatColor.BOLD + getName();
            case 2: return ChatColor.BOLD + getCurrentStateFormatted();
            case 3: return ChatColor.YELLOW + "Players: " + getPlayerCount() + "/" + MAX_PLAYERS + ")";
            default: return "";
        }
    }

    /**
     * Accesses the current state of the minigame.
     *
     * @return The current game state.
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * Sets the current state of the minigame.
     *
     * @param state The new state to be set to.
     */
    public void setCurrentState (GameState state) {
        currentState = state;
    }

    /**
     * Accesses the current state of the minigame as a String for easy formatting.
     *
     * @return The string representation of the game state.
     */
    public String getCurrentStateFormatted () {
        switch (currentState) {
            case SETUP: return ChatColor.YELLOW + "Setting up";
            case WAITING: return ChatColor.GRAY + "Waiting";
            case STARTING_SOON: return ChatColor.GREEN + "Starting soon";
            case IN_PROGRESS: return ChatColor.RED + "In progress";
        }
    }

    /**
     * Accesses the world which this minigame has control over.
     *
     * @return The controlled world.
     */
    public World getWorld () {
        return world;
    }

    /**
     * Returns the name of the world which this Minigame instance has control over.
     *
     * @return The name of the minigame world.
     */
    public String getName () {
        return world.getName();
    }

    /**
     * Gets the maximum number of players that this minigame can hold.
     *
     * @return The maximum player count.
     */
    public int getMaxPlayers () {
        return MAX_PLAYERS;
    }

    /**
     * Gets the minimum number of players that this minigame needs to run.
     *
     * @return The minimum player count.
     */
    public int getMinPlayers () {
        return MIN_PLAYERS;
    }

    /**
     * Returns the number of players that are currently in this game.
     *
     * @return The number of players in the game.
     */
    public int getPlayerCount () {
        return players.size();
    }

    /**
     * Returns the starting location for players who are joining the minigame.
     *
     * @return The starting location for the minigame.
     */
    public abstract Location getStartingLocation ();

    /**
     * Connects a player to the minigame. At this level its just teleporting the player to the world. Minigames should
     * override with any additional things that need to happen.
     *
     * @param player The player to connect to this minigame.
     */
    public void connect (Player player) {
        player.teleport(getStartingLocation());
    }

    /**
     * Handles the logic for the main game loop of the minigame.
     */
    public void gameLoop () {
        switch (currentState) {
            case WAITING: waitingLoop(); break;
            case STARTING_SOON: startingSoonLoop(); break;
            case IN_PROGRESS: gameplayLoop(); break;
        }
        Bukkit.getScheduler().runTaskLater(main.plugin, this::gameLoop, 20);
    }

    /**
     * Handles the gameplay loop of the minigame including win checking.
     */
    public abstract void gameplayLoop ();

    /**
     * Handles the waiting lobby of the minigame, mainly used for checking if its reached the player counts.
     */
    public abstract void waitingLoop ();

    /**
     * Handles the starting soon loop of the minigame.
     */
    public abstract void startingSoonLoop ();
}
