package project.views.components;

import project.views.UIs.BloodyButtonUI;

import javax.swing.*;

/**
 * Customs class for quitting the application
 */
public class QuitButton extends JButton {
    /**
     * Constructs a new QuitButton
     */
    public QuitButton() {
        super("Quit");
        setUI(new BloodyButtonUI());

        addActionListener(e -> {
            // TODO: Finish dialog UI
            int result = JOptionPane.showConfirmDialog(getParent(), "Are you sure?", "Quit Application", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon());
            if (result == JOptionPane.YES_OPTION) {
                ((JFrame) getTopLevelAncestor()).dispose();
            }
        });
    }
}
