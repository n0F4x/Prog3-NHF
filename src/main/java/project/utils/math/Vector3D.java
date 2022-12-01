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

    public Vector3D(@NotNull Vector3D other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
    }

    @Contract(pure = true)
    public @NotNull Vector3D multiply(double rhs) {
        Vector3D result = new Vector3D(this);
        result.x = x * rhs;
        result.y = y * rhs;
        result.z = z * rhs;
        return result;
    }

    @Contract(pure = true)
    public @NotNull Vector3D add(@NotNull Vector3D rhs) {
        Vector3D result = new Vector3D(this);
        result.x += rhs.x;
        result.y += rhs.y;
        result.z += rhs.z;
        return result;
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Vector3D other)) {
            return false;
        }

        return Double.compare(x, other.x) == 0
                && Double.compare(y, other.y) == 0
                && Double.compare(z, other.z) == 0
                && Double.compare(w, other.w) == 0;
    }
}
