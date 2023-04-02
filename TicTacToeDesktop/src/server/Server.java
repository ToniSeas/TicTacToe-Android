package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import game.TicTacToe;

public class Server extends Thread {
    
    public static String SELECT_SQUARE = "selectSquare";

    private ServerSocket server;
    
    private Socket socket;
    private DataOutputStream send;
    private DataInputStream receive;

	private boolean running;

    public Server(int port) {
		super();

        try {
            this.server = new ServerSocket(port); 
			this.server.setReuseAddress(true);

            this.waitForClient("localhost", port);
            
			this.running = true;

			this.start();
		} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForClient(String ipAddress, int port) {
        try {
            this.socket = this.server.accept();
            this.send = new DataOutputStream(this.socket.getOutputStream());
            this.receive = new DataInputStream(this.socket.getInputStream());
            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
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
        String message = "";
        try {
            message = this.receive.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

	public Socket accept() throws IOException {
		return this.server.accept();
	}

    public void sendSelectSquare () {

        int state = TicTacToe.FAILED;
        
        int row = -1;
        int column = -1;
        while (state != TicTacToe.SUCCESS) {
            int min = 0;
            int max = 2;
            row = (int)(Math.random() * ((max - min) + 1)) + min;
            column = (int)(Math.random() * ((max - min) + 1)) + min;
            System.out.println(row + "     " + column);
            state = TicTacToe.getInstance().selectSquare(TicTacToe.SELECTED_CPU, row, column);
        }
        
        this.sendMessage(StringConverter.convertToSend(SELECT_SQUARE, row+","+column));
    }

    public void recvSelectSquare(String values) {
        int row = Integer.parseInt(values.split(",")[0]);
        int column = Integer.parseInt(values.split(",")[1]);
        System.out.println("Fila: " + row + " " + "Columna: " + column);
        TicTacToe.getInstance().selectSquare(TicTacToe.SELECTED_PLAYER, row, column);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!TicTacToe.getInstance().allSeleted()) {
            this.sendSelectSquare();
        }
        
    }

    public void doAction(String action, String values) {
        System.out.println("Accion: " + action);

        if (action.equals(SELECT_SQUARE)) {
            this.recvSelectSquare(values);
        }
	}

	@Override
	public void run() {

		while (this.running) {
            String message = this.recvMessage();
            this.doAction(StringConverter.getAction(message), StringConverter.getValues(message));
		}

		try {
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
