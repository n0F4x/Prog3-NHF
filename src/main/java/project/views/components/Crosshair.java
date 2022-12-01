package project.views.components;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Component class for cross-hairs
 */
public class Crosshair {
    private final @NotNull Line2D horizontalLine;
    private final @NotNull Line2D verticalLine;


    /**
     * Constructs a new {@code Crosshair}
     */
    public Crosshair() {
        @NotNull Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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

    /**
     * Paints the new crosshair to the screen
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paint(@NotNull Graphics graphics) {
        @NotNull Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GRAY);
        graphics2D.draw(horizontalLine);
        graphics2D.draw(verticalLine);
    }
}
