package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    Cell(String text, ActionListener listener) {
        setName(text);
        setCell(" ");
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        setFocusPainted(false);
        addActionListener(listener);
    }

    void setCell(String text) {
        setText(text);
    }

    String getCell() {
        return getText();
    }
}
