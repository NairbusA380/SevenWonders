import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Player {

	private String name;
	private Wonder wonder;
	private int nbPoints;
	private ArrayList<BattleToken> battleTokens;
	private ArrayList<Scientifique> scientifique = new ArrayList<Scientifique>();
	private ArrayList<Card> playedCards;
	private Player leftPlayer, rightPlayer;
	private RessourceList ressources;
	private RessourceList purchasedRessources;
	private ArrayList<Card> drawedCards;
	private BufferedImage doubleBuffer;
	private int nbGoldEarnedThisTurn = 0;
	private ArrayList<Ressource> purchasedLeftRessources, purchasedRightRessources;

	public Player(String name, Player leftPlayer, Player rightPlayer) {
		this.doubleBuffer = null;
		this.name = name;
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
		this.ressources = new RessourceList();
		for (int i = 0; i < 3; i++){
			ressources.add(Ressource.PIECE);
		}
		this.purchasedRessources = new RessourceList();
		this.nbPoints = 0;
		this.battleTokens = new ArrayList<BattleToken>();
		this.playedCards = new ArrayList<Card>();
		this.drawedCards = new ArrayList<Card>();
		this.wonder = null;
		this.purchasedLeftRessources = new ArrayList<Ressource>();
		this.purchasedRightRessources = new ArrayList<Ressource>();
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

	public int getPlace(){
		int place = 0;
		for (int i = 0; i < Game.getNbPlayer(); i++){
			try {
				if (this.getName().equals(Game.getPlayer(i).getName())){
					place = i;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return place;
	}

	public int acheterRessourcesAGauche(Ressource r){
		int nbGoldNecessaire = 2;
		boolean isRessourceElaboree = false;
		if (!r.isRessourceSimple()){
			Frame.choixUtilisateur = new JOptionPane();
			String[] choix = r.toString().split("_");
			
			int retour = Frame.choixUtilisateur.showOptionDialog(null, "Choisissez la ressource parmi celles disponibles", "Choix de ressource", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			r = Ressource.toRessource(choix[retour]);
		}
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
			System.out.println();
			RessourceList ressources = getRessources();
			for (int i = 0; i < nbGoldNecessaire; i++){
				ressources.remove(Ressource.PIECE);
			}
			setRessources(ressources);
			RessourceList purchasedRessources = getPurchasedRessources();
			purchasedRessources.add(r);
			setPurchasedRessources(purchasedRessources);

			Player left = getLeftPlayer();
			left.nbGoldEarnedThisTurn = nbGoldNecessaire;
			return nbGoldNecessaire;
		}else{
			return -1;
		}
	}

	public int acheterRessourcesADroite(Ressource r){
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
			right.nbGoldEarnedThisTurn = nbGoldNecessaire;
			return nbGoldNecessaire;
		}else{
			return -1;
		}
	}


	public int getNbGoldEarnedThisTurn() {
		return nbGoldEarnedThisTurn;
	}

	public void setNbGoldEarnedThisTurn(int nbGoldEarnedThisTurn) {
		this.nbGoldEarnedThisTurn = nbGoldEarnedThisTurn;
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
				case ROUE:
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
		RessourceList availableRessources = this.getRessources().clone();
		availableRessources.addAll(this.getPurchasedRessources());
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

	public boolean canPay(Card card){
		RessourceList cost;
		if (card.getCost()== null){
			cost = new RessourceList();
		}else{
			cost = card.getCost().clone();
		}

		return this.free(card) || this.canPay(cost);
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

	public ArrayList<Scientifique> getScientifique() {
		return scientifique;
	}

	public void setScientifique(ArrayList<Scientifique> scientifique) {
		this.scientifique = scientifique;
	}

	@Override
	public String toString() {
		return "Player : \nname=" + name + "\nwonder=" + wonder.toString() + "\n nbPoints=" + nbPoints + ", leftPlayer=" + leftPlayer.name + "\n rightPlayer=" + rightPlayer.name;
	}

	public ArrayList<Ressource> getPurchasedLeftRessources() {
		return purchasedLeftRessources;
	}

	public void setPurchasedLeftRessources(ArrayList<Ressource> purchasedLeftRessources) {
		this.purchasedLeftRessources = purchasedLeftRessources;
	}

	public ArrayList<Ressource> getPurchasedRightRessources() {
		return purchasedRightRessources;
	}

	public void setPurchasedRightRessources(ArrayList<Ressource> purchasedRightRessources) {
		this.purchasedRightRessources = purchasedRightRessources;
	}
}
