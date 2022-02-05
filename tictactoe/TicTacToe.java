package tictactoe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToe extends JFrame {
    private final ArrayList<Cell> cells = new ArrayList<>();
    private       StatusBar       statusBar;

    public TicTacToe() {
        setWindowProperties();
        addComponents();
        setVisible(true);
        new Controller(cells, statusBar);
    }

    private void setWindowProperties() {
        setTitle("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void addComponents() {
        Controller.Listener listener = new Controller.Listener();
        defineCells(listener);

        Board board = new Board();
        for (Cell cell : cells) {
            board.add(cell);
        }
        this.add(board, BorderLayout.CENTER);

        statusBar = new StatusBar();
        ResetButton resetButton = new ResetButton(listener);
        this.add(createStatusBar(statusBar, resetButton), BorderLayout.PAGE_END);
    }

    private void defineCells(ActionListener listener) {
        String[] rowNames = {"3", "2", "1"};
        String[] colNames = {"A", "B", "C"};
        for (var row : rowNames) {
            for (var col : colNames) {
                cells.add(new Cell("Button" + col + row, listener));
            }
        }
    }

    private JPanel createStatusBar(StatusBar statusBar, ResetButton resetButton) {
        JPanel statusPanel = new JPanel();
        statusPanel.setSize(300, 100);
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        statusPanel.add(statusBar, BorderLayout.WEST);
        statusPanel.add(resetButton, BorderLayout.EAST);
        return statusPanel;
    }
}