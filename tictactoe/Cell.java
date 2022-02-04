package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    Cell(String text) {
        setName(text);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        setFocusPainted(false);
        addActionListener(new Controller());
    }

    void setCell(String text) {
        setText(text);
    }

    String getCell() {
        return getText();
    }
}
