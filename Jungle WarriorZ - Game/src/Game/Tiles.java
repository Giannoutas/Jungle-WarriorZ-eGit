package Game;

import java.util.Hashtable;
import java.util.ArrayList;

/*
 * fortwsh twn tiles se arraylist gia eukolh anaklhsh apo allou
 */

public class Tiles {
  	private static ArrayList tiles = new ArrayList();
  	private static Hashtable lookUp = new Hashtable();
  	private static int count = 0;
  
  	public static void addTile(Tile tile) {
    	tiles.add(tile);
  	}

  	public static Tile getTile(int index) {
    	return (Tile)tiles.get(index);
  	}
  
  	public static int getCount() {
    	return tiles.size();
  	}
  
  	public static void initialize(SpriteLoader spriteLoader) {
    	tiles.add(new Tile(spriteLoader.load("Res/Tiles/empty.png"),TileType.Blank));
    	tiles.add(new Tile(spriteLoader.load("Res/Tiles/water1.png"),TileType.Blank));
    	tiles.add(new Tile(spriteLoader.load("Res/Tiles/grassground.png"),TileType.Solid));
    	tiles.add(new Tile(spriteLoader.load("Res/Tiles/Cuttree1.png"),TileType.Solid));
    	tiles.add(new Tile(spriteLoader.load("Res/Tiles/Cuttree2.png"),TileType.Solid));      
  	}
  	
}