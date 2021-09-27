package io.github.math0898.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static io.github.math0898.Management.getPlugin;

/**
 * The inventory manager object which holds all the inventory objects.
 *
 * @author Sugaku
 */
public class InventoryManager <E extends Enum<E>> {

    /**
     * A map of all the inventories that are registered with the Inventory Manager.
     */
    private final HashMap<E, GUI> inventories;

    /**
     * Creates a new Inventory Manager object with the given enum of inventory types.
     */
    public InventoryManager () {
        inventories = new HashMap<>();
    }

    /**
     * Adds an inventory to the inventory manager. This also registers the events.
     *
     * @param type The enum type of the inventory, used when opening inventories of a certain type to a player.
     * @param gui The GUI of the inventory being added.
     */
    public void addInventory (E type, GUI gui) {
        inventories.put(type, gui);
        Bukkit.getPluginManager().registerEvents(gui, getPlugin());
    }

    /**
     * Attempts open the given inventory to the given player.
     *
     * @param player The player to open the inventory to.
     * @param type The type of inventory to open.
     */
    public void openInventory (Player player, E type) {
        GUI gui = inventories.get(type);
        if (gui == null) return; // No inventory found.
        gui.openInventory(player);
    }
}
