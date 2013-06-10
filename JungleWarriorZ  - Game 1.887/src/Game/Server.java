package Game;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Menu.SinglePlayerMenu.MyRunner;

public class Server {

	JPanel serverPanel = new JPanel();
	ServerSocket serverSocket;
	Socket socket;
	DataOutputStream output;
	InetAddress inetAddress;
	String ip;
	Game g = new Game();
	
	public Server() throws Exception {
		inetAddress = inetAddress.getLocalHost();
		ip = inetAddress.getHostAddress();
		JOptionPane.showMessageDialog(null, "The ip of the game server is: " +ip+ "\n Waiting for incoming connections...");
		serverSocket = new ServerSocket(7897);
		socket = serverSocket.accept();
		g.run();
		int servscore = g.getScore();
		output = new DataOutputStream(socket.getOutputStream());
		output.writeInt(servscore);
		serverSocket.close();
	}

}
