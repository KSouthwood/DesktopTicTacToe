package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private static final int[][]         INDICES  = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                                     {0, 4, 8}, {2, 4, 6}};
    private static       int             move     = 1;
    private static       boolean         gameOver = false;
    private static       ArrayList<Cell> cells    = new ArrayList<>();
    private static       StatusBar       statusBar;


    Controller(ArrayList<Cell> cells, StatusBar statusBar) {
        Controller.cells = cells;
        Controller.statusBar = statusBar;
    }

    private static void clearCells() {
        for (Cell cell : cells) {
            cell.setCell(" ");
            cell.paint(cell.getGraphics());
        }
    }

    private static void playCell(Cell cell) {
        if (!gameOver) {
            if (cell.getCell().equals(" ")) {
                statusBar.setStatus(Status.IN_PLAY.getMessage());
                cell.setCell(move++ % 2 == 0 ? "O" : "X");
                cell.paint(cell.getGraphics());
                if (move > 5) {
                    checkState();
                }
            }

            if (move == 10 && !gameOver) {
                statusBar.setStatus(Status.DRAW.getMessage());
                gameOver = true;
            }
        }
    }

    private static void checkState() {
        for (int[] index : INDICES) {
            if (isThreeInARow(index)) {
                gameOver = true;
                String winner = cells.get(index[0]).getCell();
                if (winner.equals("X")) {
                    statusBar.setStatus(Status.X_WON.getMessage());
                }
                if (winner.equals("O")) {
                    statusBar.setStatus(Status.O_WON.getMessage());
                }
            }
        }
    }

    private static boolean isThreeInARow(int[] indices) {
        return cells.get(indices[0]).getCell().equals(cells.get(indices[1]).getCell()) &&
               cells.get(indices[1]).getCell().equals(cells.get(indices[2]).getCell()) &&
               !cells.get(indices[0]).getCell().equals(" ");
    }


    static class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getActionCommand().equals("Reset")) {
                clearCells();
                move = 1;
                gameOver = false;
                statusBar.setStatus(Status.NOT_BEGUN.getMessage());
            } else {
                Cell cell = (Cell) actionEvent.getSource();
                playCell(cell);
            }
        }
    }

}
