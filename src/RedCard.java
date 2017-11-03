public class RedCard extends Card {

	public RedCard(int minPlayers, String name, Ressource[] cost, String free, short warPoint, String image) {
		super(minPlayers, name, cost, free, "red", 0, (Capacity)null, (Ressource[])null, (Scientifique)null, warPoint, image);
	}
	
	private RedCard(int minPlayers, String name, RessourceList cost, String free, short warPoint, String image) {
		super(minPlayers, name, cost, free, "red", 0, (Capacity)null, (RessourceList)null, (Scientifique)null, warPoint, image);
	}
	
	public RedCard cloneCard(){
	return new RedCard(this.minPlayers, this.name, this.cost, this.free, this.warPoint, this.image);
}
}
