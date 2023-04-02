package com.example.tictactoeandroid.game;

import com.example.tictactoeandroid.model.ClientController;

public class TicTacToe {

    private static TicTacToe game;

    public static int SELECTED_CPU = 1;
    public static int SELECTED_PLAYER = 2;
    public static int NOT_SELETED = 0;
    public static int SUCCESS = 2;
    public static int FAILED = 3;
    public static int FAILED_INDEX = 4;
    public static int VICTORY = 5;
    public static int DEFEAT = 6;

    private int[][] board;
    private ClientController clientController;
    private boolean running = true;

    private TicTacToe () {
        this.board = new int[3][3];
        this.clientController = ClientController.getInstance();
    }

    public static TicTacToe getInstance() {
        if (TicTacToe.game == null) {
            TicTacToe.game = new TicTacToe();
        }
        return TicTacToe.game;
    }

    public int selectSquare (int type, int row, int column) {
        if (row >= 3 || column >= 3) {
            return TicTacToe.FAILED_INDEX;
        }

        if (this.board[row][column] != TicTacToe.NOT_SELETED) {
            return TicTacToe.FAILED;
        }

        this.board[row][column] = type;
        return TicTacToe.SUCCESS;
    }

    public int check() {

        if ((board[0][0] == board[0][1]) &&  (board[0][1] == board[0][2])) {
            return board[0][0];

        } else if ((board[1][0] == board[1][1]) &&  (board[1][1] == board[1][2])) {
            return board[1][0];

        } else if ((board[2][0] == board[2][1]) &&  (board[2][1] == board[2][2])) {
            return board[2][0];

        } else if ((board[0][0] == board[1][0]) &&  (board[1][0] == board[2][0])) {
            return board[0][0];

        } else if ((board[0][1] == board[1][1]) &&  (board[1][1] == board[2][1])) {
            return board[0][1];

        } else if ((board[0][2] == board[1][2]) &&  (board[1][2] == board[2][2])) {
            return board[0][2];

        } else if ((board[0][0] == board[1][1]) &&  (board[1][1] == board[2][2])) {
            return board[0][0];

        }  else if ((board[0][2] == board[1][1]) &&  (board[1][1] == board[2][0])) {
            return board[0][2];
        }

        return FAILED;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
