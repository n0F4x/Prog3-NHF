package project.utils.math;

// https://github.com/titandino/3d-projection/blob/master/src/com/proj/math/Matrix3D.java

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Matrix3D {
    private final double[][] matrix;


    public Matrix3D(double[][] matrix) {
        this.matrix = matrix.clone();
    }

    @Contract(" -> new")
    public static @NotNull Matrix3D buildIdentityMatrix() {
        return new Matrix3D(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull Matrix3D buildPerspectiveMatrix(double FOV, double aspectRatio, double near, double far) {
        double tanHalfFOV = Math.tan(FOV * Math.PI / 180.0 / 2.0);

        double[][] matrix = new double[4][4];

        matrix[0][0] = 1.0 / (aspectRatio * tanHalfFOV);
        matrix[1][1] = 1.0 / tanHalfFOV;
        matrix[2][2] = (near + far) / (near - far);
        matrix[3][2] = 2 * far * near / (near - far);
        matrix[2][3] = -1;

        return new Matrix3D(matrix);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3D buildTranslationMatrix(@NotNull Vector3D vector) {
        double[][] matrix = buildIdentityMatrix().matrix;

        matrix[0][3] = vector.x;
        matrix[1][3] = vector.y;
        matrix[2][3] = vector.z;

        return new Matrix3D(matrix);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3D buildRotationXMatrix(double rX) {
        double[][] matrix = new double[4][4];

        matrix[0][0] = matrix[3][3] = 1;
        matrix[1][1] = matrix[2][2] = Math.cos(rX);

        double sine = Math.sin(rX);
        matrix[2][1] = sine;
        matrix[1][2] = -sine;

        return new Matrix3D(matrix);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3D buildRotationYMatrix(double rY) {
        double[][] matrix = new double[4][4];

        matrix[1][1] = matrix[3][3] = 1;
        matrix[0][0] = matrix[2][2] = Math.cos(rY);

        double sine = Math.sin(rY);
        matrix[0][2] = -sine;
        matrix[2][0] = sine;

        return new Matrix3D(matrix);
    }

    @Contract("_ -> new")
    public static @NotNull Matrix3D buildRotationZMatrix(double rZ) {
        double[][] matrix = new double[4][4];

        matrix[2][2] = matrix[3][3] = 1;
        matrix[0][0] = matrix[1][1] = Math.cos(rZ);

        double sine = Math.sin(rZ * Math.PI / 180);
        matrix[1][0] = sine;
        matrix[0][1] = -sine;

        return new Matrix3D(matrix);
    }

    public static @NotNull Matrix3D buildRotationMatrix(@NotNull Vector3D rotation) {
        return Matrix3D.buildRotationXMatrix(rotation.x)
                .concat(Matrix3D.buildRotationYMatrix(rotation.y))
                .concat(Matrix3D.buildRotationZMatrix(rotation.z));
    }

    @Contract("_ -> new")
    public @NotNull Matrix3D concat(@NotNull Matrix3D rhs) {
        double[][] result = buildIdentityMatrix().matrix;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                double temp = 0;
                for (int i = 0; i < 4; i++) {
                    temp += matrix[row][i] * rhs.matrix[i][col];
                }
                result[row][col] = temp;
            }
        }

        return new Matrix3D(result);
    }

    @Contract("_ -> new")
    public @NotNull Vector3D concat(@NotNull Vector3D rhs) {
        Vector3D result = new Vector3D();

        result.x = matrix[0][0] * rhs.x + matrix[0][1] * rhs.y + matrix[0][2] * rhs.z + matrix[0][3] * rhs.w;
        result.y = matrix[1][0] * rhs.x + matrix[1][1] * rhs.y + matrix[1][2] * rhs.z + matrix[1][3] * rhs.w;
        result.z = matrix[2][0] * rhs.x + matrix[2][1] * rhs.y + matrix[2][2] * rhs.z + matrix[2][3] * rhs.w;
        result.w = matrix[3][0] * rhs.x + matrix[3][1] * rhs.y + matrix[3][2] * rhs.z + matrix[3][3] * rhs.w;

        return result;
    }

    /**
     * Returns new Vector3D() either if no solution or infinite solutions exist
     */
    public @NotNull Vector3D eliminate(@NotNull Vector3D rhs) {
        double[][] result = {
                { matrix[0][0], matrix[0][1], matrix[0][2], matrix[0][3], rhs.x },
                { matrix[1][0], matrix[1][1], matrix[1][2], matrix[1][3], rhs.y },
                { matrix[2][0], matrix[2][1], matrix[2][2], matrix[2][3], rhs.z },
                { matrix[3][0], matrix[3][1], matrix[3][2], matrix[3][3], rhs.w }
        };
        int i, j, k, c;

        // Performing elementary operations
        for (i = 0; i < 4; i++) {
            if (result[i][i] == 0) {
                c = 1;
                while ((i + c) < 4 && result[i + c][i] == 0)
                    c++;
                if ((i + c) == 4) {
                    return new Vector3D();
                }
                for (j = i, k = 0; k <= 4; k++) {
                    double temp = result[j][k];
                    result[j][k] = result[j + c][k];
                    result[j + c][k] = temp;
                }
            }

            for (j = 0; j < 4; j++) {

                // Excluding all i == j
                if (i != j) {

                    // Converting Matrix to reduced row
                    // echelon form(diagonal matrix)
                    double p = result[j][i] / result[i][i];

                    for (k = 0; k <= 4; k++)
                        result[j][k] = result[j][k] - (result[i][k]) * p;
                }
            }
        }

        return new Vector3D(
                result[0][4] / result[0][0],
                result[1][4] / result[1][1],
                result[2][4] / result[2][2],
                1
        );
    }

    public @NotNull Matrix3D transpose() {
        return new Matrix3D(new double[][] {
                { matrix[0][0], matrix[1][0], matrix[2][0], matrix[3][0] },
                { matrix[0][1], matrix[1][1], matrix[2][1], matrix[3][1] },
                { matrix[0][2], matrix[1][2], matrix[2][2], matrix[3][2] },
                { matrix[0][3], matrix[1][3], matrix[2][3], matrix[3][3] }
        });
    }
}
