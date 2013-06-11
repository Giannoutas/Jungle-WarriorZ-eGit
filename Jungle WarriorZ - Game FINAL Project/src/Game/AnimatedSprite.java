package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

//Mesa stin klasi vriskontai methodoi gia tin dimiourgia Animations.

public class AnimatedSprite {

  	private ArrayList images;
  	private int currentFrame;
  	private int currentAnimationFrame;
  	private boolean playing, looping;
  	private int width, height;
  
 	private float elapsedTime = 0;
  	private float timePerFrame = 50f;
  	private Hashtable animations;
  	private int[] currentFrames;
  	
  	public AnimatedSprite(float timePerFrame, boolean playing,boolean looping) {
    	animations = new Hashtable();
    	images = new ArrayList();
    	this.timePerFrame = timePerFrame;
    	this.playing = playing;
    	this.looping = looping;
  	}

  	public AnimatedSprite(Sprite[] images) {
    	for (int i = 0; i < images.length; i++)
      		this.images.add(images[i]);
  	}
  
 	//Pros8etei eikona sto telos enos animation
  	public void addFrame(Sprite image) {
    	this.images.add(image);
    	newDimensions();
 	}
  
  	public void addAnimation(String name, int[] frames) {
    	animations.put(name, frames);
  	}
  
  	public int getWidth() {
    	return width;
  	}
  
  	public int getHeight() {
    	return height;
  	}
  
  	public void draw(Graphics graphics, int x, int y) {
    	((Sprite)images.get(currentFrame)).draw(graphics,x,y);
  	}
  
  	public void play() {
    	this.playing = true;
    	this.currentFrames = null;
  	}
  	
  //Ksekinaei to animation toy antikeimenou
  	public void play(String animationName) {
    	this.playing = true;
    	this.currentFrames = (int[])animations.get(animationName);
    	this.currentAnimationFrame = 0;
    	this.currentFrame = currentFrames[0];
  	}
  
  	public boolean isPlaying(String animationName) {
    	return currentFrames == (int[])animations.get(animationName);
  	}
  
  	public void setLoop(boolean loop) {
    	this.looping = loop;
  	}
  
  	//Ksekinaei apo sygkekrimeno frame ena animation
  	public void playFromFrame(int frame) {
    	this.goToFrame(frame);
    	this.play();
  	}
  
  	//Pigainei se sygkekrimeno frame tou animation
  	public void goToFrame(int frame) {
    	this.currentFrame = frame;
    
    	if (currentFrame > images.size()-1) {
      		if (looping == true)
        		currentFrame = 0;
      	else
        	currentFrame = images.size() -1;
    	}
  	}
  	
  	public void update(float gameTime) {
    	if (!playing)
      		return;
    	elapsedTime += gameTime;
    
    	if (elapsedTime > timePerFrame) {
     		elapsedTime -= timePerFrame;
     	if (currentFrames == null)
       		goToFrame(currentFrame+1);
     	else {
       		currentAnimationFrame += 1;
       		if (currentAnimationFrame >= currentFrames.length-1 && looping)
         		currentAnimationFrame = 0;
       		goToFrame(currentFrames[currentAnimationFrame]);
     		}
    	}
  	}
  
  	public void draw(Graphics graphics, Point position) {
    	((Sprite)images.get(currentFrame)).draw(graphics,position);
  	}

  	public void drawFlipped(Graphics graphics, Point position) {
    	((Sprite)images.get(currentFrame)).drawFlipped(graphics,position);
  	}

  	public void drawFlipped(Graphics graphics, int x, int y) {
    	((Sprite)images.get(currentFrame)).drawFlipped(graphics,x,y);
  	}
  
  	private void newDimensions() {
    	width = getImageWidth(0);
    	height = getImageHeight(0);
    	for(int i = 1; i < images.size(); i++) {
      		if (getImageWidth(i) > width)
        		width = getImageWidth(i);
      		if (getImageHeight(i) > height)
        		height = getImageHeight(i);
    	}
  	}
  
  	private int getImageWidth(int index) {
    	return ((Sprite)images.get(index)).getWidth();
  	}
  	
  	private int getImageHeight(int index) {
    	return ((Sprite)images.get(index)).getHeight();
  	}
}