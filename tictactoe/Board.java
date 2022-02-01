package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Board() {
        setLayout(new GridLayout(3, 3));
        addCells();
    }

    private void addCells() {
        var rowNames = new String[]{"3", "2", "1"};
        String[] colNames = {"A", "B", "C"};
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                add(new Cell(colNames[col] + rowNames[row]));
            }
        }
    }
}
