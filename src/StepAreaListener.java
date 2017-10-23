import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StepAreaListener implements MouseListener {

	StepArea step;
	StepInstructionLabel instructions;
	Frame frame;
	StepAnimationTimer help;
	StepPanel stepPanel;

	public StepAreaListener(StepArea step, StepInstructionLabel instructions, StepPanel stepPanel, Frame frame) {
		this.step = step;
		this.instructions = instructions;
		this.frame = frame;
		this.stepPanel = stepPanel;
	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {
		if (help == null){
			HelpAnimation anim = new HelpAnimation(this, frame, instructions, stepPanel, true);
			help = new StepAnimationTimer(20, anim);
			help.start();
		}else{
			HelpAnimation anim = (HelpAnimation) help.actionListener;
			anim.mouseHover = true;
		}
	}

	public void mouseExited(MouseEvent arg0) {
		if (help == null){
			HelpAnimation anim = new HelpAnimation(this, frame, instructions, stepPanel, false);
			help = new StepAnimationTimer(2, anim);
			help.start();
		}else{
			HelpAnimation anim = (HelpAnimation) help.actionListener;
			anim.mouseHover = false;
		}
	}
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void stopTimer(){
		this.help.stop();
		this.help = null;
	}

}
