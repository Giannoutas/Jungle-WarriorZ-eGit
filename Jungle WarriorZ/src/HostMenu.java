import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JScrollPane scroll;
    private JTextField textFieldNickname;
    private JFrame HostFrame;
    
    public HostMenu() {
    
    	labelNickname = new JLabel();
        labelTitle = new JLabel();
        textFieldNickname = new JTextField();
        labelCharacter = new JLabel();
        scroll = new JScrollPane();
        listCharacters = new JList();
        labelPlayerPicture = new JLabel();
        buttonStartGame = new JButton();
        buttonCancel = new JButton();
        labelImage = new JLabel();
        HostFrame = this;

        HostFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        getContentPane().add(textFieldNickname);
        textFieldNickname.setBounds(280, 140, 280, 30);

        labelCharacter.setFont(new Font("Stencil Std", 3, 24));
        labelCharacter.setForeground(new Color(204, 204, 0));
        labelCharacter.setText("Character:");
        HostFrame.getContentPane().add(labelCharacter);
        labelCharacter.setBounds(90, 230, 200, 40);

        scroll.setForeground(new Color(204, 204, 0));
        scroll.setViewportBorder(new LineBorder(new Color(204, 204, 0), 1, true));

        listCharacters.setFont(new Font("Stencil Std", 3, 18));
        listCharacters.setForeground(new Color(204, 204, 0));
        listCharacters.setModel(new AbstractListModel() {
            String[] strings = { "Character 1", "Character 2", "Character 3", "Character 4", "Character 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listCharacters.setToolTipText("");
        scroll.setViewportView(listCharacters);

        HostFrame.getContentPane().add(scroll);
        scroll.setBounds(280, 240, 170, 110);

        labelPlayerPicture.setIcon(new ImageIcon("Paiktis.jpg"));
        labelPlayerPicture.setToolTipText("");
        HostFrame.getContentPane().add(labelPlayerPicture);
        labelPlayerPicture.setBounds(530, 240, 102, 240);

        buttonStartGame.setFont(new java.awt.Font("Sylfaen", 3, 36));
        buttonStartGame.setForeground(new java.awt.Color(102, 102, 0));
        buttonStartGame.setText("Host Game");
        buttonStartGame.setToolTipText("");
        HostFrame.getContentPane().add(buttonStartGame);
        buttonStartGame.setBounds(130, 620, 240, 60);

        buttonCancel.setFont(new java.awt.Font("Sylfaen", 3, 36));
        buttonCancel.setForeground(new java.awt.Color(102, 102, 0));
        buttonCancel.setText("Cancel");
        HostFrame.getContentPane().add(buttonCancel);
        buttonCancel.setBounds(450, 620, 250, 60);

        labelImage.setIcon(new ImageIcon("jungle_hunter_by_cliffhangar.jpg"));
        HostFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, 0, 786, 1099);

		ActionListener buttonListener = new HostPlayerButtonListener();
        
        buttonStartGame.addActionListener(buttonListener);
        buttonCancel.addActionListener(buttonListener);

        HostFrame.setVisible(true);
        HostFrame.pack();
        HostFrame.setSize(805,805);
        centerFrame(HostFrame);
    	
    }
    
    public class HostPlayerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonStartGame) {
				
			} else if (e.getSource() == buttonCancel) {
				HostFrame.dispose();
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
