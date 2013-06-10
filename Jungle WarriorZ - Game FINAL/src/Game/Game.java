package Game;

import Menu.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
/* 
 * Mesw auths thw klashs kalountai oles oi me8odoi gia thn fortwsh kai to update twn grafikwn
 */
public class Game extends GameFrame {
	public static final int TILE_SIZE = 64;
	public static boolean gameOver = false;
	public static boolean titleScreen = true;
	private String currentLevel;
	private Map gameMap;
	private Tile tileType;
	private Sprite background;
	private Camera camera;
	private Hero character;
	private CollisionDetection collider;
	private ArrayList backgroundImages;
	private ArrayList enemies;
	private ArrayList goals;
	public static boolean soundFlag;
	public static boolean musicFlag;
	private String nickname;
	private int checkMultiPlayer;
	
	public Game(String nickname, int index) {
		super();
		new ListOfKeys();
		Tiles.initialize(spriteLoader);
		this.nickname = nickname;
		// An o arithmos index einai 1 tote exoume epilogi SinglePlayer, an einai 2 tote Multiplayer kai prepei na emfanistoun sto telos eikones.
		checkMultiPlayer = index;
	}

	//forwnoume oti xreiazetai gia na treksei to paixnidi
	protected void loadContent() {
		gameMap = new Map(spriteLoader,30,10);
		camera = new Camera(0,0,800,600);
		background = spriteLoader.load("Res/Images/TempBackground.jpg");

		String selectedSkin = null;
		try {
			FileReader fr = new FileReader("Res/Texts/StartSinglePlayerText.txt");
			FileReader fr1 = new FileReader("Res/Texts/SoundMuted.txt");
			FileReader fr2 = new FileReader("Res/Texts/MusicMuted.txt");
			BufferedReader textReader = new BufferedReader(fr);
			BufferedReader textReader1 = new BufferedReader(fr1);
			BufferedReader textReader2 = new BufferedReader(fr2);

			String textData;

			textData = textReader.readLine();
			
			String s = textReader1.readLine();
			String s2 = textReader2.readLine();
			
			if(s.equals("Off")) {
				soundFlag = true;
			} else if (s.equals("On")) {
				soundFlag = false;
			}
			
			if(s2.equals("Off")) {
				musicFlag = true;
			} else if (s2.equals("On")) {
				musicFlag = false;
			}
			
			textReader.close();
			textReader1.close();
			selectedSkin=textData;
		} catch (IOException e) {}
		
		if(musicFlag) {
			ListOfClips.InGameMusic.loop(Clip.LOOP_CONTINUOUSLY);
		}
		
		String skin1="Res/Characters/Hero/Skin 1 Stand.png";
		String skin2="Res/Characters/Hero/Skin 2 Stand.png";
        AnimatedSprite anime = new AnimatedSprite(200f,true, true);
		if(selectedSkin.equals(skin1)){
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 1 Move 1.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 1 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 1 Stand.png"));
		}
		else if(selectedSkin.equals(skin2)){
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 2 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 2 Move 1.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 2 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 2 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 2 Stand.png"));
			anime.addFrame(spriteLoader.load("Res/Characters/Hero/Skin 2 Stand.png"));
		}
		
		anime.addAnimation("walk",new int[] {0,1,2,1});
		anime.addAnimation("stand",new int[] {0});
		anime.addAnimation("dead",new int[] {5});  
		anime.addAnimation("shoot", new int[] {6});

		character = new Hero("Guy", anime,196,400,1);

		gameMap = Map.load(spriteLoader, "Res/data/Level 1.txt");
		loadLevel("Res/data/Level 1_level.txt");
		this.currentLevel = "Level 1";
		collider = new CollisionDetection();

	}

	int count = 0;
	
	//ananewsh gia pi8anh fortwsh o8onhs arxhs h telous ka8ws kai gia collision detection
	protected void update() {
		if (count == 0)
			if(soundFlag) {
				ListOfClips.Game.loop(Clip.LOOP_CONTINUOUSLY);
			}
		count++;
		if (Game.titleScreen) {
			if (input.isKeyPressed(Keys.Enter)) {
				Game.titleScreen = false;
			} else
				return;
		}
		else if (Game.gameOver) {
			if (input.isKeyDown(Keys.Enter)) {
				Game.gameOver = false;
				Game.titleScreen = true;
				loadLevel("Res/data/"+this.currentLevel+"_level.txt");
				character.moveTo(64,400);
				camera.setPosition(0,0);
				character.lives = 5;
				character.score = 0;
				character.ammo = 40;
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

	protected void draw(Graphics2D graphics) {
		if (Game.titleScreen) {
			spriteLoader.load("Res/Images/MainScreen.png").draw(graphics,0,0);
			graphics.setColor(new Color(204,0,0));
			graphics.setFont(new java.awt.Font("Rockwell Extra Bold",java.awt.Font.BOLD,28));
			graphics.drawString("Press [Enter] to Continue...",160,520);
			graphics.setColor(new Color(204, 204, 0));
			return;
		}
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
			graphics.drawImage(m.getImage(),m.getX()- camera.getPosition().x, m.getY()- camera.getPosition().y, null);
		}

		hud(graphics);
	}
	
	//elegxos gia telos level kai fortwsh epomenou
	private void checkEndOfLevel() {
		for (int i = 0; i < goals.size(); i++) {
			if (collider.collisionWithEndOfLevel(character,(Goal)goals.get(i))) {
				if(Game.soundFlag) {
					try {
						File f = new File("Res/Audio/End lvl.wav");
						AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
						Clip clip = AudioSystem.getClip();
						clip.open(ais);
						clip.start();
					} catch(Exception e) {}
				}
				this.currentLevel = ((Goal)goals.get(i)).getLevel();
				gameMap = Map.load(spriteLoader,"Res/data/"+((Goal)goals.get(i)).getLevel()+".txt");
				if(currentLevel.equals("Level 1")) {
					new mainMenu();
					ListOfClips.InGameMusic.stop();
					frame.dispose();
					if(checkMultiPlayer == 2) {
						// Kodikas gia to MultiPlayer. Sugkekrimena tha mporouse na epistrefei to score me mia methodo kai na uparxei mia sundesi me
						// tin klasi Client kai Server kai se autes tis klaseis meta na sugrinontai oi duo times kai na emfanizetai o nikits se mia 
						// othoni.
					}
					// Me kapoion tropo tha ekleine to Thread pou trexei to sugkekrimeno antikeimeno tou Game kai tha sunexize i roi tou programmatos
					// apo tis klaseis Server kai Client.
				} else {
					loadLevel("Res/data/"+((Goal)goals.get(i)).getLevel()+"_level.txt");
				}
				character.moveTo(196,400);
				character.score+= 500;
				camera.setPosition(0,0);
			}
		}
	}

	//fortwsh plhroforiwn sthn o8onh(p.x. score)
	private void hud(Graphics2D graphics) {
		graphics.setColor(new Color(204, 170, 60));
		graphics.setFont(new java.awt.Font("Rockwell Extra Bold",java.awt.Font.BOLD,30));		
		character.getSprite().draw(graphics,7,50);
		graphics.drawString("x"+character.lives,75,84);

		graphics.drawString("Score: "+character.score,240,64);
		graphics.drawString("Bullets: "+character.ammo, 500, 64);
		
		graphics.setFont(new java.awt.Font("Rockwell Extra Bold",java.awt.Font.BOLD,22));
		graphics.drawString(nickname,7,34);

		if(Game.gameOver) {
			graphics.setFont(new java.awt.Font("Rockwell Extra Bold",java.awt.Font.BOLD,38));
			graphics.drawString("GAME OVER ",260,300);
			graphics.setFont(new java.awt.Font("Rockwell Extra Bold",java.awt.Font.BOLD,26));
			graphics.drawString("Hit [Enter] to go back at main screen",130,360);
		}
	}

	//diabazei tis plhrofories apo ta txt gia thn dhmiourgia tou epomenou epipedou
	private void loadLevel(String fileName) {
		backgroundImages = new ArrayList();
		enemies = new ArrayList();
		goals = new ArrayList();

		java.io.BufferedReader reader;
		String line = new String();
		String[] elements;
		try {
			reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
			while(!line.equals("eof")) {
				line = reader.readLine();
				elements = line.split(":");
				if (elements[0].equals("image")) {
					Background img = new Background(spriteLoader.load(elements[1]),
							Integer.parseInt(elements[2]), Integer.parseInt(elements[3]),
							Integer.parseInt(elements[4]));;
							backgroundImages.add(img);
				} else if (elements[0].equals("enemy")) {
					Enemy enemy;
					if (elements[1].equals("enemy2"))
						enemy = Enemy.createEnemy2(spriteLoader,Integer.parseInt(elements[2]),Integer.parseInt(elements[3])); 
					else
						enemy = Enemy.createEnemy1(spriteLoader,Integer.parseInt(elements[2]),Integer.parseInt(elements[3]));
					enemy.patrol(Integer.parseInt(elements[4]));
					enemies.add(enemy);
				} else if (elements[0].equals("goal")) {
					goals.add(new Goal(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), elements[3]));
				}
			}
			reader.close();
		} catch(Exception e) {
			System.err.println("There was an error with the _level file");
			e.printStackTrace();
		}
	}
	
}