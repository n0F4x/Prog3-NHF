package project.models;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import project.utils.math.Vector3D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraTest {
    @Test
    public void testClone() {
        @NotNull Camera camera = new Camera();

        assertEquals(camera, camera.clone());
    }

    @Test
    public void testFOV() {
        @NotNull Camera camera = new Camera();

        camera.setFOV(Camera.minFOV - 1);
        assertEquals(Camera.minFOV, camera.getFOV());

        camera.setFOV(Camera.maxFOV + 1);
        assertEquals(Camera.maxFOV, camera.getFOV());
    }

    @Test
    public void testMove() {
        @NotNull Camera camera = new Camera();
        @NotNull Vector3D pos1 = new Vector3D(camera.position);

        @NotNull Vector3D amount = new Vector3D(1, 1, 1, 1);

        camera.move(amount);
        assertEquals(pos1.add(amount), camera.position);
    }

    @Test
    public void testRotate() {
        @NotNull Camera camera = new Camera();
        @NotNull Vector3D rot1 = new Vector3D(camera.rotation);

        @NotNull Vector3D amount = new Vector3D(1, 1, 0, 1);

        camera.rotate(amount);
        assertEquals(rot1.add(amount), camera.rotation);
    }
}
