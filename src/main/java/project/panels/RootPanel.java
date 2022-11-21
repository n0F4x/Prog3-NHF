package project.panels;

import project.ResourceManager;
import project.room.Engine;

import javax.swing.*;
import java.awt.*;

public class RootPanel extends JPanel {
    public RootPanel(ResourceManager resourceManager) {
        super(new CardLayout());

        add(new RoomPanel(resourceManager), "Room");
        add(new SettingsPanel(resourceManager), "Settings");
    }

    public void switchLayout(String name) {
        ((CardLayout) getLayout()).show(this, name);
    }
}
