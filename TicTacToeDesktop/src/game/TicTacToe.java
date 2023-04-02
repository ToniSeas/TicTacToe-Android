package game;

import java.awt.Graphics;

public class TicTacToe {

    private static TicTacToe game;

    public static int SELECTED_CPU = 1;
    public static int SELECTED_PLAYER = 2;
    public static int NOT_SELETED = 0;
    public static int SUCCESS = 2;
    public static int FAILED = 3;
    public static int FAILED_INDEX = 4;

    private Square[][] board;
    private boolean running = true;

    private TicTacToe () {
        this.board = new Square[3][3];
        this.loadBoard();
    }

    public static TicTacToe getInstance() {
        if (TicTacToe.game == null) {
            TicTacToe.game = new TicTacToe();
        }
        return TicTacToe.game;
    }

    public boolean allSeleted () {
        int count = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getSeleted() != TicTacToe.NOT_SELETED) {
                    count++;
                }
            }    
        }

        return count == 9;
    }

    public void loadBoard() {
        int selfWidth = 800;
        int selfHeight = 600;
        int selfX = 0;
        int selfY = 55;
        int paddingX = 5;
        int paddingY = 5;
        int squareWidth = (selfWidth / 8) - paddingX;
        int squareHeight = (selfHeight / 8) - paddingY;
    
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[j][i] = new Square(j, i, selfX + (i*(squareWidth+paddingX)), selfY + (j*(squareHeight+paddingY)), squareWidth, squareHeight);
            }    
        }
    }

    public int selectSquare (int type, int row, int column) {
        if (row >= 3 || column >= 3) {
            return TicTacToe.FAILED_INDEX;
        }

        if (this.board[row][column].getSeleted() != TicTacToe.NOT_SELETED) {
            return TicTacToe.FAILED;
        }

        this.board[row][column].setSeleted(type);
        if (type == TicTacToe.SELECTED_CPU) {
            this.board[row][column].setCPUImage();
        } else if (type == TicTacToe.SELECTED_PLAYER) {
            this.board[row][column].setPlayerImage();
        }
        return TicTacToe.SUCCESS;
    }

    public void draw (Graphics g) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j].draw(g);
            }    
        }
    } 

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
