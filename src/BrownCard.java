public class BrownCard extends Card{

	public BrownCard(int minPlayers, String name, Ressource[] cost, Ressource[] giveRessources, String image){
		super(minPlayers, name, cost, "", "brown", 0, (Capacity)null, giveRessources, (Scientifique)null, (short)0, image);
	}
	
	private BrownCard(int minPlayers, String name, RessourceList cost, RessourceList giveRessources, String image){
		super(minPlayers, name, cost, "", "brown", 0, (Capacity)null, giveRessources, (Scientifique)null, (short)0, image);
	}	
	
	public BrownCard cloneCard(){
		return new BrownCard(this.minPlayers, this.name, this.cost, this.giveRessources, this.image);
	}
	
}
