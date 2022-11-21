package project;

public class App {
    private final Window window = new Window(new ResourceManager());


    public void run() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();

        app.run();
    }
}
