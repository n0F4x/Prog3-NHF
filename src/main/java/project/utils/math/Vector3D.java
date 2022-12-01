package project.utils.math;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a vector in 3D
 */
public class Vector3D {
    public double x;
    public double y;
    public double z;
    public double w;

    /**
     * Constructs a new {@code Vector3D} with default values
     * x=0, y=0, z=0, w=1
     */
    public Vector3D() {
        this(0, 0, 0, 1);
    }

    /**
     * Constructs a new {@code Vector3D} from the given values
     * @param x value of x
     * @param y value of y
     * @param z value of z
     * @param w value of w
     */
    public Vector3D(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Constructs a new {@code Vector3D} from another vector
     * @param other other {@code Vector3D} object
     */
    public Vector3D(@NotNull Vector3D other) {
        x = other.x;
        y = other.y;
        z = other.z;
        w = other.w;
    }

    /**
     * Multiplies {@code this} by given amount
     * @param rhs amount
     * @return result
     */
    @Contract(pure = true)
    public @NotNull Vector3D multiply(double rhs) {
        Vector3D result = new Vector3D(this);
        result.x = x * rhs;
        result.y = y * rhs;
        result.z = z * rhs;
        return result;
    }

    /**
     * Adds another vector to {@code this}
     * @param rhs the other vector
     * @return result
     */
    @Contract(pure = true)
    public @NotNull Vector3D add(@NotNull Vector3D rhs) {
        Vector3D result = new Vector3D(this);
        result.x += rhs.x;
        result.y += rhs.y;
        result.z += rhs.z;
        return result;
    }

    /**
     * @param obj another object
     * @return <code>true</code> - if the two vectors are equal
     */
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
