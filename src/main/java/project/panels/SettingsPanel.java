package project.panels;

import project.ResourceManager;
import project.components.BackButton;
import project.components.FOVScroller;
import project.components.QuitButton;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel(ResourceManager resourceManager) {
        setBackground(Color.YELLOW);
        setFocusable(true);

        // TODO: Finish layout

        add(new BackButton());
        add(new FOVScroller(resourceManager.resources.camera));
        add(new QuitButton());
    }
}
