import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollBarUI;

public class RessourceList<T> extends AbstractList{

	ArrayList<T> ressourceList;

	public RessourceList(){
		this.ressourceList = new ArrayList<T>();
	}

	public RessourceList(ArrayList<T> c){
		this.ressourceList = c;
	}

	public boolean add(Object e){
		return ressourceList.add((T)e);
	}

	public boolean addAll(RessourceList<? extends T> c){
		return ressourceList.addAll(c);
	}

	public boolean remove(Object e){
		return ressourceList.remove(e);
	}

	public T get(int arg0) {
		return ressourceList.get(arg0);
	}

	public int size() {
		return ressourceList.size();
	}

	public boolean containsSimple(RessourceList<T> list, Object c){
		boolean result = false;
		T caste = (T)c;
		String nameCompared = caste.toString();
		for (Object o : list){
			T object = (T)o;
			String nameParcouru = object.toString();
			if (nameParcouru == nameCompared){
				result = true;
				list.remove(object);
				break;
			}
		}
		return result;
	}

	public RessourceList<T> rechercheElaboree (List<T> dispo, List<T> cout){
		RessourceList<T> newDispo = new RessourceList<T>();
		boolean besoinPierre = false, besoinArgile = false, besoinBois = false, besoinMinerai = false, besoinTapis = false, besoinPapyrus = false, besoinFiole = false;

		/* Recensement des ressources non demandées (besoinXXX = false) */
		for (T object : cout){
			Ressource ress = (Ressource) object;
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
		for (T rDispo : dispo){
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
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement == 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE_BOIS_ARGILE);
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.PIERRE_BOIS_MINERAI);
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE_BOIS);
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.PIERRE_MINERAI_ARGILE);
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE_ARGILE);
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.PIERRE_MINERAI);
					}
					if (donnePierreInutilement == 1 && donneBoisInutilement != 1 && donneArgileInutilement != 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.PIERRE);
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement == 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.BOIS_MINERAI_ARGILE);
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.BOIS_ARGILE);
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.BOIS_MINERAI);
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement == 1 && donneArgileInutilement != 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.BOIS);
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.MINERAI_ARGILE);
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement != 1 && donneArgileInutilement == 1 && donneMineraiInutilement != 1){
						newDispo.add(Ressource.ARGILE);
					}
					if (donnePierreInutilement != 1 && donneBoisInutilement != 1 && donneArgileInutilement != 1 && donneMineraiInutilement == 1){
						newDispo.add(Ressource.MINERAI);
					}
				}else{
					if (donneFioleInutilement == 1 && donneTapisInutilement == 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.FIOLE_TAPIS_PAPYRUS);
					}
					if (donneFioleInutilement == 1 && donneTapisInutilement == 1 && donnePapyrusInutilement != 1){
						newDispo.add(Ressource.FIOLE_TAPIS);
					}
					if (donneFioleInutilement == 1 && donneTapisInutilement != 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.FIOLE_PAPYRUS);
					}
					if (donneFioleInutilement == 1 && donneTapisInutilement != 1 && donnePapyrusInutilement != 1){
						newDispo.add(Ressource.FIOLE);
					}
					if (donneFioleInutilement != 1 && donneTapisInutilement == 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.TAPIS_PAPYRUS);
					}
					if (donneFioleInutilement != 1 && donneTapisInutilement == 1 && donnePapyrusInutilement != 1){
						newDispo.add(Ressource.TAPIS);
					}
					if (donneFioleInutilement != 1 && donneTapisInutilement != 1 && donnePapyrusInutilement == 1){
						newDispo.add(Ressource.PAPYRUS);
					}
				}
			}
		}
		return newDispo;
	}


	public boolean containsAll(RessourceList<T> cout){
		RessourceList<T> dispo = new RessourceList<T>();
		for (Object e : this.ressourceList){
			dispo.add((T)e);
		}
		boolean result = true;
		RessourceList<T> previousDispo = new RessourceList<T>();

		while ((cout != null && cout.size()> 0) && (!previousDispo.equals(dispo))){
			previousDispo = dispo;
			for (int i = 0; i < cout.size(); i++){
				T o = cout.ressourceList.get(i);
				if (!this.containsSimple(dispo, o)){
				}else{
					cout.remove(o);
				}
				dispo = this.rechercheElaboree(dispo, cout);
			}
		}

		return !(cout.size() > 0);
	}

	public List<T> getRessourceList() {
		return ressourceList;
	}

	public void setRessourceList(ArrayList<T> ressourceList) {
		this.ressourceList = ressourceList;
	}



}