package Game;

import java.awt.Point;
/*Kyle Zimmerman
 * Jan 7/09
 * 
 * Camera
 * This class is designed to help manage the positions of all objects.
 * It provides useful methods to manage screen space
 */
public class Camera
{
  private Point position;
  private int width;
  private int height;
  private Point center;
  
  //Constructs a new Camera at position (x,y) with the specified width and height
  public Camera(int x, int y, int width, int height)
  {
    this.position = new Point(x,y);
    this.width = width;
    this.height = height;
    
    this.center = new Point(x+(int)(width/2f),y+(int)(width/2f));
  }
  
  //Methods that return position and bound information.
  public Point getPosition()
  {
    return this.position;
  }
  
  public Point getCenter()
  {
    return this.center;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height; 
  }
  
  //Sets the position of the camera at (x,y)
  public void setPosition(int x, int y)
  {
    this.position.x = x;
    this.position.y = y;
  }
  

 

  
  //Follows something, keeping it within the inner 70% of the screen
  public void follow(Character character)
  {
    Point target = character.getWorldPosition();
    //target.x -= this.position.x;
    //target.y -= this.position.y;
    
    //Get the minumum and maximum points the following target is allowed to reach before the camera moves to follow it
    Point min = new Point(this.position.x+(int)(this.width * 0.6f),this.position.y-(int)(this.height*0.3f));
    Point max = new Point(this.position.x+(int)(this.width * 0.6f),this.position.y-(int)(this.height*0.7f)+this.position.y);
    
    //check horizontal bounds, and move if we're out of them
    if (target.x < min.x)
      this.position.x += target.x - min.x;
    else if (character.getWorldPosition().x > max.x)
      //this.position.x = max.x - target.x;
      this.position.x = target.x - max.x;
      //this.position.x += target.x+64 - max.x;
      //this.position.x = target.x - max.x;
      //this.position.x = target.x - (int)(this.width * 0.8f);
    
    //make sure the camera stays inside the stage
    if (this.position.x < 0)
      this.position.x = 0;
    //TODO: add Vertical camera bound checking and following aswell. Im not sure if we're going to want this or not..?
  }
}