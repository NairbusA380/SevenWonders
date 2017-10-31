import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ArrowListener implements MouseListener {

	ArrowButton arrowButton;

	public ArrowListener (ArrowButton arrowButton){
		this.arrowButton = arrowButton;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arrowButton.isLeft){
			Game.setPlayerToShow(Game.getPlayerToShow().getLeftPlayer());
			Frame.entireDraw();
		}else{
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
