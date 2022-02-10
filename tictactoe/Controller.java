package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controller {
    private static int                     move          = 1;
    private static boolean                 gameOver      = false;
    private static ArrayList<Cell>         cells         = new ArrayList<>();
    private static ArrayList<PlayerButton> playerButtons = new ArrayList<>();
    private static JLabel                  statusBar;

    static void initialize(ArrayList<Cell> cells, JLabel statusBar, ArrayList<PlayerButton> players) {
        Controller.cells = cells;
        Controller.statusBar = statusBar;
        Controller.playerButtons = players;
    }

    static void startGame() {
        gameOver = false;
        move = 1;

        for (Cell cell : cells) {
            cell.setEnabled(true);
        }

        for (PlayerButton playerButton : playerButtons) {
            playerButton.setEnabled(false);
        }

        statusBar.setText(Status.IN_PLAY.getMessage());
    }

    static void resetBoard() {
        for (Cell cell : cells) {
            cell.setCellText(" ");
            cell.setEnabled(false);
            cell.setBackground(Color.LIGHT_GRAY);
        }

        for (PlayerButton playerButton : playerButtons) {
            playerButton.setEnabled(true);
        }

        statusBar.setText(Status.NOT_BEGUN.getMessage());
    }

    static void playCell(Cell cell) {
        if (!gameOver) {
            if (cell.getCellText().equals(" ")) {
                statusBar.setText(Status.IN_PLAY.getMessage());
                cell.setCellText(move++ % 2 == 0 ? "O" : "X");
                cell.setEnabled(false);
                if (move > 5) {
                    checkState();
                }
            }

            if (move == 10 && !gameOver) {
                statusBar.setText(Status.DRAW.getMessage());
                gameOver = true;
            }
        }
    }

    private static void checkState() {
        final int[][] INDICES = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                 {0, 4, 8}, {2, 4, 6}};

        for (int[] index : INDICES) {
            if (isThreeInARow(index)) {
                gameOver = true;
                String winner = cells.get(index[0]).getCellText();
                if (winner.equals("X")) {
                    statusBar.setText(Status.X_WON.getMessage());
                }
                if (winner.equals("O")) {
                    statusBar.setText(Status.O_WON.getMessage());
                }
                for (int i : index) {
                    cells.get(i).setBackground(Color.GRAY);
                }
            }
        }
    }

    private static boolean isThreeInARow(int[] indices) {
        return cells.get(indices[0]).getCellText().equals(cells.get(indices[1]).getCellText()) &&
               cells.get(indices[1]).getCellText().equals(cells.get(indices[2]).getCellText()) &&
               !cells.get(indices[0]).getCellText().equals(" ");
    }

}