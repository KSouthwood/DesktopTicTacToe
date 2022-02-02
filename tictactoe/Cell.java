package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    Cell(String text) {
        super(" ");
        setName("Button" + text);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        setFocusPainted(false);
    }

    void setCell(String text) {
        setText(text);
    }

    String getCell() {
        return getText();
    }
}
