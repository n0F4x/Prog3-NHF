package project.views;

import project.App;
import project.models.Camera;
import project.views.components.BackButton;
import project.views.components.FOVScroller;
import project.views.components.QuitButton;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    public Settings() {
        setBackground(Color.BLACK);
        setFocusable(true);

        // TODO: Finish layout
        setLayout(null);

        add(new BackButton(this::getParent));
        add(new FOVScroller(App.database.resources.camera.getFOV(), Camera.minFOV, Camera.maxFOV, App.database.resources.camera::setFOV), FlowLayout.CENTER);
        add(new QuitButton(), FlowLayout.RIGHT);
    }
}
