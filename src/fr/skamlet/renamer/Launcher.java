/**
 * Author: Simon Kamlet
 */

package fr.skamlet.renamer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {

	public static String path = null;
	public static String name = null;
	public static Boolean confirmed = false;
	public static Boolean printHistoryFile = true;

	public static void main(String[] args) {

		Util util = new Util();

		if (args.length > 0) {

			name = args[0];

			if (args.length > 1) {
				path = args[1];
			}
			else {
				try {
					path = new File(".").getCanonicalPath();
				} catch (IOException e) {
					System.out.println("Path error!");
					e.printStackTrace();
				}
			}
			System.out.println("name = " + name);
			System.out.println("path = " + path);
			System.out.println();
		}
		else {
			System.out.println("usage: renamer name [directory] ...");
			return;
		}

		// Create test files
		if (name.equals("test")) {
			System.out.println("Creating some test files...");
			util.createTestFile();
			return;
		}

		List<File> files = util.listPath(new File(path));
		List<File> confirmedFiles = new ArrayList<File>();
		List<Episode> episodes = new ArrayList<Episode>();
		
		System.out.println();
		System.out.println("This will be the output:");
		for (File file : files) {
			Episode episode = util.matcher(file.getName());
			
			if (episode != null) {
				episodes.add(episode);
				confirmedFiles.add(file);
				System.out.println(episode.getFileName());
			}
		}
		
		String response = "0";
		Scanner in = new Scanner(System.in);

		do {
			System.out.println();
			System.out.println("Do you confirm? (y/n)");
			response = in.nextLine().toLowerCase();
		} while (!response.equals("y") && !response.equals("n"));
		
		if (response.equals("y")){
			System.out.println("Processing...");
			for (int i = 0; i < episodes.size(); i++) {
				util.rename(confirmedFiles.get(i), episodes.get(i));
			}
		}
		else if (response.equals("n")) {
			System.out.println("Canceled...");
			return;
		}
		
		System.out.println("Exiting...");
		
		if (printHistoryFile) {
			util.createHistoryFile(confirmedFiles);
		}
	}
	// TODO: ajouter le code d'erreur à la fin de l'éxecution du programme
	// TODO: ajouter une option pour choisir pour chaque fichier si on le traite
	// ou pas
	// TODO: résumé dans la sortie standard (éventuellement)
	// TODO: remettre la reconnaissance d'épisode
	// FIXME: liste des anciens noms sous windows n'est pas remis à la ligne
}
