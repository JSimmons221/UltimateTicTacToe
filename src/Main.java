//Completed Project by Jakob Simmons

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ultimate Tic Tac Toe - Simmons");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int width = 720;
        int height = 720;
        frame.setPreferredSize(new Dimension(width, height + 24));


        JPanel panel = new UltimateTicTacToe(width,height);
        panel.setFocusable(true);
        panel.grabFocus();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}