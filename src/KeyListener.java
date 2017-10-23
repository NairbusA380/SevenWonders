import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyListener implements java.awt.event.KeyListener {

	ArrayList<CardArea> cardArea;
	Frame frame;

	public KeyListener(Frame frame){
		this.frame = frame;
		cardArea = new ArrayList<CardArea>();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (cardArea.size() > 0){
			if (arg0.getKeyCode() == KeyEvent.VK_CONTROL){
				for (CardArea c : cardArea){
					c.discard = true;
				}
				frame.pack();
				frame.repaint();
			}else if (arg0.getKeyCode() == KeyEvent.VK_SHIFT){
				for (CardArea c : cardArea){
					c.validate = true;
				}
				frame.pack();
				frame.repaint();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_CONTROL){
			for (CardArea c : cardArea){
				c.discard = false;
				c.repaint();
			}
			frame.pack();
			frame.repaint();
		}else if (arg0.getKeyCode() == KeyEvent.VK_SHIFT){
			for (CardArea c : cardArea){
				c.validate = false;
				c.repaint();
			}
			frame.pack();
			frame.repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void addArea(CardArea c){
		cardArea.add(c);
	}

	public void removeArea(CardArea c){
		cardArea.remove(c);
	}

}
