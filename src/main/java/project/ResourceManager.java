package project;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import org.jetbrains.annotations.NotNull;
import project.models.Camera;
import project.models.Room;

import java.io.*;

public class ResourceManager {
    private final @NotNull Toml tomlReader = new Toml();
    private final @NotNull File cameraFile = new File("src/main/resources/camera.toml");
    public final @NotNull Camera camera = tomlReader.read(cameraFile).to(Camera.class);
    private final @NotNull File roomFile = new File("src/main/resources/room.toml");
    public final @NotNull Room room = tomlReader.read(roomFile).to(Room.class);


    public void save() {
        @NotNull TomlWriter tomlWriter = new TomlWriter.Builder()
                .padArrayDelimitersBy(1)
                .build();

        camera.rotation.x %= 360;
        camera.rotation.y %= 360;
        try {
            tomlWriter.write(camera, cameraFile);
        } catch (IOException ignored) {
            System.out.println("Failed saving resources to file");
        }
    }
}
