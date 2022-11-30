package project;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class Engine {
    private final @NotNull ArrayList<@NotNull Runnable> systems = new ArrayList<>();


    public void addSystem(@NotNull Runnable system) {
        systems.add(system);
    }

    public void run() {
        for (@NotNull Runnable system : systems) {
            new Thread(system).start();
        }
    }
}
