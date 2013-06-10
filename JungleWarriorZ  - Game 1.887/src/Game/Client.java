package Game;

import java.io.*;

import java.net.*;

import javax.swing.JOptionPane;


public class Client {

	Socket socket;
	DataInputStream input;
	Game g = new Game();
	
	public Client(String serverAddress) throws Exception {
		//String serverAddress = JOptionPane.showInputDialog("Enter IP Address of the game server");
		socket = new Socket(serverAddress, 7897);
		g.run();
		int cliscore = g.getScore();
		input = new DataInputStream(socket.getInputStream());
		int test = input.readInt();
		if(cliscore > test) {
			System.out.println("You Win");
		}
		else if(cliscore == test) {
			System.out.println("Draw");
		}
		else {
			System.out.println("You loose");
		}

	}

}
