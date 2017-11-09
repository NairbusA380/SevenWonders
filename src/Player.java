import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Player {

	private String name;
	private Wonder wonder;
	private int nbPoints;
	private ArrayList<BattleToken> battleTokens;
	private ArrayList<Scientifique> scientifique = new ArrayList<Scientifique>();
	private ArrayList<Card> playedCards;
	private Card cardChoosen;
	private Player leftPlayer, rightPlayer;
	private RessourceList ressources;
	private RessourceList purchasedRessources;
	private ArrayList<Card> drawedCards;
	private BufferedImage doubleBuffer;
	private int nbGoldEarnedThisTurn = 0;
	private int nbGoldPayedThisTurn = 0;

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
			SevenWonders.getLogger().log(Level.INFO, "Tentative d'achat de la ressource doublée "+r.toString());
			Frame.choixUtilisateur = new JOptionPane();
			String[] choix = r.toString().split("_");

			int retour = Frame.choixUtilisateur.showOptionDialog(null, "Choisissez la ressource parmi celles disponibles", "Choix de ressource", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
			r = Ressource.toRessource(choix[retour]);
			SevenWonders.getLogger().log(Level.INFO, "Choix du joueur : "+r.toString());

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
			SevenWonders.getLogger().log(Level.INFO, "Le joueur peut payer la ressource");
			RessourceList ressources = getRessources();
			for (int i = 0; i < nbGoldNecessaire; i++){
				ressources.remove(Ressource.PIECE);
			}
			setRessources(ressources);
			RessourceList purchasedRessources = getPurchasedRessources();
			purchasedRessources.add(r);
			setPurchasedRessources(purchasedRessources);

			this.setNbGoldPayedThisTurn(nbGoldNecessaire);
			Player left = getLeftPlayer();
			left.nbGoldEarnedThisTurn = nbGoldNecessaire;
			return nbGoldNecessaire;
		}else{
			SevenWonders.getLogger().log(Level.INFO, "Le joueur ne peut pas payer la ressource");
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

			this.setNbGoldPayedThisTurn(nbGoldNecessaire);
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
		for (Step s : getWonder().getStep()){
			if (s.capacity.equals(Capacity.GAGNER_2_BOUCLIERS)) {
				warForce += 2;
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
		//SevenWonders.getLogger().log(Level.INFO, "Le joueur peut-il payer le cout "+cout.toString()+" ?");
		RessourceList availableRessources = this.getRessources().clone();
		availableRessources.addAll(this.getPurchasedRessources());
		//SevenWonders.getLogger().log(Level.INFO, "Le joueur a "+availableRessources.toString());
		RessourceList availableRessourcesWithoutUnused = availableRessources.supressAllUnusedRessources(cout);
		//SevenWonders.getLogger().log(Level.INFO, "Le joueur a d'utile"+availableRessourcesWithoutUnused.toString());
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
			String[] cardsFree = free.split("_");
			for (int i = 0; i < cardsFree.length; i++){
				if (cardsFree[i] == card.getName()){
					result = true;
					break;
				}
			}
			if (result){
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

	public int playCard(Card card){
		/* On vérifie encore si on peut la jouer */
		//if (Game.canPlay(player, card)){

		/* Si cout en pieces, il faut les défausser */
		if (card != null){
			int nbCoinsNeeded = 0;

			if (card.getCost() != null){
				for (Object o : card.getCost()){
					Ressource r = (Ressource)o;
					if (r.equals(Ressource.PIECE)){
						nbCoinsNeeded ++;
					}
				}
				RessourceList available = this.getRessources();
				for (int i = 0; i < nbCoinsNeeded; i++){
					available.remove(Ressource.PIECE);
				}
				this.setRessources(available);
			}

			/* Si carte marron ou grise, il faut ajouter la ressource */
			if (card.getGiveRessources() != null){
				RessourceList available = this.getRessources();
				available.addAll(card.getGiveRessources());
				this.setRessources(available);
			}

			/* Si carte verte, ajouter l'item */

			if (card.getItem() != null){
				ArrayList<Scientifique> available = this.getScientifique();
				available.add(card.getItem());
				this.setScientifique(available);
			}

			this.getPlayedCards().add(card);
			this.getDrawedCards().remove(card);

			return nbCoinsNeeded;
		}else{
			return -1;
		}
	}

	public void chooseCard(Card c){
		this.cardChoosen = c;
		int costGold = 0;
		if (c.getCost() != null) {
			for (Ressource ressource : c.getCost()) {
				if (ressource.equals(Ressource.PIECE)) {
					costGold++;
				}
			}
			this.nbGoldPayedThisTurn += costGold;
		}
		if (c.getCapacity() != null) {
			switch (c.getCapacity()) {
				case RAPPORTE_1_PIECE_MARRON_JOUEUR_ET_VOISINS:
					this.nbGoldEarnedThisTurn += this.countBrownCardYouAndNeighbours();
					break;
				case RAPPORTE_2_PIECES_GRIS_JOUEUR_ET_VOISINS:
					this.nbGoldEarnedThisTurn += 2*this.countBrownCardYouAndNeighbours();
					break;
				case RAPPORTE_1_PIECE_1_VICTOIRE_MARRON:
					this.nbGoldEarnedThisTurn += this.countYourBrownCard();
					break;
				case RAPPORTE_1_PIECE_1_VICTOIRE_JAUNE:
					this.nbGoldEarnedThisTurn += this.countYourYellowCard();
					break;
				case RAPPORTE_2_PIECE_2_VICTOIRE_GRIS:
					this.nbGoldEarnedThisTurn += 2*this.countYourGreyCard();
					break;
				case RAPPORTE_3_PIECE_1_VICTOIRE_ETAPE:
					this.nbGoldEarnedThisTurn += 3*this.countStepsValidated();
					break;
			}
		}
	}
	
	private int countStepsValidated() {
		int nbSteps = 0;
		for (Step step : this.getWonder().getStep()) {
			if (step.isValidated()) {
				nbSteps++;
			}else {
				break;
			}
		}
		return nbSteps;
	}
	
	private int countYourBrownCard() {
		int nbBrownCards = 0;
		
		for (Card card : this.getPlayedCards()) {
			if (card.getColor() == "brown") {
				nbBrownCards++;
			}
		}
		return nbBrownCards;
	}
	
	private int countYourYellowCard() {
		int nbYellowCards = 0;
		
		for (Card card : this.getPlayedCards()) {
			if (card.getColor() == "yellow") {
				nbYellowCards++;
			}
		}
		return nbYellowCards;
	}
	
	private int countYourGreyCard() {
		int nbGreyCards = 0;
		
		for (Card card : this.getPlayedCards()) {
			if (card.getColor() == "grey") {
				nbGreyCards++;
			}
		}
		return nbGreyCards;
	}
	
	private int countBrownCardYouAndNeighbours() {
		Player left = this.getLeftPlayer();
		Player right = this.getRightPlayer();
		int nbBrownCards = 0;
		for (Card card : left.getPlayedCards()) {
			if (card.getColor() == "brown") {
				nbBrownCards++;
			}
		}
		for (Card card : right.getPlayedCards()) {
			if (card.getColor() == "brown") {
				nbBrownCards++;
			}
		}
		for (Card card : this.getPlayedCards()) {
			if (card.getColor() == "brown") {
				nbBrownCards++;
			}
		}
		return nbBrownCards;
	}
	
	private int countGreyCardYouAndNeighbours() {
		Player left = this.getLeftPlayer();
		Player right = this.getRightPlayer();
		int nbGreyCards = 0;
		for (Card card : left.getPlayedCards()) {
			if (card.getColor() == "grey") {
				nbGreyCards++;
			}
		}
		for (Card card : right.getPlayedCards()) {
			if (card.getColor() == "grey") {
				nbGreyCards++;
			}
		}
		for (Card card : this.getPlayedCards()) {
			if (card.getColor() == "grey") {
				nbGreyCards++;
			}
		}
		return nbGreyCards;
	}

	public RessourceList getRessources() {
		return ressources;
	}

	public void setRessources(RessourceList ressources) {
		this.ressources = ressources;
		SevenWonders.getLogger().log(Level.INFO, "----------------------On change les ressources du joueur "+this.getName()+" : "+ressources.toString());

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
		SevenWonders.getLogger().log(Level.INFO, "----------------------On change les ressources achetées du joueur "+this.getName()+" : "+purchasedRessources.toString());

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

	public Card getCardChoosen() {
		return cardChoosen;
	}

	public void setCardChoosen(Card cardChoosen) {
		this.cardChoosen = cardChoosen;
	}

	public int getNbGoldPayedThisTurn() {
		return nbGoldPayedThisTurn;
	}

	public void setNbGoldPayedThisTurn(int nbGoldPayedThisTurn) {
		this.nbGoldPayedThisTurn = nbGoldPayedThisTurn;
	}

}
