import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class CreditsMenu extends JFrame {

	private JButton buttonCancel;
    private JLabel labelImage;
    private JLabel labelTitle;
    private JLabel labelTitleGame;
    private JScrollPane scroll;
    private JTextArea textAreaText;
    private JFrame creditsFrame;
    
    public CreditsMenu() {
    	
    	labelTitle = new JLabel();
        labelTitleGame = new JLabel();
        buttonCancel = new JButton();
        scroll = new JScrollPane();
        textAreaText = new JTextArea();
        labelImage = new JLabel();
        creditsFrame = this;

        creditsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        creditsFrame.getContentPane().setLayout(null);

        labelTitle.setFont(new Font("Stencil Std", 3, 48));
        labelTitle.setForeground(new Color(204, 204, 0));
        labelTitle.setText("Credits");
        creditsFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(300, 30, 300, 100);

        labelTitleGame.setFont(new Font("Stencil Std", 3, 24));
        labelTitleGame.setForeground(new Color(204, 204, 0));
        labelTitleGame.setText("Jungle WarriorZ");
        creditsFrame.getContentPane().add(labelTitleGame);
        labelTitleGame.setBounds(290, 100, 300, 160);

        buttonCancel.setFont(new Font("Sylfaen", 3, 36));
        buttonCancel.setForeground(new Color(153, 153, 0));
        buttonCancel.setText("Cancel");
        creditsFrame.getContentPane().add(buttonCancel);
        buttonCancel.setBounds(290, 670, 260, 50);

        scroll.setBorder(new LineBorder(new Color(204, 204, 0), 1, true));

        textAreaText.setColumns(20);
        textAreaText.setFont(new Font("Malgun Gothic", 3, 18));
        textAreaText.setRows(5);
        textAreaText.setText("Giannouta poso gamatos eisai????");
        scroll.setViewportView(textAreaText);

        creditsFrame.getContentPane().add(scroll);
        scroll.setBounds(170, 220, 520, 420);

        labelImage.setIcon(new ImageIcon("jungle_hunter_by_cliffhangar.jpg"));
        creditsFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, 0, 800, 888);

		ActionListener buttonListener = new CreditsButtonListener();
        
        buttonCancel.addActionListener(buttonListener);

        creditsFrame.setVisible(true);
        creditsFrame.pack();
        creditsFrame.setSize(805,805);
        centerFrame(creditsFrame);
    	
    }
    
    public class CreditsButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonCancel) {
				creditsFrame.dispose();
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