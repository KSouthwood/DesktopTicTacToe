package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private static int                     move          = 1;
    private static boolean                 gameOver      = false;
    private static ArrayList<Cell>         cells         = new ArrayList<>();
    private static ArrayList<PlayerButton> playerButtons = new ArrayList<>();
    private static JLabel                  statusBar;

    /**
     * Add a private constructor so we can't mistakenly create a Controller object via new.
     */
    private Controller() {

    }

    /**
     * Set our static fields to the necessary values.
     *
     * @param cells
     *         ArrayList of the buttons of the grid
     * @param statusBar
     *         JLabel used to display our game messages
     * @param players
     *         ArrayList of the player buttons
     */
    static void initialize(final ArrayList<Cell> cells, final JLabel statusBar,
                           final ArrayList<PlayerButton> players) {
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

        playerButtons.get(0).play();
    }

    static void robotPlayer() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Random space = new Random();
                int    play;
                do {
                    play = space.nextInt(9);
                } while (!cells.get(play).isEnabled());

                cells.get(play).doClick();
                return null;
            }
        };

        worker.execute();
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

    static void playCell(final Cell cell) {
        cell.setCellText(move++ % 2 == 0 ? "O" : "X");
        cell.setEnabled(false);
        if (move > 5) {
            checkState();
        }

        if (move == 10 && !gameOver) {
            statusBar.setText(Status.DRAW.getMessage());
            endGame();
        }

        if (!gameOver) {
            playerButtons.get((move - 1) % 2).play();
        }
    }

    private static void checkState() {
        final int[][] INDICES = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                 {0, 4, 8}, {2, 4, 6}};

        for (int[] index : INDICES) {
            if (isThreeInARow(index)) {
                endGame();
                if ("X".equals(cells.get(index[0]).getCellText())) {
                    statusBar.setText(Status.X_WON.getMessage());
                } else {
                    statusBar.setText(Status.O_WON.getMessage());
                }
                for (int i : index) {
                    cells.get(i).setBackground(Color.GRAY);
                }
            }
        }
    }

    private static boolean isThreeInARow(final int[] indices) {
        return cells.get(indices[0]).getCellText().equals(cells.get(indices[1]).getCellText()) &&
               cells.get(indices[1]).getCellText().equals(cells.get(indices[2]).getCellText()) &&
               !cells.get(indices[0]).getCellText().equals(" ");
    }

    private static void endGame() {
        gameOver = true;
        for (Cell cell : cells) {
            cell.setEnabled(false);
        }
    }
}