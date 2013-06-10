package Game;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Menu.SinglePlayerMenu.MyRunner;

public class Server {

	private JPanel serverPanel = new JPanel();
	private ServerSocket serverSocket;
	private Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	private InetAddress inetAddress;
	private String ip;
	private String nickname;
	
	public Server(String nickname) throws Exception {
		this.nickname = nickname;
		URL myIP = new URL("http://api.externalip.net/ip/");
	    BufferedReader in = new BufferedReader(new InputStreamReader(myIP.openStream()));
	    ip = in.readLine();
		JOptionPane.showMessageDialog(null, "The ip of the game server is: " +ip+ "\n Waiting for incoming connections...");
		serverSocket = new ServerSocket(7897);
		socket = serverSocket.accept();
		
		//�������� ��� ��� thread ��� ���� �������� ��� ��������������� ��� ������ ����������.
		MyRunner myRunner = new MyRunner(); 
		Thread myThread = new Thread(myRunner);
		myThread.start();
		ListOfClips.Intro.stop();
		output = new DataOutputStream(socket.getOutputStream());
		output.writeInt(100);
		input = new DataInputStream(socket.getInputStream());
		int score = input.read();
		System.out.println(score);
	}
	
	//��������� �����, �������� ��� ��� ������ ��� ����������. ���������� �� threads.
	public class MyRunner implements Runnable{
	
		  public void run(){
			  	Game game = new Game(nickname,2);
				game.run();
		  }
		  
	}

}
