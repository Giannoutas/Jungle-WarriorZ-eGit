package Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class KeyBoardMenu extends JFrame {
	
	private JButton buttonQ, buttonW, buttonE, buttonR, buttonT, buttonY, buttonU, buttonI, buttonO, buttonP, buttonA, buttonS, buttonD, buttonF, buttonG, buttonH, buttonJ, buttonK, buttonL, buttonZ, buttonX, buttonC, buttonV, buttonB, buttonN, buttonM;
	private JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
	private JButton buttonSpace, buttonUp, buttonDown, buttonLeft, buttonRight; 
	private JLabel labelImage;
	private KeyEvent event;
	private int index, numberKeyBoard;
	private ArrayList<String> listData;
	private JFrame 	keyBoardFrame, optionsFrame;
	boolean doubleButtonflag;
    
    public KeyBoardMenu(int index, ArrayList<String> listData, final JFrame optionsFrame) {
    
    	buttonQ = new JButton();
        buttonW = new JButton();
        buttonE = new JButton();
        buttonR = new JButton();
        buttonT = new JButton();
        buttonY = new JButton();
        buttonO = new JButton();
        buttonU = new JButton();
        buttonP = new JButton();
        buttonI = new JButton();
        button9 = new JButton();
        button0 = new JButton();
        button7 = new JButton();
        button1 = new JButton();
        button8 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        buttonL = new JButton();
        buttonJ = new JButton();
        buttonA = new JButton();
        buttonK = new JButton();
        buttonS = new JButton();
        buttonD = new JButton();
        buttonF = new JButton();
        buttonG = new JButton();
        buttonH = new JButton();
        buttonM = new JButton();
        buttonZ = new JButton();
        buttonX = new JButton();
        buttonC = new JButton();
        buttonV = new JButton();
        buttonB = new JButton();
        buttonN = new JButton();
        buttonSpace = new JButton();
        buttonRight = new JButton();
        buttonLeft = new JButton();
        buttonDown = new JButton();
        buttonUp = new JButton();
        labelImage = new JLabel();
        event = null;
        numberKeyBoard = 0;
        this.listData = listData;
        this.index = index;
        this.optionsFrame = optionsFrame;
        keyBoardFrame = this;

        keyBoardFrame.getContentPane().setLayout(null);

        button3.setFont(new Font("Sylfaen", 1, 14));
        button3.setForeground(new Color(102, 102, 0));
        button3.setText("3");
        keyBoardFrame.getContentPane().add(button3);
        button3.setBounds(130, 20, 50, 40);

        button4.setFont(new Font("Sylfaen", 1, 14));
        button4.setForeground(new Color(102, 102, 0));
        button4.setText("4");
        keyBoardFrame.getContentPane().add(button4);
        button4.setBounds(190, 20, 50, 40);

        button5.setFont(new Font("Sylfaen", 1, 14));
        button5.setForeground(new Color(102, 102, 0));
        button5.setText("5");
        button5.setToolTipText("");
        keyBoardFrame.getContentPane().add(button5);
        button5.setBounds(250, 20, 50, 40);

        button6.setFont(new Font("Sylfaen", 1, 14));
        button6.setForeground(new Color(102, 102, 0));
        button6.setText("6");
        keyBoardFrame.getContentPane().add(button6);
        button6.setBounds(310, 20, 50, 40);

        button7.setFont(new Font("Sylfaen", 1, 14));
        button7.setForeground(new Color(102, 102, 0));
        button7.setText("7");
        keyBoardFrame.getContentPane().add(button7);
        button7.setBounds(370, 20, 50, 40);

        button8.setFont(new Font("Sylfaen", 1, 14));
        button8.setForeground(new Color(102, 102, 0));
        button8.setText("8");
        keyBoardFrame.getContentPane().add(button8);
        button8.setBounds(430, 20, 50, 40);

        button9.setFont(new Font("Sylfaen", 1, 14));
        button9.setForeground(new Color(102, 102, 0));
        button9.setText("9");
        keyBoardFrame.getContentPane().add(button9);
        button9.setBounds(490, 20, 50, 40);

        button0.setFont(new Font("Sylfaen", 1, 14));
        button0.setForeground(new Color(102, 102, 0));
        button0.setText("0");
        keyBoardFrame.getContentPane().add(button0);
        button0.setBounds(550, 20, 50, 40);

        button2.setFont(new Font("Sylfaen", 1, 14));
        button2.setForeground(new Color(102, 102, 0));
        button2.setText("2");
        keyBoardFrame.getContentPane().add(button2);
        button2.setBounds(70, 20, 50, 40);

        button1.setFont(new Font("Sylfaen", 1, 14));
        button1.setForeground(new Color(102, 102, 0));
        button1.setText("1");
        keyBoardFrame.getContentPane().add(button1);
        button1.setBounds(10, 20, 50, 40);

        buttonP.setFont(new Font("Sylfaen", 1, 14));
        buttonP.setForeground(new Color(102, 102, 0));
        buttonP.setText("P");
        keyBoardFrame.getContentPane().add(buttonP);
        buttonP.setBounds(550, 80, 50, 40);

        buttonO.setFont(new Font("Sylfaen", 1, 14));
        buttonO.setForeground(new Color(102, 102, 0));
        buttonO.setText("O");
        keyBoardFrame.getContentPane().add(buttonO);
        buttonO.setBounds(490, 80, 50, 40);

        buttonI.setFont(new Font("Sylfaen", 1, 14));
        buttonI.setForeground(new Color(102, 102, 0));
        buttonI.setText("I");
        keyBoardFrame.getContentPane().add(buttonI);
        buttonI.setBounds(430, 80, 50, 40);

        buttonU.setFont(new Font("Sylfaen", 1, 14));
        buttonU.setForeground(new Color(102, 102, 0));
        buttonU.setText("U");
        keyBoardFrame.getContentPane().add(buttonU);
        buttonU.setBounds(370, 80, 50, 40);

        buttonY.setFont(new Font("Sylfaen", 1, 14));
        buttonY.setForeground(new Color(102, 102, 0));
        buttonY.setText("Y");
        keyBoardFrame.getContentPane().add(buttonY);
        buttonY.setBounds(310, 80, 50, 40);

        buttonT.setFont(new Font("Sylfaen", 1, 14));
        buttonT.setForeground(new Color(102, 102, 0));
        buttonT.setText("T");
        keyBoardFrame.getContentPane().add(buttonT);
        buttonT.setBounds(250, 80, 50, 40);

        buttonR.setFont(new Font("Sylfaen", 1, 14));
        buttonR.setForeground(new Color(102, 102, 0));
        buttonR.setText("R");
        keyBoardFrame.getContentPane().add(buttonR);
        buttonR.setBounds(190, 80, 50, 40);

        buttonE.setFont(new Font("Sylfaen", 1, 14));
        buttonE.setForeground(new Color(102, 102, 0));
        buttonE.setText("E");
        keyBoardFrame.getContentPane().add(buttonE);
        buttonE.setBounds(130, 80, 50, 40);

        buttonW.setFont(new Font("Sylfaen", 1, 14));
        buttonW.setForeground(new Color(102, 102, 0));
        buttonW.setText("W");
        keyBoardFrame.getContentPane().add(buttonW);
        buttonW.setBounds(70, 80, 50, 40);

        buttonQ.setFont(new Font("Sylfaen", 1, 14));
        buttonQ.setForeground(new Color(102, 102, 0));
        buttonQ.setText("Q");
        keyBoardFrame.getContentPane().add(buttonQ);
        buttonQ.setBounds(10, 80, 50, 40);

        buttonL.setFont(new Font("Sylfaen", 1, 14));
        buttonL.setForeground(new Color(102, 102, 0));
        buttonL.setText("L");
        keyBoardFrame.getContentPane().add(buttonL);
        buttonL.setBounds(490, 140, 50, 40);

        buttonK.setFont(new Font("Sylfaen", 1, 14));
        buttonK.setForeground(new Color(102, 102, 0));
        buttonK.setText("K");
        keyBoardFrame.getContentPane().add(buttonK);
        buttonK.setBounds(430, 140, 50, 40);

        buttonJ.setFont(new Font("Sylfaen", 1, 14));
        buttonJ.setForeground(new Color(102, 102, 0));
        buttonJ.setText("J");
        keyBoardFrame.getContentPane().add(buttonJ);
        buttonJ.setBounds(370, 140, 50, 40);

        buttonH.setFont(new Font("Sylfaen", 1, 14));
        buttonH.setForeground(new Color(102, 102, 0));
        buttonH.setText("H");
        keyBoardFrame.getContentPane().add(buttonH);
        buttonH.setBounds(310, 140, 50, 40);

        buttonG.setFont(new Font("Sylfaen", 1, 14));
        buttonG.setForeground(new Color(102, 102, 0));
        buttonG.setText("G");
        keyBoardFrame.getContentPane().add(buttonG);
        buttonG.setBounds(250, 140, 50, 40);

        buttonF.setFont(new Font("Sylfaen", 1, 14));
        buttonF.setForeground(new Color(102, 102, 0));
        buttonF.setText("F");
        keyBoardFrame.getContentPane().add(buttonF);
        buttonF.setBounds(190, 140, 50, 40);

        buttonD.setFont(new Font("Sylfaen", 1, 14));
        buttonD.setForeground(new Color(102, 102, 0));
        buttonD.setText("D");
        keyBoardFrame.getContentPane().add(buttonD);
        buttonD.setBounds(130, 140, 50, 40);

        buttonS.setFont(new Font("Sylfaen", 1, 14));
        buttonS.setForeground(new Color(102, 102, 0));
        buttonS.setText("S");
        keyBoardFrame.getContentPane().add(buttonS);
        buttonS.setBounds(70, 140, 50, 40);

        buttonA.setFont(new Font("Sylfaen", 1, 14));
        buttonA.setForeground(new Color(102, 102, 0));
        buttonA.setText("A");
        keyBoardFrame.getContentPane().add(buttonA);
        buttonA.setBounds(10, 140, 50, 40);

        buttonZ.setFont(new Font("Sylfaen", 1, 14));
        buttonZ.setForeground(new Color(102, 102, 0));
        buttonZ.setText("Z");
        keyBoardFrame.getContentPane().add(buttonZ);
        buttonZ.setBounds(10, 200, 50, 40);

        buttonX.setFont(new Font("Sylfaen", 1, 14));
        buttonX.setForeground(new Color(102, 102, 0));
        buttonX.setText("X");
        keyBoardFrame.getContentPane().add(buttonX);
        buttonX.setBounds(70, 200, 50, 40);

        buttonC.setFont(new Font("Sylfaen", 1, 14));
        buttonC.setForeground(new Color(102, 102, 0));
        buttonC.setText("C");
        keyBoardFrame.getContentPane().add(buttonC);
        buttonC.setBounds(130, 200, 50, 40);

        buttonV.setFont(new Font("Sylfaen", 1, 14));
        buttonV.setForeground(new Color(102, 102, 0));
        buttonV.setText("V");
        keyBoardFrame.getContentPane().add(buttonV);
        buttonV.setBounds(190, 200, 50, 40);

        buttonB.setFont(new Font("Sylfaen", 1, 14));
        buttonB.setForeground(new Color(102, 102, 0));
        buttonB.setText("B");
        keyBoardFrame.getContentPane().add(buttonB);
        buttonB.setBounds(250, 200, 50, 40);

        buttonN.setFont(new Font("Sylfaen", 1, 14));
        buttonN.setForeground(new Color(102, 102, 0));
        buttonN.setText("N");
        keyBoardFrame.getContentPane().add(buttonN);
        buttonN.setBounds(310, 200, 50, 40);

        buttonSpace.setFont(new Font("Sylfaen", 1, 14));
        buttonSpace.setForeground(new Color(102, 102, 0));
        buttonSpace.setText("Space");
        keyBoardFrame.getContentPane().add(buttonSpace);
        buttonSpace.setBounds(130, 260, 380, 40);

        buttonUp.setFont(new Font("Sylfaen", 1, 14));
        buttonUp.setForeground(new Color(102, 102, 0));
        buttonUp.setText("^");
        keyBoardFrame.getContentPane().add(buttonUp);
        buttonUp.setBounds(660, 180, 50, 40);

        buttonDown.setFont(new Font("Sylfaen", 1, 14));
        buttonDown.setForeground(new Color(102, 102, 0));
        buttonDown.setText("v");
        keyBoardFrame.getContentPane().add(buttonDown);
        buttonDown.setBounds(660, 230, 50, 40);

        buttonRight.setFont(new Font("Sylfaen", 1, 14));
        buttonRight.setForeground(new Color(102, 102, 0));
        buttonRight.setText(">");
        keyBoardFrame.getContentPane().add(buttonRight);
        buttonRight.setBounds(720, 230, 50, 40);

        buttonLeft.setFont(new Font("Sylfaen", 1, 14));
        buttonLeft.setForeground(new Color(102, 102, 0));
        buttonLeft.setText("<");
        keyBoardFrame.getContentPane().add(buttonLeft);
        buttonLeft.setBounds(600, 230, 50, 40);

        buttonM.setFont(new Font("Sylfaen", 1, 14));
        buttonM.setForeground(new Color(102, 102, 0));
        buttonM.setText("M");
        keyBoardFrame.getContentPane().add(buttonM);
        buttonM.setBounds(370, 200, 50, 40);

        labelImage.setIcon(new ImageIcon("Res/Images/MainPicture.jpg"));
        keyBoardFrame.getContentPane().add(labelImage);
        labelImage.setBounds(0, -130, 800, 470);

		ActionListener buttonListener = new KeyBoardButtonListener();
        
        button1.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
        button3.addActionListener(buttonListener);
        button4.addActionListener(buttonListener);
        button5.addActionListener(buttonListener);
        button6.addActionListener(buttonListener);
        button7.addActionListener(buttonListener);
        button8.addActionListener(buttonListener);
        button9.addActionListener(buttonListener);
        button0.addActionListener(buttonListener);
        buttonQ.addActionListener(buttonListener);
        buttonW.addActionListener(buttonListener);
        buttonE.addActionListener(buttonListener);
        buttonR.addActionListener(buttonListener);
        buttonT.addActionListener(buttonListener);
        buttonY.addActionListener(buttonListener);
        buttonU.addActionListener(buttonListener);
        buttonI.addActionListener(buttonListener);
        buttonO.addActionListener(buttonListener);
        buttonP.addActionListener(buttonListener);
        buttonA.addActionListener(buttonListener);
        buttonS.addActionListener(buttonListener);
        buttonD.addActionListener(buttonListener);
        buttonF.addActionListener(buttonListener);
        buttonG.addActionListener(buttonListener);
        buttonH.addActionListener(buttonListener);
        buttonJ.addActionListener(buttonListener);
        buttonK.addActionListener(buttonListener);
        buttonL.addActionListener(buttonListener);
        buttonZ.addActionListener(buttonListener);
        buttonX.addActionListener(buttonListener);
        buttonC.addActionListener(buttonListener);
        buttonV.addActionListener(buttonListener);
        buttonB.addActionListener(buttonListener);
        buttonN.addActionListener(buttonListener);
        buttonM.addActionListener(buttonListener);
        buttonSpace.addActionListener(buttonListener);
        buttonUp.addActionListener(buttonListener);
        buttonDown.addActionListener(buttonListener);
        buttonLeft.addActionListener(buttonListener);
        buttonRight.addActionListener(buttonListener);

        keyBoardFrame.setVisible(true);
        keyBoardFrame.pack();
        keyBoardFrame.setSize(807,368);
        keyBoardFrame.setResizable(false);
        centerFrame(keyBoardFrame);
        
        keyBoardFrame.addWindowListener(new WindowAdapter() {
      		public void windowClosing(WindowEvent e) {
      			optionsFrame.dispose();
        		new OptionsMenu();
      		}
    	});
    }
    
    public class KeyBoardButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		
			doubleButtonflag = false;
		
			if(e.getSource() == button1) {
				numberKeyBoard = event.VK_1;
			} else if (e.getSource() == button2) {
				numberKeyBoard = event.VK_2;
			} else if (e.getSource() == button3) {
				numberKeyBoard = event.VK_3;
			} else if (e.getSource() == button4) {
				numberKeyBoard = event.VK_4;
			} else if (e.getSource() == button5) {
				numberKeyBoard = event.VK_5;
			} else if (e.getSource() == button6) {
				numberKeyBoard = event.VK_6;
			} else if (e.getSource() == button7) {
				numberKeyBoard = event.VK_7;
			} else if (e.getSource() == button8) {
				numberKeyBoard = event.VK_8;
			} else if (e.getSource() == button9) {
				numberKeyBoard = event.VK_9;
			} else if (e.getSource() == button0) {
				numberKeyBoard = event.VK_0;
			} else if (e.getSource() == buttonQ) {
				numberKeyBoard = event.VK_Q;
			} else if (e.getSource() == buttonW) {
				numberKeyBoard = event.VK_W;
			} else if (e.getSource() == buttonE) {
				numberKeyBoard = event.VK_E;
			} else if (e.getSource() == buttonR) {
				numberKeyBoard = event.VK_R;
			} else if (e.getSource() == buttonT) {
				numberKeyBoard = event.VK_T;
			} else if (e.getSource() == buttonY) {
				numberKeyBoard = event.VK_Y;
			} else if (e.getSource() == buttonU) {
				numberKeyBoard = event.VK_U;
			} else if (e.getSource() == buttonI) {
				numberKeyBoard = event.VK_I;
			} else if (e.getSource() == buttonO) {
				numberKeyBoard = event.VK_O;
			} else if (e.getSource() == buttonP) {
				numberKeyBoard = event.VK_P;
			} else if (e.getSource() == buttonA) {
				numberKeyBoard = event.VK_A;
			} else if (e.getSource() == buttonS) {
				numberKeyBoard = event.VK_S;
			} else if (e.getSource() == buttonD) {
				numberKeyBoard = event.VK_D;
			} else if (e.getSource() == buttonF) {
				numberKeyBoard = event.VK_F;
			} else if (e.getSource() == buttonG) {
				numberKeyBoard = event.VK_G;
			} else if (e.getSource() == buttonH) {
				numberKeyBoard = event.VK_H;
			} else if (e.getSource() == buttonJ) {
				numberKeyBoard = event.VK_J;
			} else if (e.getSource() == buttonK) {
				numberKeyBoard = event.VK_K;
			} else if (e.getSource() == buttonL) {
				numberKeyBoard = event.VK_L;
			} else if (e.getSource() == buttonZ) {
				numberKeyBoard = event.VK_Z;
			} else if (e.getSource() == buttonX) {
				numberKeyBoard = event.VK_X;
			} else if (e.getSource() == buttonC) {
				numberKeyBoard = event.VK_C;
			} else if (e.getSource() == buttonV) {
				numberKeyBoard = event.VK_V;
			} else if (e.getSource() == buttonB) {
				numberKeyBoard = event.VK_B;
			} else if (e.getSource() == buttonN) {
				numberKeyBoard = event.VK_N;
			} else if (e.getSource() == buttonM) {
				numberKeyBoard = event.VK_M;
			} else if (e.getSource() == buttonSpace) {
				numberKeyBoard = event.VK_SPACE;
			} else if (e.getSource() == buttonUp) {
				numberKeyBoard = event.VK_UP;
			} else if (e.getSource() == buttonDown) {
				numberKeyBoard = event.VK_DOWN;
			} else if (e.getSource() == buttonLeft) {
				numberKeyBoard = event.VK_LEFT;
			} else if (e.getSource() == buttonRight) {
				numberKeyBoard = event.VK_RIGHT;
			}
			
			for(int i=0; i<listData.size(); i++) {
				if(listData.get(i).equals(Integer.toString(numberKeyBoard))) {
					JOptionPane.showMessageDialog (null, "This button used already. Choose some other !!", "Error !!", JOptionPane.WARNING_MESSAGE);
					doubleButtonflag = true;
				}
			}
			
			// Elenxos gia epilogi koumpiou pou xrisimopoieitai idi.
			if(!doubleButtonflag) {
				listData.set(index, Integer.toString(numberKeyBoard));
				writeDataToFile(listData);
				optionsFrame.dispose();
				new OptionsMenu();
				keyBoardFrame.dispose();
			}
		}
	}
	
	// Apothikeuei tis epiloges ton palaion koumpion kathos kai tin nea allagi pou ekane o xristis.
	private void writeDataToFile(ArrayList<String> myList){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Res/Texts/KeyCodeText.txt"));
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
    
    private void centerFrame(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        	
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width-w) / 2;
        int y = (dim.height-w) / 2;
        
        frame.setLocation(x,y+250);
	}
}