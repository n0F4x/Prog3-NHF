package project.room;

import project.Window;
import project.math.Vector3D;

public class Camera {
    private Vector3D position = new Vector3D();
    private Vector3D rotation = new Vector3D();
    public static final int minFOV = 60;
    public static final int maxFOV = 150;
    private int FOV = 90;
    private static final double near = 0.1;
    private static final double far = 200;
    private static final double aspectRatio = ((double) Window.screenSize.width) / ((double) Window.screenSize.height);


    public Vector3D getPosition() {
        return position;
    }

    public Vector3D getRotation() {
        return rotation;
    }

    public int getFOV() {
        return FOV;
    }

    public double getNear() {
        return near;
    }

    public double getFar() {
        return far;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setFOV(int FOV) {
        this.FOV = FOV;
    }

    public void move(Vector3D amount) {
        position = Vector3D.add(position, amount);
    }

    public void rotate(Vector3D amount) {
        rotation = Vector3D.add(rotation, amount);
    }
}
