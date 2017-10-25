import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ArrowListener implements MouseListener {

	ArrowButton arrowButton;
	Frame frame;

	public ArrowListener (ArrowButton arrowButton, Frame frame){
		this.arrowButton = arrowButton;
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arrowButton.isLeft){
			Game.setPlayerToShow(Game.getPlayerToShow().getLeftPlayer());
			arrowButton.frame.draw();
		}else{
			Game.setPlayerToShow(Game.getPlayerToShow().getRightPlayer());
			arrowButton.frame.draw();
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
