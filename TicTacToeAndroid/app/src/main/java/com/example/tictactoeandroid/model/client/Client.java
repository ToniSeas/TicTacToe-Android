package com.example.tictactoeandroid.model.client;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    /* The programming of the socket to send and receive data was based on the code shown by
        PyPI Organization at: https://pypi.org/project/jpysocket/ */

    private Socket socket;
    private DataOutputStream send;
    private DataInputStream receive;

    public Client(String ipAddress, int port) throws IOException {
        this.createClientSocket(ipAddress, port);
    }

    public void createClientSocket(String ipAddress, int port) {
        try {
            this.socket = new Socket(InetAddress.getByName(ipAddress), port);
            this.send = new DataOutputStream(this.socket.getOutputStream());
            this.receive = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            this.send.flush();
            this.send.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String recvMessage() {
        String message = null;
        try {
            message = this.receive.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

}
