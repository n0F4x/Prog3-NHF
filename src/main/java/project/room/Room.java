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
        private final List<Polygon> polygons = new ArrayList<>();

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
            polygons.clear();

            Matrix3D perspectiveMatrix = Matrix3D.buildPerspectiveMatrix(camera.getFOV(), camera.getAspectRatio(), camera.getNear(), camera.getFar());
            Matrix3D viewMatrix = calcViewMatrix(camera);
            Matrix3D projectionMatrix = perspectiveMatrix.concat(viewMatrix);

            for (Wall wall : room.walls) {
                boolean valid = false;
                int[] x_points = new int[4];
                int[] y_points = new int[4];
                for (int i = 0; i < 4; i++) {
                    Vector3D temp = Matrix3D.concat(projectionMatrix, wall.corners.get(i));
                    x_points[i] = (int) ((temp.x / temp.w + 1.0) / 2.0 * Window.screenSize.width);
                    y_points[i] = (int) ((temp.y / temp.w + 1.0) / 2.0 * Window.screenSize.height);
                    if (
                        !valid && temp.z > camera.getNear() && temp.z < camera.getFar()
                        && 0 <= x_points[i] && x_points[i] <= Window.screenSize.width
                        && 0 <= y_points[i] && y_points[i] <= Window.screenSize.height
                    ) {
                        valid = true;
                    }
                }
                if (valid) {
                    polygons.add(new Polygon(x_points, y_points, 4));
                }
            }
        }

        public void paint(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setColor(Color.GREEN);
            graphics2D.setStroke(new BasicStroke(2));
            for (Polygon polygon : polygons) {
                graphics2D.draw(polygon);
            }
        }
    }

    public static class Wall {
        public final String tomlMessage = "start of new wall";
        public List<Vector3D> corners = new ArrayList<>();
    }

    public List<Wall> walls = new ArrayList<>();
}
