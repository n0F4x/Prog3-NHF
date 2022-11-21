package project.room;

import project.math.Vector3D;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Room {
    public static class Perspective {
        private final Room room;
        private final List<Line2D> lines = new ArrayList<>();

        public Perspective(Room room) {
            this.room = room;
        }

        public void update(Camera camera) {
            lines.clear();

            for (Wall wall : room.walls) {
                // TODO: project points to 2D plane
                // TODO: add visible lines to lines
            }
        }

        public void paint(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setColor(Color.GREEN);
            graphics2D.setStroke(new BasicStroke(2));
            for (Line2D line : lines) {
                graphics2D.draw(line);
            }
        }
    }

    public static class Wall {
        public final String tomlMessage = "start of new wall";
        public List<Vector3D> corners = new ArrayList<>();
    }

    public List<Wall> walls = new ArrayList<>();
}
