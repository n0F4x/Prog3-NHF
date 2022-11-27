package project;

import java.util.ArrayList;
public class Engine {
    private final ArrayList<Runnable> systems = new ArrayList<>();


    public void addSystem(Runnable system) {
        systems.add(system);
    }

    public void run() {
        for (Runnable system : systems) {
            new Thread(system).start();
        }
    }
}
