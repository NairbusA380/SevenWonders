public enum Ressource {
	PIECE,
	BOIS,
	PIERRE,
	MINERAI,
	TAPIS,
	FIOLE,
	PAPYRUS,
	ARGILE,
	PIERRE_BOIS,
	PIERRE_MINERAI,
	PIERRE_ARGILE,
	BOIS_MINERAI,
	BOIS_ARGILE,
	MINERAI_ARGILE,
	PIERRE_BOIS_MINERAI,
	PIERRE_BOIS_ARGILE,
	PIERRE_MINERAI_ARGILE,
	BOIS_MINERAI_ARGILE,
	PIERRE_BOIS_MINERAI_ARGILE,
	FIOLE_TAPIS,
	FIOLE_PAPYRUS,
	TAPIS_PAPYRUS,
	FIOLE_TAPIS_PAPYRUS;

	public boolean isProduitManufacture(){
		boolean isFiole = this.equals(Ressource.FIOLE);
		boolean isPapyrus = this.equals(Ressource.PAPYRUS);
		boolean isTapis = this.equals(Ressource.TAPIS);
		boolean isFiolePapyrusTapis = this.equals(Ressource.FIOLE_TAPIS_PAPYRUS);
		return isFiole || isPapyrus || isTapis || isFiolePapyrusTapis;
	}
	
	public boolean isRessourceSimple(){
		return this.toString().split("_").length == 1;
	}
	
	public static Ressource toRessource(String s){
		Ressource r = null;
		switch (s){
		case "BOIS" :
			r = Ressource.BOIS;
			break;
		case "PIERRE" :
			r = Ressource.PIERRE;
			break;
		case "MINERAI" :
			r = Ressource.MINERAI;
			break;
		case "TAPIS" :
			r = Ressource.TAPIS;
			break;
		case "FIOLE" :
			r = Ressource.FIOLE;
			break;
		case "PAPYRUS" :
			r = Ressource.PAPYRUS;
			break;
		case "ARGILE" :
			r = Ressource.ARGILE;
			break;
		default :
		}
		return r;
	}
}
