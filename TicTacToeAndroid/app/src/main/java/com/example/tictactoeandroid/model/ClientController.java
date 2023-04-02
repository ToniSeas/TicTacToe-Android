package com.example.tictactoeandroid.model;

import com.example.tictactoeandroid.game.TicTacToe;
import com.example.tictactoeandroid.model.client.Client;
import com.example.tictactoeandroid.view.GameActivity;

import java.io.IOException;

public class ClientController extends Thread {

    public static String SELECT_SQUARE = "selectSquare";
    private static ClientController CLIENT_CONTROLLER;

    private Client client;
    private boolean waiting;
    private GameActivity ga;

    private ClientController() {
        super();
        this.waiting = false;
        this.ga = null;

        try {
            this.client = new Client("192.168.1.2",11111);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
    }

    public void setContext(GameActivity context) {
        this.ga = context;
    }

    public static ClientController getInstance() {
        if (CLIENT_CONTROLLER == null) {
            CLIENT_CONTROLLER = new ClientController();
        }
        return CLIENT_CONTROLLER;
    }

    public int sendSelectSquare (int row, int column) {
        int state = TicTacToe.getInstance().selectSquare(TicTacToe.SELECTED_PLAYER, row, column);
        if (TicTacToe.getInstance().check() == TicTacToe.SELECTED_PLAYER) {
            this.ga.animation(TicTacToe.SELECTED_PLAYER);
            this.client.sendMessage(StringConverter.convertToSend(SELECT_SQUARE, row + "," + column));
        } else {
            if (state == TicTacToe.SUCCESS) {
                this.client.sendMessage(StringConverter.convertToSend(SELECT_SQUARE, row + "," + column));
                this.ga.waitTurn();
                this.waiting = true;


            }
        }
        return state;
    }

    public void recvSelectSquare(String values) {
        int row = Integer.parseInt(values.split(",")[0]);
        int column = Integer.parseInt(values.split(",")[1]);
        TicTacToe.getInstance().selectSquare(TicTacToe.SELECTED_CPU, row, column);
        if (TicTacToe.getInstance().check() == TicTacToe.SELECTED_CPU) {
            this.ga.animation(TicTacToe.SELECTED_CPU);
        } else {
            this.ga.playTurn();
            this.waiting = false;
        }
    }

    public void doAction (String message) {
        String action = StringConverter.getAction(message);
        if (action.equals(SELECT_SQUARE)) {
            this.recvSelectSquare(StringConverter.getValues(message));
        }
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public void run () {

        while (TicTacToe.getInstance().isRunning()) {
            String message = this.client.recvMessage();
            this.doAction(message);
        }
    }

}
