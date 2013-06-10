package Game;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/*Kyle Zimmerman
 * Jan 7/09
 * 
 * Character
 * This is the base class for making 'Characters' or 'Actors' for the world
 * This class should not ever need to be used, use subclasses instead
 * 
 * NOTE: Consider making it abstract (or Java equivilent if thats not what its called)
 */
public abstract class Character {

	protected String name;
	protected Point position;
	protected Point worldPosition;
	protected int speed;

	protected AnimatedSprite sprite;

	protected Point velocity;
	protected boolean dead = false;
	//CHANGE THIS TO PROTECTED AND ADD ACCESS METHODS
	//THis is for testing only!!!
	protected boolean jumping;
	protected Direction direction = Direction.Right;
	//Used to make a character.
	int ammo = 100;
	static ArrayList bullets;
	
	public Character(String name, AnimatedSprite sprite, int x, int y, int speed) {
		this.name = name;
		this.sprite = sprite;
		this.position = new Point(x,y);
		this.speed = speed;
		this.worldPosition = new Point(0,0);
		this.velocity = new Point(0,0);
		this.jumping = false;
		bullets = new ArrayList();
	}

	public void update(float gameTime, Camera camera) {
		if (dead) {
			return;
		}
		//TODO: add update logic here
		this.move(this.velocity);
		this.worldPosition.x = this.position.x + camera.getPosition().x;
		this.worldPosition.y = this.position.y + camera.getPosition().y;
		this.velocity.x *= 0.5f;
		this.velocity.y *= 0.9f;

		sprite.update(gameTime);
	}

	public void draw(Graphics graphics, Camera camera) {
		if (dead) {
			sprite.play("dead");
		}
		if (direction == Direction.Right)
			sprite.draw(graphics,position.x - camera.getPosition().x, position.y - camera.getPosition().y);
		else
			sprite.drawFlipped(graphics,position.x - camera.getPosition().x, position.y - camera.getPosition().y);

	}

	public AnimatedSprite getSprite() {
		return sprite;
	}
	
	public void applyForce(int x, int y) {
		this.velocity.x += x;
		this.velocity.y += y;
	}

	public Point getForce() {
		return this.velocity;
	}

	public void setForce(int x, int y) {
		this.velocity.x = x;
		this.velocity.y = y;
	}
	
	//Moves the character by a sepecific amount
	public void move(int x, int y) {
		this.position.x += x;
		this.position.y += y;
	}

	//Moves the character by a specific amount
	public void move(Point amount) {
		this.position.x += amount.x;
		this.position.y += amount.y;
	}

	public void moveTo(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}
	
	
	public static ArrayList getBullets() {
		return bullets;
	}
	
	public void fire() {
		
		if (ammo > 0) {
			if(Game.soundFlag) {
				try {
					File f = new File("Audio/Gun Shot.wav");
					AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				} catch(Exception e) {}
			}
			ammo--;
			//The v is from the board class, which corresponds to the character's
			//position when it is jumping, resulting in the bullet being formed 
			//at a higher position when the character is at the peak of its jump
			Bullet z = new Bullet((position.x -10 ), (position.y + 10));
			if(direction==Direction.Right)
				z.setDirection(Direction.Right);
			else
				z.setDirection(Direction.Left);
			bullets.add(z);
			}
		}

	//gets the character's position in pixel coords
	public Point getPosition() {
		return position;
	}

	public Point getWorldPosition() {
		return worldPosition;
	}
	
	//Gets the characer's position in tile coords
	public Point getTilePosition() {
		return new Point((this.position.x+32) / Game.TILE_SIZE, this.position.y / Game.TILE_SIZE);
		//return new Point((this.worldPosition.x+32) / SideScroller.TILE_SIZE, this.worldPosition.y / SideScroller.TILE_SIZE);
	}

	public Rectangle getBounds() {
		//return new Rectangle(worldPosition.x, worldPosition.y, 64,128);
		//return new Rectangle(position.x, position.y, 64,128);
		return new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());
	}

	abstract void die();

	public boolean alive() {
		return !dead;
	}

	public  Direction getDirection() {
		return direction;
	}
	
}