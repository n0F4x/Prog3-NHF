package project;

import org.jetbrains.annotations.NotNull;

public class App {
    public final static @NotNull ResourceManager resourceManager = new ResourceManager();
    public final static @NotNull Window window = new Window();
    private final static @NotNull Engine engine = new Engine();
    public static final int UPS = 60;


    private static void refresh() {
        long lastLoopTime = System.currentTimeMillis();
        while (window.isVisible()) {
            window.update();
            window.repaint();

            long currentLoopTime = System.currentTimeMillis();
            long sleepTime = 1000 / UPS - (currentLoopTime - lastLoopTime);
            if (sleepTime > 1) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lastLoopTime = currentLoopTime;
        }
    }

    public static void run() {
        engine.addSystem(App::refresh);

        window.setVisible(true);
        engine.run();
    }

    public static void main(String[] args) {
        App.run();
    }
}
