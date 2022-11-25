package project;

import project.views.Room;
import project.views.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {
    private final JPanel panel = new JPanel(new CardLayout());


    public Window() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        panel.add(new Room(), "Room");
        panel.add(new Settings(), "Settings");
        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                App.database.save();
            }
        });
    }

    public void switchView(String name) {
        ((CardLayout) panel.getLayout()).show(panel, name);
    }
}
