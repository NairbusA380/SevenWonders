import java.util.ArrayList;

public class Player {

	String name;
	Wonder wonder;
	int nbPoints;
	ArrayList<BattleToken> battleTokens;
	ArrayList<FirstAgeCard> playedCards;
	Player leftPlayer, rightPlayer;
	int gold;
	
	public Player(String name, Player leftPlayer, Player rightPlayer) {
		this.name = name;
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
		this.nbPoints = 0;
		this.battleTokens = new ArrayList<BattleToken>();
		this.wonder = null;
		this.gold = 3;
	}
	
	public Player(String name) {
		this(name, null, null);
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

	public ArrayList<FirstAgeCard> getPlayedCards() {
		return playedCards;
	}

	public void setPlayedCards(ArrayList<FirstAgeCard> playedCards) {
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

	@Override
	public String toString() {
		return "Player : \nname=" + name + "\nwonder=" + wonder.toString() + "\n nbPoints=" + nbPoints + ", leftPlayer=" + leftPlayer.name + "\n rightPlayer=" + rightPlayer.name;
	}
	
	
}
