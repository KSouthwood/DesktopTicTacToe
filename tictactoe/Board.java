package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Board() {
        setLayout(new GridLayout(3, 3));
        setSize(300, 300);
        addCells();
    }

    private void addCells() {
        String[] rowNames = {"3", "2", "1"};
        String[] colNames = {"A", "B", "C"};
        for (var row : rowNames) {
            for (var col : colNames) {
                add(new Cell("Button" + col + row));
            }
        }
    }
}
