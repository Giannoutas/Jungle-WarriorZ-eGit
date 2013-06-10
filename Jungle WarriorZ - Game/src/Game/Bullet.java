package Game;

import java.awt.*;

import javax.swing.ImageIcon;

// Edw montelopoiountai oi sfaires tou paixnidiou

public class Bullet {

	private int x,y;
	private Image img;
	private boolean visible;
	private Direction direction=Direction.Right;
	
	public Bullet(int startX, int startY) {
		x = startX;
		y = startY;
		ImageIcon newBullet = new ImageIcon("Res/Images/Bullet.png");
		img = newBullet.getImage();
		visible = true;
	}
	
	//dhmiourgei antikeimeno typoy rectangle pou boi8aei sto collision detection
	public Rectangle getBounds() {
		return new Rectangle(x,y, 25, 8);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public Image getImage() {
		return img;
	}
	
	//me auth th me8odo kounietai h sfaira
	public void move() {
	 	if(direction==Direction.Right)	
			x = x + 10;
	 	else
			x = x - 10;
	}
	

	public void setVisible(boolean isVisible) {
		visible = isVisible;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}