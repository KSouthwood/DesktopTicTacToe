package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    private final ArrayList<Cell> cells = new ArrayList<>();
    private static final int[][] INDICES = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                         {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                         {0, 4, 8}, {2, 4, 6}};

    Board() {
        setLayout(new GridLayout(3, 3));
        setSize(300, 300);
        addCells();
        clearCells();
    }

    private void addCells() {
        String[] rowNames = {"3", "2", "1"};
        String[] colNames = {"A", "B", "C"};
        for (var row : rowNames) {
            for (var col : colNames) {
                Cell cell = new Cell("Button" + col + row);
                add(cell);
                cells.add(cell);
            }
        }
    }

    void clearCells() {
        for (Cell cell : cells) {
            cell.setCell(" ");
        }
    }

    void checkState() {
        for (int[] index : INDICES) {
            if (isThreeInARow(index)) {
                System.out.print("Found winner: '" + cells.get(index[0]).getCell() + "'");
            }
            System.out.println();
        }
    }

    private boolean isThreeInARow(int[] indices) {
        System.out.printf("Checking: %s, %s, %s. ", cells.get(indices[0]).getName(), cells.get(indices[1]).getName(),
                          cells.get(indices[2]).getName());
        return cells.get(indices[0]).getCell().equals(cells.get(indices[1]).getCell()) &&
               cells.get(indices[1]).getCell().equals(cells.get(indices[2]).getCell());
    }
}
