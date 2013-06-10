package Game;

import java.awt.Graphics;
public class Background
{
  private Sprite sprite;
  private int x;
  private int y;
  private int multiplier;
  public Background(Sprite sprite, int x, int y, int multiplier)
  {    
    this.sprite = sprite;
    this.x = x;
    this.y = y;
    this.multiplier = multiplier;
  }
  
  public void draw(Graphics graphics, Camera camera)
  {
    sprite.draw(graphics,x-camera.getPosition().x/multiplier,y);
  }
}