package project.components;

import javax.swing.*;
import java.awt.*;

public class BackButton extends JButton {
    public BackButton() {
        super("Back");

//            setIcon(new ImageIcon("src/main/resources/backButton.png"));
        setFont(new Font("Calibri", Font.ITALIC, 30));
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
        setUI(new StyledButtonUI());
        // TODO: Finish UI

        addActionListener(actionEvent -> {
            JPanel cardHolder = (JPanel) getParent().getParent();
            ((CardLayout) (cardHolder.getLayout())).previous(cardHolder);
        });
    }
}
