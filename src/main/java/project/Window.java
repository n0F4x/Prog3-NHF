package project;

import project.views.Room;
import project.views.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {
    private final Room room = new Room();


    public Window() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
        setIgnoreRepaint(true);

        getContentPane().setLayout(new CardLayout());
        add(room, "Room");
        add(new Settings(), "Settings");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                App.resourceManager.save();
            }
        });
    }

    public void switchView(String name) {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), name);
    }

    public void update() {
        if (room.isVisible()) {
            room.update();
        }
    }
}
