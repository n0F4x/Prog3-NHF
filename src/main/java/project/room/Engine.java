package project.room;

import project.ResourceManager;
import project.math.Vector3D;

import project.math.Direction;

import java.awt.geom.Point2D;

public class Engine {
    Room room;
    Camera camera;
    Room.Perspective perspective;


    public Engine(ResourceManager resourceManager) {
        room = resourceManager.resources.room;
        camera = resourceManager.resources.camera;
        perspective = new Room.Perspective(room);

        recalculateGeometry();
    }

    public Room.Perspective getPerspective() {
        return perspective;
    }

    public void recalculateGeometry() {
        perspective.update(camera);
    }

    public void moveCamera(Direction direction) {
        double moveAmount;
        // TODO: Move camera by amount based on direction
    }

    public void rotateCamera(Point2D.Double point) {
        double scale = 0.01;
        camera.rotate(new Vector3D(point.y * scale, point.x * scale, 0, 0));
        recalculateGeometry();
    }
}
