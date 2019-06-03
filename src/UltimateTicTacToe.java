import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UltimateTicTacToe extends JPanel{

    private int[][][] board = new int[9][3][3];
    private int[][] boardB = new int[3][3];
    private int playerTurn=0;
    private int width, height;

    public UltimateTicTacToe(int w, int h){

        width = w;
        height = h;
        setSize(w,h);

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < board[0][0].length; k++) {
                    board [i][j][k] = 0;
                }
            }
        }

        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0][0].length; j++) {
                board[0][i][j] = 2;
            }
        }

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }
        });

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawLines(g2);
    }

    protected void drawLines (Graphics2D g2){

        for (int i = width/9; i < width; i+=width/9) {
            g2.drawLine(0,i,width,i);
            g2.drawLine(i,0,i,height);
        }

        for (int i = width/3; i < width; i+=width/3) {
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(0,i,width,i);
            g2.drawLine(i,0,i,height);
            g2.setStroke(new BasicStroke(1));
        }

    }
}
