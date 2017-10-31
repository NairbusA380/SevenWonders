import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RessourceListener implements MouseListener {

	RessourcePanel r;

	public RessourceListener(RessourcePanel ressourcePanel){
		this.r = ressourcePanel;
	}

	public void mouseClicked(MouseEvent arg0) {
		RessourcePanel ressourcePanel = (RessourcePanel)arg0.getComponent();
		if (!ressourcePanel.hasBeenPurchased(Game.getPlayerTurn().getPlace())){
			if (Game.getPlayerToShow().equals(Game.getPlayerTurn().getLeftPlayer())){
				int gold = Game.getPlayerTurn().acheterRessourcesAGauche(ressourcePanel.ressource);
				if (gold > 0){
					Frame.achatRessource(ressourcePanel, gold);
					ressourcePanel.setHasBeenPurchased(Game.getPlayerTurn().getPlace(),true);
				}else{
					Frame.choixUtilisateur = new JOptionPane();
					Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous n'avez pas assez d'or pour acheter cette ressource à ce joueur", "Impossible d'acheter cette ressource", JOptionPane.ERROR_MESSAGE);
				}
			}else if (Game.getPlayerToShow().equals(Game.getPlayerTurn().getRightPlayer())){
				int gold = Game.getPlayerTurn().acheterRessourcesADroite(ressourcePanel.ressource);
				if (gold > 0){
					Frame.achatRessource(ressourcePanel, gold);
					ressourcePanel.setHasBeenPurchased(Game.getPlayerTurn().getPlace(), true);
				}else{
					Frame.choixUtilisateur = new JOptionPane();
					Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous n'avez pas assez d'or pour acheter cette ressource à ce joueur", "Impossible d'acheter cette ressource", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else{
			Frame.choixUtilisateur = new JOptionPane();
			Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous avez déjà acheté cette ressource à ce joueur ce tour-ci", "Impossible d'acheter cette ressource", JOptionPane.ERROR_MESSAGE);
		
		}
	}

	public void mouseEntered(MouseEvent arg0) {

		RessourcePanel ressourcePanel = (RessourcePanel)arg0.getComponent();
		if (!Game.getPlayerToShow().equals(Game.getPlayerTurn())){
			if (!ressourcePanel.hasBeenPurchased(Game.getPlayerTurn().getPlace())){
				ArrayList<RessourcePanel> ressourcePanels = Frame.getRessourcePanel(Game.getPlayerToShow().getPlace());
				Container c = Frame.frame.getContentPane();
				c.remove(ressourcePanel);
				ressourcePanels.remove(ressourcePanel);
				String newUrl = ressourcePanel.ressource.toString().toLowerCase()+"Hover.png";
				ressourcePanel.setUrl(newUrl);
				c.add(ressourcePanel);
				ressourcePanels.add(ressourcePanel);
				Frame.setRessourcePanel(ressourcePanels, Game.getPlayerToShow().getPlace());
				Frame.frame.setContentPane(c);
			}
		}
	}

	public void mouseExited(MouseEvent arg0) {
		RessourcePanel ressourcePanel = (RessourcePanel)arg0.getComponent();
		if (!Game.getPlayerToShow().equals(Game.getPlayerTurn())){
			ArrayList<RessourcePanel> ressourcePanels = Frame.getRessourcePanel(Game.getPlayerToShow().getPlace());
			Container c = Frame.frame.getContentPane();
			c.remove(ressourcePanel);
			ressourcePanels.remove(ressourcePanel);
			String url = ressourcePanel.getUrl();
			String newUrl = ressourcePanel.ressource.toString().toLowerCase()+".png";
			ressourcePanel.setUrl(newUrl);
			c.add(ressourcePanel);
			ressourcePanels.add(ressourcePanel);
			Frame.setRessourcePanel(ressourcePanels, Game.getPlayerToShow().getPlace());
			Frame.frame.setContentPane(c);
		}
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}



}
