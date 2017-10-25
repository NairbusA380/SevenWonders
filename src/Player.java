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
	private RessourceList ressources;
	private RessourceList purchasedRessources;
	private ArrayList<Card> drawedCards;
	private BufferedImage doubleBuffer;

	public Player(String name, Player leftPlayer, Player rightPlayer) {
		this.doubleBuffer = null;
		this.name = name;
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
		this.ressources = new RessourceList();
		this.purchasedRessources = new RessourceList();
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

	public RessourceList getLeftRessources(){
		return getLeftPlayer().getRessources();
	}

	public RessourceList getRightRessources(){
		return getRightPlayer().getRessources();
	}

	public boolean acheterRessourcesAGauche(Ressource r){
		int nbGoldNecessaire = 2;
		boolean isRessourceElaboree = false;
		if (r.equals(Ressource.FIOLE) || r.equals(Ressource.TAPIS) || r.equals(Ressource.PAPYRUS)){
			isRessourceElaboree = true;
		}

		if (isRessourceElaboree){
			for (Card c : getPlayedCards()){
				if (c.getCapacity() != null && c.getCapacity().equals(Capacity.ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE)){
					nbGoldNecessaire = 1;
				}
			}
		}else{
			for (Card c : getPlayedCards()){
				if (c.getCapacity() != null && (c.getCapacity().equals(Capacity.ECHANGE_MATIERE_PREMIERE_DROITE_GAUCHE) || (c.getCapacity().equals(Capacity.ECHANGE_MATIERE_PREMIERE_GAUCHE)))){
					nbGoldNecessaire = 1;
				}
			}
		}

		if(getGold()>=nbGoldNecessaire){
			RessourceList ressources = getRessources();
			for (int i = 0; i < nbGoldNecessaire; i++){
				ressources.remove(Ressource.PIECE);
			}
			setRessources(ressources);
			RessourceList purchasedRessources = getPurchasedRessources();
			purchasedRessources.add(r);
			setPurchasedRessources(purchasedRessources);

			Player left = getLeftPlayer();
			RessourceList leftRessources = left.getRessources();
			for (int i = 0; i < nbGoldNecessaire; i++){
				ressources.add(Ressource.PIECE);
			}
			left.setRessources(leftRessources);
			return true;
		}else{
			return false;
		}
	}

	public boolean acheterRessourcesADroite(Ressource r){
		int nbGoldNecessaire = 2;
		boolean isRessourceElaboree = false;
		if (r.equals(Ressource.FIOLE) || r.equals(Ressource.TAPIS) || r.equals(Ressource.PAPYRUS)){
			isRessourceElaboree = true;
		}

		if (isRessourceElaboree){
			for (Card c : getPlayedCards()){
				if (c.getCapacity() != null && c.getCapacity().equals(Capacity.ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE)){
					nbGoldNecessaire = 1;
				}
			}
		}else{
			for (Card c : getPlayedCards()){
				if (c.getCapacity() != null && (c.getCapacity().equals(Capacity.ECHANGE_MATIERE_PREMIERE_DROITE_GAUCHE) || (c.getCapacity().equals(Capacity.ECHANGE_MATIERE_PREMIERE_GAUCHE)))){
					nbGoldNecessaire = 1;
				}
			}
		}

		if(getGold()>=nbGoldNecessaire){
			RessourceList ressources = getRessources();
			for (int i = 0; i < nbGoldNecessaire; i++){
				ressources.remove(Ressource.PIECE);
			}
			setRessources(ressources);
			RessourceList purchasedRessources = getPurchasedRessources();
			purchasedRessources.add(r);
			setPurchasedRessources(purchasedRessources);

			Player right = getRightPlayer();
			RessourceList leftRessources = right.getRessources();
			for (int i = 0; i < nbGoldNecessaire; i++){
				ressources.add(Ressource.PIECE);
			}
			right.setRessources(leftRessources);
			return true;
		}else{
			return false;
		}
	}


	public int getGold(){
		int resultat = 0;
		for (Ressource r : ressources.ressourceList){
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

	public int countGreenPoints() {
		int compas = 0, tablette = 0, rouage = 0;
		for (Card c : playedCards){
			if (c.getColor() == "green"){
				switch (c.getItem()){
				case COMPAS:
					compas++;
					break;
				case TABLETTE:
					tablette++;
					break;
				case ROUAGE:
					rouage++;
					break;
				default:
					break;
				}
			}
		}

		return (tablette*tablette)+(rouage*rouage)+(compas*compas);
	}


	public int countYellowPoints() {
		return 0;
	}


	public int countBluePoints() {
		int bluePoints = 0;
		for (Card c : getPlayedCards()){
			if (c.getColor() == "blue"){
				bluePoints += c.getPoint();
			}
		}

		return bluePoints;
	}


	public int countPurplePoints() {
		return 0;
	}


	public int countStepPoints() {
		int stepPoints= 0;
		for (Step s : getWonder().getStep()){
			if (s.isValidated()){
				stepPoints += s.points;
			}else{
				break;
			}
		}
		return stepPoints;
	}


	public int countCoinPoints() {
		int coins = 0;
		for (Ressource o : getRessources().ressourceList){
			Ressource r = (Ressource)o;
			if (r.equals(Ressource.PIECE)){
				coins++;
			}
		}
		return (coins/3);
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
			RessourceList ressources = this.getRessources();
			ressources.addAll(validateStep.ressourcesGained);
			this.setRessources(ressources);
		}
	}

	public boolean canPay(RessourceList cout){
		RessourceList availableRessources = this.getRessources();
		RessourceList availableRessourcesWithoutUnused = availableRessources.supressAllUnusedRessources(cout);
		return availableRessourcesWithoutUnused.containsAll(cout);
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
		RessourceList cost;
		if (card.getCost()== null){
			cost = new RessourceList();
		}else{
			cost = card.getCost().clone();
		}

		return (this.free(card)) || (this.cardNotAlreadyPlayed(card) && this.canPay(cost));
	}

	public RessourceList getRessources() {
		return ressources;
	}

	public void setRessources(RessourceList ressources) {
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

	public RessourceList getPurchasedRessources() {
		return purchasedRessources;
	}


	public void setPurchasedRessources(RessourceList purchasedRessources) {
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
