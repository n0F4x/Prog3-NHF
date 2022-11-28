package project.views.components;

import org.jetbrains.annotations.NotNull;
import project.models.Camera;
import project.models.Room;
import project.utils.math.Matrix3D;
import project.utils.math.Vector3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Perspective {
    private final Room room;
    private final Camera camera;
    private Camera snapshot = new Camera();
    private final Collection<Polygon> polygons = Collections.synchronizedList(new ArrayList<>());


    public Perspective(Room room, Camera camera) {
        this.room = room;
        this.camera = camera;
    }

    private @NotNull Matrix3D calcViewMatrix() {
        return Matrix3D.buildRotationXMatrix(camera.getRotation().x * -1)
                .concat(Matrix3D.buildRotationYMatrix(camera.getRotation().y * -1))
                .concat(Matrix3D.buildRotationZMatrix(camera.getRotation().z * -1))
                .concat(Matrix3D.buildTranslationMatrix(camera.getPosition().multiply(-1)));
    }

    public void update(@NotNull Dimension screenSize) {
        if (!snapshot.equals(camera)) {
            snapshot = camera.clone();
            synchronized (polygons) {
                polygons.clear();

                Matrix3D perspectiveMatrix = Matrix3D.buildPerspectiveMatrix(camera.getFOV(), (screenSize.getWidth()) / (screenSize.getHeight()), camera.getNear(), camera.getFar());
                Matrix3D viewMatrix = calcViewMatrix();
                Matrix3D projectionMatrix = perspectiveMatrix.concat(viewMatrix);

                for (Room.Wall wall : room.walls) {
                    boolean valid = false;
                    int[] x_points = new int[4];
                    int[] y_points = new int[4];
                    for (int i = 0; i < 4; i++) {
                        Vector3D temp = projectionMatrix.concat(wall.corners.get(i));
                        x_points[i] = (int) ((temp.x / temp.w + 1.0) / 2.0 * screenSize.width);
                        y_points[i] = (int) ((temp.y / temp.w + 1.0) / 2.0 * screenSize.height);
                        if (
                                !valid && temp.z > camera.getNear() && temp.z < camera.getFar()
                                        && 0 <= x_points[i] && x_points[i] <= screenSize.width
                                        && 0 <= y_points[i] && y_points[i] <= screenSize.height
                        ) {
                            valid = true;
                        }
                    }
                    if (valid) {
                        polygons.add(new Polygon(x_points, y_points, 4));
                    }
                }
            }
        }
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN);
        graphics2D.setStroke(new BasicStroke(2));
        synchronized (polygons) {
            for (Polygon polygon : polygons) {
                graphics2D.draw(polygon);
            }
        }
    }
}
