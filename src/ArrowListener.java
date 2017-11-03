import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

public class ArrowListener implements MouseListener {

	ArrowButton arrowButton;

	public ArrowListener (ArrowButton arrowButton){
		this.arrowButton = arrowButton;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arrowButton.isLeft){
			SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" a cliqué sur la flèche gauche. Il regarde maintenant le jeu du joueur "+Game.getPlayerToShow().getName());
			Game.setPlayerToShow(Game.getPlayerToShow().getLeftPlayer());
			Frame.entireDraw();
		}else{
			SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" a cliqué sur la flèche droite. Il regarde maintenant le jeu du joueur "+Game.getPlayerToShow().getName());
			Game.setPlayerToShow(Game.getPlayerToShow().getRightPlayer());
			Frame.entireDraw();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		arrowButton.isHover = true;
	}

	public void mouseExited(MouseEvent arg0) {
		arrowButton.isHover = false;
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

}
