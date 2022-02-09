package tictactoe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToe extends JFrame {
    private final ArrayList<Cell> cells = new ArrayList<>();
    private       JLabel          statusBar;

    public TicTacToe() {
        setWindowProperties();
        addComponents();
        setVisible(true);
        new Controller(cells, statusBar);
    }

    /**
     * Set the properties for the main game window here.
     */
    private void setWindowProperties() {
        setTitle("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 460);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    /**
     * Add in the various components into the game window here.
     */
    private void addComponents() {
        Controller.Listener listener = new Controller.Listener();
        defineCells(listener);

        var buttonBar = createButtonBar(listener);
        var field = createField();
        statusBar = createStatusBar();

        this.add(buttonBar, BorderLayout.PAGE_START);
        this.add(field, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.PAGE_END);
    }

    /**
     * Create the JPanel which holds the 3 x 3 grid of cells of the tic-tac-toe board.
     * @return JPanel with nine buttons
     */
    private JPanel createField() {
        JPanel jPanel = new JPanel(new GridLayout(3, 3));
        jPanel.setPreferredSize(new Dimension(360, 360));

        for (Cell cell : cells) {
            jPanel.add(cell);
        }

        return jPanel;
    }

    /**
     * Instantiates the JButtons with their name and associated listener method.
     * Adds each one to the ArrayList<Cell> field.
     * @param listener to handle when the button is clicked
     */
    private void defineCells(ActionListener listener) {
        String[] rowNames = {"3", "2", "1"};
        String[] colNames = {"A", "B", "C"};
        for (var row : rowNames) {
            for (var col : colNames) {
                cells.add(new Cell("Button" + col + row, listener));
            }
        }
    }

    private JLabel createStatusBar() {
        JLabel statusPanel = new JLabel();
        statusPanel.setPreferredSize(new Dimension(360, 50));
        statusPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        statusPanel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        statusPanel.setName("LabelStatus");
        statusPanel.setText(Status.NOT_BEGUN.getMessage());
        statusPanel.setBackground(Color.GRAY);
        statusPanel.setOpaque(true);
        return statusPanel;
    }

    /**
     * Create the button bar holding the player toggle buttons and the game start/reset button.
     * @param listener ActionListener for the ResetButton
     * @return JPanel with the three buttons
     */
    private JPanel createButtonBar(ActionListener listener) {
        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonBar.setPreferredSize(new Dimension(360, 50));
        buttonBar.setBackground(Color.GRAY);

        buttonBar.add(new PlayerButton("ButtonPlayer1"));
        buttonBar.add(new ResetButton(listener));
        buttonBar.add(new PlayerButton("ButtonPlayer2"));

        return buttonBar;
    }
}