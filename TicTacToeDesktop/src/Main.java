import javax.swing.JFrame;

import gui.JPDrawArea;
import server.Server;

public class Main {
    public static void main(String[] args) throws Exception {

        JFrame jframe = new JFrame();
        jframe.add(new JPDrawArea());
        jframe.setResizable(false);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("Examen_AntonySeas");
        jframe.setVisible(true);

        new Server(11111);

    }
}
