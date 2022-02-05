package tictactoe;

import javax.swing.*;

public class ResetButton extends JButton {
    ResetButton(Controller.Listener listener) {
        super("Reset");
        setName("ButtonReset");
        addActionListener(listener);
    }
}
