package Game;

import java.awt.Graphics;
import java.awt.Point;
import java.io.*;

/*
 * h klash poy einai ypey8hnh gia twn forwsh twn xartwn
 */

public class Map {
	Tile[][] tiles;
	int width;
	int height;

	public Map(SpriteLoader spriteLoader, int width, int height) {
		tiles = new Tile[width][height];
		this.width = width;
		this.height = height;
		initTiles(spriteLoader);
	}

	private void initTiles(SpriteLoader spriteLoader) {
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				tiles[x][y] = new Tile(spriteLoader.load("Res/Tiles/empty.png"), TileType.Blank);
			}
		}
	}

	public void setTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void draw(Graphics graphics, Point position) {
		this.draw(graphics,-position.x, -position.y);
	}

	public void draw(Graphics graphics, int offsetX, int offsetY) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				tiles[x][y].draw(graphics,offsetX + x*Game.TILE_SIZE,offsetY + y*Game.TILE_SIZE);
			}
		}
	}

	//diabazei mege8os xarti apo txt kai topo8etei ta stoixeia pou yparxoun sto arxeio
	public static Map load(SpriteLoader spriteLoader, String fileName) {
		java.io.BufferedReader reader;
		String line;
		String[] elements;
		Map map = null;
		try {
			reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
			line = reader.readLine();
			elements = line.split(",");
			map = new Map(spriteLoader,Integer.parseInt(elements[0]),Integer.parseInt(elements[1]));
			for (int h = 0; h < map.height; h++) {
				for (int w = 0; w < map.width; w++) {
					line = reader.readLine();
					elements = line.split(":");
					map.setTile(w,h,new Tile(spriteLoader.load(elements[0]),TileType.parse(elements[1])));
				}
			}
			reader.close();
		}
		catch(Exception e) {}
		return map;
	}
}