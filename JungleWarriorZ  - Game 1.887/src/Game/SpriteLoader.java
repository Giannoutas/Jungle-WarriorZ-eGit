package Game;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Hashtable;

public class SpriteLoader
{  
  //Used to stores Sprites once they are loaded
  //They are accesible the spriteName that was used to load it originally.
  //This will speed up spriteLoading, and reduce memory.
  Hashtable spriteTable;
  
  public SpriteLoader()
  {
    spriteTable = new Hashtable();
  }
  
  public Sprite load(String spriteName)
  {
    if (spriteTable != null)
    {
      if (spriteTable.containsKey(spriteName))
            return (Sprite)spriteTable.get(spriteName);
    }
    
    BufferedImage sourceImage = null;
    
    try{
      sourceImage = ImageIO.read(new File(spriteName));
    }
    catch(IOException e)
    {
      displayErrorMessage("There was an error loading the file '" + spriteName + "'. Please ensure the file exsists in the specified directory");
    }
    
    //Convert the loaded image into an internal java accelerated image format.
    GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    
    Image image = graphicsConfig.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(), Transparency.BITMASK);
    image.getGraphics().drawImage(sourceImage,0,0,null);
      
    //Load the accelerated image into a Sprite object for convinience.
    Sprite sprite = new Sprite(image,spriteName);
    
    //There is a warning here for some reason
    //Don't know why cause its Java (C# ftw)
    spriteTable.put(spriteName, sprite);

    return sprite;
  }
  
  private void displayErrorMessage(String message)
  {
    System.err.println(message);
    System.exit(0);
  }
}