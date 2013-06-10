package Game;

import java.util.Hashtable;
import java.util.ArrayList;

public class Tiles
{
  private static ArrayList tiles = new ArrayList();
  private static Hashtable lookUp = new Hashtable();
  private static int count = 0;
  
  public static void addTile(Tile tile)
  {
    tiles.add(tile);
  }
  
 
  
  public static Tile getTile(int index)
  {
    return (Tile)tiles.get(index);
  }
  
  public static int getCount()
  {
    return tiles.size();
  }
  
  public static void initialize(SpriteLoader spriteLoader)
  {
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/empty.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/test1.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/test2.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/test3.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/tile-sand-jump.png"),TileType.Solid));   
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/grass.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/tile-snow-ground.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/water.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/wave1.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/wave2.png"),TileType.Blank));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/brick.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/block.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/randomTile.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/MountainFloatTile.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/MountainTile.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/MountainTopTile.png"),TileType.Solid));
    tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/flowers.png"),TileType.Blank));
    
  }
}