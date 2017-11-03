public class GreyCard extends Card{

	public GreyCard(int minPlayers, String name, Ressource[] cost, Ressource[] giveRessources, String image){
		super(minPlayers, name, cost, "", "grey", 0, (Capacity)null, giveRessources, (Scientifique)null, (short)0, image);
	}
	
	private GreyCard(int minPlayers, String name, RessourceList cost, RessourceList giveRessources, String image){
		super(minPlayers, name, cost, "", "grey", 0, (Capacity)null, giveRessources, (Scientifique)null, (short)0, image);
	}	
	
	public GreyCard cloneCard(){
		return new GreyCard(this.minPlayers, this.name, this.cost, this.giveRessources, this.image);
	}
	
}
