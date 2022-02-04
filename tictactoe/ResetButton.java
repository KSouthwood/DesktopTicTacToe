package tictactoe;

import javax.swing.*;

public class ResetButton extends JButton {
    ResetButton() {
        super("Reset");
        setName("ButtonReset");
        addActionListener(new Controller());
    }
}
