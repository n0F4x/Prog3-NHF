package project.room;

import project.math.Matrix3D;
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

            Matrix3D perspectiveMatrix = Matrix3D.buildPerspectiveMatrix(camera.getFOV(), camera.getAspect(), camera.getNear(), camera.getFar());
            Matrix3D cameraTranslationMatrix = Matrix3D.buildTranslationMatrix(Vector3D.multiply(camera.getPosition(), -1));
            Matrix3D cameraRotationMatrix = Matrix3D.concat(Matrix3D.concat(Matrix3D.buildRotationXMatrix(camera.getRotation().x), Matrix3D.buildRotationYMatrix(camera.getRotation().y)), Matrix3D.buildRotationZMatrix(camera.getRotation().z));
            Matrix3D projectionMatrix = Matrix3D.concat(Matrix3D.concat(perspectiveMatrix, cameraTranslationMatrix), cameraRotationMatrix);

            Vector3D[] corners = new Vector3D[4];
            for (Wall wall : room.walls) {
                for (int i = 0; i < 4; i++) {
                    corners[i] = Matrix3D.concat(projectionMatrix, wall.corners.get(i));
                }
                lines.add(new Line2D.Double(corners[0].x, corners[0].y, corners[1].x, corners[1].y));
                lines.add(new Line2D.Double(corners[1].x, corners[1].y, corners[2].x, corners[2].y));
                lines.add(new Line2D.Double(corners[2].x, corners[2].y, corners[3].x, corners[3].y));
                lines.add(new Line2D.Double(corners[3].x, corners[3].y, corners[0].x, corners[0].y));
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
