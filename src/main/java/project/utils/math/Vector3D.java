package project.utils.math;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Vector3D {
    public double x;
    public double y;
    public double z;
    public double w;

    public Vector3D() {
        this(0, 0, 0, 1);
    }

    public Vector3D(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Contract("_ -> new")
    public @NotNull Vector3D multiply(double rhs) {
        Vector3D result = new Vector3D();
        result.x = x * rhs;
        result.y = y * rhs;
        result.z = z * rhs;
        return result;
    }

    @Contract("_ -> new")
    public @NotNull Vector3D add(@NotNull Vector3D rhs) {
        Vector3D result = new Vector3D();
        result.x = x + rhs.x;
        result.y = y + rhs.y;
        result.z = z + rhs.z;
        return result;
    }
}
