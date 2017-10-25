import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingUtilities;

public class FrameListener extends ComponentAdapter {
	
	Frame frame;
	boolean isResizing = true;
	
	public FrameListener(Frame frame){
		super();
		this.frame = frame;
	}
	
	public void componentResized(ComponentEvent e){
		frame.pourcentageX =((double)frame.getWidth())/1253.0;
		frame.pourcentageY = ((double)frame.getHeight())/615.0;
		for (int i = 0; i < Game.getNbPlayer(); i++){
			Player p;
			try {
				p = Game.getPlayer(i);
				p.setDoubleBuffer(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		Container cont = frame.getContentPane();
		int i = 0;
		for (CardArea a : frame.cardAreas){
			cont.remove(a);
			Card card = a.getCard();
			a.setBounds((int)((200+30*i)*frame.pourcentageX), (int)(100*frame.pourcentageY), card.getMiniWidth(), card.getMiniHeight());
			cont.add(a);
			i++;
		}
		i = 0;
		for (StepPanel s : frame.stepPanel){
			int y = 457;
			cont.remove(s);
			switch (s.nbSteps){
			case 2:
				System.out.println(2);
				s.setBounds((int)((241+390*i)*frame.pourcentageX), (int)(y*frame.pourcentageY), 256, 115);
				break;
			case 3:
				System.out.println(3);
				s.setBounds((int)((142+348*i)*frame.pourcentageX), (int)(y*frame.pourcentageY), 256, 115);
				break;
			case 4:
				System.out.println(4);
				s.setBounds((int)((42+298*i)*frame.pourcentageX), (int)(y*frame.pourcentageY), 256, 115);
				break;
			}
			System.out.println(s.getBounds().toString());
			cont.add(s);
			i++;
		}
		frame.right.setBounds(frame.getWidth()-85, 250, 75, 75);
		
		frame.setContentPane(cont);
	}
}
