package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
/*
 * oles oi me8odoi xeirismou tiles
 */
public class Tile {
  	TileType tileType;
  	Sprite sprite;
  
  	public Tile(Sprite sprite, TileType tileType) {
    	this.sprite = sprite;
    	this.tileType = tileType;
  	}
  
  	public Rectangle getBounds(int x,int y) {
		return new Rectangle(x,y, -64, -64);
	}
  
  	public void draw(Graphics graphics, int x, int y) {
    	this.sprite.draw(graphics,x,y);
  	}
  
  	public String getImagePath() {
    	return this.sprite.getImagePath();
  	}
  	
  	public TileType getTileType() {
    	return tileType;
  	}
  	
	public boolean isSolid() {
		return tileType == TileType.Solid;
	}
  
}