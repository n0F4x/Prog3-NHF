package project.views.components;

import javax.swing.*;
import java.awt.*;

public class QuitButton extends JButton {
    public QuitButton() {
        super("Quit");
        setBounds(1680, 0, 240, 160);
        setFont(new Font("Sherif", Font.BOLD, 50));
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);

        // TODO: Finish UI

        addActionListener(e -> {
            // TODO: Finish dialog UI
            int result = JOptionPane.showConfirmDialog(getParent(), "Are you sure?", "Quit Application", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon());
            if (result == JOptionPane.YES_OPTION) {
                ((JFrame) getTopLevelAncestor()).dispose();
            }
        });
    }
}
