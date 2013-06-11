package Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;

import Game.ListOfClips;

public class mainMenu extends JFrame{

	private JButton buttonSinglePlayer;
    private JButton buttonMultiPlayer;
    private JButton buttonOptions;
    private JButton buttonCredits;
    private JButton buttonExit;
    private JLabel labelImage;
    private JLabel labelTitle;
    private JFrame mainMenuFrame;
    private ArrayList<String> listData;
    private ArrayList<String> helpListData;
    private String musicMuted;
    
    public mainMenu() {
    	
    	// Anagnosi arxeiou gia ton elenxo tis mousikis.
    	readData();
    	
    	if(musicMuted.equals("Off")) {
    		ListOfClips.Intro.loop(Clip.LOOP_CONTINUOUSLY);
    	}
    	
    	buttonSinglePlayer = new JButton();
        buttonMultiPlayer = new JButton();
        buttonOptions = new JButton();
        buttonCredits = new JButton();
        buttonExit = new JButton();
        labelImage = new JLabel();
        labelTitle = new JLabel();
        mainMenuFrame = this;
        listData = new ArrayList<String>();
        helpListData = new ArrayList<String>();
        
        // Diavazei tis default epiloges pliktron pou eginan stin main, kai tis ekxorei se ena allo voithitiko arxeio keimenou.
        readData("Res/Texts/KeyCodeText.txt", listData);
		writeDataToFile("Res/Texts/HelpKeyCodeText.txt", listData);

        mainMenuFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        mainMenuFrame.getContentPane().setLayout(null);

        buttonMultiPlayer.setFont(new Font("Old English Text MT", 3, 18));
        buttonMultiPlayer.setText("Multi Player");
        buttonMultiPlayer.setForeground(new Color(102, 102, 0));
        
        mainMenuFrame.getContentPane().add(buttonMultiPlayer);
        buttonMultiPlayer.setBounds(290, 250, 250, 60);

        buttonOptions.setFont(new Font("Old English Text MT", 3, 18));
        buttonOptions.setText("Options");
        buttonOptions.setForeground(new Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonOptions);
        buttonOptions.setBounds(290, 380, 250, 60);

        buttonCredits.setFont(new Font("Old English Text MT", 3, 18));
        buttonCredits.setText("Credits");
        buttonCredits.setForeground(new Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonCredits);
        buttonCredits.setBounds(290, 500, 250, 60);

        buttonExit.setFont(new Font("Old English Text MT", 3, 18));
        buttonExit.setText("Exit");
        buttonExit.setForeground(new Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonExit);
        buttonExit.setBounds(290, 620, 250, 60);

        buttonSinglePlayer.setFont(new Font("Old English Text MT", 3, 18));
        buttonSinglePlayer.setText("Single Player");
        buttonSinglePlayer.setForeground(new Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonSinglePlayer);
        buttonSinglePlayer.setBounds(290, 130, 250, 60);
        
        labelTitle.setFont(new Font("Stencil Std", 3, 36));
        labelTitle.setForeground(new Color(204, 204, 0));
        labelTitle.setText("** Jungle WarriorZ **");
        mainMenuFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(190, 20, 540, 80);
        labelImage.setIcon(new ImageIcon("Res/Images/MainPicture.jpg"));
        mainMenuFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, -140, 800, 920);
        
        ActionListener buttonListener = new GameButtonListener();
        
        buttonSinglePlayer.addActionListener(buttonListener);
        buttonMultiPlayer.addActionListener(buttonListener);
        buttonOptions.addActionListener(buttonListener);
        buttonCredits.addActionListener(buttonListener);
        buttonExit.addActionListener(buttonListener);
        
		mainMenuFrame.setVisible(true);
        mainMenuFrame.pack();
        mainMenuFrame.setSize(805,805);
        mainMenuFrame.setResizable(false);
        centerFrame(mainMenuFrame);
        
        mainMenuFrame.addWindowListener(new WindowAdapter() {
      		public void windowClosing(WindowEvent e) {
        		new ExitFrame();
      		}
    	});
	}
	
	public class GameButtonListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonSinglePlayer) {
				new SinglePlayerMenu();
				mainMenuFrame.dispose();
			} else if (e.getSource() == buttonMultiPlayer) {
				new MultiPlayerMenu();
				mainMenuFrame.dispose();
			} else if (e.getSource() == buttonOptions) {
				new OptionsMenu();
				mainMenuFrame.dispose();
			} else if (e.getSource() == buttonCredits) {
				new CreditsMenu();
				mainMenuFrame.dispose();
			} else if (e.getSource() == buttonExit) {
				new ExitFrame();
			}
		}
		
	}
	
	// Diavazei tous kodikous ton pliktron apo ena arxeio kai ta apothikeuei se mia domi dedomenon.
	private void readData(String path, ArrayList<String> list) {
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(path)));
			while(s.hasNextLine()){
				list.add(s.nextLine());
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
		catch (NumberFormatException e) {
			System.out.println("Error with default button !");
		}
		finally{
			if(s != null) {
				s.close();
			}
		}
	}
	
	// Grafei se ena arxeio tous kodikous ton pliktron pou vriskontai se mia domi dedomenon.
	private void writeDataToFile(String path, ArrayList<String> myList){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			out.write(myList.get(0));
			out.newLine();
			out.write(myList.get(1));
			out.newLine();
			out.write(myList.get(2));
			out.newLine();
			out.write(myList.get(3));
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Diavazei tin epilogi gia ixo
	private void readData() {
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader("Res/Texts/MusicMuted.txt")));
			musicMuted = s.nextLine();
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
		catch (NumberFormatException ee) {
			System.out.println("Error with default button !");
		}
		finally{
			if(s != null) {
				s.close();
			}
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
}