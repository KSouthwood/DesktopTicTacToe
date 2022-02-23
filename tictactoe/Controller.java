package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private static final MessageFormat           TURN_MSG      = new MessageFormat("The turn of {0} Player ({1})");
    private static final MessageFormat           WIN_MSG       = new MessageFormat("The {0} Player ({1}) wins");
    private static       int                     move          = 0;
    private static       boolean                 gameOver      = false;
    private static       ArrayList<Cell>         cells         = new ArrayList<>();
    private static       ArrayList<PlayerButton> playerButtons = new ArrayList<>();
    private static       JLabel                  statusBar;

    /**
     * Add a private constructor so that we can't mistakenly create a Controller object via new.
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

    static void playCell(final Cell cell) {
        cell.setCellText(move % 2 == 0 ? "X" : "O");
        cell.setEnabled(false);
        move++;
        statusBar.setText(getTurnMessage());

        checkState();
        if (!gameOver) {
            if (move == 9) {
                statusBar.setText(Status.DRAW.getMessage());
                endGame();
            } else {
                playerButtons.get(move % 2).play();
            }
        }
    }

    /**
     * Checks to see if we have a winner.
     * <p>
     * Loops through each row, column and diagonal calling isThreeInARow to check for a winner. If we do have a winner,
     * update the status message and change the winning line background colors to highlight it.
     */
    private static void checkState() {
        final int[][] INDICES = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                 {0, 4, 8}, {2, 4, 6}};

        for (int[] index : INDICES) {
            if (isThreeInARow(index)) {
                endGame();
                var player = (move - 1) % 2;
                if ("X".equals(cells.get(index[0]).getCellText())) {
                    statusBar.setText(WIN_MSG.format(new Object[]{playerButtons.get(player).getText(), "X"}));
                } else {
                    statusBar.setText(WIN_MSG.format(new Object[]{playerButtons.get(player).getText(), "O"}));
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

    private static String getTurnMessage() {
        int      player = move % 2;
        Object[] args   = {playerButtons.get(player).getText(), player == 0 ? "X" : "O"};
        return TURN_MSG.format(args);
    }

    /**
     * Configures the game for the chosen menu item.
     *
     * @param menuItem
     *         the enum of the chosen menu item
     */
    static void handleMenuItem(MenuItemSpec menuItem) {
        statusBar.setText(String.format("Menu item %s clicked.", menuItem.getMenuText()));
        resetBoard();

        switch (menuItem) {
            case HUMAN_HUMAN:
                setPlayerButtons(new String[]{"Human", "Human"});
                startGame();
                break;
            case HUMAN_ROBOT:
                setPlayerButtons(new String[]{"Human", "Robot"});
                startGame();
                break;
            case ROBOT_HUMAN:
                setPlayerButtons(new String[]{"Robot", "Human"});
                startGame();
                break;
            case ROBOT_ROBOT:
                setPlayerButtons(new String[]{"Robot", "Robot"});
                startGame();
                break;
            case EXIT:
                System.exit(0);
            default:
                break;
        }
    }

    static void startGame() {
        gameOver = false;
        move = 0;

        for (Cell cell : cells) {
            cell.setEnabled(true);
        }

        for (PlayerButton playerButton : playerButtons) {
            playerButton.setEnabled(false);
        }

        statusBar.setText(getTurnMessage());
        playerButtons.get(0).play();
    }

    /**
     * Reset the board.
     * <p>
     * Clears each cell of X's/O's, disables each button (until a game is started), reset the background color, enable
     * the player buttons so that they can be changed, and finally update the status message.
     */
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

    private static void setPlayerButtons(String[] players) {
        for (int index = 0; index < 2; index++) {
            if (!playerButtons.get(index).getText().equals(players[index])) {
                playerButtons.get(index).doClick();
            }
        }
    }
}