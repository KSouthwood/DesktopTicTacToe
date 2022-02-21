package tictactoe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TicTacToe extends JFrame {

    public TicTacToe() {
        setWindowProperties();
        addComponents();
        setVisible(true);
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
        var cells = defineCells();
        var playerBtns = definePlayerButtons();

        var menuBar = createMenuBar();
        var buttonBar = createButtonBar(playerBtns);
        var field = createField(cells);
        var statusBar = createStatusBar();

        this.setJMenuBar(menuBar);
        this.add(buttonBar, BorderLayout.PAGE_START);
        this.add(field, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.PAGE_END);

        Controller.initialize(cells, statusBar, playerBtns);
    }

    private ArrayList<PlayerButton> definePlayerButtons() {
        ArrayList<PlayerButton> buttons = new ArrayList<>();
        buttons.add(new PlayerButton("ButtonPlayer1"));
        buttons.add(new PlayerButton("ButtonPlayer2"));
        return buttons;
    }

    /**
     * Create the JPanel which holds the 3 x 3 grid of cells of the tic-tac-toe board.
     * @return JPanel with nine buttons
     * @param cells ArrayList of Cell
     */
    private JPanel createField(ArrayList<Cell> cells) {
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
     */
    private ArrayList<Cell> defineCells() {
        ArrayList<Cell> cells = new ArrayList<>();
        String[] rowNames = {"3", "2", "1"};
        String[] colNames = {"A", "B", "C"};
        for (var row : rowNames) {
            for (var col : colNames) {
                cells.add(new Cell("Button" + col + row));
            }
        }
        return cells;
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
     * @return JPanel with the three buttons
     * @param buttons ArrayList of PlayerButton
     */
    private JPanel createButtonBar(ArrayList<PlayerButton> buttons) {
        JPanel buttonBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonBar.setPreferredSize(new Dimension(360, 50));
        buttonBar.setBackground(Color.GRAY);

        buttonBar.add(buttons.get(0));
        buttonBar.add(new ResetButton());
        buttonBar.add(buttons.get(1));

        return buttonBar;
    }

    private JMenuBar createMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setName("MenuGame");
        jMenuBar.setMargin(new Insets(10, 10, 10, 10));

        jMenuBar.add(createGameMenu());
        return jMenuBar;
    }

    private JMenu createGameMenu() {
        JMenu jMenu = new JMenu();
        jMenu.setText("Game");
        jMenu.setMnemonic(KeyEvent.VK_G);

        for (var item : MenuItemSpec.values()) {
            jMenu.add(new MenuItem(item));
        }

        jMenu.insertSeparator(jMenu.getMenuComponentCount() - 1);

        return jMenu;
    }
}