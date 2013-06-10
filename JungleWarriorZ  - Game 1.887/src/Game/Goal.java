package Game;

import java.awt.Rectangle;

public class Goal
{
  public int x;
  public int y;
  private String level;
  
  public Goal(int x, int y, String level)
  {
    this.x = x;
    this.y = y;
    this.level = level;
  }
  
  public String getLevel()
  {
    return level;
  }
  
  public Rectangle getBounds(){
	  return new Rectangle(x,y,64,128);
  }
}