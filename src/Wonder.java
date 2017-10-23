import java.awt.Image;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public enum Wonder {

	ALEXANDRIEA (Ressource.FIOLE, "AlexandrieA", "Alexandrie/phare.png", 3, null),
	ALEXANDRIEB (Ressource.FIOLE, "AlexandrieB", "Alexandrie/phare.png", 3, null),
	HALIKARNASSOSB (Ressource.TAPIS, "HalikarnassosB", "Halikarnassos/mausolee.png", 3, null),
	HALIKARNASSOSA (Ressource.TAPIS, "HalikarnassosB", "Halikarnassos/mausolee.png", 2, null);
	
	private Ressource ressource;
	private String name;
	private String url;
	private int nbSteps;
	private Step[] step;
	
	Wonder(Ressource ressource, String name, String url, int nbSteps, Step[] step){
		this.ressource = ressource;
		this.name = name;
		this.url = url;
		this.nbSteps = nbSteps;
		this.step = step;		
	}
	
	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public int getNbSteps() {
		return nbSteps;
	}

	public void setNbSteps(int nbSteps) {
		this.nbSteps = nbSteps;
	}

	public Step[] getStep() {
		return step;
	}

	public void setStep(Step[] step) {
		this.step = step;
	}

	public String toString() {
		String result = "Wonder : ressource=" + ressource.toString() + "\n name=" + name + "\n nbSteps=" + nbSteps
				+ "\n";
		for (int i = 0; i < step.length; i++){
			result += "step "+ i +" : "+ step[i].toString() + "\n";
		}
		return result;
	}	
}
