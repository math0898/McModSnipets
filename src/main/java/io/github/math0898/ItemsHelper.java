package io.github.math0898;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * This class has a lot of helpful methods when it comes to manipulating items and creating them in a single line.
 *
 * @author Sugaku
 */
public class ItemsHelper {

//    /**
//     * Creates an AttributeModifier with a unique UUID
//     *
//     * @param a The attribute being modified.
//     * @param value The desired value.
//     * @param slot The slot this should apply to.
//     * @return The AttributeModifier with a unique UUID.
//     */
//    public static AttributeModifier attributeModifier(Attribute a, double value, EquipmentSlot slot) {
//        int mod = -1;
//        switch (a) {
//            case GENERIC_MAX_HEALTH: mod = 1; break;
//            case GENERIC_ARMOR: mod = 2; break;
//            case GENERIC_ARMOR_TOUGHNESS: mod = 3; break;
//            case GENERIC_ATTACK_DAMAGE: mod = 4; break;
//            case GENERIC_KNOCKBACK_RESISTANCE: mod = 5; break;
//            case GENERIC_MOVEMENT_SPEED: mod = 6; break;
//            case GENERIC_LUCK: mod = 7; break;
//            case HORSE_JUMP_STRENGTH: mod = 8; break;
//            case GENERIC_ATTACK_SPEED: mod = 9; break;
//            case GENERIC_ATTACK_KNOCKBACK: mod = 10; break;
//            case GENERIC_FLYING_SPEED: mod = 11; break;
//            case GENERIC_FOLLOW_RANGE: mod = 12; break;
//            case ZOMBIE_SPAWN_REINFORCEMENTS: mod = 13; break;
//        }
//        int slotN = -1;
//        switch (slot) {
//            case FEET: slotN = 1; break;
//            case LEGS: slotN = 2; break;
//            case CHEST: slotN = 3; break;
//            case HEAD: slotN = 4; break;
//            case HAND: slotN = 5; break;
//            case OFF_HAND: slotN = 6; break;
//        }
//        return new AttributeModifier(new UUID(slotN, mod), a.toString(), value, AttributeModifier.Operation.ADD_NUMBER, slot);
//    }

    /**
     * Applies the given strings to the lore of the given meta.
     *
     * @param m The meta which will have the lore.
     * @param lines The lines of lore.
     */
    public static void setLore(ItemMeta m, String[] lines) {
        ArrayList<String> l = new ArrayList<>();
        Collections.addAll(l, lines);
        m.setLore(l);
    }

    /**
     * Creates an item with the option of making the item unbreakable.
     *
     * @param m The material for the item.
     * @param i The number of the item.
     * @param u Is the item unbreakable.
     * @return The completed item.
     */
    public static ItemStack createItem (Material m, int i, boolean u) {
        ItemStack r = new ItemStack(m, i);
        ItemMeta meta = r.getItemMeta();
        if (meta == null) meta = Bukkit.getItemFactory().getItemMeta(r.getType());
        assert meta != null;
        meta.setUnbreakable(u);
        r.setItemMeta(meta);
        return r;
    }

    /**
     * Creates a custom item of the given material and name. Used to create items in line.
     *
     * @param m The material for the item.
     * @param i The number of items in the stack.
     * @param n The name of the item.
     */
    public static ItemStack createItem(Material m, int i, String n) { return createItem(m, i, n, new String[]{}); }

    /**
     * Creates a custom item of the given material, name, and lore. Used to created items in line. Using this generally
     * also reduces scope.
     *
     * @param m The material for the item.
     * @param i The number of items in the stack.
     * @param n The name of the item.
     * @param lines The lines of lore.
     */
    public static ItemStack createItem(Material m, int i, String n, String[] lines) {
        ItemStack r = new ItemStack(m, i);
        ItemMeta meta = Bukkit.getItemFactory().getItemMeta(m);
        assert meta != null;
        setLore(meta, lines);
        meta.setDisplayName(n);
        meta.setUnbreakable(true);
        r.setItemMeta(meta);
        return r;
    }

//    /**
//     * Creates a custom item of the given material, name, lore, and array of attribute modifiers. Used to create items
//     * in line. This method can significantly reduce scope.
//     *
//     * @param m The material for the item.
//     * @param i The number of items in the stack.
//     * @param n The name of the item.
//     * @param lines The lines of lore.
//     * @param attributes The attributes to be added to the item.
//     */
//    public static ItemStack createItem(Material m, int i, String n, String[] lines, AttributeModifier[] attributes) {
//        ItemStack item = createItem(m, i, n, lines);
//        ItemMeta meta = item.getItemMeta();
//        if (meta == null) meta = Bukkit.getItemFactory().getItemMeta(item.getType());
//        assert meta != null;
//        for (AttributeModifier a: attributes) meta.addAttributeModifier(Attribute.valueOf(a.getName()), a);
//        item.setItemMeta(meta);
//        return item;
//    }

    /**
     * Creates a leather armor item with the given dyes. It's implied that each item stack will only have one item.
     *
     * @param m The material for the item.
     * @param n The name of the item.
     * @param lines The lines of lore.
     * @param r The red of the dye.
     * @param g The green of the dye.
     * @param b The blue of the dye.
     */
    public static ItemStack createLeatherArmor(Material m, String n, String[] lines, int r, int g, int b) {
        if (m != Material.LEATHER_BOOTS && m != Material.LEATHER_LEGGINGS && m != Material.LEATHER_CHESTPLATE && m != Material.LEATHER_HELMET) return null;
        ItemStack item = createItem(m, 1, n, lines);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        if (meta == null) meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(item.getType());
        assert meta != null;
        meta.setColor(Color.fromRGB(r, g, b));
        item.setItemMeta(meta);
        return item;
    }

//    /**
//     * Creates a leather armor item with the given dyes. This also handles the adding attributes to the leather armor.
//     *
//     * @param m The material for the item.
//     * @param n The name of the item.
//     * @param lines The lines of lore.
//     * @param r The red of the dye.
//     * @param g The green of the dye.
//     * @param b The blue of the dye.
//     * @param attributes The attributes to be added to the item.
//     */
//    public static ItemStack createLeatherArmor(Material m, String n, String[] lines, int r, int g, int b, AttributeModifier[] attributes) {
//        ItemStack item = createLeatherArmor(m, n, lines, r, g, b);
//        if (item == null) return null;
//        ItemMeta meta = item.getItemMeta();
//        if (meta == null) meta = Bukkit.getItemFactory().getItemMeta(item.getType());
//        assert meta != null;
//        for (AttributeModifier a: attributes) meta.addAttributeModifier(Attribute.valueOf(a.getName()), a);
//        item.setItemMeta(meta);
//        return item;
//    }
}
