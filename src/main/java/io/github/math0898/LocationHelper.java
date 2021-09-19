package io.github.math0898;

import org.bukkit.Location;

/**
 * Useful class for working with locations in bukkit.
 *
 * @author Sugaku
 */
public class LocationHelper {

    /**
     * A nice helper method that takes a location and a delta then returns the new location.
     *
     * @param location The starting location.
     * @param dx The change in the x direction.
     * @param dy The change in the y direction.
     * @param dz The change in the z direction.
     * @return The resulting location.
     */
    public static Location deltaLocation (Location location, double dx, double dy, double dz) {
        return new Location(location.getWorld(), location.getX() + dx, location.getY() + dy, location.getZ() + dz);
    }

    /**
     * Determines the equality of locations. Returning true if and only if the locations match.
     *
     * @param l1 The first location.
     * @param l2 The second location.
     * @param delta The difference each distance coordinate must be within to be considered this location.
     * @return True if and only if the locations match.
     */
    public static boolean compare (Location l1, Location l2, double delta) {
        if (l1.getWorld() == null || l2.getWorld() == null) return false;
        if (!(Math.abs(l1.getX() - l2.getX()) < delta)) return false;
        if (!(Math.abs(l1.getY() - l2.getY()) < delta)) return false;
        if (!(Math.abs(l1.getZ() - l2.getZ()) < delta)) return false;
        return l1.getWorld().getName().equals(l2.getWorld().getName());
    }
}
