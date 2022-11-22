package project.math;

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

    @Contract("_, _ -> new")
    public static @NotNull Vector3D multiply(@NotNull Vector3D lhs, @NotNull Vector3D rhs) {
        Vector3D result = new Vector3D();
        result.x = lhs.x * rhs.x;
        result.y = lhs.y * rhs.y;
        result.z = lhs.z * rhs.z;
        return result;
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3D multiply(@NotNull Vector3D lhs, double rhs) {
        Vector3D result = new Vector3D();
        result.x = lhs.x * rhs;
        result.y = lhs.y * rhs;
        result.z = lhs.z * rhs;
        return result;
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3D subtract(@NotNull Vector3D lhs, @NotNull Vector3D rhs) {
        Vector3D result = new Vector3D();
        result.x = lhs.x - rhs.x;
        result.y = lhs.y - rhs.y;
        result.z = lhs.z - rhs.z;
        return result;
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3D add(@NotNull Vector3D lhs, @NotNull Vector3D rhs) {
        Vector3D result = new Vector3D();
        result.x = lhs.x + rhs.x;
        result.y = lhs.y + rhs.y;
        result.z = lhs.z + rhs.z;
        return result;
    }
}
