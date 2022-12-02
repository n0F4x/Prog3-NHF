package project.views.components;

import org.jetbrains.annotations.NotNull;
import project.views.UIs.CustomScrollBarUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class CustomScrollBar extends JScrollBar {
    private static final int extent = 10;


    public CustomScrollBar(int initialValue, int minValue, int maxValue, @NotNull Consumer<@NotNull Integer> listener) {
        super(JScrollBar.HORIZONTAL, initialValue, extent, minValue, maxValue + extent);
        setUI(new CustomScrollBarUI());

        addAdjustmentListener(adjustmentEvent -> listener.accept(adjustmentEvent.getValue()));
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }
        });
    }
}
