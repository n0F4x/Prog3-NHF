package project.components;

import project.room.Camera;

import javax.swing.*;
import java.awt.*;

public class FOVScroller extends JScrollBar {
    private static final int extent = 10;


    public FOVScroller(Camera camera) {
        super(JScrollBar.HORIZONTAL, camera.getFOV(), extent, Camera.minFOV, Camera.maxFOV + extent);
        setBounds(650,0, 600, 35);
        setBackground(Color.GREEN);

        // TODO: Finish UI

        addAdjustmentListener(adjustmentEvent -> camera.setFOV(adjustmentEvent.getValue()));
    }
}
