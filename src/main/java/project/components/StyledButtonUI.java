package project.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class StyledButtonUI extends BasicButtonUI {
    public static class RoundedBorder implements Border {
        private final int radius;
        public RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component component) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component component, Graphics graphics, int x, int y, int width, int height) {
            graphics.setColor(component.getForeground());
            graphics.drawOval(0, 0, component.getSize().width-1, component.getSize().height-1);
            // https://www.roseindia.net/tutorial/java/swing/createRoundButton.html !!!
        }
    }

    @Override
    public void installUI (JComponent component) {
        super.installUI(component);
        AbstractButton button = (AbstractButton) component;
        button.setOpaque(false);
        button.setBorder(new StyledButtonUI.RoundedBorder(50));
    }

    @Override
    public void paint (Graphics graphics, JComponent component) {
        AbstractButton button = (AbstractButton) component;
        paintBackground(graphics, button, button.getModel().isPressed() ? 2 : 0);
        super.paint(graphics, component);
    }

    private void paintBackground (Graphics graphics, JComponent component, int yOffset) {
        Dimension size = component.getSize();
//        component.setBorder(new EmptyBorder(12 + yOffset, 15, 5 + yOffset, 15));
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(component.getBackground().darker());
        graphics.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        graphics.setColor(component.getBackground());
        graphics.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
    }
}
