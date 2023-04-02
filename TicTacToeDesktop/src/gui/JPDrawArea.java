package gui;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import game.GameButton;
import game.TicTacToe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class JPDrawArea extends JPanel implements MouseInputListener{
    
    private GameButton soundButton;

    public JPDrawArea () {
        this.setPreferredSize(new Dimension(295, 280));
        this.addMouseListener(this);
        this.setFocusable(true);
        this.requestFocus();

        this.soundButton = new GameButton(120, 0, 50, 50);
    }

    @Override
    public void paintComponent (Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 600);

        TicTacToe.getInstance().draw(g);
        this.soundButton.draw(g);

        this.repaint();
        try {
            Thread.sleep(1/60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        this.soundButton.mouseInput(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

}
