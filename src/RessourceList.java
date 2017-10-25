import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollBarUI;

public class RessourceList implements Iterable{

	ArrayList<Ressource> ressourceList;

	public RessourceList(){
		this.ressourceList = new ArrayList<Ressource>();
	}

	public RessourceList(ArrayList<Ressource> c){
		this.ressourceList = new ArrayList<Ressource>();
		this.ressourceList.addAll(c);
	}

	public RessourceList supressAllUnusedRessources(RessourceList r){
		RessourceList available = this.clone();
		for (int i = 0; i < this.ressourceList.size(); i++){
			Ressource ressAvailable = ressourceList.get(i);
			if (!r.contains(ressAvailable)){
				available.remove(ressAvailable);
			}
		}
		return available;
	}

	public boolean add(Ressource e){
		return ressourceList.add(e);
	}

	public boolean addAll(RessourceList c){
		boolean result = true;
		for (Ressource r : ressourceList){
			result = result && add(r);
		}
		return result;
	}

	public boolean remove(Ressource e){
		return ressourceList.remove(e);
	}

	public Ressource get(int arg0) {
		return ressourceList.get(arg0);
	}

	public int size() {
		return ressourceList.size();
	}

	public boolean contains(Ressource r){
		boolean contains = false;
		for (int i = 0; i < this.size(); i++){
			Ressource ress = this.ressourceList.get(i);
			if (ress.toString() == r.toString()){
				contains = true;
				break;
			}
		}
		return contains;
	}

	public boolean containsSimple(RessourceList list, Ressource c){
		boolean result = false;
		String nameCompared = c.toString();
		for (Ressource ressource : list.ressourceList){
			String nameParcouru = ressource.toString();
			if (nameParcouru == nameCompared){
				result = true;
				list.remove(ressource);
				break;
			}
		}
		return result;
	}

	public RessourceList rechercheElaboree (RessourceList dispo, RessourceList cout){
		RessourceList newDispo = new RessourceList();
		boolean besoinPierre = false, besoinArgile = false, besoinBois = false, besoinMinerai = false, besoinTapis = false, besoinPapyrus = false, besoinFiole = false;

		boolean somethingDone = false;
		/* Recensement des ressources non demandées (besoinXXX = false) */
		for (Ressource ress : cout.ressourceList){
			switch (ress.toString()){
			case "BOIS":
				besoinBois = true;
				break;
			case "ARGILE":
				besoinArgile = true;
				break;
			case "PIERRE":
				besoinPierre = true;
				break;
			case "MINERAI":
				besoinMinerai = true;
				break;
			case "FIOLE":
				besoinFiole = true;
				break;
			case "TAPIS":
				besoinTapis = true;
				break;
			case "PAPYRUS":
				besoinPapyrus = true;
				break;
			}
		}

		/* Transformation des ressources multiples disponibles en enlevant la ressource non demandée */
		for (Ressource rDispo : dispo.ressourceList){
			if (rDispo != Ressource.PIECE){
				String ressourcesNames = rDispo.toString();
				String[] ressources = ressourcesNames.split("_");
				/* Marqueur de don de ressources par la carte
				 * 0 = ne donne pas la ressource
				 * 1 = donne la ressource utilement
				 * 2 = donne la ressource inutilement*/
				int donneBoisInutilement = 0, donnePierreInutilement = 0, donneMineraiInutilement = 0, donneArgileInutilement = 0, donneFioleInutilement = 0, donnePapyrusInutilement = 0, donneTapisInutilement = 0;

				for (int i = 0; i < ressources.length; i++){
					/* La ressource multiple donne la ressource alors qu'on en a besoin (donneXXX = true) */
					String ressource = ressources[i];
					switch (ressource){
					case "BOIS":
						if (besoinBois){
							donneBoisInutilement = 1;
						}else{
							donneBoisInutilement = 2;
						}
						break;
					case "ARGILE":
						if (besoinArgile){
							donneArgileInutilement = 1;
						}else{
							donneArgileInutilement = 2;
						}
						break;
					case "PIERRE":
						if (besoinPierre){
							donnePierreInutilement = 1;
						}else{
							donnePierreInutilement = 2;
						}
						break;
					case "MINERAI":
						if (besoinMinerai){
							donneMineraiInutilement = 1;
						}else{
							donneMineraiInutilement = 2;
						}
						break;
					case "FIOLE":
						if (besoinFiole){
							donneFioleInutilement = 1;
						}else{
							donneFioleInutilement = 2;
						}
						break;
					case "TAPIS":
						if (besoinTapis){
							donneTapisInutilement = 1;
						}else{
							donneTapisInutilement = 2;
						}
						break;
					case "PAPYRUS":
						if (besoinPapyrus){
							donnePapyrusInutilement = 1;
						}else{
							donnePapyrusInutilement = 2;
						}
						break;
					}
				}
				/* Création de la nouvelle ressource (éventuellement multiple) en fonction */
				if (donnePierreInutilement > 0 || donneBoisInutilement > 0 || donneMineraiInutilement > 0 || donneArgileInutilement > 0){
					if (donnePierreInutilement == 1 && donneBoisInutilement == 1 && donneArgileInutilement == 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.PIERRE_BOIS_MINERAI_ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement == 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE_BOIS_ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.PIERRE_BOIS_MINERAI);
						somethingDone = true;
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE_BOIS);
						somethingDone = true;
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.PIERRE_MINERAI_ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE_ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.PIERRE_MINERAI);
						somethingDone = true;
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement != 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE);
						somethingDone = true;
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement == 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.BOIS_MINERAI_ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.BOIS_ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.BOIS_MINERAI);
						somethingDone = true;
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.BOIS);
						somethingDone = true;
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.MINERAI_ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.ARGILE);
						somethingDone = true;
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement != 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.MINERAI);
						somethingDone = true;
					}
				}else{
					if (donneFioleInutilement == 1 && donneTapisInutilement == 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.FIOLE_TAPIS_PAPYRUS);
						somethingDone = true;
					}
					if (donneFioleInutilement == 1 && donneTapisInutilement == 1 && donnePapyrusInutilement != 1){
						newDispo.add(Ressource.FIOLE_TAPIS);
						somethingDone = true;
					}
					if (donneFioleInutilement == 1 && donneTapisInutilement != 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.FIOLE_PAPYRUS);
						somethingDone = true;
					}
					if (donneFioleInutilement == 1 && donneTapisInutilement != 1 && donnePapyrusInutilement != 1){
						newDispo.add(Ressource.FIOLE);
						somethingDone = true;
					}
					if (donneFioleInutilement != 1 && donneTapisInutilement == 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.TAPIS_PAPYRUS);
						somethingDone = true;
					}
					if (donneFioleInutilement != 1 && donneTapisInutilement == 1 && donnePapyrusInutilement != 1){
						newDispo.add(Ressource.TAPIS);
						somethingDone = true;
					}
					if (donneFioleInutilement != 1 && donneTapisInutilement != 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.PAPYRUS);
						somethingDone = true;
					}
				}
			}
		}
		if (somethingDone){
			return newDispo;
		}else{
			return null;
		}
	}


	public boolean containsAll(RessourceList cout){
		RessourceList dispo = this.clone();
		boolean somethingHasBeenDone = true;

		while ((cout != null && cout.size()> 0) && somethingHasBeenDone){
			for (int i = 0; i < cout.size(); i++){
				Ressource o = cout.ressourceList.get(i);
				if (this.containsSimple(dispo, o)){
					cout.remove(o);
					somethingHasBeenDone = true;
				}else{
					somethingHasBeenDone = false;
				}
				RessourceList newDispo = this.rechercheElaboree(dispo, cout);
				if (newDispo == null){
					somethingHasBeenDone = false;
				}else{
					dispo = newDispo;
					somethingHasBeenDone = true;
				}
			}
		}


		return !(cout.size() > 0);
	}

	public boolean isEmpty(){
		return ressourceList.isEmpty();
	}

	public List<Ressource> getRessourceList() {
		return ressourceList;
	}

	public void setRessourceList(ArrayList<Ressource> ressourceList) {
		this.ressourceList = ressourceList;
	}

	public Iterator<Ressource> iterator() {
		return ressourceList.iterator();
	}

	public boolean equals(RessourceList r){
		return this.ressourceList.equals(r);
	}

	public RessourceList clone(){
		RessourceList clone = new RessourceList();
		for(Object o : this){
			Ressource ressource = (Ressource)o;
			clone.add(ressource);
		}
		return clone;
	}

	public String toString(){
		String result = "<html>", resultFin = "";
		int piece = 0, bois = 0, pierre = 0, minerai = 0, fiole = 0, argile = 0, tapis = 0, papyrus = 0;
		for (Object o : this){
			Ressource r = (Ressource) o;
			switch(r){
			case BOIS:
				bois++;
				break;
			case MINERAI:
				minerai++;
				break;
			case ARGILE:
				argile++;
				break;
			case PIERRE:
				pierre++;
				break;
			case TAPIS:
				tapis++;
				break;
			case FIOLE:
				fiole++;
				break;
			case PAPYRUS:
				papyrus++;
				break;
			case PIECE:
				piece++;
				break;
			default:
				resultFin += r+"\n";
				break;
			}
		}

		if (piece > 0){
			result += piece+" pièce";
			if (piece > 1){
				result += "s";
			}
			result += "<br />";
		}
		if (bois > 0){
			result += bois+" bois<br />";
		}
		if (argile > 0){
			result += argile+" argile";
			if (argile > 1){
				result += "s";
			}
			result += "<br />";
		}
		if (minerai > 0){
			result += minerai+" minerai";
			if (minerai > 1){
				result += "s";
			}
			result += "<br />";
		}
		if (pierre > 0){
			result += pierre+" pierre";
			if (pierre > 1){
				result += "s";
			}
			result += "<br />";
		}
		if (fiole > 0){
			result += fiole+" fiole";
			if (fiole > 1){
				result += "s";
			}
			result += "<br />";
		}
		if (tapis > 0){
			result += tapis+" tapis<br />";
		}
		if (papyrus > 0){
			result += papyrus+" papyrus<br />";
		}

		result += resultFin+"</html>";
		return result;
	}
}