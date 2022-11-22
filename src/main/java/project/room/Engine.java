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
        double moveAmount = 1;
        switch (direction) {
//            case Forward ->
//            case Left ->
//            case Backward ->
//            case Right ->
            case Up -> camera.move(new Vector3D(0, -moveAmount, 0, 0));
            case Down -> camera.move(new Vector3D(0, moveAmount, 0, 0));
        }
        recalculateGeometry();
    }

    public void rotateCamera(Point2D.Double point) {
        double scale = 0.05;
        camera.rotate(new Vector3D(point.y * scale, point.x * scale, 0, 0));
        recalculateGeometry();
    }
}
