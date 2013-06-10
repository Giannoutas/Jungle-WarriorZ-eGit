package Game;

/*Kyle Zimmerman
 * 
 * TileType
 * This is a way to organize how a tile behaves
 */
public enum TileType
{
  Blank,
  Solid;
  
  public static TileType parse(String str)
  {
    if (str.equals("Blank"))
      return TileType.Blank;
    else 
      return TileType.Solid;
 
  }
}