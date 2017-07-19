import java.awt.Image;
import java.util.ArrayList;

public class Step {

	int position; //Position de l'étape au sein de la merveille
	ArrayList<Ressource> ressourceCost; // Cout en ressources
	boolean validated; //Indique si l'étape est validée
	int points; //Nombre de points marqués si validée
	Capacity capacity; //Capacité spéciale si validée
	ArrayList<Ressource> ressourcesGained; //Gain de ressources si validée
	Step previousStep;
	String url;

	public Step(int position, ArrayList<Ressource> ressourceCost, int points, Capacity capacity, ArrayList<Ressource> ressourcesGained, Step previousStep, String url) {
		this.position = position;
		this.ressourceCost = ressourceCost;
		this.validated = false;
		this.points = points;
		this.capacity = capacity;
		this.ressourcesGained = ressourcesGained;
		this.previousStep = previousStep;
		this.url = url;
	}

	public Step(int position, ArrayList<Ressource> ressourceCost, Capacity capacity, ArrayList<Ressource> ressourcesGained, Step previousStep, String url){
		this(position, ressourceCost, 0, capacity, ressourcesGained, previousStep, url);
	}

	public Step(int position, ArrayList<Ressource> ressourceCost, int nbPoints, ArrayList<Ressource> ressourcesGained, Step previousStep, String url){
		this(position, ressourceCost, nbPoints, null, ressourcesGained, previousStep, url);
	}

	public Step(int position, ArrayList<Ressource> ressourceCost, int nbPoints, Capacity capacity, Step previousStep, String url){
		this(position, ressourceCost, nbPoints, capacity, null, previousStep, url);
	}

	public Step(int position, ArrayList<Ressource> ressourceCost, ArrayList<Ressource> ressourcesGained, Step previousStep, String url){
		this(position, ressourceCost, 0, null, ressourcesGained, previousStep, url);
	}

	public Step(int position, ArrayList<Ressource> ressourceCost, int nbPoints, Step previousStep, String url){
		this(position, ressourceCost, nbPoints, null, null, previousStep, url);
	}

	public Step(int position, ArrayList<Ressource> ressourceCost, Capacity capacity, Step previousStep, String url){
		this(position, ressourceCost, 0, capacity, null, previousStep, url);
	}

	public Step(int position, ArrayList<Ressource> ressourceCost, Step previousStep, String url){
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

	public String toString() {
		String result = "<html>"; //Balise html afin que le label prenne le multi-lignes

		result += "Cette étape, ";

		//Annonce du coût de la merveille si elle n'a pas encore été validée
		if (!this.validated){
			result += "d'un coût ";
			int argile = 0, bois = 0, pierre = 0, minerai = 0, tapis = 0, fiole = 0, papyrus = 0;

			if (!ressourceCost.isEmpty()){
				result += "de ";
				for (Ressource r : ressourceCost){
					switch (r.toString()){
					case "ARGILE":
						argile += 1;
						break;
					case "BOIS":
						bois += 1;
						break;
					case "PIERRE":
						pierre += 1;
						break;
					case "MINERAI":
						minerai += 1;
						break;
					case "TAPIS":
						tapis += 1;
						break;
					case "FIOLE":
						fiole += 1;
						break;
					case "PAPYRUS":
						papyrus += 1;
						break;
					}
				}
				if (argile > 0){
					result += argile+" argile,";
				}
				if (bois > 0){
					result += bois+" bois,";
				}
				if (pierre > 0){
					result += pierre+" pierre,";
				}
				if (tapis > 0){
					result += tapis+" tapis,";
				}
				if (fiole > 0){
					result += fiole+" fiole,";
				}
				if (minerai > 0){
					result += minerai+" minerai,";
				}
				if (papyrus > 0){
					result += papyrus+" papyrus,";
				}
			}
		}

		result += " vous<br />";
		if (this.validated){
			result += "permet ";
		}else{
			result += "permettra ";
		}
		result += "de :<br /><br />";
		//if (this.points > 0){
		result +="- Gagner "+this.points+" point";
		if (this.points > 1){
			result +="s";
		}
		//}
		result +="</html>";
		return result;
	}
}
