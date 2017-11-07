public class BlueCard extends Card {

	public BlueCard(int minPlayers, String name, Ressource[] cost, String free, int points, String image){
		super(minPlayers, name, cost, free, "blue", points, (Capacity)null, (Ressource[])null, (Scientifique)null, (short)0, image);
	}
	
	private BlueCard(int minPlayers, String name, RessourceList cost, String free, int points, String image){
		super(minPlayers, name, cost, free, "blue", points, (Capacity)null, (RessourceList)null, (Scientifique)null, (short)0, image);
	}
	
	public BlueCard cloneCard(){
		return new BlueCard(this.minPlayers, this.name, this.cost, this.free, this.point, image);
	}
}
