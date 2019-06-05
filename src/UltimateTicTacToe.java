import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UltimateTicTacToe extends JPanel{

    private int[][][] board = new int[9][3][3];
    private int[][] boardB = new int[3][3];
    private int playerTurn=0;
    private int width, height;
    private boolean findGoodName;                   //FIND A GOOD NAME FOR THIS

    public UltimateTicTacToe(int w, int h){

        width = w;
        height = h;
        setSize(w,h);
        findGoodName=false;

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
                int y = mouseEvent.getY()/(width/9)%3;
                int x = mouseEvent.getX()/(width/9)%3;
                int z = mouseEvent.getX()/(width/3)+mouseEvent.getY()/(width/3)*3;
                int z2 = x+y*3;
                playerTurn(z,x,y,z2);
            }

        });

    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawBoxes(g2);
        drawLines(g2);
        drawXandO(g2);
    }

    private void drawLines (Graphics2D g2){

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
    private void drawX (Graphics2D g2, int z, int x, int y){
        int positionX=(x+z%3*3)*width/9+2;
        int positionY=(y+z/3*3)*height/9+2;
        g2.drawLine(positionX, positionY, positionX+width/9-4, positionY+width/9-4);
        g2.drawLine(positionX, positionY+width/9-4, positionX+width/9-4, positionY);

    }
    private void drawO (Graphics2D g2, int z, int x, int y){
        int positionX=(x+z%3*3)*width/9+2;
        int positionY=(y+z/3*3)*height/9+2;
        g2.drawOval(positionX, positionY, width/9-4,width/9-4);

    }
    private void drawXandO (Graphics2D g2){
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
    private void drawBoxes (Graphics2D g2){
        for (int z = 0; z < board.length; z++) {
            for (int x = 0; x < board[0].length; x++) {
                for (int y = 0; y < board[0][0].length; y++) {
                    int x1 = x*(width/9) + (z%3)*(width/3);
                    int y1 = y*(width/9) + (z/3)*(width/3);
                    if (board[z][x][y]==0 || board[z][x][y]==2 || board[z][x][y]==3 || board[z][x][y]==4){
                        g2.setColor(new Color(128,128,128));
                        g2.fillRect(x1,y1,width/9,width/9);
                        g2.setColor(Color.black);
                    }
                    if (board[z][x][y]==1){
                        g2.setColor(Color.white);
                        g2.fillRect(x1,y1,width/9,width/9);
                        g2.setColor(Color.black);
                    }
                }
            }
        }
    }

    private void playerTurn (int z, int x, int y, int z2){

        if (board[z][x][y]==1){
            if (playerTurn%2==0){
                board[z][x][y]=2;
                playerTurn++;
            }
            else{
                board[z][x][y]=3;
                playerTurn++;
            }
        }

        //TODO Add winning a box here

        //TODO Add winning the entire board here

        if (findGoodName){
            findGoodName=false;
            for (int i = 0; i < board.length; i++) {
                if (i!=z){
                    for (int j = 0; j < board[0].length; j++) {
                        for (int k = 0; k < board[0][0].length; k++) {
                            if (board[i][j][k]==1) {
                                board[i][j][k] = 0;
                            }
                        }
                    }
                }
            }
        }

        int counterZ=0;
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0][0].length; j++) {
                if (board[z][i][j]==1){
                    board[z][i][j]=0;
                    counterZ++;
                }
                if (board[z2][i][j]==0){
                    board[z2][i][j]=1;
                }
            }
        }
        
        if (counterZ==0){
            boardB[z%3][z/3]=3;
        }
        
        if (boardB[z2%3][z2/3]!=0){
            findGoodName=true;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    for (int k = 0; k < board[0][0].length; k++) {
                        if (board[i][j][k]==0){
                            board[i][j][k]=1;
                        }
                    }
                }
            }
        }
        repaint();

    }

}
