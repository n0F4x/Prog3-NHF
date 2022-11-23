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
            case Forward -> camera.move(new Vector3D(Math.sin(camera.getRotation().y * Math.PI / 180.0), 0, -Math.cos(camera.getRotation().y * Math.PI / 180.0), 0));
            case Left -> camera.move(new Vector3D(-Math.cos(camera.getRotation().y * Math.PI / 180.0), 0, -Math.sin(camera.getRotation().y * Math.PI / 180.0), 0));
            case Backward -> camera.move(new Vector3D(-Math.sin(camera.getRotation().y * Math.PI / 180.0), 0, Math.cos(camera.getRotation().y * Math.PI / 180.0), 0));
            case Right -> camera.move(new Vector3D(Math.cos(camera.getRotation().y * Math.PI / 180.0), 0, Math.sin(camera.getRotation().y * Math.PI / 180.0), 0));
            case Up -> camera.move(new Vector3D(0, -moveAmount, 0, 0));
            case Down -> camera.move(new Vector3D(0, moveAmount, 0, 0));
        }
        recalculateGeometry();
    }

    public void rotateCamera(Point2D.Double point) {
        double scale = 0.1;
        camera.rotate(new Vector3D(point.y * scale, point.x * scale, 0, 0));
        recalculateGeometry();
    }
}
