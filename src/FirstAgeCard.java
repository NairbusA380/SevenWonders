public enum FirstAgeCard implements Card{
	
	/* Cartes bleues */
	AUTEL(null, /*Card.TEMPLE*/null, 2, "autelP.png"),
	
	/* Cartes marrons */
	BASSIN_ARGILEUX(null, new Ressource[]{Ressource.ARGILE}, "bassin_argileux.jpg"),
	EXPLOITATION_FORESTIERE(new Ressource[]{Ressource.PIECE}, new Ressource[]{Ressource.PIERRE, Ressource.BOIS}, "exploitation_forestière.jpg"),

	/* Cartes grises */
	PRESSE(null, new Ressource[]{Ressource.PAPYRUS}, "presse.jpg"),
	
	/* Cartes rouges */
	CASERNE(new Ressource[]{Ressource.MINERAI}, null, 1, "caserne.jpg"),
	
	/* Cartes vertes */
	SCRIPTORIUM(new Ressource[]{Ressource.PAPYRUS}, null,  /*Item.TABLETTE*/0, "scriptorium.jpg"),
	
	/* Cartes jaunes */
	COMPTOIR_EST(null, /*FirstAgeCard.FORUM*/null, /*Capacity.ECHANGE_BASE_DROITE*/0, "comptoir_est.jpg"),
	MARCHE(null, /*FirestAgeCard.CARAVANSERAIL*/null, /*Capacity.ECHANGE_DROITE_GAUCHE*/0, "marché.jpg");	
	
	Ressource[] cost;
	Card free ;
	String color;
	int point; // Pour les cartes bleues
	Capacity capacity; // Pour les cartes jaunes
	Ressource[] giveRessources; // Pour les cartes marrons
	String item; // Pour les cartes vertes (rouage, compas, tablette)
	short warPoint; // Pour les cartes rouges
	String image;
	CardListener listener;
	
	private FirstAgeCard(Ressource[] cost, Card free, int points, String image){ // Bleu
		this.cost = cost;
		this.free = free;
		this.color = "bleu";
		this.point = points;
		this.image = image;
	}
	
	private FirstAgeCard(Ressource[] cost, Ressource[] giveRessources, String image){ // Marron
		this.cost = cost;
		this.color = "marron";
		this.giveRessources = giveRessources;
		this.image = image;
	}
	
	private FirstAgeCard(Ressource[] cost, Ressource giveRessource, String image){ // Gris
		this.cost = cost;
		this.color = "gris";
		this.giveRessources = new Ressource[]{giveRessource};
		this.image = image;
	}
	
	private FirstAgeCard(Ressource[] cost, Card free, short warPoint, String image) { // Rouge
		this.cost = cost;
		this.color = "rouge";
		this.free = free;
		this.warPoint = warPoint;
		this.image = image;
	}

	private FirstAgeCard(Ressource[] cost, Card free, String item, String image) { // Vert
		this.cost = cost;
		this.free = free;
		this.item = item;
		this.image = image;
	}

	private FirstAgeCard(Ressource[] cost, Card free, Capacity capacity, String image) {
		this.cost = cost;
		this.color = "jaune";
		this.free = free;
		this.capacity = capacity;
		this.image = image;
	}

	public Ressource[] getCost(){
		return this.cost;
	}
	
	public Card getFree(){
		return this.free;
	}
	
	public int getPoints(){
		return this.point;
	}
	
	public Capacity getCapacity(){
		return this.capacity;
	}
}
