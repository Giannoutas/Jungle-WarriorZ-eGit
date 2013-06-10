package Game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/* Kyle Zimmerman
 * 
 * AICharacter
 * AIChacater is an extension of Character which allows a character to patrol an area.
 */
public class Enemy extends Character {
  	private int distance;
  	private int originalPosition;
  	private int distanceWalked;
  	public Enemy(String name, AnimatedSprite sprite, int x, int y, int speed) {
    	super(name,sprite,x,y,speed);
    	sprite.play("walk");
  	}
  
  	public void update(float gameTime, Camera camera) {
    	//If the character is dead, set its animation to show that, and dont do anything else
    	if (dead) {
      		sprite.play("dead");
      		return;
    	}
    	super.update(gameTime, camera);
    
    	//Update the distance the character is traveled since it turned around
    	distanceWalked += speed;
    
    	//Once the character has traveled it's patrol distance turn it around, and reset the distance traveled
    	if (distanceWalked >= distance) {
      		if (this.direction == Direction.Right)
        		this.direction = Direction.Left;
      		else if (this.direction == Direction.Left)
        		this.direction = Direction.Right;
      	distanceWalked = 0;
    	}
    
    	//Move the character in the direction traveled
    	if (direction == Direction.Right)
      		this.applyForce(speed,0);
    	else
      	this.applyForce(-speed,0);
  	}
  
  	//Sets the distance the AICharacter should walk
  	public void patrol(int distance) {
    	this.originalPosition = this.position.x;
    	this.distance = distance;
  	}
  
  	//A method to help make the snail type of enemy
  	//It is static so it is called by AICharacter.createSnail()
  	public static Enemy createEnemy1(SpriteLoader spriteLoader, int tileX, int tileY) {
    	//Load the frames into the animation
    	AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    	enemyAnim.addFrame(spriteLoader.load("Characters/Enemy1/Enemy1 stand.png"));
    	enemyAnim.addFrame(spriteLoader.load("Characters/Enemy1/Enemy1 Move 2.png"));
    	enemyAnim.addFrame(spriteLoader.load("Characters/Enemy1/Enemy1 Move 2.png"));
    	enemyAnim.addFrame(spriteLoader.load("Characters/Enemy1/Enemy1 Move 2.png"));
    
   	 	//Register animations
   	 	enemyAnim.addAnimation("walk",new int[] {0,1,2,0});
    	enemyAnim.addAnimation("dead",new int[] {2});
    
    	//Create and return the new snail with the animations we just made
    	Enemy snail = new Enemy("Snail", enemyAnim, tileX*64,tileY*64,1);
    	return snail;
  	}
  
  	//A method to help make the spider type of enemy
  	//It is static so it is called by AICharacter.createSpider()
  	public static Enemy createEnemy2(SpriteLoader spriteLoader, int tileX, int tileY) {
    	//Load the frames into the animation
    	AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    	enemyAnim.addFrame(spriteLoader.load("Characters/Enemy2/walk1.png"));
    	enemyAnim.addFrame(spriteLoader.load("Characters/Enemy2/walk2.png"));
    	enemyAnim.addFrame(spriteLoader.load("Characters/Enemy2/dead.png"));
    
    	//Register animations
    	enemyAnim.addAnimation("walk",new int[] {0,1,0});
    	enemyAnim.addAnimation("dead",new int[] {2});
    
    	//Create and return the new spider with the animations we just made
    	Enemy spider = new Enemy("Spider", enemyAnim, tileX*64,tileY*64,1);
    	return spider;
  	}
  	
  	public void die() {
  		this.dead = true;
  		if(Game.soundFlag) {
				try {
					File f = new File("Audio/Die Monster.wav");
					AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				} catch(Exception e) {}
		}
  	}
  	
}