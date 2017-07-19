import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

	Player[] players;
	private static Player playerTurn;
	List<FirstAgeCard> firstAgeCards;

	public Game(int nbPlayers){
		this.players = new Player[nbPlayers];
		this.createPlayers();
		this.setPlayersPosition();
		this.attributeWondersToPlayers();
		this.createFirstAgeCards();
		this.start();
		playerTurn = players[0];
	}

	private void createFirstAgeCards() {
		ArrayList<FirstAgeCard> cardsCreated = new ArrayList<FirstAgeCard>();
		FirstAgeCard c;
		
		firstAgeCards = Arrays.asList(FirstAgeCard.values());
		//Melange des cartes
		
		Random r = new Random();
		
		while (!cardsCreated.isEmpty()){
			int index = r.nextInt(cardsCreated.size());
			c = cardsCreated.get(index);
			cardsCreated.remove(index);
			firstAgeCards.add(c);
		}	
	}

	private void start() {
		System.out.println(firstAgeCards.toString());
	}

	private void attributeWondersToPlayers() {
		for (int i = 0; i < players.length; i++){
			Player p = players[i];
			p.setWonder(Wonder.HALIKARNASSOSB);
			Step[] steps = new Step[3];
			ArrayList<Ressource> ress = new ArrayList<Ressource>();
			ress.add(Ressource.PIERRE);
			ress.add(Ressource.PIERRE);
			Step step1 = new Step(1, ress, 2, null, "Halikarnassos/FaceB/Step1.png");
			steps[0] = step1;
			
			ress = new ArrayList<Ressource>();
			ress.add(Ressource.PIERRE);
			ress.add(Ressource.PIERRE);
			Step step2 = new Step(2, ress, 2, step1, "Halikarnassos/FaceB/Step2.png");
			steps[1] = step2;
			
			ress = new ArrayList<Ressource>();
			ress.add(Ressource.PIERRE);
			ress.add(Ressource.PIERRE);
			Step step3 = new Step(3, ress, 2, step2, "Halikarnassos/FaceB/Step3.png");
			steps[2] = step3;
			p.getWonder().setStep(steps);
		}
	}

	private void setPlayersPosition() {
		for (int i = 0; i < players.length; i++){
			// Positionnement du joueur à gauche 
			if (i==0){
				players[i].setLeftPlayer(players[players.length-1]);
			}else{
				players[i].setLeftPlayer(players[i-1]);
			}
			
			// Positionnement du joueur à droite
			if (i==players.length-1){
				players[i].setRightPlayer(players[0]);
			}else{
				players[i].setRightPlayer(players[i+1]);
			}
		}
	}

	public void createPlayers() {
		for (int i = 0; i < players.length; i++){
			players[i] = new Player("Joueur "+(i+1));
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

	public static void setPlayerTurn(Player playerTurn) {
		Game.playerTurn = playerTurn;
	}
	
}
