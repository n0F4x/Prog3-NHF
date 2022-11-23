package project;

import project.panels.RootPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
    public static final Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);


    public Window(ResourceManager resourceManager) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        RepaintManager.currentManager(this).setDoubleBufferingEnabled(true);

        add(new RootPanel(resourceManager));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                resourceManager.save();
            }
        });
    }
}
