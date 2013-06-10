import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class mainMenu extends JFrame{

	private JButton buttonSinglePlayer;
    private JButton buttonMultiPlayer;
    private JButton buttonOptions;
    private JButton buttonCredits;
    private JButton buttonExit;
    private JLabel labelImage;
    private JLabel labelTitle;
    private JFrame mainMenuFrame;
    
    public mainMenu() {
    	buttonSinglePlayer = new JButton();
        buttonMultiPlayer = new JButton();
        buttonOptions = new JButton();
        buttonCredits = new JButton();
        buttonExit = new JButton();
        labelImage = new JLabel();
        labelTitle = new JLabel();
        mainMenuFrame = this;
        
		mainMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuFrame.getContentPane().setLayout(null);

        buttonMultiPlayer.setFont(new java.awt.Font("Old English Text MT", 3, 18));
        buttonMultiPlayer.setText("Multi Player");
        buttonMultiPlayer.setForeground(new java.awt.Color(102, 102, 0));
        
 
        mainMenuFrame.getContentPane().add(buttonMultiPlayer);
        buttonMultiPlayer.setBounds(290, 250, 250, 60);

        buttonOptions.setFont(new java.awt.Font("Old English Text MT", 3, 18));
        buttonOptions.setText("Options");
        buttonOptions.setForeground(new java.awt.Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonOptions);
        buttonOptions.setBounds(290, 380, 250, 60);

        buttonCredits.setFont(new java.awt.Font("Old English Text MT", 3, 18));
        buttonCredits.setText("Credits");
        buttonCredits.setForeground(new java.awt.Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonCredits);
        buttonCredits.setBounds(290, 500, 250, 60);

        buttonExit.setFont(new java.awt.Font("Old English Text MT", 3, 18));
        buttonExit.setText("Exit");
        buttonExit.setForeground(new java.awt.Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonExit);
        buttonExit.setBounds(290, 620, 250, 60);

        buttonSinglePlayer.setFont(new java.awt.Font("Old English Text MT", 3, 18));
        buttonSinglePlayer.setText("Single Player");
        buttonSinglePlayer.setForeground(new java.awt.Color(102, 102, 0));

        mainMenuFrame.getContentPane().add(buttonSinglePlayer);
        buttonSinglePlayer.setBounds(290, 130, 250, 60);
        
        labelTitle.setFont(new java.awt.Font("Stencil Std", 3, 36));
        labelTitle.setForeground(new java.awt.Color(204, 204, 0));
        labelTitle.setText("** Jungle WarriorZ **");
        mainMenuFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(190, 20, 540, 80);

        labelImage.setIcon(new ImageIcon("jungle_hunter_by_cliffhangar.jpg"));
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
        centerFrame(mainMenuFrame);
	}
	
	public class GameButtonListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonSinglePlayer) {
				new SinglePlayerMenu();
			} else if (e.getSource() == buttonMultiPlayer) {
				new MultiPlayerMenu();
			} else if (e.getSource() == buttonOptions) {
				new OptionsMenu();
			} else if (e.getSource() == buttonCredits) {
				new CreditsMenu();
			} else if (e.getSource() == buttonExit) {
				new ExitFrame(mainMenuFrame);
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
