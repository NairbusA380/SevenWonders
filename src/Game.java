import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

	private static Player[] players;
	private static Player playerTurn;
	private static CardList<Card> firstAgeCards, secondAgeCards, thirdAgeCards;
	private static CardList<Card> defausse;
	private static int currentAge;
	private static int identifierPlayerTurn;
	private static int identifierStart;
	private static int nbPlayer;

	public Game(int nbPlayers){		
		Game.nbPlayer = nbPlayers;
		Game.players = new Player[nbPlayers];
		createPlayers();
		this.setPlayersPosition();
		Game.players = this.attributeWondersToPlayers();
		firstAgeCards = new CardList<Card>();
		secondAgeCards = new CardList<Card>();
		thirdAgeCards = new CardList<Card>();
		defausse = new CardList<Card>();
		createFirstAgeCards();
		addFirstAgeCardsToPoolCards(Game.players);
		playerTurn = players[0];
		identifierPlayerTurn = 0;
		identifierStart = 0;
		currentAge = 1;
	}

	private static void createFirstAgeCards() {
		ArrayList<Card> cardsCreated = new ArrayList<Card>();
		Card autel = new Card("Autel", (Ressource[])null, "Temple", 2, "autel", "blue");
		Card exploitFor = new Card("Exploitation forestière", new Ressource[]{Ressource.PIECE}, Ressource.PIERRE_BOIS, "exploitation_forestière", "brown");
		Card caserne = new Card("Caserne", new Ressource[]{Ressource.BOIS}, "", (short)1, "caserne", "red");
		Card comptoirEst = new Card("Comptoir Est", (Ressource[])null, "Forum", Capacity.ECHANGE_BASE_DROITE, "comptoir_est", "yellow");
		Card presse = new Card("Presse", (Ressource[])null, Ressource.PAPYRUS, "presse", "grey");
		Card scriptorium = new Card("Scriptorium", new Ressource[]{Ressource.PAPYRUS}, "", "Tablette", "scriptorium", "green");
		Card marche = new Card("Marche", (Ressource[])null, "", Capacity.ECHANGE_ELABORE_DROITE_GAUCHE, "marche", "yellow");
		
		cardsCreated.add(autel);
		cardsCreated.add(exploitFor);
		cardsCreated.add(caserne);
		cardsCreated.add(comptoirEst);
		cardsCreated.add(presse);
		cardsCreated.add(scriptorium);
		cardsCreated.add(marche);

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitFor.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitFor.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		//Melange des cartes

		Random r = new Random();
		Card c;
		while (!cardsCreated.isEmpty()){
			int index = r.nextInt(cardsCreated.size());
			c = cardsCreated.get(index);
			cardsCreated.remove(index);
			firstAgeCards.add(c);
		}	
	}

	private static void createThirdAgeCards() {
		ArrayList<Card> cardsCreated = new ArrayList<Card>();
		Card autel = new Card("Autel", (Ressource[])null, "Temple", 2, "autel", "blue");
		Card exploitFor = new Card("Exploitation forestière", new Ressource[]{Ressource.PIECE}, Ressource.PIERRE_BOIS, "exploitation_forestière", "brown");
		Card caserne = new Card("Caserne", new Ressource[]{Ressource.BOIS}, "", (short)1, "caserne", "red");
		Card comptoirEst = new Card("Comptoir Est", (Ressource[])null, "Forum", Capacity.ECHANGE_BASE_DROITE, "comptoir_est", "yellow");
		Card presse = new Card("Presse", (Ressource[])null, Ressource.PIERRE_BOIS, "presse", "grey");
		Card scriptorium = new Card("Scriptorium", new Ressource[]{Ressource.PAPYRUS}, "", "Tablette", "scriptorium", "green");
		Card marche = new Card("Marche", (Ressource[])null, "", Capacity.ECHANGE_ELABORE_DROITE_GAUCHE, "marche", "yellow");
		
		cardsCreated.add(autel);
		cardsCreated.add(exploitFor);
		cardsCreated.add(caserne);
		cardsCreated.add(comptoirEst);
		cardsCreated.add(presse);
		cardsCreated.add(scriptorium);
		cardsCreated.add(marche);

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitFor.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitFor.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		//Melange des cartes

		Random r = new Random();
		Card c;
		while (!cardsCreated.isEmpty()){
			int index = r.nextInt(cardsCreated.size());
			c = cardsCreated.get(index);
			cardsCreated.remove(index);
			thirdAgeCards.add(c);
		}	
	}

	private static void createSecondAgeCards() {
		ArrayList<Card> cardsCreated = new ArrayList<Card>();
		Card autel = new Card("Autel", (Ressource[])null, "Temple", 2, "autel", "blue");
		Card exploitFor = new Card("Exploitation forestière", new Ressource[]{Ressource.PIECE}, Ressource.PIERRE_BOIS, "exploitation_forestière", "brown");
		Card caserne = new Card("Caserne", new Ressource[]{Ressource.BOIS}, "", (short)1, "caserne", "red");
		Card comptoirEst = new Card("Comptoir Est", (Ressource[])null, "Forum", Capacity.ECHANGE_BASE_DROITE, "comptoir_est", "yellow");
		Card presse = new Card("Presse", (Ressource[])null, Ressource.PAPYRUS, "presse", "grey");
		Card scriptorium = new Card("Scriptorium", new Ressource[]{Ressource.PAPYRUS}, "", "Tablette", "scriptorium", "green");
		Card marche = new Card("Marche", (Ressource[])null, "", Capacity.ECHANGE_ELABORE_DROITE_GAUCHE, "marche", "yellow");
		
		cardsCreated.add(autel);
		cardsCreated.add(exploitFor);
		cardsCreated.add(caserne);
		cardsCreated.add(comptoirEst);
		cardsCreated.add(presse);
		cardsCreated.add(scriptorium);
		cardsCreated.add(marche);

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitFor.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitFor.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		//Melange des cartes

		Random r = new Random();
		Card c;
		while (!cardsCreated.isEmpty()){
			int index = r.nextInt(cardsCreated.size());
			c = cardsCreated.get(index);
			cardsCreated.remove(index);
			secondAgeCards.add(c);
		}	
	}

	private static void addFirstAgeCardsToPoolCards(Player[] players){
		/*for (Card c : this.firstAgeCards){
			poolCards.add(c);
		}*/
		ArrayList<Card> pioche = new ArrayList<Card>();
		for (Object o : Game.firstAgeCards){
			Card c = (Card)o;
			pioche.add(c);
		}
		int piocheJoueur = 0;
		Random r = new Random();

		while (!pioche.isEmpty()){
			int i = r.nextInt(pioche.size());
			Game.players[piocheJoueur].getDrawedCards().add(pioche.get(i));
			pioche.remove(i);
			piocheJoueur = (piocheJoueur+1)%(getNbPlayer());
		}
	}

	private static void addSecondAgeCardsToPoolCards(Player[] players){
		/*for (Card c : this.firstAgeCards){
			poolCards.add(c);
		}*/
		ArrayList<Card> pioche = new ArrayList<Card>();
		for (Object o : Game.secondAgeCards){
			Card c = (Card)o;
			pioche.add(c);
		}
		int piocheJoueur = 0;
		Random r = new Random();

		while (!pioche.isEmpty()){
			int i = r.nextInt(pioche.size());
			Game.players[piocheJoueur].getDrawedCards().add(pioche.get(i));
			pioche.remove(i);
			piocheJoueur = (piocheJoueur-1+getNbPlayer())%(getNbPlayer());
		}
	}

	private static void addThirdAgeCardsToPoolCards(Player[] players){
		/*for (Card c : this.firstAgeCards){
			poolCards.add(c);
		}*/
		ArrayList<Card> pioche = new ArrayList<Card>();
		for (Object o : Game.thirdAgeCards){
			Card c = (Card)o;
			pioche.add(c);
		}
		int piocheJoueur = 0;
		Random r = new Random();

		while (!pioche.isEmpty()){
			int i = r.nextInt(pioche.size());
			Game.players[piocheJoueur].getDrawedCards().add(pioche.get(i));
			pioche.remove(i);
			piocheJoueur = (piocheJoueur+1)%(getNbPlayer());
		}
	}

	private Player[] attributeWondersToPlayers() {
		int i = 0;
		for (Player p : players){
			if (i == 0){
				p.setWonder(Wonder.ALEXANDRIEA);
			}else if (i == 1){
				p.setWonder(Wonder.HALIKARNASSOSB);
			}else{
				p.setWonder(Wonder.HALIKARNASSOSA);
			}
			p.getRessources().add(p.getWonder().getRessource());
			ArrayList<Ressource> ress = new ArrayList<Ressource>();
			Step step1, step2, step3 = null;
			if (i == 0){
				ress.add(Ressource.PIERRE);
				ress.add(Ressource.PIERRE);
				step1 = new Step(1, ress, 3, null, "Alexandrie/FaceA/Step1.png");
				ress = new ArrayList<Ressource>();
				ress.add(Ressource.MINERAI);
				ress.add(Ressource.MINERAI);
				step2 = new Step(1, ress, 0, step1, "Alexandrie/FaceA/Step2.png");
				ress = new ArrayList<Ressource>();
				ress.add(Ressource.FIOLE);
				ress.add(Ressource.FIOLE);
				step3 = new Step(1, ress, 7, step2, "Alexandrie/FaceA/Step3.png");

				Step[] steps = new Step[3];

				steps[0] = step1;
				steps[1] = step2;
				steps[2] = step3;

				p.getWonder().setStep(steps);

			}else if (i == 1){
				ress.add(Ressource.MINERAI);
				ress.add(Ressource.MINERAI);
				step1 = new Step(1, ress, 2, null, "Halikarnassos/FaceB/Step1.png");
				ress = new ArrayList<Ressource>();
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				step2 = new Step(2, ress, 1, step1, "Halikarnassos/FaceB/Step2.png");
				ress = new ArrayList<Ressource>();
				ress.add(Ressource.TAPIS);
				ress.add(Ressource.PAPYRUS);
				ress.add(Ressource.FIOLE);
				step3 = new Step(3, ress, 0, step2, "Halikarnassos/FaceB/Step3.png");

				Step[] steps = new Step[3];

				steps[0] = step1;
				steps[1] = step2;
				steps[2] = step3;

				p.getWonder().setStep(steps);
			}else{
				ress.add(Ressource.MINERAI);
				ress.add(Ressource.MINERAI);
				step1 = new Step(1, ress, 2, null, "Halikarnassos/FaceB/Step1.png");
				ress = new ArrayList<Ressource>();
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				step2 = new Step(2, ress, 1, step1, "Halikarnassos/FaceB/Step2.png");

				Step[] steps = new Step[2];

				steps[0] = step1;
				steps[1] = step2;

				p.getWonder().setStep(steps);
			}

			i++;
		}
		return players;
	}

	private void setPlayersPosition() {
		for (int i = 0; i < players.length; i++){
			// Positionnement du joueur à gauche 
			players[0].setLeftPlayer(players[players.length-1]);
			if (i > 0){
				players[i].setLeftPlayer(players[i-1]);
			}

			// Positionnement du joueur à droite
			players[players.length-1].setRightPlayer(players[0]);
			if (i < players.length-1){
				players[i].setRightPlayer(players[i+1]);
			}
		}
	}

	public static void createPlayers() {
		for (int i = 0; i < players.length; i++){
			players[i] = new Player("Joueur "+(i+1));
		}
	}

	public static void defausser(Card c){
		Player player = getPlayerTurn();
		defausse.add(c);
		player.getDrawedCards().remove(c);
		for (int i = 0; i < 3; i++){
			RessourceList<Ressource> ressource = player.getRessources();
			ressource.add(Ressource.PIECE);
			player.setRessources(ressource);
		}
	}

	public static boolean acheterRessourcesAGauche(Ressource r){
		Player current = getPlayerTurn();
		if(current.getGold()>=2){
			RessourceList<Ressource> ressources = current.getRessources();
			ressources.remove(Ressource.PIECE);
			ressources.remove(Ressource.PIECE);
			current.setRessources(ressources);
			RessourceList<Ressource> purchasedRessources = current.getPurchasedRessources();
			purchasedRessources.add(r);
			current.setPurchasedRessources(purchasedRessources);

			Player left = getPlayerTurn().getLeftPlayer();
			RessourceList<Ressource> leftRessources = left.getRessources();
			leftRessources.add(Ressource.PIECE);
			leftRessources.add(Ressource.PIECE);
			left.setRessources(leftRessources);
			return true;
		}else{
			return false;
		}
	}

	public static boolean acheterRessourcesADroite(Ressource r){
		Player current = getPlayerTurn();
		if(current.getGold()>=2){
			RessourceList<Ressource> ressources = current.getRessources();
			ressources.remove(Ressource.PIECE);
			ressources.remove(Ressource.PIECE);
			current.setRessources(ressources);
			RessourceList<Ressource> purchasedRessources = current.getPurchasedRessources();
			purchasedRessources.add(r);
			current.setPurchasedRessources(purchasedRessources);

			Player right = getPlayerTurn().getRightPlayer();
			RessourceList<Ressource> rightRessources = right.getRessources();
			rightRessources.add(Ressource.PIECE);
			rightRessources.add(Ressource.PIECE);
			right.setRessources(rightRessources);
			return true;
		}else{
			return false;
		}
	}

	public static void cardChosen(Card card){
		Player player = getPlayerTurn();
		/* On vérifie encore si on peut la jouer */
		//if (Game.canPlay(player, card)){

		/* Si cout en pieces, il faut les défausser */
		int nbCoinsNeeded = 0;
		if (card.getCost() != null){
			for (int i = 0; i<card.getCost().length; i++){
				Ressource r = card.getCost()[i];
				if (r.equals(Ressource.PIECE)){
					nbCoinsNeeded ++;
				}
			}
			RessourceList<Ressource> available = player.getRessources();
			for (int i = 0; i < nbCoinsNeeded; i++){
				available.remove(Ressource.PIECE);
			}
			player.setRessources(available);
		}

		/* Si carte marron ou grise, il faut ajouter la ressource */
		if (card.getGiveRessources() != null){
			RessourceList<Ressource> available = player.getRessources();
			available.add(card.getGiveRessources());
			player.setRessources(available);
		}

		player.getPlayedCards().add(card);
		player.getDrawedCards().remove(card);
	}
	
	public static void nextPlayer(){
		identifierPlayerTurn = (identifierPlayerTurn+1)%players.length;
		playerTurn = players[identifierPlayerTurn];
		if (identifierPlayerTurn == identifierStart){
			nextTurn(Game.getCurrentAge());
		}
	}

	public static void nextTurn(int age){
		if (age == 1 || age == 3){
			ArrayList<Card> playerDrawedCards = players[0].getDrawedCards();
			for (int i = players.length-1; i> 0; i--){
				players[(i+1)%players.length].setDrawedCards(players[i].getDrawedCards());
			}
			players[1].setDrawedCards(playerDrawedCards);
		}else if (age == 2){
			ArrayList<Card> playerDrawedCards = players[0].getDrawedCards();
			for (int i = 0; i< players.length-1; i++){
				players[i].setDrawedCards(players[i+1].getDrawedCards());
			}
			players[players.length-1].setDrawedCards(playerDrawedCards);
		}
	}

	public static void nextAge(){
		for (Player p : players){
			ArrayList<Card> drawedCardLastAge = p.getDrawedCards();
			defausse.addAll(drawedCardLastAge);
			p.setDrawedCards(new ArrayList<Card>());
		}
		if (getCurrentAge() == 1){
			createSecondAgeCards();
			addSecondAgeCardsToPoolCards(Game.players);
		}else if (getCurrentAge() == 2){
			createThirdAgeCards();
			addThirdAgeCardsToPoolCards(Game.players);
		}
		Game.setCurrentAge(Game.getCurrentAge()+1);
		playerTurn = players[0];
		identifierPlayerTurn = 0;
	}
	public static boolean isLastTour() {
		boolean lastTour = true;
		int nextPlayer = (identifierPlayerTurn+1)%players.length;
		if (currentAge == 1 || currentAge == 3){
			lastTour = ((nextPlayer == identifierStart) && Game.players[identifierPlayerTurn].getDrawedCards().size() == 1);
		}else if (currentAge == 2){
			lastTour = ((nextPlayer == identifierStart) && Game.players[identifierPlayerTurn].getDrawedCards().size() == 1);
		}
		return lastTour;
	}
	
	public static void compterCombat(int age){
		for (Player p : Game.players){
			Player gauche = p.getLeftPlayer();
			Player droite = p.getRightPlayer();

			if (p.countWarForce() > gauche.countWarForce()){
				ArrayList<BattleToken> b = p.getBattleTokens();
				b.add(new BattleToken(1+2*(age-1)));
				p.setBattleTokens(b);
			}else if (p.countWarForce() < gauche.countWarForce()){
				ArrayList<BattleToken> b = p.getBattleTokens();
				b.add(new BattleToken(-1));
				p.setBattleTokens(b);
			}

			if (p.countWarForce() > droite.countWarForce()){
				ArrayList<BattleToken> b = p.getBattleTokens();
				b.add(new BattleToken(1+2*(age-1)));
				p.setBattleTokens(b);
			}else if (p.countWarForce() < droite.countWarForce()){
				ArrayList<BattleToken> b = p.getBattleTokens();
				b.add(new BattleToken(-1));
				p.setBattleTokens(b);
			}
		}
	}

	@Override
	public String toString() {
		String result = "Game : players=\n";
		for (Player p : players){
			result += p.toString() +"\n";
		}
		return result;
	}

	public static Player getPlayerTurn() {
		return playerTurn;
	}

	public static Wonder getActiveWonder(){
		return getPlayerTurn().getWonder();
	}

	public static void setPlayerTurn(Player playerTurn) {
		Game.playerTurn = playerTurn;
	}

	public static int getCurrentAge(){
		return currentAge;
	}

	public static void setCurrentAge(int currentAge) {
		Game.currentAge = currentAge;
	}

	public static int getNbPlayer() {
		return nbPlayer;
	}

	public static int getIdentifierPlayerTurn() {
		return identifierPlayerTurn;
	}

	public static void setIdentifierPlayerTurn(int identifierPlayerTurn) {
		Game.identifierPlayerTurn = identifierPlayerTurn;
	}

}
