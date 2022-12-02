package project.views.components;

import org.jetbrains.annotations.NotNull;
import project.views.UIs.BloodyButtonUI;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class BackButton extends JButton {
    public BackButton(@NotNull Supplier<@NotNull Container> rootSupplier) {
        super("Back");
        setUI(new BloodyButtonUI());

        addActionListener(actionEvent -> ((CardLayout) (rootSupplier.get().getLayout())).previous(rootSupplier.get()));
    }
}
