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

/*
 * fortwsh twn sprites 
 */

public class SpriteLoader {  
  	Hashtable spriteTable;
  
  	public SpriteLoader() {
    	spriteTable = new Hashtable();
  	}
  
  	public Sprite load(String spriteName) {
    	if (spriteTable != null) {
      		if (spriteTable.containsKey(spriteName))
            	return (Sprite)spriteTable.get(spriteName);
    	}
    	BufferedImage sourceImage = null;
    	try {
      		sourceImage = ImageIO.read(new File(spriteName));
    	} catch(IOException e) {
      		displayErrorMessage("There was an error loading the file '" + spriteName + "'. Please ensure the file exsists in the specified directory");
    	}
    	GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    	Image image = graphicsConfig.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(), Transparency.BITMASK);
    	image.getGraphics().drawImage(sourceImage,0,0,null);
    	Sprite sprite = new Sprite(image,spriteName);
    	spriteTable.put(spriteName, sprite);
    	return sprite;
  	}
  
  	private void displayErrorMessage(String message) {
    	System.err.println(message);
    	System.exit(0);
  	}
  	
}