package project;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import project.models.Camera;
import project.models.Room;

import java.io.*;

public class DataBase {
    public static class Resources {
        public final Camera camera = new Camera();
        public final Room room = new Room();
    }

    private final File settingsFile = new File("src/main/resources/settings.toml");
    public final Resources resources = new Toml().read(settingsFile).to(Resources.class);


    public void save() {
        TomlWriter tomlWriter = new TomlWriter.Builder()
                .padArrayDelimitersBy(1)
                .build();

        try {
            tomlWriter.write(resources, settingsFile);
        } catch (IOException ignored) {
            System.out.println("Failed saving resources to file");
        }
    }
}
