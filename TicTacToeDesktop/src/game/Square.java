package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Square {
    
    private int row;
    private int column;
    private int x;
    private int y;
    private int width;
    private int height;
    private int seleted;
    private BufferedImage cpuImage;
    private BufferedImage playerImage;
    private BufferedImage normalImage;

    public Square (int row, int column, int x, int y, int width, int height) {

        File file = new File("src\\o.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.cpuImage = image;

        file = new File("src\\x.png");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.playerImage = image;

        file = new File("src\\e.png");
        image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.normalImage = image;

        this.seleted = TicTacToe.NOT_SELETED;
        this.row = row;
        this.column = column;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        
        g.drawImage(this.normalImage, this.x, this.y, this.width, this.height, null);
        
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
		return ((this.getLeft() <= mouseX + 1 && this.getRight() >= mouseX)
				&& (this.getTop() <= mouseY + 1 && this.getBottom() >= mouseY));
	}

    public int getTop() {return (int)this.y;}
    public int getBottom() {return (int)this.y + this.height;}
    public int getLeft() {return (int)this.x;}
    public int getRight() {return (int)this.x + this.width;}

    public void setCPUImage() {
        this.normalImage = this.cpuImage;
    }

    public void setPlayerImage() {
        this.normalImage = this.playerImage;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSeleted() {
        return seleted;
    }

    public void setSeleted(int seleted) {
        this.seleted = seleted;
    }

    

}
