package MultiPlayer;

import java.io.*;
import java.net.*;
import javax.swing.*;
import Game.*;

public class Client extends JFrame {

	Socket socket;
	DataInputStream input;
	DataOutputStream output;
	private String nickname;
	
	public Client(String serverAddress, String nickname) throws Exception {
		this.nickname = nickname;
		socket = new Socket(serverAddress, 5691);
		
		DataInputStream enemyName = new DataInputStream(socket.getInputStream());
		String sameName = enemyName.readLine();
		if(!nickname.equals(sameName)) {
			//Ksekinaei ena neo Thread, pou mesa periexei tin leitourgikotita tou kurios paixnidiou.
			MyRunner myRunner = new MyRunner(); 
			Thread myThread = new Thread(myRunner);
			myThread.start();
			ListOfClips.Intro.stop();
			
			// Diavazei to score tou antipalou gia na exei prosvasi se auto kai o xristis pou ekane join.
			input = new DataInputStream(socket.getInputStream());
			int score = input.read();
			System.out.println(score);
			// Stelnei to diko tou score ston paikti pou ekane to Host.
			output = new DataOutputStream(socket.getOutputStream());
			output.write(200);
		} else {
			JOptionPane.showMessageDialog (null, "You have the same nickname with enemy. Please change !!", "Error !!", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	// Esoteriki klasi, anagkaia gia tin enraksi tou paixnidiou. Sxetizetai me threads.
	public class MyRunner implements Runnable{
	
		  public void run(){
			  	Game game = new Game(nickname,2);
				game.run();
		  }
		  
	}
	
}