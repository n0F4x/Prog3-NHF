package project.room;

import project.Window;

import java.awt.*;
import java.awt.geom.Line2D;

public class Crosshair {
    private final double size = Math.min(Window.screenSize.width, Window.screenSize.height) / 100.0;
    private final Line2D horizontalLine = new Line2D.Double(
            Window.screenSize.width / 2.0 - size,
            Window.screenSize.height / 2.0,
            Window.screenSize.width / 2.0 + size,
            Window.screenSize.height / 2.0
    );
    private final Line2D verticalLine = new Line2D.Double(
            Window.screenSize.width / 2.0,
            Window.screenSize.height / 2.0 - size,
            Window.screenSize.width / 2.0,
            Window.screenSize.height / 2.0 + size
    );


    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GRAY);
        graphics2D.draw(horizontalLine);
        graphics2D.draw(verticalLine);
    }
}
