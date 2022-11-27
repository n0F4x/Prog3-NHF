package project;

public class App {
    public final static DataBase database = new DataBase();
    public final static Window window = new Window();
    private final static Engine engine = new Engine();


    private static void refresh() {
        int FPS = 60;
        long lastLoopTime = System.currentTimeMillis();
        while (window.isVisible()) {
            window.update();
            window.repaint();

            long sleepTime = 1000 / FPS - System.currentTimeMillis() - lastLoopTime;
            if (sleepTime > 1) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
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
