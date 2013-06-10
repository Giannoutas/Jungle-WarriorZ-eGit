package Game;

import Menu.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.DataLine;
/* Kyle Zimmerman
 * Jan 5/09
 * 
 * SideScroller
 * This will be the main class that will make loading, drawing, and updating very easy.
 */
public class Game extends GameFrame {
	public static final int TILE_SIZE = 64;
	public static boolean gameOver = false;
	public static boolean titleScreen = true;
	public static boolean endingScreen = false;
	private String currentLevel;

	private Map gameMap;
	private Tile tileType;
	private Sprite background;
	private Camera camera;
	//private AudioPlayer audioPlayer;
	private javax.sound.sampled.Clip backgroundMusic;

	private Hero character;
	private CollisionDetection collider;
	private ArrayList backgroundImages;
	private ArrayList enemies;
	private ArrayList goals;
	public Game() {
		super();
		new ListOfKeys();
		Tiles.initialize(spriteLoader);
	}

	//Load all content into memory in this method.
	protected void loadContent() {
		//audioPlayer = new AudioPlayer();
		gameMap = new Map(spriteLoader,30,10);
		camera = new Camera(0,0,800,600);
		background = spriteLoader.load("Content/Textures/jungle2.jpg");

		//Main Character
		
		String selectedSkin = null;
		try {
			FileReader fr=new FileReader("Texts/StartSinglePlayerText.txt");
			BufferedReader  textReader=new BufferedReader(fr);
			
			int numberOfLines=3;
			String[] textData=new String[numberOfLines];
			
			
			
			for(int i=0;i<numberOfLines;i++){
				textData[i]=textReader.readLine();
				
			}
			textReader.close();
			selectedSkin=textData[1];
		
			
		} catch (IOException e) {
			
		}


		

		String skin1="Content/Textures/Characters/Essay/Skin 1 Stand.png";
		String skin2="Content/Textures/Characters/Essay/Takis skin2.png";
        AnimatedSprite anime = new AnimatedSprite(200f,true, true);
		if(selectedSkin.equals(skin1)){


			

			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Skin 1 Move 1.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/bullet.png"));

	
			
		}
		else if(selectedSkin.equals(skin2)){
			

			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Takis skin2.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Takis skin2 Mov.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Takis skin2.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Takis skin2.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Takis skin2.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/Characters/Essay/Takis skin2.png"));
			anime.addFrame(spriteLoader.load("Content/Textures/bullet.png"));

		

			
		}
		
		anime.addAnimation("walk",new int[] {0,1,2,1});
		anime.addAnimation("stand",new int[] {0});
		anime.addAnimation("dead",new int[] {5});  
		anime.addAnimation("shoot", new int[] {6});

		
		character = new Hero("Guy", anime,196,400,1);

		//gameMap.save("testmap.txt");

		gameMap = Map.load(spriteLoader, "data/Level 1.txt");
		loadLevel("data/Level 1_level.txt");
		this.currentLevel = "Level 1";
		//Background Music
		//audioPlayer.play("Content/Audio/bgMusic.wav",true);
		//daudioPlayer.play("Content/Audio/bgMusic.aiff",true);
		collider = new CollisionDetection();

	}

	int count = 0;
	//Add Update (Game Logic) to this method
	protected void update() {
		if (count == 0)
			Sound.play("Content/Audio/Game.wav",true);

		count++;
		if (Game.titleScreen) {
			if (input.isKeyPressed(Keys.Space))
				Game.titleScreen = false;
			else
				return;
		} /*else if (Game.endingScreen) {
      		if (input.isKeyPressed(Keys.Space))
        		Game.endingScreen = false;
      		else
        		return;
    	} */
		else if (Game.gameOver) {
			if (input.isKeyDown(Keys.Space)) {
				Game.gameOver = false;
				Game.titleScreen = true;
				loadLevel("data/"+this.currentLevel+"_level.txt");
				character.moveTo(64,400);
				camera.setPosition(0,0);
				character.lives = 15;
				character.score = 0;
				character.ammo=100;
			}
		}
		character.move(0,10);

		character.update(gameTime, camera, input);

		if (character.alive()) camera.follow(character);


		collider.collisionWithFloor(character,gameMap);
		collider.collisionWithLeftWall(character, gameMap);
		collider.collisionWithRightWall(character, gameMap);

		for(int i = 0; i < enemies.size(); i++) {
			Enemy enemy = ((Enemy)enemies.get(i));
			enemy.move(0,10);

			collider.collisionWithFloor(enemy,gameMap);
			collider.collisionWithLeftWall(enemy, gameMap);
			collider.collisionWithRightWall(enemy, gameMap);
			enemy.update(gameTime,camera);
			if (character.alive() && enemy.alive()) {
				if (collider.collisionWithEnemy(character,enemy) == 0) {
					enemy.die();
					character.score+= 100;
				} else if (collider.collisionWithEnemy(character,enemy) == 1) {
					character.die();
					character.lives--;
				} else if(collider.collisionWithEnemy(character,enemy) == 2) {
					enemy.die();
					character.score+= 50;
				}
			}
		}
		ArrayList bullets = Character.getBullets();
		for (int w = 0; w < bullets.size(); w++) {
			Bullet m = (Bullet) bullets.get(w);
			if (m.getVisible())
				m.move();
			else
				bullets.remove(w);
		}
		checkEndOfLevel();
	}

	//Add Drawing logic here. (This should generally just conist of drawing things, if you want to update and change
	//values, use the update() method instead
	protected void draw(Graphics2D graphics) {
		if (Game.titleScreen) {
			spriteLoader.load("Content/Textures/titleScreen.png").draw(graphics,0,0);
			graphics.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,28));
			graphics.drawString("Press [Space] to Continue...",220,500);
			return;
		} /*else if (Game.endingScreen) {
      		spriteLoader.load("Content/Textures/ending.jpg").draw(graphics,0,0);
      		graphics.setFont(new java.awt.Font("Arial",java.awt.Font.BOLD,28));
      		graphics.drawString("Press [Space] to Continue...",220,500);
      		return;
    	}*/
		background.draw(graphics,0,0);
		for (int i = 0; i < backgroundImages.size(); i++) {
			((Background)backgroundImages.get(i)).draw(graphics,camera);
		}
		gameMap.draw(graphics,camera.getPosition());
		character.draw(graphics,camera);

		for(int i = 0; i < enemies.size(); i++) {
			((Enemy)enemies.get(i)).draw(graphics,camera);
		}

		ArrayList bullets = Character.getBullets();
		for (int w = 0; w < bullets.size(); w++) {
			Bullet m = (Bullet) bullets.get(w);
			//if (m.getVisible())
				//{
			//((Bullet) bullets.get(w)).draw(graphics,camera);
			//if(character.getDirection()==Direction.Right)
			graphics.drawImage(m.getImage(),m.getX()- camera.getPosition().x, m.getY()- camera.getPosition().y, null);


			//System.out.println(character.getPosition());

			//System.out.println(m.getX());





			//}

		}



		hud(graphics);
	}
	private void checkEndOfLevel() {
		for (int i = 0; i < goals.size(); i++) {
			if (collider.collisionWithEndOfLevel(character,(Goal)goals.get(i))) {
				Sound.play("Content/Audio/victory.wav");
				//Load new map/level here
				this.currentLevel = ((Goal)goals.get(i)).getLevel();
				gameMap = Map.load(spriteLoader,"data/"+((Goal)goals.get(i)).getLevel()+".txt");
				if(currentLevel.equals("Level 1")) {
					Game.endingScreen=true;
					new CreditsMenu();
					frame.dispose();
				} else {
					loadLevel("data/"+((Goal)goals.get(i)).getLevel()+"_level.txt");

				}
				character.moveTo(196,400);
				character.score+= 500;
				camera.setPosition(0,0);
			}
		}
	}

	private void playBackgroundMusic() {}

	private void hud(Graphics2D graphics) {
		graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,32));
		character.getSprite().draw(graphics,0,-32);
		graphics.drawString("x"+character.lives,68,64);

		graphics.drawString("Score: "+character.score,350,64);
		graphics.drawString("Bullets: "+character.ammo, 550, 64);

		if(Game.gameOver) {
			graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,40));
			graphics.drawString("GAME OVER ",300,300);
			graphics.setFont(new java.awt.Font("Arial",java.awt.Font.PLAIN,28));
			graphics.drawString("Hit [Space] to go back to the main menu",220,360);
		}
	}

	private void loadLevel(String fileName) {
		backgroundImages = new ArrayList();
		enemies = new ArrayList();
		goals = new ArrayList();

		java.io.BufferedReader reader;
		String line = new String();
		String[] elements;
		try {
			reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
			//Keep looping through the level file until we reach the end
			while(!line.equals("eof")) {
				//Read the new line
				line = reader.readLine();
				//Split that line into its elements
				elements = line.split(":");
				//Check to see if we're dealing with an image
				if (elements[0].equals("image")) {
					Background img = new Background(spriteLoader.load(elements[1]),
							Integer.parseInt(elements[2]), Integer.parseInt(elements[3]),
							Integer.parseInt(elements[4]));;
							backgroundImages.add(img);
				} else if (elements[0].equals("enemy")) {
					//Check to see if we're dealing with an enemy
					//Create the new enemy with the specified properties
					Enemy enemy;
					if (elements[1].equals("spider"))
						enemy = Enemy.createEnemy2(spriteLoader,Integer.parseInt(elements[2]),Integer.parseInt(elements[3])); 
					else
						enemy = Enemy.createEnemy1(spriteLoader,Integer.parseInt(elements[2]),Integer.parseInt(elements[3]));
					enemy.patrol(Integer.parseInt(elements[4]));
					//Add the enemy to the list of enemies
					enemies.add(enemy);
				} else if (elements[0].equals("goal")) {
					//Check to see if we're dealing with a goal
					goals.add(new Goal(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), elements[3]));
				}
			}
			//Done reading the file, so close it
			reader.close();
		} catch(Exception e) {
			System.err.println("There was an error with the _level file");
			e.printStackTrace();
		}
	}
	
	public int getScore() {
		return character.score;
	}
}