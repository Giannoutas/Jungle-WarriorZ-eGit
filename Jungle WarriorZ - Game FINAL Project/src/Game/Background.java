package Game;

import java.awt.Graphics;

//H klassh ayth yparxei gia thn eukolh topo8ethsh twn grafikwn tou background

public class Background {

  	private Sprite sprite;
  	private int x;
  	private int y;
  	private int multiplier;
  	
  	public Background(Sprite sprite, int x, int y, int multiplier) {    
    	this.sprite = sprite;
    	this.x = x;
    	this.y = y;
    	this.multiplier = multiplier;
  	}
  	
  	//H me8odos dexetai mia metablhth typoy graphics mesw ths opoias ginetai to rendering
  	//ka8ws kai mia metablhth typoy camera poy krataei ta oria thw o8onhs
  	public void draw(Graphics graphics, Camera camera) {
    	sprite.draw(graphics,x-camera.getPosition().x/multiplier,y);
  	}
}