package Game;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class Client extends JFrame {

	Socket socket;
	DataInputStream input;
	DataOutputStream output;
	private String nickname;
	
	public Client(String serverAddress, String nickname) throws Exception {
		this.nickname = nickname;
		socket = new Socket(serverAddress, 5691);
		
		//Ξεκινάει ένα νέο thread που μέσα περιέχει την λειτουργικότητα του κυρίως παιχνιδιού.
		MyRunner myRunner = new MyRunner(); 
		Thread myThread = new Thread(myRunner);
		myThread.start();
		ListOfClips.Intro.stop();
		
		input = new DataInputStream(socket.getInputStream());
		int score = input.read();
		System.out.println(score);
		output = new DataOutputStream(socket.getOutputStream());
		output.write(200);
		
		
		
	}
	
	//Εσωτερική κλάση, αναγκαία για την έναρξη του παιχνιδιού. Σχετίζεται με threads.
	public class MyRunner implements Runnable{
	
		  public void run(){
			  	Game game = new Game(nickname,2);
				game.run();
		  }
		  
	}
	
}
