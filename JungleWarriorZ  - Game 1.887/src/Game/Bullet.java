package Game;

import java.awt.*;

import javax.swing.ImageIcon;

public class Bullet {

	private int x,y;
	private Image img;
	private boolean visible;
	private Direction direction=Direction.Right;
	


	
	public Bullet(int startX, int startY)
	{
		x = startX;
		y = startY;
		ImageIcon newBullet = new ImageIcon("Content/Textures/bulletSprite.png");
		img = newBullet.getImage();
		visible = true;
        
		
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y, 10, 10);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public boolean getVisible()
	{
		return visible;
	}
	public Image getImage()
	{
		return img;
	}
	
	public void move()
	{
	 if(direction==Direction.Right)	
		x = x + 10;
	 else
		 x = x - 10;
		//int v=x;
		//if ( v > 700){
			//visible = false;
		   // v=0;}
		   
	}
	
	
	
	public void setVisible(boolean isVisible)//down
	{
		visible = isVisible;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	


}