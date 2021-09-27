package io.github.math0898.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class to be expanded upon by GUI implemented by projects.
 *
 * @author Sugaku
 */
public abstract class GUI implements Listener {

    /**
     * The size of the eventual inventory that needs to be made.
     */
    private final int size;

    /**
     * The name of the inventory to be made.
     */
    private final String name;

    /**
     * The item to fill inventories with.
     */
    private final ItemStack fill;

    /**
     * Should inventory click events be canceled by default?
     */
    private final boolean cancel;

    /**
     * A map of items which will be added to the GUI when it is created.
     */
    private final Map<Integer, ItemStack> items = new HashMap<>();

    /**
     * Creates a new GUI using the size it should be, what the display name is, and whether click events should be
     * canceled by default.
     *
     * @param size The size of the new gui.
     * @param name The name of the new gui.
     * @param cancel Should click events be canceled by default?
     */
    public GUI (int size, String name, boolean cancel) {
        this.size = size;
        this.name = name;
        fill = new ItemStack(Material.AIR, 0);
        this.cancel = cancel;
    }

    /**
     * Creates a new GUI using the size it should be, what the display name is, the item to fill in the gabs with, and
     * whether click events should be canceled by default.
     *
     * @param size The size of the new gui.
     * @param name The name of the new gui.
     * @param fill The item to fill the inventory with.
     */
    public GUI (int size, String name, ItemStack fill, boolean cancel) {
        this.size = size;
        this.name = name;
        this.fill = fill;
        this.cancel = cancel;
    }

    /**
     * Creates a new GUI using the size it should be along with the name to display. Defaults to canceling inventory
     * clicks.
     *
     * @param size The size of the new gui.
     * @param name The name of the new gui.
     */
    public GUI (int size, String name) {
        this.size = size;
        this.name = name;
        fill = new ItemStack(Material.AIR, 0);
        this.cancel = true;
    }

    /**
     * Creates a new GUI using the size it should be along with the name to display and fill material. Defaults to
     * canceling inventory clicks.
     *
     * @param size The size of the new gui.
     * @param name The name of the new gui.
     * @param fill The item to fill the inventory with.
     */
    public GUI (int size, String name, ItemStack fill) {
        this.size = size;
        this.name = name;
        this.fill = fill;
        this.cancel = true;
    }

    /**
     * Puts a given item into the map of items to add to this inventory.
     *
     * @param pos The position that this item should be added at.
     * @param item The item to add into the map.
     */
    public void addItem (int pos, ItemStack item) {
        items.put(pos, item);
    }

    /**
     * Builds the inventory, putting it into the inventory passed.
     *
     * @param inv The inventory to place the items into.
     */
    public void buildInventory (Inventory inv) {
        for (int i = 0; i < size; i++) {
            ItemStack attempt = items.get(i);
            if (attempt == null) inv.setItem(i, fill);
            else inv.setItem(i, attempt);
        }
    }

    /**
     * Opens this inventory to the given player.
     *
     * @param player The player to open the inventory to.
     */
    public void openInventory (Player player) {
        Inventory inventory = Bukkit.createInventory(player, size, name);
        buildInventory(inventory);
        player.openInventory(inventory);
    }

    /**
     * Handles when this inventory was clicked. Used so that implementations do not need to filter click events. By
     * default, the event is canceled by this point.
     *
     * @param player The player who clicked in this inventory.
     * @param slot The slot number that was clicked by the player.
     * @param event The inventory click event.
     */
    public abstract void clicked (Player player, int slot, InventoryClickEvent event);

    /**
     * Handles the situation when an inventory gets clicked. Determines if it was this inventory that was clicked or if
     * it was another.
     *
     * @param event The inventory click event.
     */
    @EventHandler
    public void onClick (InventoryClickEvent event) {
        Inventory clicked = event.getClickedInventory();
        InventoryView open = event.getWhoClicked().getOpenInventory();
        if (clicked == null) return;
        if (open.getTitle().equals(name)) {
            event.setCancelled(cancel);
            if (!(event.getWhoClicked() instanceof Player)) return;
            clicked((Player) event.getWhoClicked(), event.getSlot(), event);
        }
    }
}
