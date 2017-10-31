import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyListener implements java.awt.event.KeyListener {

	ArrayList<CardArea> cardArea;

	public KeyListener(){
		cardArea = new ArrayList<CardArea>();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (cardArea.size() > 0){
			if (arg0.getKeyCode() == KeyEvent.VK_CONTROL){
				for (CardArea c : cardArea){
					c.discard = true;
				}
				Frame.frame.pack();
				Frame.frame.repaint();
			}else if (arg0.getKeyCode() == KeyEvent.VK_SHIFT){
				for (CardArea c : cardArea){
					c.validate = true;
				}
				Frame.frame.pack();
				Frame.frame.repaint();
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
			Frame.frame.pack();
			Frame.frame.repaint();
		}else if (arg0.getKeyCode() == KeyEvent.VK_SHIFT){
			for (CardArea c : cardArea){
				c.validate = false;
				c.repaint();
			}
			Frame.frame.pack();
			Frame.frame.repaint();
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
