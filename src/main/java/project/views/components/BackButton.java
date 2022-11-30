package project.views.components;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class BackButton extends JButton {
    public BackButton(@NotNull Supplier<@NotNull Container> rootSupplier) {
        setIcon(new ImageIcon("src/main/resources/backButton.png"));
        setFont(new Font("Calibri", Font.ITALIC, 50));
        setBackground(Color.GREEN);
        setForeground(Color.WHITE);
        setUI(new StyledButtonUI());
        setBounds(0,0, 200, 200);

        // TODO: Finish UI

        addActionListener(actionEvent -> ((CardLayout) (rootSupplier.get().getLayout())).previous(rootSupplier.get()));
    }
}
