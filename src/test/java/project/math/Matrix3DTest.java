package project.math;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import project.utils.math.Matrix3D;
import project.utils.math.Vector3D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Matrix3DTest {
    @Test
    public void concatVectorTest() {
        @NotNull Matrix3D matrix3D = new Matrix3D(new double[][] {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        });
        @NotNull Vector3D vector3D = new Vector3D(1, 1, 1, 1);

        assertEquals(new Vector3D(4, 4, 4, 4), matrix3D.concat(vector3D));
    }

    @Test
    public void identityTest() {
        @NotNull Matrix3D matrix3D = Matrix3D.buildIdentityMatrix();

        assertEquals(new Vector3D(1, 1, 1, 1), matrix3D.concat(new Vector3D(1, 1, 1, 1)));
    }

    @Test
    public void transposeTest() {
        @NotNull Matrix3D matrix3D = new Matrix3D(new double[][] {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11},
                {12, 13, 14, 15}
        });

        @NotNull Matrix3D result = matrix3D.transpose();

        assertEquals(new Matrix3D(new double[][] {
                {0, 4, 8, 12},
                {1, 5, 9, 13},
                {2, 6, 10, 14},
                {3, 7, 11, 15}
        }), result);
    }

    @Test
    public void eliminationTest() {
        @NotNull Matrix3D matrix3D = new Matrix3D(new double[][] {
                {1, 2, -1, 1},
                {-1, 1, 2, -1},
                {2, -1, 2, 2},
                {1, 1, -1, 2}
        });

        @NotNull Vector3D result = matrix3D.eliminate(new Vector3D(6, 3, 14, 8));

        assertEquals(1.0, result.x, 0.0000000001);
        assertEquals(2.0, result.y, 0.0000000001);
        assertEquals(3.0, result.z, 0.0000000001);
        assertEquals(1.0, result.w, 0.0000000001);
    }
}
