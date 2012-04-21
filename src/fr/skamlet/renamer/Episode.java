/**
 * Author: Simon Kamlet
 */

package fr.skamlet.renamer;

public class Episode {
	private String name;
	private String season;
	private String episode;
	private String extension;

	public Episode() {
	}

	public Episode(String name, String season, String episode, String extension) {
		super();
		
		//TODO: if null value, do nothing
		
		this.name = name;
		this.season = season;
		this.episode = episode;
		this.extension = extension;
	}

	public String getFileName() {
		return (name + " - " + season + "x" + episode + extension);
	}

	public Boolean isComplete() {
		if (this.name != null && this.season != null && this.episode != null
				&& this.extension != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}
