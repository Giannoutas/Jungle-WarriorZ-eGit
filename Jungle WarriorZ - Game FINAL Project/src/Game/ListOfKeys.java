package Game;

import java.io.*;
import java.util.*;

public class ListOfKeys {
	
	private Scanner s = null;
	public static ArrayList<String> LIST = new ArrayList<String>();
	
	public ListOfKeys() {
		try {
			s = new Scanner(new BufferedReader(new FileReader("Res/Texts/KeyCodeText.txt")));
			while(s.hasNextLine()){
				LIST.add(s.nextLine());
			}

			} catch (FileNotFoundException e) {
				System.out.println("File not found !");
			} catch (NumberFormatException ee) {
				System.out.println("Error with default button !");
			} finally {
				if(s != null) {
					s.close();
				}
			}
	}
}
