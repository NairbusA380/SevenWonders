import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class PlayedCardListener implements MouseListener{

	Card card;
	CardArea cardArea;
	JLabel labelCard;

	public PlayedCardListener(Card card){
		this.card = card;
		this.cardArea = null;
		this.labelCard = null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		SevenWonders.getLogger().log(Level.INFO, "Clic sur une carte");
		if (Game.getPlayerToShow().equals(Game.getPlayerTurn())){
			if (arg0.isControlDown()){
				Game.defausser(card);
				this.passToNext();
			}else if (arg0.isShiftDown()){
				SevenWonders.getLogger().log(Level.INFO, "Tentative de validation d'étape");
				Player player = Game.getPlayerTurn();
				int i = 0;
				Step wantToValidate = null;
				for (Step s : player.getWonder().getStep()){
					if (!s.isValidated()){
						wantToValidate = s;
						break;
					}else{
						i++;
					}
				}
				SevenWonders.getLogger().log(Level.INFO, "C'est l'étape "+(i+1)+" qu'il faut valider");
				RessourceList cost = new RessourceList(wantToValidate.ressourceCost.ressourceList);
				if (player.canPay(cost)){
					SevenWonders.getLogger().log(Level.INFO, "Le joueur "+player.getName()+" peut payer le cout de validation de l'étape");
					player.validerStep(card);
					this.passToNext();
				}else{
					SevenWonders.getLogger().log(Level.INFO, "Le joueur "+player.getName()+" ne peut pas payer le cout de validation de l'étape");
					Frame.choixUtilisateur = new JOptionPane();
					Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous n'avez pas les ressources pour valider l'étape "+(i+1), "Impossible de valider cette étape", JOptionPane.ERROR_MESSAGE);
				}
			}else if (!arg0.isControlDown() && !arg0.isShiftDown()){
				SevenWonders.getLogger().log(Level.INFO, "Tentative de jouer la carte "+card.name+" par le joueur "+Game.getPlayerTurn().getName());
				if (Game.getPlayerTurn().canPay(card)){
					SevenWonders.getLogger().log(Level.INFO,"Le joueur "+Game.getPlayerTurn().getName()+" peut payer la carte "+card.name);
					if (Game.getPlayerTurn().cardNotAlreadyPlayed(card)){
						SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" n'a pas déjà joué la carte "+card.name);
						Game.getPlayerTurn().chooseCard(card);
						SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" a pu jouer la carte "+card.name);
						this.passToNext();
					}else{
						SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" a déjà joué cette carte, on lui affiche une boite de dialogue l'en informant");
						Frame.choixUtilisateur = new JOptionPane();
						Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous avez déjà joué cette carte, vous ne pouvez pas la rejouer", "Impossible de jouer cette carte", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					SevenWonders.getLogger().log(Level.INFO, "Le joueur "+Game.getPlayerTurn().getName()+" ne peut pas payer la carte, on lui affiche une boite de dialogue l'en informant");
					Frame.choixUtilisateur = new JOptionPane();
					Frame.choixUtilisateur.showMessageDialog(Frame.frame, "Vous n'avez pas les ressources pour payer cette carte", "Impossible de jouer cette carte", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {			
		SevenWonders.getLogger().log(Level.INFO, "Survol de la carte "+card.name+" par le joueur "+Game.getPlayerToShow().getName());
		this.cardArea = new CardArea(card, true, true);
		this.cardArea.setBounds(400, 250, cardArea.getBigWidth(), cardArea.getBigHeight());
		this.cardArea.setOpaque(false);
		Container container = Frame.frame.getContentPane();
		container.add(this.cardArea);
		labelCard = new JLabel(card.toString());
		labelCard.setForeground(new Color(255, 255, 0, 200));
		labelCard.setBounds(600, 200, 300, 200);
		Border b = labelCard.getBorder();
		Border marginLeft = new EmptyBorder(0, 0, 0, 0);
		labelCard.setBorder(new CompoundBorder(b, marginLeft));
		container.add(labelCard, 0);
		Frame.frame.setContentPane(container);
		Frame.keyListener.addArea(this.cardArea);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		SevenWonders.getLogger().log(Level.INFO, "Arret de survol de la carte "+card.name+" par le joueur "+Game.getPlayerToShow().getName());
		if (!cardArea.equals(null)){
			Container container = Frame.frame.getContentPane();
			container.remove(cardArea);
			container.remove(labelCard);
			Frame.frame.setContentPane(container);
			Frame.keyListener.removeArea(cardArea);
			cardArea = null;
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	private void passToNext(){
		if ((Game.getIdentifierPlayerTurn()+1)%(Game.getNbPlayer()) == 0){
			Frame.nextTurn();
		}
		
		if (!Game.isLastTour()){
			Game.nextPlayer();
			Frame.passToNextPlayer();
		}else{
			Game.compterCombat(Game.getCurrentAge());
			if (Game.getCurrentAge() < 3){
				// il faut compter les points de combat
				Frame.interAge();
			}else{
				Frame.finPartie();
			}
		}
	}

}
