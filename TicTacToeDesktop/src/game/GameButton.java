package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class GameButton {

    private BufferedImage background;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isOver;
    private GameSound sound;
    
    public GameButton(int x, int y, int witdh, int height) {

            File file = new File("src\\sound.png");
            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.background = image;

        this.x = x;
        this.y = y;
        this.width = witdh;
        this.height = height;
        this.isOver = false;
        this.sound = new GameSound("src\\sound.wav");
        this.sound.setVolume(-20);
    }

    public void setBackground(BufferedImage backgroud) {
        this.background = backgroud;
    }

    public void draw(Graphics g) {
        g.drawImage(this.background, this.x, this.y, this.width, this.height, null);
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
		return ((this.getLeft() <= mouseX + 1 && this.getRight() >= mouseX)
				&& (this.getTop() <= mouseY + 1 && this.getBottom() >= mouseY));
	}

    public void mouseInput(int mouseX, int mouseY) {

        if (this.isMouseOver(mouseX, mouseY)) {    
            if (!isOver) {
                isOver = true;
                this.sound.play();
            } else {
                isOver = false;
                this.sound.stop();
            }
        }

    }

    public int getTop() {return (int)this.y;}
    public int getBottom() {return (int)this.y + this.height;}
    public int getLeft() {return (int)this.x;}
    public int getRight() {return (int)this.x + this.width;}


}
