package tictactoe;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JLabel {
    StatusBar() {
        setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setStatus("Game is in development");
    }

    void setStatus(String text) {
        this.setText(text);
    }
}