package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Menu.mainMenu;

/* Kyle Zimmerman
 * Jan 5/09
 * 
 * Game
 * This is the base class for any game. It preforms the tasks of making the window, preparing a drawing surface,
 * the game loop, and methods that can be overrided in subclasses to organize code.
 */
public class GameFrame extends Canvas {
	protected JFrame frame;
  	protected BufferStrategy strategy;
  	protected SpriteLoader spriteLoader;
  	protected boolean gameRunning = true;
  	protected Keyboard input;
  	long gameTime = 0;
  	long lastLoopTime = 0;
  
  	public GameFrame() {
  	
    	frame = new JFrame("Jungle WarriorZ");
    	JPanel panel = (JPanel)frame.getContentPane();
    	panel.setPreferredSize(new Dimension(790,590));
    	panel.setLayout(null);
    
    	setBounds(0,0,800,600);
    	panel.add(this);
    
    	//Don't use the slow awt rendering mode. Instead use the accelerated graphics.
    	setIgnoreRepaint(true);
    
    	frame.pack();
    	frame.setResizable(false);
    	frame.setVisible(true);
    	centerFrame(frame);
    
    	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
      		ListOfClips.InGameMusic.stop();
      		ListOfClips.Game.stop();
      		new mainMenu();
      	}});
    
    	//Allow user input
    	input = new Keyboard();
    	this.addKeyListener(input);
    
    	//Get the focus on our game.
   	 	requestFocus();
    
    	//setup our accelerated graphics
    	createBufferStrategy(2);
    	strategy = getBufferStrategy();
    
    	spriteLoader = new SpriteLoader();
    
    	this.loadContent();
  	}
  
  	public void run() {
	
    	lastLoopTime = System.currentTimeMillis();
    	while(gameRunning) {
      		this.updateTime();
     
      		//Get the Graphics surface to draw to, and clear it.
      		Graphics2D graphics = (Graphics2D)strategy.getDrawGraphics();
      		this.clear(graphics);
      		input.poll();
      		//Update the game logic
      		this.update();
      
      		//Draw the graphics to the backbuffer
      		this.draw(graphics);
      
      		//We've finished drawing, so show the backbuffer and dispose of the graphics surface.
      		this.flush(graphics);
      
      		//Slow down the loop. This should make the game run at approximatly 100fps.
      		//Look into a better method of doing this, as this is a variable time step that can easily fluctuate.
      		try { 
      			Thread.sleep(5); 
      		} catch(Exception e) {}
    	} 
  	}
  
  	private void centerFrame(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        	
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width-w) / 2;
        int y = (dim.height-w) / 2;
        
        frame.setLocation(x,y);
	}
	
  	protected void loadContent() {}
  
  	private void updateTime() {
    	gameTime = System.currentTimeMillis() - lastLoopTime;
    	lastLoopTime = System.currentTimeMillis();
  	}
  
  	private void clear(Graphics2D graphics) {
    	graphics.setColor(Color.black);
    	graphics.fillRect(0,0,800,600);
  	}
  
  	private void flush(Graphics2D graphics) {
    	graphics.dispose();
    	strategy.show();
  	}
  
  	//Add Update (Game Logic) to this method
  	protected void update() {}
  
  	//Add Drawing logic here. (This should generally just conist of drawing things, if you want to update and change
  	//values, use the update() method instead
  	protected void draw(Graphics2D graphics) {}
  	
}

