package project.views.UIs;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Custom UI for the FOV scroller
 */
public class CustomScrollBarUI extends BasicScrollBarUI {
    private static final int THUMB_SIZE = 60;
    private static final BufferedImage thumbImage;

    static {
        try {
            BufferedImage original = ImageIO.read(new File("src/main/resources/fish_eye_perspective_grid.png"));
            thumbImage = resizeImage(original, original.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void installUI(@NotNull JComponent component) {
        super.installUI(component);

        @NotNull JScrollBar scrollBar = (JScrollBar) component;

        scrollBar.setPreferredSize(new Dimension(THUMB_SIZE, THUMB_SIZE));
        scrollBar.setForeground(new Color(48, 144, 216));
        scrollBar.setBackground(new Color(255, 255, 255, 12));
        scrollBar.setOpaque(false);
    }

    @Override
    protected @NotNull Dimension getMaximumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, THUMB_SIZE);
        } else {
            return new Dimension(THUMB_SIZE, 0);
        }
    }

    @Override
    protected @NotNull Dimension getMinimumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(0, THUMB_SIZE);
        } else {
            return new Dimension(THUMB_SIZE, 0);
        }
    }

    @Override
    protected @NotNull JButton createIncreaseButton(int ignored) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createDecreaseButton(int ignored) {
        return new ScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics graphics, JComponent ignored, Rectangle rectangle) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int scale = 15;
        int size = rectangle.height / scale;
        int width = rectangle.width;
        int x = 0;
        int y = rectangle.y + ((rectangle.height - size) / 2);
        graphics2D.setColor(scrollbar.getBackground());
        graphics2D.fillRoundRect(x, y, width, size, 10, 10);
    }

    @Override
    protected void paintThumb(Graphics graphics, JComponent component, Rectangle rectangle) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(thumbImage, rectangle.x, rectangle.y, null);
    }

    private static class ScrollBarButton extends JButton {
        public ScrollBarButton() {
            setBorder(BorderFactory.createEmptyBorder());
        }
        @Override
        public void paint(Graphics graphics) {
        }
    }

    private static @NotNull BufferedImage resizeImage(BufferedImage originalImage, int type) throws IOException {
        BufferedImage resizedImage = new BufferedImage(CustomScrollBarUI.THUMB_SIZE, CustomScrollBarUI.THUMB_SIZE, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, CustomScrollBarUI.THUMB_SIZE, CustomScrollBarUI.THUMB_SIZE, null);
        g.dispose();
        return resizedImage;
    }
}