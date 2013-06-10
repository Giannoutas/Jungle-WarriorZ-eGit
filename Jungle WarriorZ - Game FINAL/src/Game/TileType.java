package Game;

/*
 * Oi 2 typoi tiles
 */
public enum TileType {
  	Blank,
  	Solid;
  
  	public static TileType parse(String str) {
    	if (str.equals("Blank"))
     	 	return TileType.Blank;
    	else 
      		return TileType.Solid;
 
  	}
  	
}