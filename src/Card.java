public abstract class Card {

	String name;
	RessourceList cost;
	String free ;
	final String color;
	int point; // Pour les cartes bleues
	Capacity capacity; // Pour les cartes jaunes
	RessourceList giveRessources; // Pour les cartes marrons
	Scientifique item; // Pour les cartes vertes (rouage, compas, tablette)
	short warPoint; // Pour les cartes rouges
	String image;
	boolean haveBeenPlayed;
	static final int widthMini = 65, heightMini = 100;
	static final int widthBig = 194, heightBig = 300;
	int minPlayers;

	public Card(int minPlayers, String name, Ressource[] cout, String free, String color, int points, Capacity capacity, Ressource[] giveRessources, Scientifique item, short warPoints, String image){
		this.minPlayers = minPlayers;
		this.name = name;
		if (cout != null){
			cost = new RessourceList();
			for (Ressource r : cout){
				cost.add(r);
			}
		}else{
			cost = null;
		}
		this.free = free;
		this.color = color;
		this.point = points;
		this.capacity = capacity;
		if (giveRessources != null){
			this.giveRessources = new RessourceList();
			for (Ressource r : giveRessources){
				this.giveRessources.add(r);
			}
		}else{
			this.giveRessources = null;
		}
		this.item = item;
		this.warPoint = warPoints;
		this.image = image;
		this.haveBeenPlayed = false;
	}

	public Card(int minPlayers, String name, RessourceList cout, String free, String color, int points, Capacity capacity, RessourceList giveRessources, Scientifique item, short warPoints, String image){
		this.minPlayers = minPlayers;
		this.name = name;
		this.cost = cout;
		this.free = free;
		this.color = color;
		this.point = points;
		this.capacity = capacity;
		this.giveRessources = giveRessources;
		this.item = item;
		this.warPoint = warPoints;
		this.image = image;
		this.haveBeenPlayed = false;
	}


	public String getCostString(){
		return cost.toString();
	}	

	public boolean equals(Card c){
		return this.getName() == c.getName();
	}

	/* Getters et setters */
	public RessourceList getCost() {
		return cost;
	}
	public void setCost(RessourceList cost) {
		this.cost = cost;
	}
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
	public String getColor() {
		return color;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Capacity getCapacity() {
		return capacity;
	}
	public void setCapacity(Capacity capacity) {
		this.capacity = capacity;
	}
	public RessourceList getGiveRessources() {
		return giveRessources;
	}
	public void setGiveRessources(RessourceList giveRessources) {
		this.giveRessources = giveRessources;
	}
	public Scientifique getItem() {
		return item;
	}
	public void setItem(Scientifique item) {
		this.item = item;
	}
	public short getWarPoint() {
		return warPoint;
	}
	public void setWarPoint(short warPoint) {
		this.warPoint = warPoint;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean haveBeenPlayed() {
		return haveBeenPlayed;
	}
	public void setPlayed(boolean played) {
		this.haveBeenPlayed = played;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		String result = "<html>";
		if (this.giveRessources != null){
			if (this.giveRessources.equals(Ressource.PIERRE_BOIS)){
				result += "- Récupération des ressources pierre et bois (Impossible d'utiliser les deux en un tour)";
			}else{
				result += "- Récupération de la ressource "+giveRessources.toString().toLowerCase();
			}
		}
		if (this.point> 0){

			result += "- Gain de "+point+" point";
			if (point > 1){
				result += "s";
			}
			result +="<br />";
		}
		if (this.getWarPoint() > 0){
			result += "- Gain de "+this.getWarPoint()+" point";
			if (this.getWarPoint() > 1){
				result += "s";
			}
			result +=" de combat</br>";
		}
		if (this.item != null){
			result += "- Gain de l\'item scientifique ";
			result += this.item.toString().toLowerCase()+"<br />";
		}
		if (this.capacity != null){
			result += capacity.toString()+"<br />";
		}
		return result+"</html>";
	}



}
