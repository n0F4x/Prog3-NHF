package project.controllers;

import org.jetbrains.annotations.NotNull;
import project.App;
import project.utils.math.Vector3D;

import project.utils.math.Direction;
import project.models.Camera;

import java.awt.geom.Point2D;

public class Room {
    private final project.models.Room room = App.database.resources.room;
    private final Camera camera = App.database.resources.camera;


    public project.models.Room getRoom() {
        return room;
    }

    public Camera getCamera() {
        return camera;
    }

    public void moveCamera(@NotNull Direction direction) {
        double moveAmount = 1;
        switch (direction) {
            case Forward  -> camera.move(new Vector3D( Math.sin(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0, -Math.cos(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0));
            case Left     -> camera.move(new Vector3D(-Math.cos(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0, -Math.sin(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0));
            case Backward -> camera.move(new Vector3D(-Math.sin(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0,  Math.cos(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0));
            case Right    -> camera.move(new Vector3D( Math.cos(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0,  Math.sin(camera.getRotation().y * Math.PI / 180.0) * moveAmount, 0));
            case Up       -> camera.move(new Vector3D(0, -moveAmount, 0, 0));
            case Down     -> camera.move(new Vector3D(0,  moveAmount, 0, 0));
        }
    }

    public void rotateCamera(@NotNull Point2D.Double mouseMoved) {
        double scale = 0.1;
        camera.rotate(new Vector3D(mouseMoved.y * scale, mouseMoved.x * scale, 0, 0));
    }
}
