import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class PlayedCardListener implements MouseListener{

	Frame frame;
	Card card;
	CardArea cardArea;
	boolean enabled;
	JLabel labelCard;

	public PlayedCardListener(Frame frame, Card card, boolean enabled){
		this.frame = frame;
		this.card = card;
		this.cardArea = null;
		this.labelCard = null;
		this.enabled = enabled;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (Game.getPlayerToShow().equals(Game.getPlayerTurn())){
			if (!arg0.isControlDown() && !arg0.isShiftDown()){
				if (Game.getPlayerTurn().canPlay(card)){
					if (!card.haveBeenPlayed()){
						card.setPlayed(true);
						Game.cardChosen(card);
						frame.cardChosen(cardArea);
						Game.getPlayerTurn().setPurchasedRessources(new RessourceList());
						if (!Game.isLastTour()){
							Game.nextPlayer();
							frame.passToNextPlayer();
						}else{
							Game.compterCombat(Game.getCurrentAge());
							if (Game.getCurrentAge() < 3){
								// il faut compter les points de combat
								frame.interAge();
							}else{
								frame.finPartie();
							}
						}
					}else{
						//System.out.println("Cette carte a déjà été jouée par ce joueur");
					}
				}else{
					//System.out.println("Carte jouée : "+card.getName()+"\n");
					//System.out.println("Vous n'avez pas les ressources nécessaire pour jouer cette carte.");
					//System.out.println("Vous avez "+Game.getPlayerTurn().getRessources());
					//System.out.println("Il vous faut :"+card.getCostString());
				}
			}else{
				if (arg0.isControlDown()){
					Game.defausser(card);
					if (!Game.isLastTour()){
						Game.nextPlayer();
						frame.passToNextPlayer();
					}else{
						Game.compterCombat(Game.getCurrentAge());
						if (Game.getCurrentAge() < 3){
							// il faut compter les points de combat
							frame.interAge();
						}else{
							frame.finPartie();
						}
					}

				}
				if (arg0.isShiftDown()){
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
					RessourceList cost = new RessourceList(wantToValidate.ressourceCost.ressourceList);
					if (player.canPay(cost)){
						player.validerStep(card);
						if (!Game.isLastTour()){
							Game.nextPlayer();
							frame.passToNextPlayer();
						}else{
							Game.compterCombat(Game.getCurrentAge());
							if (Game.getCurrentAge() < 3){
								// il faut compter les points de combat
								frame.interAge();
							}else{
								frame.finPartie();
							}
						}
					}
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.cardArea = new CardArea(card, true);
		this.cardArea.setBounds(400, 200, card.getBigWidth(), card.getBigHeight());
		this.cardArea.setOpaque(false);
		Container container = frame.getContentPane();
		container.add(this.cardArea);
		labelCard = new JLabel(card.toString());
		labelCard.setForeground(Color.GREEN);
		labelCard.setBounds(600, 200, 300, 200);
		container.add(labelCard, 0);
		frame.setContentPane(container);
		frame.keyListener.addArea(this.cardArea);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (!cardArea.equals(null)){
			Container container = frame.getContentPane();
			container.remove(cardArea);
			container.remove(labelCard);
			frame.setContentPane(container);
			frame.keyListener.removeArea(cardArea);
			cardArea = null;
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
