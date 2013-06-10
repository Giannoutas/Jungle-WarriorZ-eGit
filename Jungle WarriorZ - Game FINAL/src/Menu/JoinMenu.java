package Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import MultiPlayer.*;

public class JoinMenu extends JFrame {

	private JButton buttonJoinGame;
    private JButton buttonCancel;
    private JLabel labelImage;
    private JLabel labelNickname;
    private JLabel labelTitle;
    private JLabel labelCharacter;
    private JLabel labelPlayerPicture;
    private JList listCharacters;
    private JTextField textFieldNickname;
    private JLabel labelAddress;
    private JTextField textFieldAddress;
    private String selectedCharacter;
    private JFrame joinFrame;
    
    public JoinMenu() {
    	
    	labelNickname = new JLabel();
        labelTitle = new JLabel();
        textFieldNickname = new JTextField();
        labelCharacter = new JLabel();
        listCharacters = new JList();
        labelPlayerPicture = new JLabel();
        buttonJoinGame = new JButton();
        buttonCancel = new JButton();
        labelImage = new JLabel();
        labelAddress = new JLabel();
        textFieldAddress = new JTextField();
        selectedCharacter = "Res/Characters/Hero/Skin 1 Stand.png";
        joinFrame = this;

		joinFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        joinFrame.getContentPane().setLayout(null);

        labelNickname.setFont(new Font("Stencil Std", 3, 24));
        labelNickname.setForeground(new Color(204, 204, 0));
        labelNickname.setText("Nickname:");
        joinFrame.getContentPane().add(labelNickname);
        labelNickname.setBounds(110, 110, 160, 90);

        labelTitle.setFont(new Font("Stencil Std", 3, 36));
        labelTitle.setForeground(new Color(204, 204, 0));
        labelTitle.setText("Join Game Menu");
        joinFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(220, 20, 530, 90);

        joinFrame.getContentPane().add(textFieldNickname);
        textFieldNickname.setBounds(280, 140, 280, 30);
        textFieldNickname.setFont(new Font("Malgun Gothic", 3, 18));

        labelCharacter.setFont(new Font("Stencil Std", 3, 24));
        labelCharacter.setForeground(new Color(204, 204, 0));
        labelCharacter.setText("Character:");
        joinFrame.getContentPane().add(labelCharacter);
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
        listCharacters.setToolTipText("");
        joinFrame.getContentPane().add(listCharacters);

		MouseListener mouseListener = new ListMouseListener();
    	listCharacters.addMouseListener(mouseListener);

        labelPlayerPicture.setIcon(new ImageIcon("Res/Characters/Hero/Skin 1 Stand.png"));
        labelPlayerPicture.setToolTipText("");
        joinFrame.getContentPane().add(labelPlayerPicture);
        labelPlayerPicture.setBounds(530, 240, 102, 240);

        buttonJoinGame.setFont(new Font("Sylfaen", 3, 36));
        buttonJoinGame.setForeground(new Color(102, 102, 0));
        buttonJoinGame.setText("Join Game");
        buttonJoinGame.setToolTipText("");
        joinFrame.getContentPane().add(buttonJoinGame);
        buttonJoinGame.setBounds(130, 620, 240, 60);

        buttonCancel.setFont(new Font("Sylfaen", 3, 36));
        buttonCancel.setForeground(new Color(102, 102, 0));
        buttonCancel.setText("Cancel");
        joinFrame.getContentPane().add(buttonCancel);
        buttonCancel.setBounds(450, 620, 250, 60);
        
        labelAddress.setFont(new Font("Stencil Std", 3, 24));
        labelAddress.setForeground(new Color(204, 204, 0));
        labelAddress.setText("IP Address:");
        joinFrame.getContentPane().add(labelAddress);
        labelAddress.setBounds(90, 500, 170, 50);

        joinFrame.getContentPane().add(textFieldAddress);
        textFieldAddress.setBounds(280, 510, 290, 30);
        textFieldAddress.setFont(new Font("Malgun Gothic", 3, 18));

        labelImage.setIcon(new ImageIcon("Res/Images/MainPicture.jpg"));
        joinFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, 0, 786, 1099);

		ActionListener buttonListener = new JoinPlayerButtonListener();
        
        buttonJoinGame.addActionListener(buttonListener);
        buttonCancel.addActionListener(buttonListener);

        joinFrame.setVisible(true);
        joinFrame.pack();
        joinFrame.setSize(790,805);
        joinFrame.setResizable(false);
        centerFrame(joinFrame); 	
        
        joinFrame.addWindowListener(new WindowAdapter() {
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
    
    public class JoinPlayerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonJoinGame) {
				if(textFieldNickname.getText().trim().equals("") && (textFieldAddress.getText().trim().equals(""))) {
					JOptionPane.showMessageDialog (null, "You should place a nickname and ip address !!", "Error !!", JOptionPane.WARNING_MESSAGE);
					textFieldNickname.setText("");
					textFieldAddress.setText("");
				} else if(textFieldNickname.getText().trim().equals("")) {
					JOptionPane.showMessageDialog (null, "You should place a nickname !!", "Error !!", JOptionPane.WARNING_MESSAGE);
					textFieldNickname.setText("");
				} else if(textFieldAddress.getText().trim().equals("")) {
					JOptionPane.showMessageDialog (null, "You should place a ip address !!", "Error !!", JOptionPane.WARNING_MESSAGE);
					textFieldAddress.setText("");
				} else {
					writeDataToFile(selectedCharacter);
					try {
						new Client(textFieldAddress.getText(), textFieldNickname.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dispose();
				}
			} else if (e.getSource() == buttonCancel) {
				joinFrame.dispose();
				new MultiPlayerMenu();
			}
		
		}
	}
	
	// Eggrafi se arxeio keimenou, tin diefthinsi tou xaraktira pou epelekse o xristis.
	private void writeDataToFile(String character){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Res/Texts/JoinGameText.txt"));
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