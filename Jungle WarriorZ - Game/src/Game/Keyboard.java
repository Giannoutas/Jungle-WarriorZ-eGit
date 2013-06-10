package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * oles oi me8odoi pou sxetizontai me to pathma plhktrwn
 */
public class Keyboard implements KeyListener {
  	private static final int KEY_COUNT = 256;
  	private enum KeyState {
    	Up,
    	Pressed,
    	Down
  	}
  	private boolean[] currentKeys = null;
  	private KeyState[] keys = null;
  
  	public Keyboard() {
    	currentKeys = new boolean[KEY_COUNT];
    	keys = new KeyState[KEY_COUNT];
    	for (int i = 0; i < KEY_COUNT; i++)
      		keys[i] = KeyState.Up;
  	}
  
  	public synchronized void poll() {
    	for(int i = 0; i < KEY_COUNT; ++i) {      
      		if (currentKeys[i]) {
        		if( keys[i] == KeyState.Up)
         			keys[i] = KeyState.Pressed;
        		else
          			keys[i] = KeyState.Down;
      		} else {
        		keys[i] = KeyState.Up;
      		}
    	}
  	}
  
  	public synchronized void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
    	if (keyCode >= 0 && keyCode < KEY_COUNT) {
      		currentKeys[keyCode] = true;
    	}
  	}
  
  	public synchronized void keyReleased(KeyEvent e) {
    	int keyCode = e.getKeyCode();
    	if (keyCode >= 0 && keyCode < KEY_COUNT) {
     	 	currentKeys[keyCode] = false;
    	}
  	}

  	public boolean isKeyDown (int keyCode) {
    	return keys[keyCode] == KeyState.Down || keys[keyCode] == KeyState.Pressed;
  	}

  	public boolean isKeyPressed(int keyCode) {
    	return keys[keyCode] == KeyState.Pressed;
  	}

  	public boolean isKeyUp(int keyCode) {
    	return keys[keyCode] == KeyState.Up;
  	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}