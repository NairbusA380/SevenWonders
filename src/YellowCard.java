public class YellowCard extends Card{

	public YellowCard(int minPlayers, String name, Ressource[] cost, String free, Capacity capacity, String image) {
		super(minPlayers, name, cost, free, "yellow", 0, capacity, (Ressource[])null, (Scientifique)null, (short)0, image);
	}
	
	private YellowCard(int minPlayers, String name, RessourceList cost, String free, Capacity capacity, String image) {
		super(minPlayers, name, cost, free, "yellow", 0, capacity, (RessourceList)null, (Scientifique)null, (short)0, image);
	}

	public YellowCard cloneCard(){
		return new YellowCard(this.minPlayers, this.name, this.cost, this.free, this.capacity, this.image);
	}

}
