package io.github.math0898;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;

/**
 * This class contains some useful management things that are used in most of my plugins and so it's useful to declare
 * them here.
 *
 * @author Sugaku
 */
public class Management {

    /**
     * A pointer to a given plugin instance. Needs to be initialized by plugins before any of the methods here can be
     * used effectively.
     */
    private static JavaPlugin plugin = null;

    /**
     * Sets the local plugin variable to whatever is given.
     *
     * @param plugin The plugin the Management helper class will point to.
     */
    public static void setPlugin (JavaPlugin plugin) {
        Management.plugin = plugin;
    }

    /**
     * Gets the local plugin variable.
     *
     * @return The plugin pointer.
     */
    public static JavaPlugin getPlugin () {
        return plugin;
    }

    /**
     * This method sends a message to the console and infers the level it should be sent at.
     *
     * @param message The message to send to the console.
     * @param color The main color of the message being sent.
     */
    public static void console (String message, ChatColor color) {
        switch (color) {
            case RED: console(message, color, Level.SEVERE); break;
            case YELLOW: console(message, color, Level.WARNING); break;
            default: console(message, color, Level.INFO); break;
        }
    }

    /**
     * This method sends a message to the console.
     *
     * @param message The message to send to the console.
     * @param color The main color of the message being sent.
     * @param lvl The level that the message should be sent at.
     */
    public static void console (String message, ChatColor color, Level lvl) {
        plugin.getLogger().log(lvl, color + message);
    }

    /**
     * Returns the version of the plugin as read from plugin.yml
     *
     * @return The string version of the plugin.
     */
    public static String getVersion () {
        FileConfiguration spigotYaml = new YamlConfiguration();
        InputStream inputStream = getPlugin().getResource("plugin.yml");
        if (inputStream == null) return ChatColor.RED + "ERROR";
        Reader reader = new InputStreamReader(inputStream);
        try {
            spigotYaml.load(reader);
            return spigotYaml.getString("version", ChatColor.RED + "ERROR");
        } catch (Exception exception) {
            return ChatColor.RED + "ERROR";
        }
    }
}
