package project.components;

import javax.swing.*;
import java.awt.*;

public class BackButton extends JButton {
    public BackButton() {
        setIcon(new ImageIcon("src/main/resources/backButton.png"));
        setFont(new Font("Calibri", Font.ITALIC, 50));
        setBackground(Color.GREEN);
        setForeground(Color.WHITE);
        setUI(new StyledButtonUI());
        setBounds(0,0, 200, 200);
        // TODO: Finish UI

        addActionListener(actionEvent -> {
            JPanel cardHolder = (JPanel) getParent().getParent();
            ((CardLayout) (cardHolder.getLayout())).previous(cardHolder);
        });
    }
}
