package project;

import org.jetbrains.annotations.NotNull;
import project.views.Room;
import project.views.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The window of the application
 */
public class Window extends JFrame {
    private final @NotNull Runnable updateSystem;


    /**
     * Constructs a new window
     */
    public Window() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
        setIgnoreRepaint(true);

        getContentPane().setLayout(new CardLayout());
        @NotNull Room room = new Room();
        add(room, "Room");
        add(new Settings(), "Settings");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                App.resourceManager.save();
            }
        });

        updateSystem = room::update;
    }

    /**
     * Switches the active "root" panel
     * @param name name of the panel
     */
    public void switchView(@NotNull String name) {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), name);
    }

    /**
     * Updates the contents
     */
    public void update() {
        updateSystem.run();
    }
}
