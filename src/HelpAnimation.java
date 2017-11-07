import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HelpAnimation implements ActionListener {

	StepAreaListener listener;
	StepInstructionLabel instructions;
	boolean mouseHover;
	StepPanel stepPanel;

	public HelpAnimation(StepAreaListener listener, StepInstructionLabel instructions, StepPanel stepPanel, boolean mouseHover){
		this.listener = listener;
		this.instructions = instructions;
		this.mouseHover = mouseHover;
		this.stepPanel = stepPanel;
	}

	public void actionPerformed(ActionEvent arg0) {
		int delta = 10;
		if (mouseHover){
			monter(delta);
		}else{
			descendre(delta);
		}
		Frame.frame.repaint();
	}

	public void monter (int delta){
		int widthInstructions = (int) instructions.getBounds().getWidth();
		int heightInstructions = (int) instructions.getBounds().getHeight();

		Dimension dPanel = stepPanel.getPreferredSize();
		int widthPanel = (int) dPanel.getWidth();
		int heightPanel = (int) dPanel.getHeight();

		int xPanel = (int) stepPanel.getBounds().getX();
		int yPanel = (int) stepPanel.getBounds().getY();

		// On détermine la hauteur max du panel d'instructions
		heightInstructions = Math.min(heightInstructions+delta, 115);
		// On détermine la hauteur max du panel d'étape
		heightPanel = Math.min(heightPanel+delta, 300);
		// Si on est arrivé au bout de l'animation, on arrete le timer
		if (heightInstructions == 115){
			listener.stopTimer();
		}

		yPanel = Math.max(yPanel-delta, Frame.frame.getHeight()-330);
		// On détermine la position du panel d'étape
		instructions.setBounds(0, 125, widthInstructions, heightInstructions);

		// On détermine la position finale et la taille du panel d'étape
		stepPanel.setPreferredSize(new Dimension(widthPanel, heightPanel));
		stepPanel.setBounds(xPanel, yPanel, widthPanel, heightPanel);
	}

	public void descendre (int delta){
		int widthInstructions = (int) instructions.getBounds().getWidth();
		int heightInstructions = (int) instructions.getBounds().getHeight();

		Dimension dPanel = stepPanel.getPreferredSize();
		int widthPanel = (int) dPanel.getWidth();
		int heightPanel = (int) dPanel.getHeight();

		int xPanel = (int) stepPanel.getBounds().getX();
		int yPanel = (int) stepPanel.getBounds().getY();
		
		heightInstructions = Math.max(heightInstructions-delta, 0);
		heightPanel = Math.max(heightPanel-delta, 145);
		if (heightInstructions == 0){
			listener.stopTimer();
		}
		yPanel = Math.min(yPanel+delta, Frame.frame.getHeight()-145);
		instructions.setBounds(0, 125, widthInstructions, heightInstructions);
		stepPanel.setPreferredSize(new Dimension(widthPanel, heightPanel));
		stepPanel.setBounds(xPanel, yPanel, widthPanel, heightPanel);
	}

}
