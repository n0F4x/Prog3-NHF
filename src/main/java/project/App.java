package project;

import org.jetbrains.annotations.NotNull;

/**
 * The root of the application
 */
public class App {
    public static final @NotNull ResourceManager resourceManager = new ResourceManager();
    public static final @NotNull Window window = new Window();
    private static final @NotNull Engine engine = new Engine();
    public static final int UPS = 60;


    /**
     * Updates and repaints the window {@value App#UPS} times per second
     */
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

    /**
     * Runs the application
     */
    public static void run() {
        engine.addSystem(App::refresh);

        window.setVisible(true);
        engine.run();
    }

    public static void main(String[] args) {
        App.run();
    }
}
