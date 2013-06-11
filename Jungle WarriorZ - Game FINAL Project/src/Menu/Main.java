package Menu;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.JFrame;

import Game.ListOfClips;

/* Jungle WarriorZ - Projetc Genesis - IT Creations

To project afora tin dimioyrgia enos shooting game se platforma 2 diastaseon (2D). O kodikas vasisteike sta eggrafa EPAL 
(Eggrafo Perigrafis Apaitiseon Logismikou) kai EPSL (Eggrafo Perigrafis Sxediou Logismikou) kai kata ena megalo pososto sumvadizei me auta. Exoun ginei
prosthikes ston kodika pou den sumperilamvanontan sta sxetika eggrafa pou anaptuxthikan (p.x. i epilogi gia mute sta efe tou paixnidiou). Oi periptoseis 
xrisis pou eixan katagrafei sto EPAL exoun ulopoiithei oles ektos apo merikes pou afora to MultiPlayer. Sugkekrimena den exei ulopoiithei kai gia 
Host Game kai gia Join Game i periptosi xrisis pou afora tin mi sundesi ston server logo argis sundesis. Episis sto Join Game den exei ulopoiithei
i periptosi xrisis gia elenxo an uparxei i oxi i IP pou eisigage o xristis kai tin periptosi pou an eisigage tin sosti, na exei prolavei na mpei kapoios 
allos. Oles oi upoloipes periptoseis xrisis exoun ulopiithei.

Oson afora tin leitourgikotita genika tou MultiPlayer tou paixnidiou. Den leitourgei. Mporei o xristis pou kanei Join na sundethei ama valei tin sosti 
IP Address (mexri na sundethei o xristis pou ekane Host perimenei ton antipalo tou) pou exei o xristis pou ekane Host, alla meta uparxei provlima
me tin dimiourgia tou paixnidiou. Auto sumvainei giati gia na arxisei to paixnidi prepei na dimiourgithei neo thread kai gia na kleisei meta to thread 
tautoxrona otan teleiosoun oi paiktes to paixnidi kai i lusi den vrethike sta xronika plaisia pou eixame opote to afisame os exei. I sundesi ginetai se 
epipedo LAN. Episis gia na petuxei xreiazetai na kleisei o FireWall pou exoun ta logismika Windows.

Gia tin kataskeui tou kodika parthikan sumvoules, methodoi kai oloklira tmimata kodika apo 3 diaforetikes piges. 1) apo tutorials sto Youtube pou
pareixan orismenes methodous pou kaliptan tin leitourgikotita pou thelame na anaptiksoume (opos i sfaira gia paradeigma - Class: Bullet). 2) Apo to 
vivlio "O'Reilly 2005 Killer Game Programming in Java" kai 3) apo ton kodika enos paixnidiou pou vrikame sto internet 
(http://zimmster.com/games/finalproject/finalproject.php) O kodikas den einai antigrafi tou paixnidiou, mias kai to Menu exei ginei eksoloklirou
apo tin omada anaptuksis, opos episis oi sfaires kai o purovolismos, ta collisions, i grafiki optikopoiisi ton parathuron kai tis platformas 
(eksoloklirou dikes mas pistes), i sundesi me to menu, ta grafika tou panxidiou, o ixos kai i entaksi tou sto painxidi.

Kata tin diarkeia anaptuksis tou logismikou dimiourgithikan polles ekdoseis tou paixnidiou. Epeidi omos den eixame asxolithei me to GitHub den tis 
anevazame ekei pera, alla tis kratousame topika stous sklirous ton upologiston mas kai moirazomastan ta arxeia apo alles istioselides (Drobox). Sto telos
anevasame endeiktika merikes ekdoseis stin diefthinsi "https://github.com/Giannoutas/Jungle-WarriorZ-eGit".

Oson afora to ektelesimo arxeio Jar. Dustixos den mporei na treksei autonomo, dioti den fortonei tis eikones kai tous ixous apo ta dedomena pou uparxoun.
Gia na ginei exei dimiourgithei i klasi ResourceLoader pou einai katalili gia na diavazei dedomena, mono pou anaptuxthike sto telos eno o kodikas eixe 
graftei noritera, me apotelesma na uparxoun diafores suskeues diavasmatos dedomenon mesa sto programma (scanner, files, buffers, inputstreams)
kai itan duskoli kai xronovora i allagi tou kodika se morfi multitasking, na diavazontai diladi ola apo mia suskeui anagnosis. Kata sunepeia to arxeio
.jar trexei mono ama uparxoun dipla kai oi fakeloi me ta dedomena.

*/

public class Main {
	
	// Statiki idiotita gia na exei prosvasi i klasi ListOfClips ammesa.
	public static ArrayList<Clip> LIST = new ArrayList<Clip>();
	
	public static void main(String[] args) {
		
		ArrayList<String> myHelpList = new ArrayList<String>();
		
		myHelpList.add("Res/Audio/Jungle Sounds.wav");
		myHelpList.add("Res/Audio/In Game Music.wav");
		myHelpList.add("Res/Audio/Intro.wav");
		
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
		
		// Apothikeuei se arxeia tis default epiloges tou paixnidiou. Diladi na ksekinaei me anoixto ixo se mousiki kai efe kai ta pliktra xeirismou
		// na einai ta velakia tou pliktrologiou. Mporei na allaksei tis arxikopoihseis autes argotera sta Options.
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Res/Texts/KeyCodeText.txt"));
			BufferedWriter out1 = new BufferedWriter(new FileWriter("Res/Texts/SoundMuted.txt"));
			BufferedWriter out2 = new BufferedWriter(new FileWriter("Res/Texts/MusicMuted.txt"));
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