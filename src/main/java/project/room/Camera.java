package project.room;

import project.Window;
import project.math.Vector3D;

public class Camera {
    private Vector3D position = new Vector3D();
    private Vector3D rotation = new Vector3D();
    public static final double minFOV = 60.0;
    public static final double maxFOV = 150.0;
    private double FOV = 90.0;
    private static final double near = 0.1;
    private static final double far = 2000;
    private static final double aspect = ((double) Window.screenSize.width) / ((double) Window.screenSize.height);


    public Vector3D getPosition() {
        return position;
    }

    public Vector3D getRotation() {
        return rotation;
    }

    public double getFOV() {
        return FOV;
    }

    public double getNear() {
        return near;
    }

    public double getFar() {
        return far;
    }

    public double getAspect() {
        return aspect;
    }

    public void setFOV(double FOV) {
        this.FOV = FOV;
    }

    public void move(Vector3D amount) {
        position = Vector3D.add(position, amount);
    }

    public void rotate(Vector3D amount) {
        rotation = Vector3D.add(rotation, amount);
    }
}
