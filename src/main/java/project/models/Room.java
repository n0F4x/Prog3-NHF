package project.models;

import org.jetbrains.annotations.NotNull;
import project.utils.math.Vector3D;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores information about a room
 */
public class Room {
    /**
     * Stores information about a wall of the room
     */
    public static class Wall {
        public @NotNull List<@NotNull Vector3D> corners = new ArrayList<>();
    }

    public @NotNull List<@NotNull Wall> walls = new ArrayList<>();
}
