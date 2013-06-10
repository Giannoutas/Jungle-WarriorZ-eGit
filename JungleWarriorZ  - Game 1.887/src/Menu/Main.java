package Menu;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Texts/KeyCodeText.txt"));
			out.write("38");
			out.newLine();
			out.write("39");
			out.newLine();
			out.write("37");
			out.newLine();
			out.write("32");
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		new mainMenu();
	}

}