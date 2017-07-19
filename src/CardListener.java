import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardListener implements MouseListener {
	
	Card card;
	
	public CardListener(Card card){
		this.card = card;
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Je veux prendre la carte "+this.card);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("Je survole la carte "+this.card);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		System.out.println("Je ne survole plus la carte "+this.card);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
