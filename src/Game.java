import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

public class Game {

	private static Player[] players;
	private static Player playerTurn, playerToShow;
	private static CardList firstAgeCards, secondAgeCards, thirdAgeCards;
	private static CardList defausse;
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
		firstAgeCards = new CardList();
		secondAgeCards = new CardList();
		thirdAgeCards = new CardList();
		defausse = new CardList();
		createFirstAgeCards();
		addFirstAgeCardsToPoolCards(Game.players);
		playerTurn = players[0];
		playerToShow = playerTurn;
		identifierPlayerTurn = 0;
		identifierStart = 0;
		currentAge = 1;
	}

	private static void createFirstAgeCards() {
		
		ArrayList<Card> cardsCreated = new ArrayList<Card>();
		
		BlueCard autel3 = new BlueCard(3, "Autel", null, "Temple", 2, "autel");
		BlueCard theatre3 = new BlueCard(3, "Théâtre", null, "Statue", 2, "theatre");
		BlueCard bains3 = new BlueCard(3, "Bains", new Ressource[] {Ressource.PIERRE}, "Aqueduc", 3, "bains");
		BlueCard autel5 = new BlueCard(5, "Autel", null, "Temple", 2, "autel");
		BlueCard preteurSurGages7 = new BlueCard(7," Prêteur sur gages", null, null, 3, "preteur_sur_gages");
		BrownCard exploitationForestiere3 = new BrownCard(3, "Exploitation forestière", new Ressource[]{Ressource.PIECE}, new Ressource[]{Ressource.PIERRE_BOIS}, "exploitation_forestière");
		BrownCard cavite3 = new BrownCard(3, "Cavite", null, new Ressource[]{Ressource.PIERRE}, "cavite");
		BrownCard filon3 = new BrownCard(3, "Filon", null, new Ressource[]{Ressource.MINERAI}, "filon");
		BrownCard chantier4 = new BrownCard(4, "Chantier", null, new Ressource[]{Ressource.BOIS}, "chantier");
		BrownCard cavite5 = new BrownCard(5, "Cavite", null, new Ressource[]{Ressource.PIERRE}, "cavite");
		RedCard caserne3 = new RedCard(3, "Caserne", new Ressource[]{Ressource.MINERAI}, "", (short)1, "caserne");
		RedCard tourDeGarde4 = new RedCard(4, "Tour de garde", new Ressource[]{Ressource.ARGILE}, "", (short)1, "tour_de_garde");
		YellowCard comptoirEst3 = new YellowCard(3, "Comptoir Est", new Ressource[0], "Forum", Capacity.ECHANGE_MATIERE_PREMIERE_DROITE, "comptoir_est");
		YellowCard comptoirOuest3 = new YellowCard(3, "Comptoir Ouest", new Ressource[0], "Forum", Capacity.ECHANGE_MATIERE_PREMIERE_GAUCHE, "comptoir_ouest");
		YellowCard marche3 = new YellowCard(3, "Marche", new Ressource[0], "", Capacity.ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE, "marche");
		GreyCard presse3 = new GreyCard(3, "Presse", new Ressource[0], new Ressource[]{Ressource.PAPYRUS}, "presse");
		GreyCard metierATisser3 = new GreyCard(3, "Métier à tisser", new Ressource[0], new Ressource[]{Ressource.TAPIS}, "metier_a_tisser");
		GreyCard verrerie3 = new GreyCard(3, "Verrerie", new Ressource[0], new Ressource[]{Ressource.FIOLE}, "verrerie");
		GreenCard scriptorium3 = new GreenCard(3, "Scriptorium", new Ressource[]{Ressource.PAPYRUS}, "Tribunal_Bibliothèque", Scientifique.TABLETTE, "scriptorium");
		GreenCard atelier3 = new GreenCard(3, "Atelier", new Ressource[]{Ressource.FIOLE}, "Laboratoire_Champs de tir", Scientifique.ROUE, "atelier");
		GreenCard scriptorium4 = new GreenCard(4, "Scriptorium", new Ressource[]{Ressource.PAPYRUS}, "Tribunal_Bibliothèque", Scientifique.TABLETTE, "scriptorium");
		
		cardsCreated.add(autel3);
		cardsCreated.add(exploitationForestiere3);
		cardsCreated.add(caserne3);
		cardsCreated.add(comptoirEst3);
		cardsCreated.add(presse3);
		cardsCreated.add(scriptorium3);
		cardsCreated.add(marche3);
		cardsCreated.add(metierATisser3);
		cardsCreated.add(atelier3);
		cardsCreated.add(theatre3);
		cardsCreated.add(cavite3);
		cardsCreated.add(filon3);
		cardsCreated.add(comptoirOuest3);
		cardsCreated.add(bains3);
		cardsCreated.add(verrerie3);

		cardsCreated.add(tourDeGarde4);
		cardsCreated.add(chantier4);
		cardsCreated.add(scriptorium4);
		
		cardsCreated.add(cavite5);
		cardsCreated.add(autel5);
		
		cardsCreated.add(preteurSurGages7);

		//Melange des cartes

		Random r = new Random();
		Card c;
		ArrayList<Card> cardsToTake = new ArrayList<Card>();
		for (Card card : cardsCreated){
			if (card.minPlayers <= Game.getNbPlayer()){
				cardsToTake.add(card);
			}
		}
		while (!cardsToTake.isEmpty()){
			int index = r.nextInt(cardsToTake.size());
			c = cardsToTake.get(index);
			cardsToTake.remove(index);
			firstAgeCards.add(c);
		}	
	}

	private static void createThirdAgeCards() {
		ArrayList<Card> cardsCreated = new ArrayList<Card>();
		BlueCard autel = new BlueCard(3, "Autel", null, "Temple", 2, "autel");
		BlueCard theatre = new BlueCard(3, "Théâtre", null, "Statue", 2, "theatre");
		BrownCard exploitationForestiere = new BrownCard(3, "Exploitation forestière", new Ressource[]{Ressource.PIECE}, new Ressource[]{Ressource.PIERRE_BOIS}, "exploitation_forestière");
		BrownCard cavite = new BrownCard (3, "Cavite", new Ressource[0], new Ressource[]{Ressource.PIERRE}, "cavite");
		RedCard caserne = new RedCard(3, "Caserne", new Ressource[]{Ressource.MINERAI}, "", (short)1, "caserne");
		YellowCard comptoirEst = new YellowCard(3, "Comptoir Est", new Ressource[0], "Forum", Capacity.ECHANGE_MATIERE_PREMIERE_DROITE, "comptoir_est");
		YellowCard marche = new YellowCard(3, "Marche", new Ressource[0], "", Capacity.ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE, "marche");
		GreyCard presse = new GreyCard(3, "Presse", new Ressource[0], new Ressource[]{Ressource.PAPYRUS}, "presse");
		GreyCard metierATisser = new GreyCard(3, "Métier à tisser", new Ressource[0], new Ressource[]{Ressource.TAPIS}, "metier_a_tisser");
		GreenCard scriptorium = new GreenCard(3, "Scriptorium", new Ressource[]{Ressource.PAPYRUS}, "Tribunal_Bibliothèque", Scientifique.TABLETTE, "scriptorium");
		GreenCard atelier = new GreenCard(3, "Atelier", new Ressource[]{Ressource.FIOLE}, "Laboratoire_Champs de tir", Scientifique.ROUE, "atelier");

		cardsCreated.add(autel);
		cardsCreated.add(exploitationForestiere);
		cardsCreated.add(caserne);
		cardsCreated.add(comptoirEst);
		cardsCreated.add(presse);
		cardsCreated.add(scriptorium);
		cardsCreated.add(marche);

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitationForestiere.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitationForestiere.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitationForestiere.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		//Melange des cartes

		Random r = new Random();
		Card c;
		ArrayList<Card> cardsToTake = new ArrayList<Card>();
		for (Card card : cardsCreated){
			if (card.minPlayers <= Game.getNbPlayer()){
				cardsToTake.add(card);
			}
		}
		while (!cardsToTake.isEmpty()){
			int index = r.nextInt(cardsToTake.size());
			c = cardsToTake.get(index);
			cardsToTake.remove(index);
			firstAgeCards.add(c);
		}	
	}

	private static void createSecondAgeCards() {
		ArrayList<Card> cardsCreated = new ArrayList<Card>();
		BlueCard autel = new BlueCard(3, "Autel", null, "Temple", 2, "autel");
		BlueCard theatre = new BlueCard(3, "Théâtre", null, "Statue", 2, "theatre");
		BrownCard exploitationForestiere = new BrownCard(3, "Exploitation forestière", new Ressource[]{Ressource.PIECE}, new Ressource[]{Ressource.PIERRE_BOIS}, "exploitation_forestière");
		BrownCard cavite = new BrownCard (3, "Cavite", new Ressource[0], new Ressource[]{Ressource.PIERRE}, "cavite");
		RedCard caserne = new RedCard(3, "Caserne", new Ressource[]{Ressource.MINERAI}, "", (short)1, "caserne");
		YellowCard comptoirEst = new YellowCard(3, "Comptoir Est", new Ressource[0], "Forum", Capacity.ECHANGE_MATIERE_PREMIERE_DROITE, "comptoir_est");
		YellowCard marche = new YellowCard(3, "Marche", new Ressource[0], "", Capacity.ECHANGE_PRODUIT_MANUFACTURE_DROITE_GAUCHE, "marche");
		GreyCard presse = new GreyCard(3, "Presse", new Ressource[0], new Ressource[]{Ressource.PAPYRUS}, "presse");
		GreyCard metierATisser = new GreyCard(3, "Métier à tisser", new Ressource[0], new Ressource[]{Ressource.TAPIS}, "metier_a_tisser");
		GreenCard scriptorium = new GreenCard(3, "Scriptorium", new Ressource[]{Ressource.PAPYRUS}, "Tribunal_Bibliothèque", Scientifique.TABLETTE, "scriptorium");
		GreenCard atelier = new GreenCard(3, "Atelier", new Ressource[]{Ressource.FIOLE}, "Laboratoire_Champs de tir", Scientifique.ROUE, "atelier");

		cardsCreated.add(autel);
		cardsCreated.add(exploitationForestiere);
		cardsCreated.add(caserne);
		cardsCreated.add(comptoirEst);
		cardsCreated.add(presse);
		cardsCreated.add(scriptorium);
		cardsCreated.add(marche);

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitationForestiere.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitationForestiere.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		cardsCreated.add(autel.cloneCard());
		cardsCreated.add(exploitationForestiere.cloneCard());
		cardsCreated.add(caserne.cloneCard());
		cardsCreated.add(comptoirEst.cloneCard());
		cardsCreated.add(presse.cloneCard());
		cardsCreated.add(scriptorium.cloneCard());
		cardsCreated.add(marche.cloneCard());

		//Melange des cartes

		Random r = new Random();
		Card c;
		ArrayList<Card> cardsToTake = new ArrayList<Card>();
		for (Card card : cardsCreated){
			if (card.minPlayers <= Game.getNbPlayer()){
				cardsToTake.add(card);
			}
		}
		while (!cardsToTake.isEmpty()){
			int index = r.nextInt(cardsToTake.size());
			c = cardsToTake.get(index);
			cardsToTake.remove(index);
			firstAgeCards.add(c);
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
			}else if (i == 2){
				p.setWonder(Wonder.HALIKARNASSOSA);
			}else{
				p.setWonder(Wonder.ALEXANDRIEB);
			}
			p.getRessources().add(p.getWonder().getRessource());
			RessourceList ress = new RessourceList();
			Step step1, step2, step3 = null;
			if (i == 0){
				ress.add(Ressource.PIERRE);
				ress.add(Ressource.PIERRE);
				step1 = new Step(1, ress, 3, null, "Alexandrie/FaceA/Step1.png");
				ress = new RessourceList();
				ress.add(Ressource.MINERAI);
				ress.add(Ressource.MINERAI);
				step2 = new Step(1, ress, 0, Capacity.CHOISIR_1_MATIERE_PREMIERE, step1, "Alexandrie/FaceA/Step2.png");
				ress = new RessourceList();
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
				step1 = new Step(1, ress, 2, Capacity.CONSTRUIRE_GRATUITEMENT_1_CARTE_DEFAUSSEE, null, "Halikarnassos/FaceB/Step1.png");
				ress = new RessourceList();
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				step2 = new Step(2, ress, 1, Capacity.CONSTRUIRE_GRATUITEMENT_1_CARTE_DEFAUSSEE, step1, "Halikarnassos/FaceB/Step2.png");
				ress = new RessourceList();
				ress.add(Ressource.TAPIS);
				ress.add(Ressource.PAPYRUS);
				ress.add(Ressource.FIOLE);
				step3 = new Step(3, ress, 0, Capacity.CONSTRUIRE_GRATUITEMENT_1_CARTE_DEFAUSSEE, step2, "Halikarnassos/FaceB/Step3.png");

				Step[] steps = new Step[3];

				steps[0] = step1;
				steps[1] = step2;
				steps[2] = step3;

				p.getWonder().setStep(steps);
			}else if (i==2){
				ress.add(Ressource.MINERAI);
				ress.add(Ressource.MINERAI);
				step1 = new Step(1, ress, 2, null, "Halikarnassos/FaceB/Step1.png");
				ress = new RessourceList();
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				step2 = new Step(2, ress, 1, step1, "Halikarnassos/FaceB/Step2.png");

				Step[] steps = new Step[2];

				steps[0] = step1;
				steps[1] = step2;

				p.getWonder().setStep(steps);
			}else{
				ress.add(Ressource.ARGILE);
				ress.add(Ressource.ARGILE);
				step1 = new Step(1, ress, 0, Capacity.CHOISIR_1_MATIERE_PREMIERE, null, "Alexandrie/FaceB/Step1.png");
				ress = new RessourceList();
				ress.add(Ressource.BOIS);
				ress.add(Ressource.BOIS);
				step2 = new Step(2, ress, 0, Capacity.CHOISIR_1_PRODUIT_MANUFACTURE, step1, "Alexandrie/FaceB/Step2.png");
				ress = new RessourceList();
				ress.add(Ressource.PIERRE);
				ress.add(Ressource.PIERRE);
				ress.add(Ressource.PIERRE);
				step3 = new Step(3, ress, 7, (Capacity)null, step2, "Alexandrie/FaceB/Step3.png");

				Step[] steps = new Step[3];

				steps[0] = step1;
				steps[1] = step2;
				steps[2] = step3;

				p.getWonder().setStep(steps);
			}
			String log = "Le joueur "+p.getName()+" a la merveille "+p.getWonder().getName()
					+"\n\t\t\t\t"+"Cette merveille donne la ressource "+p.getWonder().getRessource().toString();
			int j = 1;
			for (Step s : p.getWonder().getStep()){
				log += "\n\t\t\t\t"+"La "+j+"e étape est : "+s.toString();
			}

			SevenWonders.getLogger().log(Level.INFO, log);
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
		SevenWonders.getLogger().log(Level.INFO, "Le joueur "+player.getName()+" se défausse de la carte "+c.getName());
		defausse.add(c);
		player.getDrawedCards().remove(c);
		player.setNbGoldEarnedThisTurn(3);
	}

	public static void nextPlayer(){
		SevenWonders.getLogger().log(Level.INFO, "On passe au joueur suivant");
		identifierPlayerTurn = (identifierPlayerTurn+1)%players.length;
		playerTurn = players[identifierPlayerTurn];
		if (identifierPlayerTurn == identifierStart){
			SevenWonders.getLogger().log(Level.INFO, "Un nouveau tour est entamé");
			nextTurn(Game.getCurrentAge());
		}
		playerToShow = playerTurn;
		playerTurn.setPurchasedRessources(new RessourceList());
		SevenWonders.getLogger().log(Level.INFO, "Ce joueur a les ressources : "+playerTurn.getRessources().toString());
		
	}

	public static void nextTurn(int age){
		SevenWonders.getLogger().log(Level.INFO, "Fin du tour");
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
		for (Player p : players){
			int goldEarned = p.getNbGoldEarnedThisTurn();
			p.setNbGoldEarnedThisTurn(0);
			if (goldEarned > 0) {
				SevenWonders.getLogger().log(Level.INFO, "Le joueur "+p.getName()+" a gagné "+goldEarned+" or ce tour-ci");
			}else if (goldEarned < 0) {
				SevenWonders.getLogger().log(Level.INFO, "Le joueur "+p.getName()+" a perdu "+goldEarned+" or ce tour-ci");
			}else{
				SevenWonders.getLogger().log(Level.INFO, "Le joueur "+p.getName()+" n'a ni gagné ni perdu d'or");
			}
			if (goldEarned > 0){
				SevenWonders.getLogger().log(Level.INFO, "On ajoute "+goldEarned+" pièces au joueur "+p.getName());
				RessourceList ressources = p.getRessources();
				for (int i = 0; i < goldEarned; i++){	
					ressources.add(Ressource.PIECE);
				}
				p.setRessources(ressources);
			}
		}
	}

	public static void nextAge(){
		for (Player p : players){
			ArrayList<Card> drawedCardLastAge = p.getDrawedCards();
			for (Card c : drawedCardLastAge) {
				defausse.add(c);
			}
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
		playerToShow = playerTurn;
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

	public static Player getPlayer(int i) /*throws Exception*/ {
		//if (i < getNbPlayer()){
			return players[i];
		//}else{
		//	throw new Exception();
		//}
	}

	public static Player getPlayerTurn() {
		return playerTurn;
	}

	public static Player getPlayerToShow() {
		return playerToShow;
	}

	public static void setPlayerToShow(Player p){
		playerToShow = p;
	}

	public static Wonder getShowingWonder(){
		return getPlayerToShow().getWonder();
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
