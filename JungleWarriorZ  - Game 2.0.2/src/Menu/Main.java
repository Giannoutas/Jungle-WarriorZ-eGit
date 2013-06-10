package Menu;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.JFrame;

import Game.ListOfClips;

public class Main {
	
	//Στατική ιδιώτητα για να έχει πρόσβαση η κλάση ListOfClips άμμεσα.
	public static ArrayList<Clip> LIST = new ArrayList<Clip>();
	
	public static void main(String[] args) {
		
		ArrayList<String> myHelpList = new ArrayList<String>();
		
		myHelpList.add("Audio/Jungle Sounds.wav");
		myHelpList.add("Audio/In Game Music.wav");
		myHelpList.add("Audio/Intro.wav");
		
		for(int i=0; i<myHelpList.size(); i++) {
			try {
				File f = new File(myHelpList.get(i));
				AudioInputStream ais = AudioSystem.getAudioInputStream(f.getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				
				LIST.add(clip);
				
			}
			catch(Exception e){}
		}
		
		//Αποθηκέυει σε αρχεία τις default επιλογές του παιχνιδιού. Δηλαδή να ξεκινάει με ανοιχτό ήχο σε μουσική και εφέ και τα πλήκτρα χειρισμού 
		// να είναι τα βελάκια του πληκτρολογίου. Μπορεί να αλλάξει τις αρχικοποιήσεις αυτές αργότερα στα Options.
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Texts/KeyCodeText.txt"));
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Texts/SoundMuted.txt"));
			BufferedWriter out2 = new BufferedWriter(new FileWriter("Texts/MusicMuted.txt"));
			out.write("38");
			out.newLine();
			out.write("39");
			out.newLine();
			out.write("37");
			out.newLine();
			out.write("32");
			out.close();
			out1.write("Off");
			out1.close();
			out2.write("Off");
			out2.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		new ListOfClips();
		new mainMenu();
	}

}