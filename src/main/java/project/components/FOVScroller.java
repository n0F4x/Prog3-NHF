package project.components;

import project.room.Camera;

import javax.swing.*;

public class FOVScroller extends JScrollBar {
    private static final int extent = 10;


    public FOVScroller(Camera camera) {
        super(JScrollBar.HORIZONTAL, (int) camera.getFOV(), extent, (int) Camera.minFOV, (int) Camera.maxFOV);

        // TODO: Finish UI

        addAdjustmentListener(adjustmentEvent -> {
            camera.setFOV(adjustmentEvent.getValue());
        });
    }
}
