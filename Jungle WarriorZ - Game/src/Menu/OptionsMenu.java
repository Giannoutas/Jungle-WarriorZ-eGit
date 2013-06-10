package Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import Game.ListOfClips;

public class OptionsMenu extends JFrame{

	private JButton buttonChange;
    private JButton OKButton;
    private JButton cancelButton;
    private JCheckBox yesCheckBox;
    private JCheckBox noCheckBox;
    private JLabel labelImage;
    private JLabel labelDefault;
    private JLabel labelTitle;
    private JLabel labelSoundMuted;
    private JLabel labelControlButton;
    private JList listKey;
    private JTextField defaultButtonTextField;
    private ButtonGroup buttonGroup;
    private ArrayList<String> listData;
    private ArrayList<String> helpListData;
    private int index;
    private JFrame optionsFrame;
    private JCheckBox yesCheckBox1;
    private JCheckBox noCheckBox1;
    private JLabel labelSoundMuted1;
    private ButtonGroup buttonGroup1;
    private String soundMuted;
    private String musicMuted;
    
    public OptionsMenu() {
    	labelTitle = new JLabel();
        labelDefault = new JLabel();
        yesCheckBox = new JCheckBox();
        noCheckBox = new JCheckBox();
        yesCheckBox1 = new JCheckBox();
        noCheckBox1 = new JCheckBox();
        labelSoundMuted = new JLabel();
        labelSoundMuted1 = new JLabel();
        listKey = new JList();
        labelControlButton = new JLabel();
        defaultButtonTextField = new JTextField();
        buttonChange = new JButton();
        OKButton = new JButton();
        cancelButton = new JButton();
        labelImage = new JLabel();
        buttonGroup = new ButtonGroup();
        buttonGroup1 = new ButtonGroup();
        listData = new ArrayList<String>();
        helpListData = new ArrayList<String>();
        index = 0;
        optionsFrame = this;
        
        // Pairnei tis epiloges ixou gia ta efe kai tin mousiki apo ta analoga arxeia kai ta apothikeuei stis antistoixes metavlites.
        soundMuted = readData("Res/Texts/SoundMuted.txt");
        musicMuted = readData("Res/Texts/MusicMuted.txt");

		optionsFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        optionsFrame.getContentPane().setLayout(null);

        labelTitle.setFont(new Font("Stencil Std", 3, 36));
        labelTitle.setForeground(new Color(204, 204, 0));
        labelTitle.setText("Options Menu");
        optionsFrame.getContentPane().add(labelTitle);
        labelTitle.setBounds(260, 20, 530, 90);

        labelDefault.setFont(new Font("Stencil Std", 3, 18));
        labelDefault.setForeground(new Color(204, 204, 0));
        labelDefault.setText("Default Button");
        optionsFrame.getContentPane().add(labelDefault);
        labelDefault.setBounds(440, 320, 230, 60);
 		
 		buttonGroup.add(yesCheckBox);
 		buttonGroup.add(noCheckBox);
        
        ActionListener CheckBoxListener = new CheckBoxButtonListener();
        
        noCheckBox.setBackground(new Color(204, 204, 0));
        noCheckBox.setFont(new Font("Stencil Std", 3, 24));
        noCheckBox.setForeground(new Color(102, 102, 0));
        noCheckBox.setText("No");
        optionsFrame.getContentPane().add(noCheckBox);
        noCheckBox.setBounds(510, 240, 110, 30);
        noCheckBox.addActionListener(CheckBoxListener);

        yesCheckBox.setBackground(new Color(204, 204, 0));
        yesCheckBox.setFont(new Font("Stencil Std", 3, 24));
        yesCheckBox.setForeground(new Color(102, 102, 0));
        yesCheckBox.setText("Yes");
        optionsFrame.getContentPane().add(yesCheckBox);
        yesCheckBox.setBounds(370, 240, 110, 30);
        yesCheckBox.addActionListener(CheckBoxListener);
        
        // Elenxos gia na einai tsekarismeno to sosto CheckBox.
        if(musicMuted.equals("On")) {
			yesCheckBox.setSelected(true);
		} else {
			noCheckBox.setSelected(true);
		}
        
        labelSoundMuted.setFont(new Font("Stencil Std", 3, 24));
        labelSoundMuted.setForeground(new Color(204, 204, 0));
        labelSoundMuted.setText("Music Muted");
        optionsFrame.getContentPane().add(labelSoundMuted);
        labelSoundMuted.setBounds(110, 200, 240, 100);
        
        buttonGroup1.add(yesCheckBox1);
 		buttonGroup1.add(noCheckBox1);
        
        ActionListener CheckBoxListener1 = new CheckBoxButtonListener1();
        
        noCheckBox1.setBackground(new Color(204, 204, 0));
        noCheckBox1.setFont(new Font("Stencil Std", 3, 24));
        noCheckBox1.setForeground(new Color(102, 102, 0));
        noCheckBox1.setText("No");
        optionsFrame.getContentPane().add(noCheckBox1);
        noCheckBox1.setBounds(510, 170, 110, 30);
        noCheckBox1.addActionListener(CheckBoxListener1);

        yesCheckBox1.setBackground(new Color(204, 204, 0));
        yesCheckBox1.setFont(new Font("Stencil Std", 3, 24));
        yesCheckBox1.setForeground(new Color(102, 102, 0));
        yesCheckBox1.setText("Yes");
        optionsFrame.getContentPane().add(yesCheckBox1);
        yesCheckBox1.setBounds(370, 170, 110, 30);
        yesCheckBox1.addActionListener(CheckBoxListener1);
		
		// Elenxos gia na einai tsekarismeno to sosto CheckBox.
		if(soundMuted.equals("On")) {
			yesCheckBox1.setSelected(true);
		} else {
			noCheckBox1.setSelected(true);
		}
		
        labelSoundMuted1.setFont(new Font("Stencil Std", 3, 18));
        labelSoundMuted1.setForeground(new Color(204, 204, 0));
        labelSoundMuted1.setText("Sound Effects Muted");
        optionsFrame.getContentPane().add(labelSoundMuted1);
        labelSoundMuted1.setBounds(110, 130, 240, 100);
        
        DefaultListModel listModel = new DefaultListModel();
		
		String[] strList = {"Jump", "Right", "Left", "Shoot"};
		for(int i=0; i<strList.length; i++) {
			listModel.addElement(strList[i]);
		}
		listKey.setModel(listModel);
		listKey.setSelectedIndex(0);

        listKey.setFont(new Font("Stencil Std", 3, 18));
        listKey.setForeground(new Color(204, 204, 0));
        listKey.setBounds(180, 360, 170, 110);
        listKey.setToolTipText("");
        optionsFrame.getContentPane().add(listKey);
        
        MouseListener mouseListener = new ListMouseListener();
    	listKey.addMouseListener(mouseListener);

		readData("Res/Texts/KeyCodeText.txt", listData);

        labelControlButton.setFont(new Font("Stencil Std", 3, 24));
        labelControlButton.setForeground(new Color(204, 204, 0));
        labelControlButton.setText("Controll Button");
        optionsFrame.getContentPane().add(labelControlButton);
        labelControlButton.setBounds(110, 270, 240, 100);

        defaultButtonTextField.setBackground(new Color(204, 204, 0));
        defaultButtonTextField.setFont(new Font("Sylfaen", 3, 18));
        defaultButtonTextField.setForeground(new Color(102, 102, 0));
        String keyString = KeyEvent.getKeyText(new Integer(listData.get(0)));
        defaultButtonTextField.setText(keyString);
        optionsFrame.getContentPane().add(defaultButtonTextField);
        defaultButtonTextField.setBounds(440, 380, 170, 30);
        defaultButtonTextField.setEditable(false);

        buttonChange.setFont(new Font("Sylfaen", 3, 18));
        buttonChange.setForeground(new Color(102, 102, 0));
        buttonChange.setText("Change Button");
        optionsFrame.getContentPane().add(buttonChange);
        buttonChange.setBounds(440, 430, 170, 40);

		OKButton.setFont(new Font("Sylfaen", 3, 36));
        OKButton.setForeground(new Color(102, 102, 0));
        OKButton.setText("OK");
        OKButton.setToolTipText("");
        optionsFrame.getContentPane().add(OKButton);
        OKButton.setBounds(130, 620, 240, 60);
        
        cancelButton.setFont(new Font("Sylfaen", 3, 36));
        cancelButton.setForeground(new Color(102, 102, 0));
        cancelButton.setText("Cancel");
        optionsFrame.getContentPane().add(cancelButton);
        cancelButton.setBounds(450, 620, 250, 60);

        labelImage.setIcon(new ImageIcon("Res/Images/MainPicture.jpg"));
        optionsFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, -170, 800, 1099);
        
        ActionListener buttonListener = new OptionButtonListener();
        
        OKButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
        buttonChange.addActionListener(buttonListener);

        optionsFrame.setVisible(true);
        optionsFrame.pack();
        optionsFrame.setSize(805,805);
        optionsFrame.setResizable(false);
        centerFrame(optionsFrame);
        
        optionsFrame.addWindowListener(new WindowAdapter() {
      		public void windowClosing(WindowEvent e) {
        		new ExitFrame();
      		}
    	});
	}
	
	public class CheckBoxButtonListener1 implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == yesCheckBox1) {
				soundMuted = "On";
			} else if(e.getSource() == noCheckBox1) {
				soundMuted = "Off";
			}
		}
	}
	
	public class CheckBoxButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == yesCheckBox) {
				musicMuted = "On";
			} else if(e.getSource() == noCheckBox) {
				musicMuted = "Off";
			}
		}
	}
	
	// Elenxos pontikiou kai apokrisi sumvanton pontikiou oson afora tin allilepidrasi tis listas me tin emfanisi tou pliktrou sto defaultButtonTextField.
	public class ListMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent mouseEvent) {
        	JList theList = (JList) mouseEvent.getSource();
        	int helpInteger = 0;
        	if (mouseEvent.getClickCount() == 1) {
        		index = theList.locationToIndex(mouseEvent.getPoint());
          		if (index >= 0) {
            		Object o = theList.getModel().getElementAt(index);
            		if(o.toString().equals("Jump")) {
            			helpInteger = Integer.parseInt(listData.get(0));
            		} else if(o.toString().equals("Right")) {
            			helpInteger = Integer.parseInt(listData.get(1));
            		} else if(o.toString().equals("Left")) {
            			helpInteger = Integer.parseInt(listData.get(2));
            		} else if(o.toString().equals("Shoot")) {
            			helpInteger = Integer.parseInt(listData.get(3));
            		}
					String keyString = KeyEvent.getKeyText(new Integer(helpInteger));
					defaultButtonTextField.setText(keyString);				
          		}
        	}
        }
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	public class OptionButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			if(e.getSource() == OKButton) {
				JOptionPane.showMessageDialog (null, "The changes saved !!", "Information !!", JOptionPane.INFORMATION_MESSAGE);
				
				// Oi opoies allages eginan, apothikeuontai sta arxeia.
				writeDataToFile("Res/Texts/KeyCodeText.txt", listData);
				writeDataToFile("Res/Texts/soundMuted.txt",soundMuted);
				writeDataToFile("Res/Texts/MusicMuted.txt",musicMuted);
				if(musicMuted.equals("On")) {
					ListOfClips.Intro.stop();
				}
				optionsFrame.dispose();
				new mainMenu();
			} else if (e.getSource() == cancelButton) {
				JOptionPane.showMessageDialog (null, "The changes that have been done, will be lost !!", "Error !!", JOptionPane.WARNING_MESSAGE);
				// Oi allages den apothikeuontai sta arxeia kai ginetai epistrofi ton proigoumenon dedomenon.
				readData("Res/Texts/HelpKeyCodeText.txt", helpListData);
				writeDataToFile("Res/Texts/KeyCodeText.txt", helpListData);
				optionsFrame.dispose();
				new mainMenu();
			} else if(e.getSource() == buttonChange) {
				new KeyBoardMenu(index, listData, optionsFrame);
			}
		}
	}

	// Eggrafi epilogis ixou gia efe i mousikis se katallilo arxeio.
	private void writeDataToFile(String path, String kindOfMute) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			out.write(kindOfMute);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
		catch (NumberFormatException ee) {
			System.out.println("Error with default button !");
		}
		finally{
			if(s != null) {
				s.close();
			}
		}
	}
	
	// Diavazei tin epilogi gia ton ixo i ton efe i tis mousikis kai tin epistrefei se morfi String.
	private String readData(String path) {
		Scanner s = null;
		String kindOfMute = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(path)));
			kindOfMute = s.nextLine();
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
		return kindOfMute;
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