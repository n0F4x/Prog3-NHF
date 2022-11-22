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
        }

        @Override
        public void focusLost(FocusEvent e) {
            setCursor(Window.defaultCursor);
            robot.mouseMove(oldMousePosition.x, oldMousePosition.y);
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
                case KeyEvent.VK_Q -> engine.moveCamera(Direction.Up);
                case KeyEvent.VK_W -> engine.moveCamera(Direction.Forward);
                case KeyEvent.VK_E -> engine.moveCamera(Direction.Down);
                case KeyEvent.VK_A -> engine.moveCamera(Direction.Left);
                case KeyEvent.VK_S -> engine.moveCamera(Direction.Backward);
                case KeyEvent.VK_D -> engine.moveCamera(Direction.Right);
            }
        }
    }
    public class MouseEventListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            if (isFocusOwner()) {
                engine.rotateCamera(new Point2D.Double(mouseEvent.getLocationOnScreen().getX() - center.getX(), mouseEvent.getLocationOnScreen().getY() - center.getY()));
                repaint();
                if (robotEnabled) {
                    robot.mouseMove(center.x, center.y);
                }
            }
        }
    }


    private final Point center = new Point((int) (Window.screenSize.getWidth() / 2.0), (int) (Window.screenSize.getHeight() / 2.0));
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
    public void paint(Graphics graphics) {
        super.paint(graphics);

        engine.getPerspective().paint(graphics);
        crosshair.paint(graphics);
    }
}
