import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UltimateTicTacToe extends JPanel{


    private int[][][] board = new int[9][3][3];
    private int[][] boardB = new int[3][3];
    private int playerTurn=0;
    private int width, height;
    private boolean unlockAll;

    public UltimateTicTacToe(int w, int h){

        width = w;
        height = h;
        setSize(w,h);
        unlockAll=false;

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
                if (board[z][x][y]==1){
                    playerTurn(z,x,y,z2);
                }
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode()==KeyEvent.VK_R){
                    reset();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });


    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        drawBoxes(g2);
        drawLines(g2);
        drawXandO(g2);
        drawBigXandO(g2);
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
        int positionX=(x+z%3*3)*width/9+8;
        int positionY=(y+z/3*3)*height/9+8;
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(positionX, positionY, positionX+width/9-16, positionY+width/9-16);
        g2.drawLine(positionX, positionY+width/9-16, positionX+width/9-16, positionY);
        g2.setStroke(new BasicStroke(1));

    }
    private void drawO (Graphics2D g2, int z, int x, int y){
        int positionX=(x+z%3*3)*width/9+8;
        int positionY=(y+z/3*3)*height/9+8;
        g2.setStroke(new BasicStroke(10));
        g2.drawOval(positionX, positionY, width/9-16,width/9-16);
        g2.setStroke(new BasicStroke(1));

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
    private void drawBigX (Graphics2D g2, int x, int y){
        int positionX=x*width/3+5;
        int positionY=y*width/3+5;
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color(0,0,205));
        g2.drawLine(positionX, positionY, positionX+width/3-10, positionY+width/3-10);
        g2.drawLine(positionX, positionY+width/3-10, positionX+width/3-10, positionY);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1));
    }
    private void drawBigO (Graphics2D g2, int x, int y){
        int positionX=x*width/3+6;
        int positionY=y*width/3+6;
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color (220,20,60));
        g2.drawOval(positionX, positionY, width/3-12, width/3-12);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(1));
    }
    private void drawBigXandO (Graphics2D g2){
        for (int i = 0; i < boardB.length; i++) {
            for (int j = 0; j < boardB[0].length; j++) {
                if (boardB[i][j]==1){
                    drawBigX(g2,i,j);
                }
                if (boardB[i][j]==2){
                    drawBigO(g2,i,j);
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

        int counterX = 0;
        int counterY = 0;
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0][0].length; j++) {
                if (board[z][i][j]==2){
                    counterY++;
                }
                if (board[z][i][j]==3){
                    counterY--;
                }
                if (board[z][j][i]==2){
                    counterX++;
                }
                if (board[z][j][i]==3){
                    counterX--;
                }
            }
            if (counterX==3 || counterY==3){
                boardB[z%3][z/3]=1;
                for (int j = 0; j < board[0].length; j++) {
                    for (int k = 0; k < board[0][0].length; k++) {
                        if (board[z][j][k]==1){
                            board[z][j][k]=4;
                        }
                    }
                }
            }
            else if (counterX==-3 || counterY==-3){
                boardB[z%3][z/3]=2;
                for (int j = 0; j < board[0].length; j++) {
                    for (int k = 0; k < board[0][0].length; k++) {
                        if (board[z][j][k]==1){
                            board[z][j][k]=4;
                        }
                    }
                }
            }
            counterX=0;
            counterY=0;
        }
        
        int counterD1 = 0;
        int counterD2 = 0;
        for (int i = 0; i < board[0].length; i++) {
            if (board[z][i][i]==2){
                counterD1++;
            }
            if (board[z][i][2-i]==2){
                counterD2++;
            }
            if (board[z][i][i]==3){
                counterD1--;
            }
            if (board[z][i][2-i]==3){
                counterD2--;
            }
        }
        if (counterD1==3 || counterD2==3){
            boardB[z%3][z/3]=1;
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < board[0][0].length; k++) {
                    if (board[z][j][k]==1){
                        board[z][j][k]=4;
                    }
                }
            }
        }
        if (counterD1==-3 || counterD2==-3){
            boardB[z%3][z/3]=2;
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < board[0][0].length; k++) {
                    if (board[z][j][k]==1){
                        board[z][j][k]=4;
                    }
                }
            }
        }

        int counterBX = 0;
        int counterBY = 0;
        for (int i = 0; i < boardB.length; i++) {
            for (int j = 0; j < boardB[0].length; j++) {
                if (boardB[i][j]==1){
                    counterBY++;
                }
                if (boardB[i][j]==2){
                    counterBY--;
                }
                if (boardB[j][i]==1){
                    counterBX++;
                }
                if (boardB[j][i]==2){
                    counterBX--;
                }
            }
            if (counterBX==3 || counterBY==3){
                for (int j = 0; j < board.length; j++) {
                    for (int k = 0; k < board[0].length; k++) {
                        for (int l = 0; l < board[0][0].length; l++) {
                            if (board[j][k][l]==0 || board[j][k][l]==1){
                                board[j][k][l]=4;
                            }
                        }
                    }
                }
                for (int j = 0; j < boardB.length; j++) {
                    if (boardB[i][j]==0){
                        boardB[i][j]=3;
                    }
                }
            }
            if (counterBX==-3 || counterBY==-3){
                for (int j = 0; j < board.length; j++) {
                    for (int k = 0; k < board[0].length; k++) {
                        for (int l = 0; l < board[0][0].length; l++) {
                            if (board[j][k][l]==0 || board[j][k][l]==1){
                                board[j][k][l]=4;
                            }
                        }
                    }
                }
                for (int j = 0; j < boardB.length; j++) {
                    if (boardB[i][j]==0){
                        boardB[i][j]=3;
                    }
                }
            }
            counterBX=0;
            counterBY=0;
        }

        int counterBD1 = 0;
        int counterBD2 = 0;
        for (int i = 0; i < boardB.length; i++) {
            if (boardB[i][i]==1){
                counterBD1++;
            }
            if (boardB[i][i]==2){
                counterBD1--;
            }
            if (boardB[i][2-i]==1){
                counterBD2++;
            }
            if (boardB[i][2-i]==2){
                counterBD2--;
            }
        }
        if (counterBD1==3 || counterBD2==3){
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board[0].length; k++) {
                    for (int l = 0; l < board[0][0].length; l++) {
                        if (board[j][k][l]==0 || board[j][k][l]==1){
                            board[j][k][l]=4;
                        }
                    }
                }
            }
            for (int j = 0; j < boardB.length; j++) {
                for (int k = 0; k < boardB[0].length; k++) {
                    if (boardB[j][k]==0){
                        boardB[j][k]=3;
                    }
                }
            }
        }
        if (counterBD1==-3 || counterBD2==-3){
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board[0].length; k++) {
                    for (int l = 0; l < board[0][0].length; l++) {
                        if (board[j][k][l]==0 || board[j][k][l]==1){
                            board[j][k][l]=4;
                        }
                    }
                }
            }
            for (int j = 0; j < boardB.length; j++) {
                for (int k = 0; k < boardB[0].length; k++) {
                    if (boardB[j][k]==0){
                        boardB[j][k]=3;
                    }
                }
            }
        }

        if (unlockAll){
            unlockAll=false;
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
        
        if (counterZ==0 && boardB[z%3][z/3]==0){
            boardB[z%3][z/3]=3;
        }
        
        if (boardB[z2%3][z2/3]!=0){
            unlockAll=true;
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
    private void reset (){

        unlockAll=false;
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
        for (int i = 0; i < boardB.length; i++) {
            for (int j = 0; j < boardB[0].length; j++) {
                boardB[i][j] = 0;
            }
        }

        repaint();

    }



}
