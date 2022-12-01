package project.models;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import project.utils.math.Vector3D;

public class Camera implements Cloneable {
    public @NotNull Vector3D position = new Vector3D();
    public @NotNull Vector3D rotation = new Vector3D(); // in radians
    public static final int minFOV = 60;
    public static final int maxFOV = 179;
    private int FOV = 90;


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
        if (FOV < minFOV) {
            this.FOV = minFOV;
        } else this.FOV = Math.min(FOV, maxFOV);
    }

    public synchronized void move(@NotNull Vector3D amount) {
        position = position.add(amount);
    }

    public synchronized void rotate(@NotNull Vector3D amount) {
        rotation = rotation.add(amount);
    }

    @Override
    @Contract(pure = true)
    public @NotNull Camera clone() {
        try {
            @NotNull Camera clone = (Camera) super.clone();
            clone.position = new Vector3D(position);
            clone.rotation = new Vector3D(rotation);
            clone.FOV = FOV;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Camera other)) {
            return false;
        }

        return position.equals(other.position)
                && rotation.equals(other.rotation)
                && Double.compare(FOV, other.FOV) == 0;
    }
}
