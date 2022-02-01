package tictactoe;

import javax.swing.*;

public class Cell extends JButton {
    Cell(String text) {
        super(text);
        setName("Button" + text);
    }
}
