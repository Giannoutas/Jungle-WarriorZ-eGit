package Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.*;
import Game.*;

public class SinglePlayerMenu extends JFrame{
	
	private JButton buttonStartGame;
    private JButton buttonCancel;
    private JLabel labelImage;
    private JLabel labelNickname;
    private JLabel labelTitle;
    private JLabel labelCharacter;
    private JLabel labelPlayerPicture;
    private JList listCharacters;
    private JTextField textFieldNickname;
    private String selectedCharacter;
    private JFrame singlePlayerFrame;
	
	public SinglePlayerMenu() {
	
		labelNickname = new JLabel();
        labelTitle = new JLabel();
        textFieldNickname = new JTextField();
        labelCharacter = new JLabel();
        listCharacters = new JList();
        labelPlayerPicture = new JLabel();
        buttonStartGame = new JButton();
        buttonCancel = new JButton();
        labelImage = new JLabel();
        selectedCharacter = "Res/Characters/Hero/Skin 1 Stand.png";
        singlePlayerFrame = this;
		
		singlePlayerFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        singlePlayerFrame.getContentPane().setLayout(null);

        labelNickname.setFont(new Font("Stencil Std", 3, 24));
        labelNickname.setForeground(new Color(204, 204, 0));
        labelNickname.setText("Nickname:");
        singlePlayerFrame.getContentPane().add(labelNickname);
        labelNickname.setBounds(110, 110, 160, 90);

        labelTitle.setFont(new Font("Stencil Std", 3, 36));
        labelTitle.setForeground(new Color(204, 204, 0));
        labelTitle.setText("Single Player Menu");
        singlePlayerFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(170, 20, 530, 90);

        singlePlayerFrame.getContentPane().add(textFieldNickname);
        textFieldNickname.setBounds(280, 140, 280, 30);
        textFieldNickname.setFont(new Font("Malgun Gothic", 3, 18));

        labelCharacter.setFont(new Font("Stencil Std", 3, 24));
        labelCharacter.setForeground(new Color(204, 204, 0));
        labelCharacter.setText("Character:");
        singlePlayerFrame.getContentPane().add(labelCharacter);
        labelCharacter.setBounds(90, 230, 200, 40);

		DefaultListModel listModel = new DefaultListModel();
		
		String[] strList = {"Character 1", "Character 2"};
		for(int i=0; i<strList.length; i++) {
			listModel.addElement(strList[i]);
		}
		listCharacters.setModel(listModel);
		listCharacters.setSelectedIndex(0);

        listCharacters.setFont(new Font("Stencil Std", 3, 18));
        listCharacters.setForeground(new Color(204, 204, 0));
        listCharacters.setBounds(280, 240, 170, 65);
        singlePlayerFrame.getContentPane().add(listCharacters);
        
        MouseListener mouseListener = new ListMouseListener();
    	listCharacters.addMouseListener(mouseListener);
        
        labelPlayerPicture.setIcon(new ImageIcon("Res/Characters/Hero/Skin 1 Stand.png"));
        labelPlayerPicture.setToolTipText("");
        singlePlayerFrame.getContentPane().add(labelPlayerPicture);
        labelPlayerPicture.setBounds(530, 240, 102, 240);

        buttonStartGame.setFont(new Font("Sylfaen", 3, 36));
        buttonStartGame.setForeground(new Color(102, 102, 0));
        buttonStartGame.setText("Start Game");
        buttonStartGame.setToolTipText("");
        singlePlayerFrame.getContentPane().add(buttonStartGame);
        buttonStartGame.setBounds(130, 620, 240, 60);

        buttonCancel.setFont(new Font("Sylfaen", 3, 36));
        buttonCancel.setForeground(new Color(102, 102, 0));
        buttonCancel.setText("Cancel");
        singlePlayerFrame.getContentPane().add(buttonCancel);
        buttonCancel.setBounds(450, 620, 250, 60);

        labelImage.setIcon(new ImageIcon("Res/Images/MainPicture.jpg"));
        singlePlayerFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, 0, 786, 1099);

		ActionListener buttonListener = new SinglePlayerButtonListener();
        
        buttonStartGame.addActionListener(buttonListener);
        buttonCancel.addActionListener(buttonListener);

        singlePlayerFrame.setVisible(true);
        singlePlayerFrame.pack();
        singlePlayerFrame.setSize(790,805);
        singlePlayerFrame.setResizable(false);
        centerFrame(singlePlayerFrame);
        
        singlePlayerFrame.addWindowListener(new WindowAdapter() {
      		public void windowClosing(WindowEvent e) {
        		new ExitFrame();
      		}
    	});
	}
	
	// Elenxos pontikiou kai apokrisi sumvanton pontikiou oson afora tin allilepidrasi tis listas me tous xaraktires kai tis emfanisis auton.
	public class ListMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent mouseEvent) {
        	JList theList = (JList) mouseEvent.getSource();
        	if (mouseEvent.getClickCount() == 1) {
        		int index = theList.locationToIndex(mouseEvent.getPoint());
          		if (index >= 0) {
            		Object o = theList.getModel().getElementAt(index);
            		if(o.toString().equals("Character 1")) {
            			selectedCharacter = "Res/Characters/Hero/Skin 1 Stand.png";
            		} else if(o.toString().equals("Character 2")) {
            			selectedCharacter = "Res/Characters/Hero/Skin 2 Stand.png";
            		}
            		labelPlayerPicture.setIcon(new ImageIcon(selectedCharacter));
          		}
        	}
        }
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}

	}
	
	// Esoteriki klasi, anagkaia gia tin enarksi tou paixnidiou. Sxetizetai me threads.
	public class MyRunner implements Runnable{
	
		  public void run(){
			  	Game game = new Game(textFieldNickname.getText(),1);
				game.run();
		  }
		  
	}
	
	public class SinglePlayerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == buttonStartGame) {
				if(textFieldNickname.getText().trim().equals("")) {
					JOptionPane.showMessageDialog (null, "Please enter a nickname !!", "Error !!", JOptionPane.WARNING_MESSAGE);
					textFieldNickname.setText("");
				} else {
					writeDataToFile(selectedCharacter);
					// Ksekinaei ena neo thread pou mesa periexei tin leitourgikotita tou kirios paixnidiou.
					MyRunner myRunner = new MyRunner();
					Thread myThread = new Thread(myRunner);
					myThread.start();
					ListOfClips.Intro.stop();
					singlePlayerFrame.dispose();
				}
			} else if (e.getSource() == buttonCancel) {
				singlePlayerFrame.dispose();
				new mainMenu();
			}
		}
	}
	
	// Eggrafi se arxeio keimenou, tin diefthinsi tou xaraktira pou epelekse o xristis.
	private void writeDataToFile(String character){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Res/Texts/StartSinglePlayerText.txt"));
			out.write(character);
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
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