package project.views;

import org.jetbrains.annotations.NotNull;
import project.Window;
import project.utils.tools.Cursor;
import project.views.components.Crosshair;
import project.utils.math.Direction;
import project.views.components.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Room extends JPanel {
    public class RoomFocusListener implements FocusListener {
        @Override
        public void focusGained(@NotNull FocusEvent e) {
            setCursor(Cursor.getBlankCursor());
            oldMousePosition = MouseInfo.getPointerInfo().getLocation();
            if (robot != null) {
                @NotNull Point center = new Point((int) (getSize().getWidth() / 2.0) + getLocation().x, (int) (getSize().getHeight() / 2.0) + getLocation().y);
                robot.mouseMove(center.x, center.y);
            }
            focused = true;
        }

        @Override
        public void focusLost(@NotNull FocusEvent e) {
            setCursor(Cursor.getDefaultCursor());
            if (robot != null) {
                robot.mouseMove(oldMousePosition.x, oldMousePosition.y);
            }
            focused = false;
        }
    }

    public class RoomComponentListener extends ComponentAdapter {
        @Override
        public void componentShown(@NotNull ComponentEvent componentEvent) {
            requestFocus();
        }
    }

    public class KeyEventListener extends KeyAdapter {
        @Override
        public void keyPressed(@NotNull KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_ESCAPE -> ((Window) getTopLevelAncestor()).switchView("Settings");
                case KeyEvent.VK_W -> controller.beginMovement(Direction.Forward);
                case KeyEvent.VK_A -> controller.beginMovement(Direction.Left);
                case KeyEvent.VK_S -> controller.beginMovement(Direction.Backward);
                case KeyEvent.VK_D -> controller.beginMovement(Direction.Right);
                case KeyEvent.VK_SPACE -> controller.beginMovement(Direction.Up);
                case KeyEvent.VK_SHIFT -> controller.beginMovement(Direction.Down);
            }
        }

        @Override
        public void keyReleased(@NotNull KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_ESCAPE -> ((Window) getTopLevelAncestor()).switchView("Settings");
                case KeyEvent.VK_W -> controller.stopMovement(Direction.Forward);
                case KeyEvent.VK_A -> controller.stopMovement(Direction.Left);
                case KeyEvent.VK_S -> controller.stopMovement(Direction.Backward);
                case KeyEvent.VK_D -> controller.stopMovement(Direction.Right);
                case KeyEvent.VK_SPACE -> controller.stopMovement(Direction.Up);
                case KeyEvent.VK_SHIFT -> controller.stopMovement(Direction.Down);
            }
        }
    }

    private final @NotNull project.controllers.Room controller = new project.controllers.Room();
    private final @NotNull Perspective perspective = new Perspective(controller.getRoom(), controller.getCamera());
    private final @NotNull Crosshair crosshair = new Crosshair();
    private Robot robot;
    private boolean focused = false;
    private @NotNull Point oldMousePosition = new Point();


    public Room() {
        try {
            robot = new Robot();
        } catch (AWTException ignored) {
        }

        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyEventListener());
        addComponentListener(new RoomComponentListener());
        addFocusListener(new RoomFocusListener());
    }

    @Override
    public void paintComponent(@NotNull Graphics graphics) {
        super.paintComponent(graphics);

        perspective.paint(graphics);
        crosshair.paint(graphics);
    }

    public void update() {
        if (focused && hasFocus()) {
            controller.moveCamera();

            @NotNull Point center = new Point((int) (getSize().getWidth() / 2.0) + getLocation().x, (int) (getSize().getHeight() / 2.0) + getLocation().y);
            controller.rotateCamera(new Point2D.Double(MouseInfo.getPointerInfo().getLocation().getX() - center.getX(), MouseInfo.getPointerInfo().getLocation().getY() - center.getY()));
            if (robot != null) {
                try {
                    robot.mouseMove(center.x + getLocationOnScreen().x, center.y + getLocationOnScreen().y);
                } catch (IllegalComponentStateException ignored) {
                }
            }
        }

        perspective.update(SwingUtilities.getWindowAncestor(this).getSize());
    }
}
