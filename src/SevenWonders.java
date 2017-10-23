import java.util.ArrayList;

public class SevenWonders {

	private static Frame frame; // Fenetre de jeu
	private static Game game;

	public static void main(String args[]){
		int nbPlayers = 3;
		game = new Game(nbPlayers);
		frame = new Frame();
		frame.draw();
	}

	public Frame getFrame() {
		return SevenWonders.frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		SevenWonders.game = game;
	}


}
