package project;

public class App {
    public final static DataBase database = new DataBase();
    public final static Window window = new Window();


    public static void run() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        App.run();
    }
}
