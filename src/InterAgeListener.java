import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterAgeListener implements ActionListener {
	
	public void actionPerformed(ActionEvent arg0) {
		Game.nextAge();
		Frame.nextAge();
	}

}
