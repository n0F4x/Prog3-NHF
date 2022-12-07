package project.views;

import org.jetbrains.annotations.NotNull;
import project.App;
import project.models.Camera;
import project.views.components.BackButton;
import project.views.components.CustomScrollBar;
import project.views.components.QuitButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * The view of the settings of the room
 */
public class Settings extends JPanel {
    /**
     * Constructs a new Settings object
     */
    public Settings() {
        setBackground(Color.BLACK);
        setFocusable(true);

        setLayout(null);

        @NotNull BackButton backButton = new BackButton(this::getParent);
        add(backButton);

        @NotNull QuitButton quitButton = new QuitButton();
        quitButton.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - quitButton.getWidth(), 0);
        add(quitButton);

        @NotNull Font labelFont;
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

        @NotNull Font valueFont;
        try {
            valueFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/NotoSerifKhojki-VariableFont.ttf")).deriveFont(35.0f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        @NotNull JLabel value = new JLabel(Integer.toString(App.resourceManager.camera.getFOV()));
        value.setForeground(new Color(255, 255, 255, 233));
        value.setFont(valueFont);
        value.setSize(value.getPreferredSize());
        add(value);

        @NotNull CustomScrollBar customScrollBar = new CustomScrollBar(App.resourceManager.camera.getFOV(), Camera.minFOV, Camera.maxFOV, App.resourceManager.camera::setFOV);
        customScrollBar.setLocation(label.getX() + label.getWidth(), label.getY());
        customScrollBar.setSize(quitButton.getX() - label.getX() - label.getWidth(), label.getHeight());
        customScrollBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                value.setText(String.valueOf(customScrollBar.getValue()));
                value.setSize(value.getPreferredSize());
                value.setLocation(
                        (int) (customScrollBar.getLocationOnScreen().x + ((float)(customScrollBar.getValue() - Camera.minFOV) / (Camera.maxFOV - Camera.minFOV + 8)) * customScrollBar.getWidth() + 30 - value.getWidth() / 2),
                        customScrollBar.getLocationOnScreen().y - value.getHeight()
                );
            }
        });
        add(customScrollBar);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                value.setText(String.valueOf(customScrollBar.getValue()));
                value.setSize(value.getPreferredSize());
                value.setLocation(
                        (int) (customScrollBar.getLocationOnScreen().x + ((float)(customScrollBar.getValue() - Camera.minFOV) / (Camera.maxFOV - Camera.minFOV + 8)) * customScrollBar.getWidth() + 30 - value.getWidth() / 2),
                        customScrollBar.getLocationOnScreen().y - value.getHeight()
                );
            }
        });
    }
}
