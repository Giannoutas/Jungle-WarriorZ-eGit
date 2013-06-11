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

/* 
 * Dhmiourgia para8yrou gia to paixnidi
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
    
    	input = new Keyboard();
    	this.addKeyListener(input);
    
   	 	requestFocus();
    
    	createBufferStrategy(2);
    	strategy = getBufferStrategy();
    
    	spriteLoader = new SpriteLoader();
    
    	this.loadContent();
  	}
  
  	//mesw atuhs ths me8odou trexei to paixnidi
  	public void run() {
    	lastLoopTime = System.currentTimeMillis();
    	while(gameRunning) {
      		this.updateTime();
      		Graphics2D graphics = (Graphics2D)strategy.getDrawGraphics();
      		this.clear(graphics);
      		input.poll();
      		this.update();
      		this.draw(graphics);
      		this.flush(graphics);
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
  
  	protected void update() {}
  
  	protected void draw(Graphics2D graphics) {}
  	
}