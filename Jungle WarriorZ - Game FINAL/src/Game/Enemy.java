package Game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/* 
 * Klhronomei to character, ginetai montelopoihsh twn ex8rwn
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
    	if (dead) {
      		sprite.play("dead");
      		return;
    	}
    	super.update(gameTime, camera);
    	
    	distanceWalked += speed;
    
    	if (distanceWalked >= distance) {
      		if (this.direction == Direction.Right)
        		this.direction = Direction.Left;
      		else if (this.direction == Direction.Left)
        		this.direction = Direction.Right;
      	distanceWalked = 0;
    	}
    	if (direction == Direction.Right)
      		this.applyForce(speed,0);
    	else
      	this.applyForce(-speed,0);
  	}
  
  	public void patrol(int distance) {
    	this.originalPosition = this.position.x;
    	this.distance = distance;
  	}
  
  	public static Enemy createEnemy1(SpriteLoader spriteLoader, int tileX, int tileY) {
    	AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    	enemyAnim.addFrame(spriteLoader.load("Res/Characters/Enemy1/Enemy1 stand.png"));
    	enemyAnim.addFrame(spriteLoader.load("Res/Characters/Enemy1/Enemy1 Move 2.png"));
    	enemyAnim.addFrame(spriteLoader.load("Res/Characters/Enemy1/Enemy1 Move 2.png"));
    	enemyAnim.addFrame(spriteLoader.load("Res/Characters/Enemy1/Enemy1 Move 2.png"));
    
   	 	enemyAnim.addAnimation("walk",new int[] {0,1,2,0});
    	enemyAnim.addAnimation("dead",new int[] {2});
    
    	Enemy enemy1 = new Enemy("Enemy1", enemyAnim, tileX*64,tileY*64,1);
    	return enemy1;
  	}
  
  	public static Enemy createEnemy2(SpriteLoader spriteLoader, int tileX, int tileY) {
    	AnimatedSprite enemyAnim = new AnimatedSprite(200f,true, true);
    	enemyAnim.addFrame(spriteLoader.load("Res/Characters/Enemy2/Small monster move 1.png"));
    	enemyAnim.addFrame(spriteLoader.load("Res/Characters/Enemy2/Small monster move 2.png"));
    	enemyAnim.addFrame(spriteLoader.load("Res/Characters/Enemy2/Small monster move 1.png"));
    
    	enemyAnim.addAnimation("walk",new int[] {0,1,0});
    	enemyAnim.addAnimation("dead",new int[] {2});
    
    	Enemy enemy2 = new Enemy("Enemy2", enemyAnim, tileX*64,tileY*64,1);
    	return enemy2;
  	}
  	
  	public void die() {
  		this.dead = true;
  		if(Game.soundFlag) {
				try {
					File f = new File("Res/Audio/Die Monster.wav");
					AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				} catch(Exception e) {}
		}
  	}
}