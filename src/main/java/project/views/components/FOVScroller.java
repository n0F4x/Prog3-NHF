package project.views.components;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class FOVScroller extends JScrollBar {
    private static final int extent = 10;


    public FOVScroller(int initialValue, int minValue, int maxValue, @NotNull Consumer<@NotNull Integer> listener) {
        super(JScrollBar.HORIZONTAL, initialValue, extent, minValue, maxValue + extent);
        setBounds(650,0, 600, 35);
        setBackground(Color.GREEN);

        // TODO: Finish UI

        addAdjustmentListener(adjustmentEvent -> listener.accept(adjustmentEvent.getValue()));
    }
}
