import java.awt.event.ActionListener;

import javax.swing.Timer;

public class StepAnimationTimer extends Timer {

	int time;
	ActionListener actionListener;
	
	public StepAnimationTimer(int time, ActionListener actionListener) {
		super(time, actionListener);
		this.time = time;
		this.actionListener = actionListener;
	}

}
