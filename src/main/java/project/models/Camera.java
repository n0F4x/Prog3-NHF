package project.models;

import project.utils.math.Vector3D;

public class Camera {
    private Vector3D position = new Vector3D();
    private Vector3D rotation = new Vector3D();
    public static final int minFOV = 60;
    public static final int maxFOV = 150;
    private int FOV = 90;


    public synchronized Vector3D getPosition() {
        return position;
    }

    public synchronized Vector3D getRotation() {
        return rotation;
    }

    public synchronized int getFOV() {
        return FOV;
    }

    public double getNear() {
        return 0.1;
    }

    public double getFar() {
        return 200;
    }

    public synchronized void setFOV(int FOV) {
        this.FOV = FOV;
    }

    public synchronized void move(Vector3D amount) {
        position = position.add(amount);
    }

    public synchronized void rotate(Vector3D amount) {
        rotation = rotation.add(amount);
    }
}
