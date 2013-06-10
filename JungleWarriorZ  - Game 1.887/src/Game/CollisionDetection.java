package Game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
public class CollisionDetection
{



	public CollisionDetection()
	{

	}


	public void collisionWithFloor(Character character, Map map)
	{
		if (!character.alive())
			return;



		Tile[][] tiles=map.getTiles();
		try
		{

			//Checking if  the  bottom tiles  are  solid

			if (tiles[character.getBounds().x/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE].isSolid())
			{
				if (!tiles[character.getBounds().x/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid())
				{
					character.moveTo(character.getPosition().x,((character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE)*Game.TILE_SIZE-character.getSprite().getHeight());				//	System.out.println(bottomRightTile.y);

					character.jumping = false;
				}
			}

			if (tiles[(character.getBounds().x+character.getBounds().width)/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE].isSolid())
			{
				if (!tiles[(character.getBounds().x+character.getBounds().width)/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid())
				{
					character.moveTo(character.getPosition().x,((character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE)*Game.TILE_SIZE-character.getSprite().getHeight());
					character.jumping = false;
				}
			}		



		} catch(Exception e) {}
	}



	public void collisionWithRightWall(Character character, Map map)
	{
		if (!character.alive())
			return;



		Tile[][] tiles=map.getTiles();
		try
		{	

			//Checking if the  hero can  walk  to  the  right, if  not  stop  him

			if(tiles[(character.getBounds().x+character.getBounds().width)/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid()  && character.direction == Direction.Right)    			
				character.applyForce(-character.getForce().x,0);


		} catch(Exception e) {}
	}


	public void collisionWithLeftWall(Character character, Map map)
	{
		if (!character.alive())
			return;



		Tile[][] tiles=map.getTiles();
		try
		{
			//Checking if the  hero can  walk  to  the  left, if  not  stop  him

			if(tiles[character.getBounds().x/Game.TILE_SIZE][(character.getBounds().y+character.getBounds().height)/Game.TILE_SIZE-1].isSolid()  && character.direction == Direction.Left)    			
				character.applyForce(-character.getForce().x,0);

		} catch(Exception e) {}
	}




	public int collisionWithEnemy(Hero hero, Enemy enemy)
	{
		int collision=-1;


		//Checking if the hero  collides with an enemy
		if(hero.getBounds().intersects(enemy.getBounds()))
		{

			if (hero.getPosition().y < enemy.getPosition().y-64)
				collision = 0;
			else
				collision = 1;

		}

		//Checking  if  an  enemy is shot
		ArrayList bullets = Character.getBullets();
		for (int w = 0; w < bullets.size(); w++)
		{
			Bullet m = (Bullet) bullets.get(w);
			Rectangle m1 = m.getBounds();
			if (enemy.getBounds().intersects(m1) && !enemy.dead)
			{

				m.setVisible(false);
				collision = 2;
			}

		}
		// Return  what  the  outcome of the collision should be
		return collision;
	}

	public boolean collisionWithEndOfLevel(Hero hero, Goal endOfLevel)
	{

		//Checking if the  hero  has  reached  the  end of the level
		if (hero.getBounds().intersects(endOfLevel.getBounds())){
			return true;
		}
		return false;
	}


}