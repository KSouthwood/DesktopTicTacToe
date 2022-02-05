package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    Board() {
        setLayout(new GridLayout(3, 3));
        setSize(300, 300);
    }

}
