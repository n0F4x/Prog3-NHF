package project.views.components;

import java.awt.*;
import java.awt.geom.Line2D;

public class Crosshair {
    private final Line2D horizontalLine;
    private final Line2D verticalLine;


    public Crosshair() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double size = Math.min(screenSize.width, screenSize.height) / 100.0;

        horizontalLine = new Line2D.Double(
                screenSize.width / 2.0 - size,
                screenSize.height / 2.0,
                screenSize.width / 2.0 + size,
                screenSize.height / 2.0
        );

        verticalLine = new Line2D.Double(
                screenSize.width / 2.0,
                screenSize.height / 2.0 - size,
                screenSize.width / 2.0,
                screenSize.height / 2.0 + size
        );
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GRAY);
        graphics2D.draw(horizontalLine);
        graphics2D.draw(verticalLine);
    }
}
