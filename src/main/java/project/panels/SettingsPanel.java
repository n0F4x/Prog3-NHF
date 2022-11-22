package project.panels;

import project.ResourceManager;
import project.components.BackButton;
import project.components.FOVScroller;
import project.components.QuitButton;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel(ResourceManager resourceManager) {
        setBackground(Color.BLACK);
        setFocusable(true);

        // TODO: Finish layout
        setLayout(null);

        add(new BackButton());
        add(new FOVScroller(resourceManager.resources.camera), FlowLayout.CENTER);
        add(new QuitButton(), FlowLayout.RIGHT);
    }
}
