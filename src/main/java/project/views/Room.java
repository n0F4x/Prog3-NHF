package project.views;

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
        public void focusGained(FocusEvent e) {
            setCursor(Cursor.getBlankCursor());
            oldMousePosition = MouseInfo.getPointerInfo().getLocation();
            if (robot != null) {
                Point center = new Point((int) (getSize().getWidth() / 2.0) + getLocation().x, (int) (getSize().getHeight() / 2.0) + getLocation().y);
                robot.mouseMove(center.x, center.y);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            setCursor(Cursor.getDefaultCursor());
            if (robot != null) {
                robot.mouseMove(oldMousePosition.x, oldMousePosition.y);
            }
        }
    }
    public class RoomComponentListener extends ComponentAdapter {
        @Override
        public void componentShown(ComponentEvent componentEvent) {
            perspective.update(SwingUtilities.getWindowAncestor((Component) componentEvent.getSource()).getSize());
            repaint();
            requestFocus();
        }
    }
    public class KeyEventListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_ESCAPE -> ((Window) getTopLevelAncestor()).switchView("Settings");
                case KeyEvent.VK_W -> controller.moveCamera(Direction.Forward);
                case KeyEvent.VK_A -> controller.moveCamera(Direction.Left);
                case KeyEvent.VK_S -> controller.moveCamera(Direction.Backward);
                case KeyEvent.VK_D -> controller.moveCamera(Direction.Right);
                case KeyEvent.VK_SPACE -> controller.moveCamera(Direction.Up);
                case KeyEvent.VK_SHIFT -> controller.moveCamera(Direction.Down);
            }
            perspective.update(SwingUtilities.getWindowAncestor((Component) keyEvent.getSource()).getSize());
            repaint();
        }
    }
    public class MouseEventListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            if (isFocusOwner()) {
                Point center = new Point((int) (getSize().getWidth() / 2.0) + getLocation().x, (int) (getSize().getHeight() / 2.0) + getLocation().y);
                controller.rotateCamera(new Point2D.Double(mouseEvent.getLocationOnScreen().getX() - center.getX(), mouseEvent.getLocationOnScreen().getY() - center.getY()));
                perspective.update(SwingUtilities.getWindowAncestor((Component) mouseEvent.getSource()).getSize());
                repaint();
                if (robot != null) {
                    robot.mouseMove(center.x + getLocationOnScreen().x, center.y + getLocationOnScreen().y);
                }
            }
        }
    }

    private final project.controllers.Room controller = new project.controllers.Room();
    private final Perspective perspective = new Perspective(controller.getRoom(), controller.getCamera());
    private final Crosshair crosshair = new Crosshair();
    private Robot robot;
    private Point oldMousePosition = new Point();


    public Room() {
        try {
            robot = new Robot();
        } catch (AWTException ignored) {}

        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyEventListener());
        addMouseMotionListener(new MouseEventListener());
        addComponentListener(new RoomComponentListener());
        addFocusListener(new RoomFocusListener());
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        perspective.paint(graphics);
        crosshair.paint(graphics);
    }
}
