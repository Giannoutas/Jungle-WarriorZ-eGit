package Menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MultiPlayerMenu extends JFrame {

	private JButton buttonCancel;
    private JButton buttonHostGame;
    private JButton buttonJoinGame;
    private JLabel labelImage;
    private JLabel labelTitle;
    private JFrame multiPlayerFrame;
    
    public MultiPlayerMenu() {
    	
    	labelTitle = new JLabel();
        buttonCancel = new JButton();
        buttonHostGame = new JButton();
        buttonJoinGame = new JButton();
        labelImage = new JLabel();
        multiPlayerFrame = this;

		multiPlayerFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        multiPlayerFrame.getContentPane().setLayout(null);

        labelTitle.setFont(new Font("Stencil Std", 3, 36));
        labelTitle.setForeground(new Color(204, 204, 0));
        labelTitle.setText("Multi Player Menu");
        multiPlayerFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(200, 30, 460, 50);

        buttonCancel.setFont(new Font("Sylfaen", 3, 36));
        buttonCancel.setForeground(new Color(102, 102, 0));
        buttonCancel.setText("Cancel");
        multiPlayerFrame.getContentPane().add(buttonCancel);
        buttonCancel.setBounds(320, 570, 190, 40);

        buttonHostGame.setFont(new Font("Sylfaen", 3, 36));
        buttonHostGame.setForeground(new Color(102, 102, 0));
        buttonHostGame.setText("Host Game");
        multiPlayerFrame.getContentPane().add(buttonHostGame);
        buttonHostGame.setBounds(290, 200, 240, 60);

        buttonJoinGame.setFont(new Font("Sylfaen", 3, 36));
        buttonJoinGame.setForeground(new Color(102, 102, 0));
        buttonJoinGame.setText("Join Game");
        multiPlayerFrame.getContentPane().add(buttonJoinGame);
        buttonJoinGame.setBounds(290, 330, 240, 60);

        labelImage.setIcon(new ImageIcon("Res/Images/MainPicture.jpg"));
        labelImage.setText("jLabel1");
        multiPlayerFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, 0, 820, 770);
        
        ActionListener buttonListener = new MultiPlayerButtonListener();
        
        buttonHostGame.addActionListener(buttonListener);
        buttonJoinGame.addActionListener(buttonListener);
        buttonCancel.addActionListener(buttonListener);

        multiPlayerFrame.setVisible(true);
        multiPlayerFrame.pack();
        multiPlayerFrame.setSize(805,805);
        multiPlayerFrame.setResizable(false);
        centerFrame(multiPlayerFrame);
        
        multiPlayerFrame.addWindowListener(new WindowAdapter() {
      		public void windowClosing(WindowEvent e) {
        		new ExitFrame();
      		}
    	});
    }
    
    public class MultiPlayerButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonHostGame) {
				new HostMenu();
				multiPlayerFrame.dispose();
			} else if (e.getSource() == buttonJoinGame) {
				new JoinMenu();
				multiPlayerFrame.dispose();
			} else if (e.getSource() == buttonCancel) {
				multiPlayerFrame.dispose();
				new mainMenu();
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