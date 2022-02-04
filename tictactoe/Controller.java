package tictactoe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private static Board BOARD;
    private static int move = 1;

    Controller() {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Reset")) {
            System.out.println("Clear field");
            BOARD.clearCells();
            move = 1;
        } else {
            String button = ((JButton) actionEvent.getSource()).getName();
            System.out.println("Button: " + button);
            playCell((JButton) actionEvent.getSource());
        }
    }

    private void playCell(JButton button) {
        button.setText(move++ % 2 == 0 ? "O" : "X");
        if (move > 5) {
            BOARD.checkState();
        }
    }

    static void setBOARD(Board BOARD) {
        Controller.BOARD = BOARD;
    }
}
