import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
		int delta = 10;

		if (mouseHover){
			// On détermine la hauteur max du panel d'instructions
			yI = Math.min(yI+delta, 115);
			// On détermine la hauteur max du panel d'étape
			yS = Math.min(yS+delta, 245);
			// Si on est arrivé au bout de l'animation, on arrete le timer
			if (yI == 115){
				listener.stopTimer();
			}
			// On détermine la position sur l'axe Y du panel d'étape
			ySBound = Math.max(ySBound-delta, 302);
			// On détermine la position du panel d'étape
			instructions.setBounds(0, 125, xI, yI);

			// On détermine la position finale et la taille du panel d'étape
			stepPanel.setPreferredSize(new Dimension(xS, yS));
			stepPanel.setBounds(xSBound, ySBound, widthS, yS);
		}else{
			yI = Math.max(yI-delta, 0);
			yS = Math.max(yS-delta, 115);
			if (yI == 0){
				listener.stopTimer();
			}
			ySBound = Math.min(ySBound+delta, 457);
			instructions.setBounds(0, 125, xI, yI);
			stepPanel.setPreferredSize(new Dimension(xS, yS));
			stepPanel.setBounds(xSBound, ySBound, widthS, yS);

		}
		frame.repaint();
	}

}
