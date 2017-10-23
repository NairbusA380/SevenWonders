import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

	private String name;
	private Wonder wonder;
	private int nbPoints;
	private ArrayList<BattleToken> battleTokens;
	private ArrayList<Card> playedCards;
	private Player leftPlayer, rightPlayer;
	private RessourceList<Ressource> ressources;
	private RessourceList<Ressource> purchasedRessources;
	private ArrayList<Card> drawedCards;
	private BufferedImage doubleBuffer;

	public Player(String name, Player leftPlayer, Player rightPlayer) {
		this.doubleBuffer = null;
		this.name = name;
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
		this.ressources = new RessourceList<Ressource>();
		this.purchasedRessources = new RessourceList<Ressource>();
		for (int i = 0; i < 3; i++){
			ressources.add(Ressource.PIECE);
		}
		this.nbPoints = 0;
		this.battleTokens = new ArrayList<BattleToken>();
		this.playedCards = new ArrayList<Card>();
		this.drawedCards = new ArrayList<Card>();
		this.wonder = null;
	}


	public Player(String name) {
		this(name, null, null);
	}


	public int getGold(){
		int resultat = 0;
		for (Object o : ressources){
			Ressource r = (Ressource) o;
			if (r.equals(Ressource.PIECE)){
				resultat++;
			}
		}
		return resultat;
	}

	public int countWarForce(){
		int warForce = 0;
		for (Card c : playedCards){
			if (c.getColor() == "red"){
				warForce += c.getWarPoint();
			}
		}
		return warForce;
	}

	public int countWarPoints(){
		int warPoints = 0;
		for (BattleToken b : getBattleTokens()){
			warPoints += b.getPoints();
		}
		return warPoints;
	}

	public void validerStep(Card c){
		this.getDrawedCards().remove(c);
		int i = 0;
		for (Step s : wonder.getStep()){
			if (!s.isValidated()){
				break;
			}else{
				i++;
			}
		}
		Step[] steps = wonder.getStep();
		Step validateStep = steps[i];
		validateStep.setValidated(true);
		wonder.setStep(steps);
		if (validateStep.points > 0){
			this.setNbPoints(this.getNbPoints()+validateStep.points);
		}
		if (validateStep.ressourcesGained != null){
			RessourceList<Ressource> ressources = this.getRessources();
			ressources.add(validateStep.ressourcesGained);
			this.setRessources(ressources);
		}
	}

	public boolean canPay(RessourceList<Ressource> cout){
		RessourceList<Ressource> availableRessources = this.getRessources();
		return availableRessources.containsAll(cout);
	}

	public boolean cardNotAlreadyPlayed(Card card){
		boolean notAlreadyPlayed = true;
		for (Card c : this.getPlayedCards()){
			if (c.equals(card)){
				notAlreadyPlayed = false;
				break;
			}
		}
		return notAlreadyPlayed;
	}

	public boolean free(Card card){
		boolean result = false;
		for (Card c : this.getPlayedCards()){
			String free = c.getFree();
			if (free == card.getName()){
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean canPlay(Card card){
		RessourceList<Ressource> cost;
		if (card.getCost()== null){
			cost = new RessourceList<Ressource>();
		}else{
			List<Ressource> a = Arrays.asList(card.getCost());
			cost = new RessourceList<Ressource>(new ArrayList<Ressource>(a));
		}
		RessourceList<Ressource> availableRessources = this.getRessources();

		return (this.free(card)) || (this.cardNotAlreadyPlayed(card) && this.canPay(cost));
	}

	public RessourceList<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(RessourceList<Ressource> ressources) {
		this.ressources = ressources;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Wonder getWonder() {
		return wonder;
	}

	public void setWonder(Wonder wonder) {
		this.wonder = wonder;
	}

	public RessourceList<Ressource> getPurchasedRessources() {
		return purchasedRessources;
	}


	public void setPurchasedRessources(RessourceList<Ressource> purchasedRessources) {
		this.purchasedRessources = purchasedRessources;
	}


	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public ArrayList<BattleToken> getBattleTokens() {
		return battleTokens;
	}

	public void setBattleTokens(ArrayList<BattleToken> battleTokens) {
		this.battleTokens = battleTokens;
	}

	public ArrayList<Card> getPlayedCards() {
		return playedCards;
	}

	public void setPlayedCards(ArrayList<Card> playedCards) {
		this.playedCards = playedCards;
	}

	public Player getLeftPlayer() {
		return leftPlayer;
	}

	public void setLeftPlayer(Player leftPlayer) {
		this.leftPlayer = leftPlayer;
	}

	public Player getRightPlayer() {
		return rightPlayer;
	}

	public void setRightPlayer(Player rightPlayer) {
		this.rightPlayer = rightPlayer;
	}

	public ArrayList<Card> getDrawedCards() {
		return drawedCards;
	}

	public void setDrawedCards(ArrayList<Card> drawedCards) {
		this.drawedCards = drawedCards;
	}

	public BufferedImage getDoubleBuffer() {
		return doubleBuffer;
	}


	public void setDoubleBuffer(BufferedImage doubleBuffer) {
		this.doubleBuffer = doubleBuffer;
	}


	@Override
	public String toString() {
		return "Player : \nname=" + name + "\nwonder=" + wonder.toString() + "\n nbPoints=" + nbPoints + ", leftPlayer=" + leftPlayer.name + "\n rightPlayer=" + rightPlayer.name;
	}


}
