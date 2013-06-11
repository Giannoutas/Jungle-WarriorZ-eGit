package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/*
 * kyria klash gia ton xeirismo tou paikth
 */
public class Hero extends Character {
  	public int score;
  	public int lives = 3;
  
  	public Hero(String name, AnimatedSprite sprite, int x, int y, int speed) {
    	super(name,sprite,x,y,speed);
  	}
  
  	//elegxos gia tis diafores katastaseis tou paikth
  	public void update(float gameTime, Camera camera, Keyboard input) {
    	if (dead) {
      		if (lives > 0) {
        		this.moveTo(128,400);
        		camera.setPosition(0,0);
        		this.dead = false;
        		sprite.play("stand");
      		} 
      	else
        	Game.gameOver = true;
      	return;
    	}
    	super.update(gameTime,camera);
    	keysPressed(input);
    
    	if (position.y > 900) {
      		camera.setPosition(0,0);
      		lives--;
      		die();
    	}
  	}
  
  	private void keysPressed(Keyboard input) {
    
    	if (input.isKeyDown(Keys.Right)) {
      		if (input.isKeyPressed(Keys.Right))
        		sprite.play("walk");
        	if (input.isKeyDown(Keys.Space)) {
          		if (input.isKeyPressed(Keys.Space))
            		fire();
      		}
      		this.applyForce(3,0);
      		direction = Direction.Right;
    	} else if (input.isKeyDown(Keys.Left)) {
      		if (input.isKeyPressed(Keys.Left))
        		sprite.play("walk");
      		if (input.isKeyDown(Keys.Space)) {
        		if (input.isKeyPressed(Keys.Space))
          			fire(); 
      		}
      		this.applyForce(-3,0);
      		direction = Direction.Left;
    	} else if (input.isKeyDown(Keys.Space)) {
      		if (input.isKeyPressed(Keys.Space))
        		fire();
    	} else {
      		if (!sprite.isPlaying("stand"))
        		sprite.play("stand");
    	}
    
    	if (input.isKeyPressed(Keys.Up) && !this.jumping) {
      		this.jumping = true;
      		this.applyForce(0,-32);
      		if(Game.soundFlag) {
				try {
					File f = new File("Res/Audio/Jump.wav");
					AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				} catch(Exception e) {}
			}
    	}
  	}
  	
  	public void die() {
  		this.dead = true;
  		if(Game.soundFlag) {
				try {
					File f = new File("Res/Audio/Die Hero.wav");
					AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				} catch(Exception e) {}
		}
  	}

}