package tictactoe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TicTacToe extends JFrame {
    public TicTacToe() {
        setWindowProperties();
        addComponents();
        setVisible(true);
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
        add(new Board(), BorderLayout.CENTER);
        JPanel statusPanel = new JPanel();
        statusPanel.setSize(300, 100);
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        statusPanel.add(new StatusBar(), BorderLayout.WEST);
        statusPanel.add(new ResetButton(), BorderLayout.EAST);
        add(statusPanel, BorderLayout.PAGE_END);
    }
}