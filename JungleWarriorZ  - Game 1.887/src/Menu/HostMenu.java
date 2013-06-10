package Menu;

import java.awt.*;

import Game.*;
import Menu.SinglePlayerMenu.MyRunner;

import java.awt.event.*;

import java.io.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class HostMenu extends JFrame{
	
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
    private JFrame HostFrame;
    
    public HostMenu() {
    
    	labelNickname = new JLabel();
        labelTitle = new JLabel();
        textFieldNickname = new JTextField();
        labelCharacter = new JLabel();
        listCharacters = new JList();
        labelPlayerPicture = new JLabel();
        buttonStartGame = new JButton();
        buttonCancel = new JButton();
        labelImage = new JLabel();
        selectedCharacter = "Images/Character_1";
        HostFrame = this;

        HostFrame.getContentPane().setLayout(null);

        labelNickname.setFont(new Font("Stencil Std", 3, 24));
        labelNickname.setForeground(new Color(204, 204, 0));
        labelNickname.setText("Nickname:");
        HostFrame.getContentPane().add(labelNickname);
        labelNickname.setBounds(110, 110, 160, 90);

        labelTitle.setFont(new Font("Rockwell Extra Bold", 3, 36));
        labelTitle.setForeground(new Color(204, 204, 0));
        labelTitle.setText("Host Game Menu");
        HostFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(170, 20, 530, 90);

        HostFrame.getContentPane().add(textFieldNickname);
        textFieldNickname.setBounds(280, 140, 280, 30);
        textFieldNickname.setFont(new Font("Malgun Gothic", 3, 18));

        labelCharacter.setFont(new Font("Stencil Std", 3, 24));
        labelCharacter.setForeground(new Color(204, 204, 0));
        labelCharacter.setText("Character:");
        HostFrame.getContentPane().add(labelCharacter);
        labelCharacter.setBounds(90, 230, 200, 40);
        
        DefaultListModel listModel = new DefaultListModel();
		
		String[] strList = {"Character 1", "Character 2", "Character 3", "Character 4", "Character 5"};
		for(int i=0; i<strList.length; i++) {
			listModel.addElement(strList[i]);
		}
		listCharacters.setModel(listModel);
		listCharacters.setSelectedIndex(0);

        listCharacters.setFont(new Font("Stencil Std", 3, 18));
        listCharacters.setForeground(new Color(204, 204, 0));
        listCharacters.setBounds(280, 240, 170, 110);
        listCharacters.setToolTipText("");
        HostFrame.getContentPane().add(listCharacters);

		MouseListener mouseListener = new ListMouseListener();
    	listCharacters.addMouseListener(mouseListener);

        labelPlayerPicture.setIcon(new ImageIcon("Images/Character_1.jpg"));
        labelPlayerPicture.setToolTipText("");
        HostFrame.getContentPane().add(labelPlayerPicture);
        labelPlayerPicture.setBounds(530, 240, 102, 240);

        buttonStartGame.setFont(new Font("Sylfaen", 3, 36));
        buttonStartGame.setForeground(new Color(102, 102, 0));
        buttonStartGame.setText("Host Game");
        buttonStartGame.setToolTipText("");
        HostFrame.getContentPane().add(buttonStartGame);
        buttonStartGame.setBounds(130, 620, 240, 60);

        buttonCancel.setFont(new Font("Sylfaen", 3, 36));
        buttonCancel.setForeground(new Color(102, 102, 0));
        buttonCancel.setText("Cancel");
        HostFrame.getContentPane().add(buttonCancel);
        buttonCancel.setBounds(450, 620, 250, 60);

        labelImage.setIcon(new ImageIcon("Images/MainPicture.jpg"));
        HostFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, 0, 786, 1099);

		ActionListener buttonListener = new HostPlayerButtonListener();
        
        buttonStartGame.addActionListener(buttonListener);
        buttonCancel.addActionListener(buttonListener);

        HostFrame.setVisible(true);
        HostFrame.pack();
        HostFrame.setSize(790,805);
        HostFrame.setResizable(false);
        centerFrame(HostFrame);
        
        HostFrame.addWindowListener(new WindowAdapter() {
      		public void windowClosing(WindowEvent e) {
        		new MultiPlayerMenu();
      		}
    	});
    }
    
    public class ListMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent mouseEvent) {
        	JList theList = (JList) mouseEvent.getSource();
        	if (mouseEvent.getClickCount() == 1) {
        		int index = theList.locationToIndex(mouseEvent.getPoint());
          		if (index >= 0) {
            		Object o = theList.getModel().getElementAt(index);
            		if(o.toString().equals("Character 1")) {
            			selectedCharacter = "Images/Character_1.jpg";
            		} else if(o.toString().equals("Character 2")) {
            			selectedCharacter = "Images/Character_2.jpg";
            		} else if(o.toString().equals("Character 3")) {
            			selectedCharacter = "Images/Character_3.jpg";
            		} else if(o.toString().equals("Character 4")) {
            			selectedCharacter = "Images/Character_4.jpg";
            		} else if(o.toString().equals("Character 5")) {
            			selectedCharacter = "Images/Character_5.jpg";
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
    
    public class HostPlayerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonStartGame) {
				if(textFieldNickname.getText().trim().equals("")) {
					JOptionPane.showMessageDialog (null, "You should place a nickname !!", "Error !!", JOptionPane.WARNING_MESSAGE);
					textFieldNickname.setText("");
				} else {
					writeDataToFile(textFieldNickname.getText(), selectedCharacter, "On");
					MyRunner myRunner = new MyRunner(); 
					Thread myThread = new Thread(myRunner);
					myThread.start();
					dispose();
				}
			} else if (e.getSource() == buttonCancel) {
				HostFrame.dispose();
				new MultiPlayerMenu();
			}
		
		}
	}
    
    public class MyRunner implements Runnable{
    	
		  public void run(){
			  	try {
					Server serv = new Server();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  
	}
	
	private static void writeDataToFile(String nickname, String character, String soundFlag){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Texts/HostGameText.txt"));
			out.write(nickname);
			out.newLine();
			out.write(character);
			out.newLine();
			out.write(soundFlag);
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
