/**
 * Author: Simon Kamlet
 */

package fr.skamlet.renamer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public List<File> listPath(File path) {
		List<File> files = new ArrayList<File>();
		// File files[];
		files.addAll(Arrays.asList(path.listFiles()));

		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			System.out.println(file.getName());
		}

		return files;
	}

	public void rename(File file, Episode episode) {
		file.renameTo(new File(Launcher.path + "/" + episode.getFileName()));
	}

	public Episode matcher(String fileName) {

		/**
		 * File verification patterns and matchers
		 */
		Pattern isVideo = Pattern.compile(".*((\\.mkv|\\.srt|\\.avi|\\.mp4))$");
		Pattern isTempFile = Pattern.compile("^\\..*");
		Pattern isValid = Pattern
				.compile("^([A-Za-z0-9\\s]*\\s-\\s\\d\\dx\\d\\d\\.(mkv|avi|srt))$");

		Matcher matchIsVideo = isVideo.matcher(fileName);
		Matcher matchIsTempfile = isTempFile.matcher(fileName);
		Matcher matchIsValid = isValid.matcher(fileName);

		/**
		 * Format verification patterns and matchers
		 */
		Pattern format1 = Pattern.compile(".*[Ss](\\d\\d)[Ee]((\\d\\d)).*"); // S01E01
		Pattern format2 = Pattern.compile(".*(\\d\\d)x((\\d\\d)).*"); // 01x01
		Pattern format3 = Pattern.compile(".*(\\d)x((\\d\\d)).*"); // 1x01
		Pattern format4 = Pattern.compile(".*(\\d\\d)((\\d\\d)).*"); // 0101
		Pattern format5 = Pattern.compile(".*(\\d)((\\d\\d)).*"); // 101

		Matcher matchFormat1 = format1.matcher(fileName);
		Matcher matchFormat2 = format2.matcher(fileName);
		Matcher matchFormat3 = format3.matcher(fileName);
		Matcher matchFormat4 = format4.matcher(fileName);
		Matcher matchFormat5 = format5.matcher(fileName);

		if (matchIsVideo.matches() && !matchIsValid.matches()
				&& !matchIsTempfile.matches()) {

			Episode episode;
			String name = Launcher.name;
			String seasonNumber;
			String episodeNumber;
			String extension = matchIsVideo.group(1);

			if (matchFormat1.matches()) {
				seasonNumber = matchFormat1.group(1);
				episodeNumber = matchFormat1.group(2);
				episode = new Episode(name, seasonNumber, episodeNumber,
						extension);
				return episode;
			}
			else if (matchFormat2.matches()) {
				seasonNumber = matchFormat2.group(1);
				episodeNumber = matchFormat2.group(2);
				episode = new Episode(name, seasonNumber, episodeNumber,
						extension);
				return episode;
			}
			else if (matchFormat3.matches()) {
				seasonNumber = "0" + matchFormat3.group(1);
				episodeNumber = matchFormat3.group(2);
				episode = new Episode(name, seasonNumber, episodeNumber,
						extension);
				return episode;
			}
			else if (matchFormat4.matches()) {
				seasonNumber = matchFormat4.group(1);
				episodeNumber = matchFormat4.group(2);
				episode = new Episode(name, seasonNumber, episodeNumber,
						extension);
				return episode;
			}
			else if (matchFormat5.matches()) {
				seasonNumber = "0" + matchFormat5.group(1);
				episodeNumber = matchFormat5.group(2);
				episode = new Episode(name, seasonNumber, episodeNumber,
						extension);
				return episode;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}

	public void createTestFile() {
		try {
			new File(
					Launcher.path
							+ "/How.I.Met.Your.Mother.S07E01.720p.HDTV.X264-DIMENSION.mkv")
					.createNewFile();
			new File(Launcher.path + "/Fr801.mkv").createNewFile();
			new File(Launcher.path + "/Fr1001.mkv").createNewFile();
			new File(Launcher.path + "/Dexter.S07E12.1080p.mkv")
					.createNewFile();
			//new File(Launcher.path + "/._Dexter.S02E03.avi").createNewFile();
			new File(Launcher.path + "/Dexter.s01e15.mkv").createNewFile();
			new File(Launcher.path + "/Dexter.1x03.mkv").createNewFile();
		} catch (IOException e) {
			System.out
					.println("Erreur lors de la création des fichiers de test");
			e.printStackTrace();
		}
	}

	public void createHistoryFile(List<File> files) {

		File file = new File(Launcher.path + "/previous_name.txt");

		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter output = new BufferedWriter(fw);

			for (int i = 0; i < files.size(); i++) {
				output.write(files.get(i).getName() + "\n");
			}
			output.flush();
			output.close();

			if (!file.exists()) {
				file.createNewFile();
			}

		} catch (IOException e) {
			System.out.println("Erreur lors de la création du fichier...");
			e.printStackTrace();
		}
	}
}
