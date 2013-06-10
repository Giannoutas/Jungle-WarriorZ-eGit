package Game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/*
 * Elegxetai h sygkroush metaksy xarakthrwn kai kosmou
 */

public class CollisionDetection {

	public CollisionDetection() {}

	//elegxei an o xarakthras einai panw se solid tiles
	public void collisionWithFloor(Character character, Map map) {
		if (!character.alive())
			return;
		Tile[][] tiles=map.getTiles();
		try {

			if (tiles[character.getBounds().x/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE].isSolid()) {
				if (!tiles[character.getBounds().x/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid()) {
					character.moveTo(character.getPosition().x,((character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE)*Game.TILE_SIZE-character.getSprite().getHeight());				//	System.out.println(bottomRightTile.y);
					character.jumping = false;
				}
			}
			if (tiles[(character.getBounds().x+character.getBounds().width)/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE].isSolid()) {
				if (!tiles[(character.getBounds().x+character.getBounds().width)/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid()) {
					character.moveTo(character.getPosition().x,((character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE)*Game.TILE_SIZE-character.getSprite().getHeight());
					character.jumping = false;
				}
			}		
		} catch(Exception e) {}
	}
	
	//elegxos gia kati sta deksia toy xarakthra
	public void collisionWithRightWall(Character character, Map map) {
		if (!character.alive())
			return;
		Tile[][] tiles=map.getTiles();
		try {	
			if(tiles[(character.getBounds().x+character.getBounds().width)/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid()  && character.direction == Direction.Right)    			
				character.applyForce(-character.getForce().x,0);
		} catch(Exception e) {}
	}

	//elegxos gia kati sta aristera toy xarakthra
	public void collisionWithLeftWall(Character character, Map map) {
		if (!character.alive())
			return;
		Tile[][] tiles=map.getTiles();
		try {
			if(tiles[character.getBounds().x/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid()  && character.direction == Direction.Left)    			
				character.applyForce(-character.getForce().x,0);
		} catch(Exception e) {}
	}

	//elegxos gia sygkrousi me ex8ro
	public int collisionWithEnemy(Hero hero, Enemy enemy) {
		int collision=-1;
		if(hero.getBounds().intersects(enemy.getBounds())) {
			if (hero.getPosition().y < enemy.getPosition().y-64)
				collision = 0;
			else
				collision = 1;
		}
		ArrayList bullets = Character.getBullets();
		for (int w = 0; w < bullets.size(); w++) {
			Bullet m = (Bullet) bullets.get(w);
			Rectangle m1 = m.getBounds();
			if (enemy.getBounds().intersects(m1) && !enemy.dead) {
				m.setVisible(false);
				collision = 2;
			}
		}
		return collision;
	}

	//elegxos gia to telos tou level
	public boolean collisionWithEndOfLevel(Hero hero, Goal endOfLevel) {
		if (hero.getBounds().intersects(endOfLevel.getBounds())){
			return true;
		}
		return false;
	}
}