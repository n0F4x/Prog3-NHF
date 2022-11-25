package project.models;

import project.utils.math.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public static class Wall {
        public final String tomlMessage = "start of new wall";
        public List<Vector3D> corners = new ArrayList<>();
    }

    public List<Wall> walls = new ArrayList<>();
}
