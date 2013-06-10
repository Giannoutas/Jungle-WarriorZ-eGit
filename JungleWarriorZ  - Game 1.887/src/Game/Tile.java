package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
/*Kyle Zimmerman
 * 
 * Tile
 * The Tile class contains ways to handle tiles, which are 64x64pixel and are what the map is made out of. They have a Sprite to represent them
 */
public class Tile
{
  //Fields
  TileType tileType;
  Sprite sprite;
  
  //Constructor
  public Tile(Sprite sprite, TileType tileType)
  {
    this.sprite = sprite;
    this.tileType = tileType;
  }
  
  public Rectangle getBounds(int x,int y)
	{
		return new Rectangle(x,y, -64, -64);
	}
  
  //Draws the tile at tehe specified position
  public void draw(Graphics graphics, int x, int y)
  {
    this.sprite.draw(graphics,x,y);
  }
  
  public String getImagePath()
  {
    return this.sprite.getImagePath();
  }
  //Gets the TileType of the tile.
  public TileType getTileType()
  {
    return tileType;
  }
  //Returns True if tile collides with the character (IE its solid)
  public boolean isSolid()
  {
    return tileType == TileType.Solid;
  }
  

  
}