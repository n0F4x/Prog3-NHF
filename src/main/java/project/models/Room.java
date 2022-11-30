package project.models;

import org.jetbrains.annotations.NotNull;
import project.utils.math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public static class Wall {
        public @NotNull List<@NotNull Vector3D> corners = new ArrayList<>();
    }

    public @NotNull List<@NotNull Wall> walls = new ArrayList<>();
}
