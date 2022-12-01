package project.math;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import project.utils.math.Vector3D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector3DTest {
    @Test
    public void testMultiply() {
        @NotNull Vector3D vector3D = new Vector3D(1, 1, 1, 1);

        @NotNull Vector3D result = vector3D.multiply(3);

        assertEquals(vector3D.x * 3, result.x);
        assertEquals(vector3D.y * 3, result.y);
        assertEquals(vector3D.z * 3, result.z);
        assertEquals(vector3D.w, result.w);
    }

    @Test
    public void testAdd() {
        @NotNull Vector3D vector3D = new Vector3D(1, 1, 1, 1);
        @NotNull Vector3D amount = new Vector3D(1, 1, 1, 1);

        @NotNull Vector3D result = vector3D.add(amount);

        assertEquals(vector3D.x + amount.x, result.x);
        assertEquals(vector3D.y + amount.y, result.y);
        assertEquals(vector3D.z + amount.z, result.z);
        assertEquals(vector3D.w, result.w);
    }
}
