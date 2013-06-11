package Game;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/*
 * Ayth einai h bash gia thn dhmiourgia olwn twn xarakthrwn sto paixnidi
 */
public abstract class Character {

	protected String name;
	protected Point position;
	protected Point worldPosition;
	protected int speed;
	protected AnimatedSprite sprite;
	protected Point velocity;
	protected boolean dead = false;
	protected boolean jumping;
	protected Direction direction = Direction.Right;
	int ammo = 40;
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

	//edw ginetai h ananewsh twn gnwrismatwn toy ka8e xarakthra pou kaleitai
	public void update(float gameTime, Camera camera) {
		if (dead) {
			return;
		}
		this.move(this.velocity);
		this.worldPosition.x = this.position.x + camera.getPosition().x;
		this.worldPosition.y = this.position.y + camera.getPosition().y;
		this.velocity.x *= 0.5f;
		this.velocity.y *= 0.9f;

		sprite.update(gameTime);
	}

	//me auth th me8odo zwgrafizontai oi xarakthres analoga me thn katastash tous(p.x. nekroi)
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
	
	//allagh thw 8eshs tou antikeimenou
	public void move(int x, int y) {
		this.position.x += x;
		this.position.y += y;
	}

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
	
	//kaleitai otan pyrobolaei o kyrios xarakthras
	public void fire() {
		if (ammo > 0) {
			if(Game.soundFlag) {
				try {
					File f = new File("Res/Audio/Gun Shot.wav");
					AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(ais);
					clip.start();
				} catch(Exception e) {}
			}
			ammo--;
			Bullet z = new Bullet((position.x +30 ), (position.y + 48));
			if(direction==Direction.Right)
				z.setDirection(Direction.Right);
			else
				z.setDirection(Direction.Left);
			bullets.add(z);
			}
		}

	public Point getPosition() {
		return position;
	}

	public Point getWorldPosition() {
		return worldPosition;
	}
	
	public Point getTilePosition() {
		return new Point((this.position.x+32) / Game.TILE_SIZE, this.position.y / Game.TILE_SIZE);
	}

	public Rectangle getBounds() {
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