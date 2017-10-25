import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterAgeListener implements ActionListener {

	Frame frame;
	
	public InterAgeListener(Frame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Game.nextAge();
		frame.nextAge();
	}

}
