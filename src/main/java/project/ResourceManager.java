package project;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import project.models.Camera;
import project.models.Room;

import java.io.*;

public class ResourceManager {
    private final Toml tomlReader = new Toml();
    private final File cameraFile = new File("src/main/resources/camera.toml");
    public final Camera camera = tomlReader.read(cameraFile).to(Camera.class);
    private final File roomFile = new File("src/main/resources/room.toml");
    public final Room room = tomlReader.read(roomFile).to(Room.class);


    public void save() {
        TomlWriter tomlWriter = new TomlWriter.Builder()
                .padArrayDelimitersBy(1)
                .build();

        try {
            tomlWriter.write(camera, cameraFile);
        } catch (IOException ignored) {
            System.out.println("Failed saving resources to file");
        }
    }
}
