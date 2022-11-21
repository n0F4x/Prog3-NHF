package project;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import project.room.Camera;
import project.room.Room;

import java.io.*;

public class ResourceManager {
    public static class Resources {
        public String title = "app settings";
        public Camera camera = new Camera();
        public Room room = new Room();
    }

    File settingsFile = new File("src/main/resources/settings.toml");
    public final Resources resources = new Toml().read(settingsFile).to(Resources.class);


    public void save() {
        TomlWriter tomlWriter = new TomlWriter.Builder()
                .padArrayDelimitersBy(1)
                .build();

        try {
            tomlWriter.write(resources, settingsFile);
        } catch (IOException ignored) {}
    }
}
