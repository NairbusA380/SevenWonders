import java.awt.Image;
import java.util.ArrayList;

public class Step {

	int position; //Position de l'étape au sein de la merveille
	RessourceList ressourceCost; // Cout en ressources
	boolean validated; //Indique si l'étape est validée
	int points; //Nombre de points marqués si validée
	Capacity capacity; //Capacité spéciale si validée
	RessourceList ressourcesGained; //Gain de ressources si validée
	Step previousStep;
	String url;

	public Step(int position, RessourceList ressourceCost, int points, Capacity capacity, RessourceList ressourcesGained, Step previousStep, String url) {
		this.position = position;
		this.ressourceCost = ressourceCost;
		this.validated = false;
		this.points = points;
		this.capacity = capacity;
		this.ressourcesGained = ressourcesGained;
		this.previousStep = previousStep;
		this.url = url;
	}

	public Step(int position, RessourceList ressourceCost, Capacity capacity, RessourceList ressourcesGained, Step previousStep, String url){
		this(position, ressourceCost, 0, capacity, ressourcesGained, previousStep, url);
	}

	public Step(int position, RessourceList ressourceCost, int nbPoints, RessourceList ressourcesGained, Step previousStep, String url){
		this(position, ressourceCost, nbPoints, null, ressourcesGained, previousStep, url);
	}
	
	public Step(int position, RessourceList ressourceCost, int nbPoints, Capacity capacity, Step previousStep, String url){
		this(position, ressourceCost, nbPoints, capacity, null, previousStep, url);
	}

	public Step(int position, RessourceList ressourceCost, RessourceList ressourcesGained, Step previousStep, String url){
		this(position, ressourceCost, 0, null, ressourcesGained, previousStep, url);
	}

	public Step(int position, RessourceList ressourceCost, int nbPoints, Step previousStep, String url){
		this(position, ressourceCost, nbPoints, null, null, previousStep, url);
	}

	public Step(int position, RessourceList ressourceCost, Capacity capacity, Step previousStep, String url){
		this(position, ressourceCost, 0, capacity, null, previousStep, url);
	}

	public Step(int position, RessourceList ressourceCost, Step previousStep, String url){
		this(position, ressourceCost, 0, null, null, previousStep, url);
	}

	public boolean validateStep(Player p) throws AlreadyValidatedException, TooSoonToValidateException {
		boolean isValidated = false;

		//Vérification si c'est bien à cette étape d'être validée
		if (!this.validated){ //Elle n'a pas déjà été validée
			if (previousStep.equals(null) && previousStep.validated){ //La précédente a bien été validée

			}else{
				throw new TooSoonToValidateException(this);
			}
		}else{
			throw new AlreadyValidatedException(this);
		}
		return isValidated;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}
	
	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public String toString() {
		String result = "<html>"; //Balise html afin que le label prenne le multi-lignes
		//result += "Cette étape ";
		/*result += "Cette étape, ";
		

		//Annonce du coût de la merveille si elle n'a pas encore été validée
		if (!this.validated){
			result += "d'un coût ";
			int argile = 0, bois = 0, pierre = 0, minerai = 0, tapis = 0, fiole = 0, papyrus = 0;
			int nbRessource = 0;
			
			if (!ressourceCost.isEmpty()){
				result += "de ";
				for (Object o : ressourceCost){
					Ressource r = (Ressource)o;
					switch (r.toString()){
					case "ARGILE":
						argile += 1;
						nbRessource ++;
						break;
					case "BOIS":
						bois += 1;
						nbRessource ++;
						break;
					case "PIERRE":
						pierre += 1;
						nbRessource ++;
						break;
					case "MINERAI":
						minerai += 1;
						nbRessource ++;
						break;
					case "TAPIS":
						tapis += 1;
						nbRessource ++;
						break;
					case "FIOLE":
						fiole += 1;
						nbRessource ++;
						break;
					case "PAPYRUS":
						papyrus += 1;
						nbRessource ++;
						break;
					}
				}
				if (argile > 0){
					result += argile+" argile";
					if (argile > 1){
						result +="s";
					}
					nbRessource-=argile;
					if (nbRessource > 0){
						result += ", ";
					}
				}
				if (bois > 0){
					result += bois+" bois";
					nbRessource-=bois;
					if (nbRessource > 0){
						result += ", ";
					}
				}
				if (pierre > 0){
					result += pierre+" pierre";
					if (pierre > 1){
						result +="s";
					}
					nbRessource-=pierre;
					if (nbRessource > 0){
						result += ", ";
					}
				}
				if (tapis > 0){
					result += tapis+" tapis";
					nbRessource-=tapis;
					if (nbRessource > 0){
						result += ", ";
					}
				}
				if (fiole > 0){
					result += fiole+" fiole";
					if (fiole > 1){
						result +="s";
					}
					nbRessource-=fiole;
					if (nbRessource > 0){
						result += ", ";
					}
				}
				if (minerai > 0){
					result += minerai+" minerai";
					if (minerai > 1){
						result +="s";
					}
					nbRessource-=minerai;
					if (nbRessource > 0){
						result += ", ";
					}
				}
				if (papyrus > 0){
					result += papyrus+" papyrus";
					nbRessource-=papyrus;
					if (nbRessource > 0){
						result += ", ";
					}
				}
			}
		}

		result += ", vous ";*/
		/*if (this.validated){
			result += "permet ";
		}else{
			result += "permettra ";
		}
		result += "de :<br />";*/
		if (this.points > 0){
			//result +="- Gagner "+this.points+" point";
			result += "Gain de "+this.points+" point";
			if (this.points > 1){
				result +="s";
			}
		}
		if (this.capacity != null){
			result += "<br />"+capacity.toString();
		}
		result += "</html>";
		return result;
	}
}
