import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingUtilities;

public class FrameListener extends ComponentAdapter {
	
	boolean isResizing = true;
	
	public FrameListener(){
		super();
	}
	
	public void componentResized(ComponentEvent e){
		Frame.pourcentageX =((double)Frame.frame.getWidth())/1253.0;
		Frame.pourcentageY = ((double)Frame.frame.getHeight())/615.0;
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
		Container cont = Frame.frame.getContentPane();
		int i = 0;
		for (CardArea a : Frame.cardAreas){
			cont.remove(a);
			Card card = a.getCard();
			a.setBounds((int)((200+30*i)*Frame.pourcentageX), (int)(100*Frame.pourcentageY), a.getMiniWidth(), a.getMiniHeight());
			cont.add(a);
			i++;
		}
		i = 0;
		for (StepPanel s : Frame.stepPanel){
			int y = 457;
			cont.remove(s);
			switch (s.nbSteps){
			case 2:
				s.setBounds((int)((241+390*i)*Frame.pourcentageX), (int)(y*Frame.pourcentageY), 256, 115);
				break;
			case 3:
				s.setBounds((int)((142+348*i)*Frame.pourcentageX), (int)(y*Frame.pourcentageY), 256, 115);
				break;
			case 4:
				s.setBounds((int)((42+298*i)*Frame.pourcentageX), (int)(y*Frame.pourcentageY), 256, 115);
				break;
			}
			cont.add(s);
			i++;
		}
		
		Frame.frame.setContentPane(cont);
	}
}
