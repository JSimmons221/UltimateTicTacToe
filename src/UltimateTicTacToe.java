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

        //set all numbers to 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < board[0][0].length; k++) {
                    board [i][j][k] = 0;
                }
            }
        }

        //set all usable numbers that work to 1
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0][0].length; j++) {
                board[0][i][j] = 1;
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
                int y = mouseEvent.getY();
                int x = mouseEvent.getX();
            }

        });

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawLines(g2);
    }

    public void drawLines (Graphics2D g2){

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
    public void drawX (Graphics2D g2, int z, int x, int y){
        int positionX=(x+z%3*3)*width/9+2;
        int positionY=(y+z/3*3)*height/9+2;
        g2.drawLine(positionX, positionY, positionX+width/9-4, positionY+width/9-4);
        g2.drawLine(positionX, positionY+width/9-4, positionX+width/9-4, positionY);

    }
    public void drawO (Graphics2D g2, int z, int x, int y){
        int positionX=(x+z%3*3)*width/9+2;
        int positionY=(y+z/3*3)*height/9+2;
        g2.drawOval(positionX, positionY, width/9-4,width/9-4);

    }
    public void drawXandO (Graphics2D g2){

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < board[0][0].length; k++) {

                    if (board[i][j][k]==2){
                        drawX(g2,i,j,k);
                    }
                    if (board[i][j][k]==3){
                        drawO(g2,i,j,k);
                    }

                }
            }
        }


    }

}
