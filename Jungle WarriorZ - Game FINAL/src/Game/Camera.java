package Game;

import java.awt.Point;
/*
 * Auth h klash boi8aei sthn eukolh diaxeirish twn 8esewn twn antikeimenwn pou fainontai sthn o8onh
 */
public class Camera {
  	private Point position;
  	private int width;
  	private int height;
  	private Point center;
  
  	public Camera(int x, int y, int width, int height) {
    	this.position = new Point(x,y);
    	this.width = width;
    	this.height = height;
    
    	this.center = new Point(x+(int)(width/2f),y+(int)(width/2f));
  	}
  
  	public Point getPosition() {
    	return this.position;
  	}
  
  	public Point getCenter() {
    	return this.center;
  	}
  
  	public int getWidth() {
    	return this.width;
  	}
  
  	public int getHeight() {
    	return this.height; 
  	}
  
  	public void setPosition(int x, int y) {
    	this.position.x = x;
    	this.position.y = y;
  	}

  	//Me ayth th me8odo akolou8oume thn kinhsh tou character sto paixnidi
  	public void follow(Character character) {
    	Point target = character.getWorldPosition();
    	Point min = new Point(this.position.x+(int)(this.width * 0.6f),this.position.y-(int)(this.height*0.3f));
    	Point max = new Point(this.position.x+(int)(this.width * 0.6f),this.position.y-(int)(this.height*0.7f)+this.position.y);
    
    	if (target.x < min.x)
      		this.position.x += target.x - min.x;
    	else if (character.getWorldPosition().x > max.x)
      		this.position.x = target.x - max.x;
    	if (this.position.x < 0)
      		this.position.x = 0;
  }
  
}