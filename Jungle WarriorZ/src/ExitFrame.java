import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ExitFrame extends JFrame {

	private JButton buttonNO;
    private JButton buttonYES;
    private JLabel labelImage;
    private JLabel labelText;
    private JFrame exitFrame;
    private JFrame mainMenuFrame;
    
    public ExitFrame(JFrame mainMenu) {
    	
    	labelText = new javax.swing.JLabel();
        buttonNO = new javax.swing.JButton();
        buttonYES = new javax.swing.JButton();
        labelImage = new javax.swing.JLabel();
        exitFrame = this;
        mainMenuFrame = mainMenu;

        exitFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        exitFrame.getContentPane().setLayout(null);

        labelText.setFont(new Font("Stencil Std", 3, 36));
        labelText.setForeground(new Color(204, 0, 0));
        labelText.setText("Do you want to EXIT ?");
        exitFrame.getContentPane().add(labelText);
        labelText.setBounds(80, 0, 470, 80);

        buttonNO.setFont(new Font("Stencil Std", 3, 24));
        buttonNO.setForeground(new Color(240, 0, 0));
        buttonNO.setText("NO");
        exitFrame.getContentPane().add(buttonNO);
        buttonNO.setBounds(370, 120, 170, 60);

        buttonYES.setFont(new Font("Stencil Std", 3, 24));
        buttonYES.setForeground(new Color(240, 0, 0));
        buttonYES.setText("YES");
        exitFrame.getContentPane().add(buttonYES);
        buttonYES.setBounds(80, 120, 170, 60);

        labelImage.setIcon(new ImageIcon("jungle-bridge_jpg_611x226_crop_q85.jpg"));
        exitFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, 0, 610, 230);

		ActionListener buttonListener = new ExitButtonListener();
        
        buttonYES.addActionListener(buttonListener);
        buttonNO.addActionListener(buttonListener);

        exitFrame.setVisible(true);
        exitFrame.pack();
        exitFrame.setSize(620,265);
        centerFrame(exitFrame);
    }
    
    public class ExitButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == buttonYES) {
				mainMenuFrame.dispose();
				exitFrame.dispose();
			} else if (e.getSource() == buttonNO) {
				exitFrame.dispose();
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
