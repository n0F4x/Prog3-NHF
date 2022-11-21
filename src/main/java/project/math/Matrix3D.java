package project.math;

// https://github.com/titandino/3d-projection/blob/master/src/com/proj/math/Matrix3D.java

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Matrix3D {
    private final double[][] matrix;


    public Matrix3D() {
        matrix = buildIdentityMatrix().matrix;
    }

    public Matrix3D(double[][] matrix) {
        this.matrix = matrix.clone();
    }

    @Contract(" -> new")
    public static @NotNull Matrix3D buildIdentityMatrix() {
        return new Matrix3D(new double[][] {
                { 1, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 1 }
        });
    };

    @Contract("_, _, _, _ -> new")
    public static @NotNull Matrix3D buildProjectionMatrix(double FOV, double aspect, double n, double f) {
        double tanHalfFOV = Math.tan(FOV / 2);
        double zRange = n - f;

        double[][] matrix = new double[4][4];

        matrix[0][0] = 1.0 / (tanHalfFOV * aspect);
        matrix[1][1] = 1.0 / tanHalfFOV;
        matrix[2][2] = (-n - f) / zRange;
        matrix[3][2] = 2 * f * n / zRange;
        matrix[2][3] = 1;

        return new Matrix3D(matrix);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Matrix3D buildMappingMatrix(@NotNull Vector3D x, @NotNull Vector3D y, @NotNull Vector3D z) {
        double[][] matrix = new double[4][4];

        matrix[0][0] = x.x;
        matrix[0][1] = x.y;
        matrix[0][2] = x.z;
        matrix[0][3] = x.w;
        matrix[1][0] = y.x;
        matrix[1][1] = y.y;
        matrix[1][2] = y.z;
        matrix[1][3] = y.w;
        matrix[2][0] = z.x;
        matrix[2][1] = z.y;
        matrix[2][2] = z.z;
        matrix[2][3] = z.w;
        matrix[3][0] = 0;
        matrix[3][1] = 0;
        matrix[3][2] = 0;
        matrix[3][3] = 1;

        return new Matrix3D(matrix);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Matrix3D buildTranslationMatrix(double tX, double tY, double tZ) {
        double[][] matrix = buildIdentityMatrix().matrix;

        matrix[0][3] = tX;
        matrix[1][3] = tY;
        matrix[2][3] = tZ;

        return new Matrix3D(matrix);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Matrix3D buildScaleMatrix(double sX, double sY, double sZ) {
        double[][] matrix = new double[4][4];

        matrix[0][0] = sX;
        matrix[1][1] = sY;
        matrix[2][2] = sZ;
        matrix[3][3] = 1;

        return new Matrix3D(matrix);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3D buildRotationXMatrix(double rX) {
        double[][] matrix = new double[4][4];

        matrix[0][0] = matrix[3][3] = 1;
        matrix[1][1] = matrix[2][2] = Math.cos(rX * Math.PI / 180);

        double sine = Math.sin(rX * Math.PI / 180);
        matrix[2][1] = sine;
        matrix[1][2] = -sine;

        return new Matrix3D(matrix);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3D buildRotationYMatrix(double rY) {
        double[][] matrix = new double[4][4];

        matrix[1][1] = matrix[3][3] = 1;
        matrix[0][0] = matrix[2][2] = Math.cos(rY * Math.PI / 180);

        double sine = Math.sin(rY * Math.PI / 180);
        matrix[0][2] = sine;
        matrix[2][0] = -sine;

        return new Matrix3D(matrix);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3D buildRotationZMatrix(double rZ) {
        double[][] matrix = new double[4][4];

        matrix[2][2] = matrix[3][3] = 1;
        matrix[0][0] = matrix[1][1] = Math.cos(rZ * Math.PI / 180);

        double sine = Math.sin(rZ * Math.PI / 180);
        matrix[1][0] = sine;
        matrix[0][1] = -sine;

        return new Matrix3D(matrix);
    }

    @Contract("_, _ -> new")
    public static @NotNull Matrix3D concat(@NotNull Matrix3D lhs, @NotNull Matrix3D rhs) {
        double[][] result = buildIdentityMatrix().matrix;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                double temp = 0;
                for (int i = 0; i < 4; i++) {
                    temp += lhs.matrix[row][i] * rhs.matrix[i][col];
                }
                result[row][col] = temp;
            }
        }

        return new Matrix3D(result);
    }

    @Contract("_, _ -> new")
    public static @NotNull Vector3D concat(@NotNull Matrix3D lhs, @NotNull Vector3D rhs) {
        Vector3D result = new Vector3D();

        result.x = lhs.matrix[0][0] * rhs.x + lhs.matrix[0][1] * rhs.y + lhs.matrix[0][2] * rhs.z + lhs.matrix[0][3] * rhs.w;
        result.y = lhs.matrix[1][0] * rhs.x + lhs.matrix[1][1] * rhs.y + lhs.matrix[1][2] * rhs.z + lhs.matrix[1][3] * rhs.w;
        result.z = lhs.matrix[2][0] * rhs.x + lhs.matrix[2][1] * rhs.y + lhs.matrix[2][2] * rhs.z + lhs.matrix[2][3] * rhs.w;
        result.w = lhs.matrix[3][0] * rhs.x + lhs.matrix[3][1] * rhs.y + lhs.matrix[3][2] * rhs.z + lhs.matrix[3][3] * rhs.w;

        return result;
    }
}
