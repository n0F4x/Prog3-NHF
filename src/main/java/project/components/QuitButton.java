package project.components;

import javax.swing.*;
import java.awt.*;

public class QuitButton extends JButton {
    public QuitButton() {
        super("Quit");

        // TODO: Finish UI

        addActionListener(e -> {
            // TODO: Finish dialog UI
            int result = JOptionPane.showConfirmDialog(getParent(), "Are you sure?", "Quit Application", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon());
            if (result == JOptionPane.YES_OPTION) {
                ((Window) getTopLevelAncestor()).dispose();
            }
        });
    }
}
