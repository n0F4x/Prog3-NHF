package project.views;

import org.jetbrains.annotations.NotNull;
import project.App;
import project.models.Camera;
import project.views.components.BackButton;
import project.views.components.CustomScrollBar;
import project.views.components.QuitButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Settings extends JPanel {
    public Settings() {
        setBackground(Color.BLACK);
        setFocusable(true);

        setLayout(null);

        @NotNull BackButton backButton = new BackButton(this::getParent);
        add(backButton);

        @NotNull QuitButton quitButton = new QuitButton();
        quitButton.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - quitButton.getWidth(), 0);
        add(quitButton);

        Font labelFont;
        try {
            labelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/MyScars.ttf")).deriveFont(60.0f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        @NotNull JLabel label = new JLabel("Field of view ");
        label.setFont(labelFont);
        label.setForeground(Color.LIGHT_GRAY);
        label.setSize(label.getPreferredSize());
        label.setLocation(
                backButton.getWidth(),
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - label.getHeight() / 2);
        add(label);

        @NotNull CustomScrollBar customScrollBar = new CustomScrollBar(App.resourceManager.camera.getFOV(), Camera.minFOV, Camera.maxFOV, App.resourceManager.camera::setFOV);
        customScrollBar.setLocation(label.getX() + label.getWidth(), label.getY());
        customScrollBar.setSize(quitButton.getX() - label.getX() - label.getWidth(), label.getHeight());
        add(customScrollBar);
    }
}
