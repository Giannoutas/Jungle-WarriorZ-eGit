package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/*
 * oi me8odoi gia ton xeirismo twn sprites
 */

public class Sprite {
  	private Image image;
  	private String path;
  	
  	public Sprite(Image image, String imagePath) {
    	this.image = image;
    	this.path = imagePath;
  	}
  
  	public int getWidth() {
    	return image.getWidth(null);
  	}
  
  	public int getHeight() {
    	return image.getHeight(null);
  	}
  	
  	public String getImagePath() {
    	return path;
  	}

  	public void draw(Graphics graphics, int x, int y) {
    	graphics.drawImage(image,x,y,null);
  	}

  	public void draw(Graphics graphics, Point position) {
    	graphics.drawImage(image,position.x,position.y,null);
  	}

  	public void drawFlipped(Graphics graphics, Point position) {
    	graphics.drawImage(image, image.getWidth(null)+position.x, position.y,position.x,image.getHeight(null)+position.y, 0,0,image.getWidth(null), image.getHeight(null),null);
  	}
  	
  	public void drawFlipped(Graphics graphics, int x, int y) {
		graphics.drawImage(image, image.getWidth(null)+x, y,x,image.getHeight(null)+y, 0,0,image.getWidth(null), image.getHeight(null),null);
	}
}