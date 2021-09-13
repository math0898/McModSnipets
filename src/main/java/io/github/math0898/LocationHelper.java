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
}
