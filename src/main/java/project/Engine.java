package project;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Used to run tasks
 */
public class Engine {
    private final @NotNull ArrayList<@NotNull Runnable> systems = new ArrayList<>();


    /**
     * @param system function to be run
     */
    public void addSystem(@NotNull Runnable system) {
        systems.add(system);
    }

    /**
     * Runs the added systems in their separate threads
     */
    public void run() {
        for (@NotNull Runnable system : systems) {
            new Thread(system).start();
        }
    }
}
