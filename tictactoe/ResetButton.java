package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResetButton extends JButton {
    ResetButton(ActionListener listener) {
        super("Reset");
        setName("ButtonStartReset");
        setPreferredSize(new Dimension(80, 30));
        setBackground(Color.LIGHT_GRAY);
        addActionListener(listener);
    }
}
