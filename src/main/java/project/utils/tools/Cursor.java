package project.utils.tools;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cursor {
    private static final @NotNull java.awt.Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
    private static final @NotNull java.awt.Cursor defaultCursor = new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR);


    public static java.awt.@NotNull Cursor getBlankCursor() {
        return blankCursor;
    }

    public static java.awt.@NotNull Cursor getDefaultCursor() {
        return defaultCursor;
    }
}
