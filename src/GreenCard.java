public class GreenCard extends Card{

	public GreenCard(int minPlayers, String name, Ressource[] cost, String free, Scientifique item, String image) { 
		super(minPlayers, name, cost, free, "green", 0, (Capacity)null, (Ressource[])null, item, (short)0, image);
	}
	
	private GreenCard(int minPlayers, String name, RessourceList cost, String free, Scientifique item, String image) {
		super(minPlayers, name, cost, free, "green", 0, (Capacity)null, (RessourceList)null, item, (short)0, image);
	}

	public GreenCard cloneCard(){
		return new GreenCard(this.minPlayers, this.name, this.cost, this.free, this.item, this.image);
	}
}
