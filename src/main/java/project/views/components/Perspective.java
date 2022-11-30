package project.views.components;

import org.jetbrains.annotations.NotNull;
import project.models.Camera;
import project.models.Room;
import project.utils.math.Matrix3D;
import project.utils.math.Vector3D;

import java.util.List;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Perspective {
    private final Room room;
    private final Camera camera;
    private Camera snapshot = new Camera();
    private final Collection<Line2D> lines = Collections.synchronizedList(new ArrayList<>());
    private Matrix3D projectionMatrix;
    private Vector3D nearCameraPosition;
    private Vector3D normal;


    public Perspective(Room room, Camera camera) {
        this.room = room;
        this.camera = camera;
    }

    private @NotNull Matrix3D calcViewMatrix() {
        return Matrix3D.buildRotationMatrix(camera.getRotation().multiply(-1))
                .concat(Matrix3D.buildTranslationMatrix(camera.getPosition().multiply(-1)));
    }

    private @NotNull Vector3D correctPoint(@NotNull Vector3D front, @NotNull Vector3D back, @NotNull Vector3D camera) {
        return new Matrix3D(new double[][]{
                {1, 0, 0, front.x - back.x},
                {0, 1, 0, front.y - back.y},
                {0, 0, 1, front.z - back.z},
                {normal.x, normal.y, normal.z, 0}
        }).eliminate(new Vector3D(
                front.x,
                front.y,
                front.z,
                normal.x * camera.x + normal.y * camera.y + normal.z * camera.z
        ));
    }

    private boolean addCorrectedLine(Vector3D[] points, List<Vector3D> corners, Dimension screenSize, int index, int prevIndex, int nextIndex) {
        if (points[prevIndex].z <= 0) {
            Vector3D corrected = correctPoint(corners.get(index), corners.get(prevIndex), nearCameraPosition);
            Vector3D projected = projectionMatrix.concat(corrected);
            projected.x = (projected.x / projected.w + 1.0) / 2.0 * screenSize.width;
            projected.y = (projected.y / projected.w + 1.0) / 2.0 * screenSize.height;
            lines.add(new Line2D.Double(
                    new Point2D.Double(points[index].x, points[index].y),
                    new Point2D.Double(projected.x, projected.y)
            ));
        }
        if (points[nextIndex].z <= 0) {
            Vector3D corrected = correctPoint(corners.get(index), corners.get(nextIndex), nearCameraPosition);
            Vector3D projected = projectionMatrix.concat(corrected);
            projected.x = (projected.x / projected.w + 1.0) / 2.0 * screenSize.width;
            projected.y = (projected.y / projected.w + 1.0) / 2.0 * screenSize.height;
            lines.add(new Line2D.Double(
                    new Point2D.Double(points[index].x, points[index].y),
                    new Point2D.Double(projected.x, projected.y)
            ));
        } else return true;
        return false;
    }

    private void addLines(Vector3D[] points, List<Vector3D> corners, Dimension screenSize) {
        normal = Matrix3D.buildRotationMatrix(camera.getRotation()).concat(new Vector3D(0, 0, -1, 1));
        nearCameraPosition = camera.getPosition().add(normal.multiply(camera.getNear()));

        if (points[0].z > 0) {
            if (addCorrectedLine(points, corners, screenSize, 0, 3, 1)) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(points[0].x, points[0].y),
                        new Point2D.Double(points[1].x, points[1].y)
                ));
            }
        }
        if (points[1].z > 0) {
            if (addCorrectedLine(points, corners, screenSize, 1, 0, 2)) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(points[1].x, points[1].y),
                        new Point2D.Double(points[2].x, points[2].y)
                ));
            }
        }
        if (points[2].z > 0) {
            if (addCorrectedLine(points, corners, screenSize, 2, 1, 3)) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(points[2].x, points[2].y),
                        new Point2D.Double(points[3].x, points[3].y)
                ));
            }
        }
        if (points[3].z > 0) {
            if (addCorrectedLine(points, corners, screenSize, 3, 2, 0)) {
                lines.add(new Line2D.Double(
                        new Point2D.Double(points[3].x, points[3].y),
                        new Point2D.Double(points[0].x, points[0].y)
                ));
            }
        }
    }

    public void update(@NotNull Dimension screenSize) {
        if (!snapshot.equals(camera)) {
            snapshot = camera.clone();
            synchronized (lines) {
                lines.clear();

                Matrix3D perspectiveMatrix = Matrix3D.buildPerspectiveMatrix(camera.getFOV(), (screenSize.getWidth()) / (screenSize.getHeight()), camera.getNear(), camera.getFar());
                Matrix3D viewMatrix = calcViewMatrix();
                projectionMatrix = perspectiveMatrix.concat(viewMatrix);

                for (Room.Wall wall : room.walls) {
                    boolean valid = false;
                    Vector3D[] points = new Vector3D[4];

                    for (int i = 0; i < 4; i++) {
                        Vector3D point3D = wall.corners.get(i);
                        points[i] = projectionMatrix.concat(point3D);

                        points[i].x = (points[i].x / points[i].w + 1.0) / 2.0 * screenSize.width;
                        points[i].y = (points[i].y / points[i].w + 1.0) / 2.0 * screenSize.height;

                        if (!valid && points[i].z > camera.getNear() && points[i].z < camera.getFar()
//                                && 0 <= points[i].x && points[i].x <= screenSize.width && 0 <= points[i].y && points[i].y <= screenSize.height
                        ) {
                            valid = true;
                        }
                    }

                    if (valid) {
                        addLines(points, wall.corners, screenSize);
                    }
                }
            }
        }
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN);
        graphics2D.setStroke(new BasicStroke(2));
        synchronized (lines) {
            for (Line2D line : lines) {
                graphics2D.draw(line);
            }
        }
    }
}
