public class Card {

	String name;
	RessourceList cost;
	String free ;
	final String color;
	int point; // Pour les cartes bleues
	Capacity capacity; // Pour les cartes jaunes
	Ressource giveRessources; // Pour les cartes marrons
	Scientifique item; // Pour les cartes vertes (rouage, compas, tablette)
	short warPoint; // Pour les cartes rouges
	String image;
	CardListener listener;
	boolean haveBeenPlayed;
	final int miniWidth = 65, miniHeight = 100;
	final int bigWidth = 194, bigHeight = 300;

	public Card(String name, Ressource[] cost, String free, int points, String image, String color){ // Bleu
		this(name, cost, free, color, points, (Capacity)null, (Ressource)null, (Scientifique)null, (short)0, image, (CardListener)null);
	}
	public Card(String name, Ressource[] cost, Ressource giveRessources, String image, String color){ // Marron
		this(name, cost, "", color, 0, (Capacity)null, giveRessources, (Scientifique)null, (short)0, image, (CardListener)null);
	}
	public Card(String name, Ressource[] cost, String free, short warPoint, String image, String color) { // Rouge
		this(name, cost, free, color, 0, (Capacity)null, (Ressource)null, (Scientifique)null, warPoint, image, (CardListener)null);
	}

	public Card(String name, Ressource[] cost, String free, Scientifique item, String image, String color) { // Vert
		this(name, cost, free, color, 0, (Capacity)null, (Ressource)null, item, (short)0, image, (CardListener)null);
	}

	public Card(String name, Ressource[] cost, String free, Capacity capacity, String image, String color) {
		this(name, cost, free, color, 0, capacity, (Ressource)null, (Scientifique)null, (short)0, image, (CardListener)null);
	}

	public Card(String name, Ressource[] cout, String free, String color, int points, Capacity capacity, Ressource giveRessources, Scientifique item, short warPoints, String image, CardListener cardListener){
		this.name = name;
		cost = new RessourceList();
		for (Ressource r : cout){
			cost.add(r);
		}
		this.free = free;
		this.color = color;
		this.point = points;
		this.capacity = capacity;
		this.giveRessources = giveRessources;
		this.item = item;
		this.warPoint = warPoints;
		this.image = image;
		this.listener = cardListener;
		this.haveBeenPlayed = false;
	}

	public Card(String name, RessourceList cout, String free, String color, int points, Capacity capacity, Ressource giveRessources, Scientifique item, short warPoints, String image, CardListener cardListener){
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
		this.listener = cardListener;
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
	public Ressource getGiveRessources() {
		return giveRessources;
	}
	public void setGiveRessources(Ressource giveRessources) {
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
	public CardListener getListener() {
		return listener;
	}
	public void setListener(CardListener listener) {
		this.listener = listener;
	}
	public boolean haveBeenPlayed() {
		return haveBeenPlayed;
	}
	public void setPlayed(boolean played) {
		this.haveBeenPlayed = played;
	}
	public int getMiniWidth() {
		return miniWidth;
	}
	public int getMiniHeight() {
		return miniHeight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBigWidth() {
		return bigWidth;
	}
	public int getBigHeight() {
		return bigHeight;
	}

	public Card cloneCard(){
		return new Card(this.name, this.cost, this.free, this.color, this.point, this.capacity, this.giveRessources, this.item, this.warPoint, this.image, this.listener);
	}

	public String toString(){
		String result = "<html>Cette carte permet de :<br /><br />";
		if (this.giveRessources != null){
			if (this.giveRessources.equals(Ressource.PIERRE_BOIS)){
				result += "- Récupérer les ressources pierre et bois (Impossible d'utiliser les deux en un tour)";
			}else{
				result += "- Récupérer la ressource "+giveRessources.toString().toLowerCase();
			}
		}
		if (this.point> 0){

			result += "- Gagner "+point+" point";
			if (point > 1){
				result += "s";
			}
			result +="<br />";
		}
		if (this.getWarPoint() > 0){
			result += "- Gagner "+this.getWarPoint()+" point";
			if (this.getWarPoint() > 1){
				result += "s";
			}
			result +=" de combat</br>";
		}
		if (this.item != null){
			result += "- Gagner l\'item scientifique ";
			result += this.item.toString().toLowerCase()+"<br />";
		}
		return result+"</html>";
	}



}
