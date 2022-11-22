package project.room;

import project.Window;
import project.math.Matrix3D;
import project.math.Vector3D;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Room {
    public static class Perspective {
        private final Room room;
        private final List<Line2D> lines = new ArrayList<>();

        public Perspective(Room room) {
            this.room = room;
        }

        private Matrix3D calcViewMatrix(Camera camera) {
            return Matrix3D.buildRotationXMatrix(camera.getRotation().x * -1)
                    .concat(Matrix3D.buildRotationYMatrix(camera.getRotation().y * -1))
                    .concat(Matrix3D.buildRotationZMatrix(camera.getRotation().z * -1))
                    .concat(Matrix3D.buildTranslationMatrix(Vector3D.multiply(camera.getPosition(), -1)));
        }

        public void update(Camera camera) {
            lines.clear();

            Matrix3D perspectiveMatrix = Matrix3D.buildPerspectiveMatrix(camera.getFOV(), camera.getAspectRatio(), camera.getNear(), camera.getFar());
            Matrix3D viewMatrix = calcViewMatrix(camera);
            Matrix3D projectionMatrix = perspectiveMatrix.concat(viewMatrix);

            Point2D[] corners = { new Point2D.Double(), new Point2D.Double(), new Point2D.Double(), new Point2D.Double() };

            for (Wall wall : room.walls) {
                for (int i = 0; i < 4; i++) {
                    Vector3D temp = Matrix3D.concat(projectionMatrix, wall.corners.get(i));
                    corners[i].setLocation((temp.x / temp.w + 1.0) / 2.0 * Window.screenSize.width, (temp.y / temp.w + 1.0) / 2.0 * Window.screenSize.height);
                }
                lines.add(new Line2D.Double(corners[0], corners[1]));
                lines.add(new Line2D.Double(corners[1], corners[2]));
                lines.add(new Line2D.Double(corners[2], corners[3]));
                lines.add(new Line2D.Double(corners[3], corners[0]));
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
