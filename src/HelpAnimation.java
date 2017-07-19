import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class HelpAnimation implements ActionListener {

	StepAreaListener listener;
	StepInstructionLabel instructions;
	Frame frame;
	boolean mouseHover;
	StepPanel stepPanel;

	public HelpAnimation(StepAreaListener listener, Frame f, StepInstructionLabel instructions, StepPanel stepPanel, boolean mouseHover){
		this.listener = listener;
		this.instructions = instructions;
		this.frame = f;
		this.mouseHover = mouseHover;
		this.stepPanel = stepPanel;
	}

	public void actionPerformed(ActionEvent arg0) {
		int xI = (int) instructions.getBounds().getWidth();
		int yI = (int) instructions.getBounds().getHeight();
		
		Dimension dS = stepPanel.getPreferredSize();
		int xS = (int) dS.getWidth();
		int yS = (int) dS.getHeight();
		
		int xSBound = (int) stepPanel.getBounds().getX();
		int ySBound = (int) stepPanel.getBounds().getY();
		int widthS = (int) stepPanel.getBounds().getWidth();
		
		int delta = 4;

		if (mouseHover){
			yI = Math.min(yI+delta, 130);
			yS = Math.min(yS+delta, 245);
			if (yI == 130){
				listener.stopTimer();
			}
			ySBound = Math.max(ySBound-delta, 302);
			instructions.setBounds(0, 125, xI, yI);
			stepPanel.setPreferredSize(new Dimension(xS, yS));
			stepPanel.setBounds(xSBound, ySBound, widthS, yS);
		
		}else{
			yI = Math.max(yI-delta, 0);
			yS = Math.max(yS-delta, 115);
			if (yI == 0){
				listener.stopTimer();
			}
			ySBound = Math.min(ySBound+delta, 432);
			instructions.setBounds(0, 125, xI, yI);
			stepPanel.setPreferredSize(new Dimension(xS, yS));
			stepPanel.setBounds(xSBound, ySBound, widthS, yS);
			
		}
		
		frame.pack();
		frame.repaint();

	}

}
