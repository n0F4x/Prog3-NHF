package project.panels;

import project.ResourceManager;
import project.Window;
import project.room.Crosshair;
import project.room.Engine;
import project.math.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class RoomPanel extends JPanel {
    public class RoomFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            setCursor(Window.blankCursor);
            oldMousePosition = MouseInfo.getPointerInfo().getLocation();
            if (robotEnabled) {
                Point center = new Point((int) (getSize().getWidth() / 2.0) + getLocation().x, (int) (getSize().getHeight() / 2.0) + getLocation().y);
                robot.mouseMove(center.x, center.y);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            setCursor(Window.defaultCursor);
            if (robotEnabled) {
                robot.mouseMove(oldMousePosition.x, oldMousePosition.y);
            }
        }
    }
    public class RoomComponentListener extends ComponentAdapter {
        @Override
        public void componentShown(ComponentEvent e) {
            engine.recalculateGeometry();
            repaint();
            requestFocus();
        }
    }
    public class KeyEventListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_ESCAPE -> ((RootPanel) getParent()).switchLayout("Settings");
                case KeyEvent.VK_W -> engine.moveCamera(Direction.Forward);
                case KeyEvent.VK_A -> engine.moveCamera(Direction.Left);
                case KeyEvent.VK_S -> engine.moveCamera(Direction.Backward);
                case KeyEvent.VK_D -> engine.moveCamera(Direction.Right);
                case KeyEvent.VK_SPACE -> engine.moveCamera(Direction.Up);
                case KeyEvent.VK_SHIFT -> engine.moveCamera(Direction.Down);
            }
            repaint();
        }
    }
    public class MouseEventListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            if (isFocusOwner()) {
                Point center = new Point((int) (getSize().getWidth() / 2.0) + getLocation().x, (int) (getSize().getHeight() / 2.0) + getLocation().y);
                engine.rotateCamera(new Point2D.Double(mouseEvent.getLocationOnScreen().getX() - center.getX(), mouseEvent.getLocationOnScreen().getY() - center.getY()));
                repaint();
                if (robotEnabled) {
                    robot.mouseMove(center.x + getLocationOnScreen().x, center.y + getLocationOnScreen().y);
                }
            }
        }
    }


    private final Engine engine;
    private final Crosshair crosshair = new Crosshair();
    private Robot robot;
    private boolean robotEnabled = true;
    private Point oldMousePosition = new Point();


    public RoomPanel(ResourceManager resourceManager) {
        engine = new Engine(resourceManager);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            robotEnabled = false;
        }

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

        engine.getPerspective().paint(graphics);
        crosshair.paint(graphics);
    }
}
