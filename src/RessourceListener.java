import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JOptionPane;

public class RessourceListener implements MouseListener {

	RessourcePanel r;

	public RessourceListener(RessourcePanel ressourcePanel){
		this.r = ressourcePanel;
	}

	public void mouseClicked(MouseEvent arg0) {
		RessourcePanel ressourcePanel = (RessourcePanel)arg0.getComponent();
		SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" essaye d'acheter la ressource "+ressourcePanel.ressource.toString()+" au joueur "+Game.getPlayerToShow().getName());
		if (!ressourcePanel.hasBeenPurchased(Game.getPlayerTurn().getPlace())){
			SevenWonders.getLogger().log(Level.INFO, "Cette ressource n'a pas déjà été achetée");
			if (Game.getPlayerToShow().equals(Game.getPlayerTurn().getLeftPlayer())){
				SevenWonders.getLogger().log(Level.INFO, "C'est le joueur de gauche ("+Game.getPlayerToShow().getName()+") qui vend sa ressource");
				int gold = Game.getPlayerTurn().acheterRessourcesAGauche(ressourcePanel.ressource);
				if (gold > 0){
					//Frame.achatRessource(ressourcePanel, gold);
					ressourcePanel.setHasBeenPurchased(Game.getPlayerTurn().getPlace(),true);
				}else{
					SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" n'a pas les moyens de l'acheter");
					Frame.choixUtilisateur = new JOptionPane();
					Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous n'avez pas assez d'or pour acheter cette ressource à ce joueur", "Impossible d'acheter cette ressource", JOptionPane.ERROR_MESSAGE);
				}
			}else if (Game.getPlayerToShow().equals(Game.getPlayerTurn().getRightPlayer())){
				SevenWonders.getLogger().log(Level.INFO, "C'est le joueur de droite ("+Game.getPlayerToShow().getName()+") qui vend sa ressource");
				int gold = Game.getPlayerTurn().acheterRessourcesADroite(ressourcePanel.ressource);
				if (gold > 0){
					//Frame.achatRessource(ressourcePanel, gold);
					ressourcePanel.setHasBeenPurchased(Game.getPlayerTurn().getPlace(), true);
				}else{
					SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" N'a pas les moyens de l'acheter");
					Frame.choixUtilisateur = new JOptionPane();
					Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous n'avez pas assez d'or pour acheter cette ressource à ce joueur", "Impossible d'acheter cette ressource", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else{
			SevenWonders.getLogger().log(Level.INFO,"Le joueur "+Game.getPlayerTurn().getName()+" a déjà acheté cette ressource ce tour-ci" );
			Frame.choixUtilisateur = new JOptionPane();
			Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous avez déjà acheté cette ressource à ce joueur ce tour-ci", "Impossible d'acheter cette ressource", JOptionPane.ERROR_MESSAGE);
		
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		RessourcePanel ressourcePanel = (RessourcePanel)arg0.getComponent();
		SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName() +"survole la ressource "+ressourcePanel.ressource.toString()+" appartenant au joueur "+Game.getPlayerToShow().getName());
		if (!Game.getPlayerToShow().equals(Game.getPlayerTurn())){
			SevenWonders.getLogger().log(Level.INFO, "La ressource survolée n'appartient pas au joueur qui joue ("+Game.getPlayerTurn().getName()+")");
			if (!ressourcePanel.hasBeenPurchased(Game.getPlayerTurn().getPlace())){
				SevenWonders.getLogger().log(Level.INFO, "La ressource n'a pas déjà été achetée par le joueur "+Game.getPlayerToShow().getName()+" ce tour_ci");
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
			}else{
				SevenWonders.getLogger().log(Level.INFO, "La ressource a déjà été achetée par le joueur "+Game.getPlayerToShow().getName());
			}
		}else{
			SevenWonders.getLogger().log(Level.INFO, "La ressource survolée appartient au joueur qui joue ("+Game.getPlayerToShow().getName()+")");
			
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
