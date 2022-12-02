package project.views.UIs;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class BloodyButtonUI extends BasicButtonUI {
    private static final Font font;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Youmurdererbb.otf")).deriveFont(200.0f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void installUI (@NotNull JComponent component) {
        super.installUI(component);

        @NotNull AbstractButton button = (AbstractButton) component;

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(font);
        button.setForeground(Color.GREEN);
        button.setSize(button.getPreferredSize());
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.GREEN);
            }
        });
    }
}
