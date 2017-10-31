import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;

public class CardArea extends JPanel{

	private Card card;
	BufferedImage cardImage;
	private String url;
	boolean discard;
	boolean validate;
	boolean big;
	private boolean enabled;
	final int miniWidth = 65, miniHeight = 100;
	final int bigWidth = 194, bigHeight = 300;

	public CardArea (Card card, boolean big){
		this.card = card;
		this.url = card.getImage();
		cardImage = null;
		this.discard = false;
		this.validate = false;
		this.big = big;
	}

	public CardArea (Card card, boolean enabled, boolean big){
		this.url = card.getImage();
		this.card = card;
		cardImage = null;
		this.discard = false;
		this.validate = false;
		this.big = big;
		this.enabled = enabled;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D drawable = (Graphics2D)g;
		if (cardImage == null){
			try {
				String completeUrl = url;
				if (!big){
					completeUrl += "Mini";
				}
				completeUrl += ".png";
				cardImage = ImageIO.read(getCardImage(completeUrl));
			} catch (IOException e) {
				System.err.println(this.card.toString());
				e.printStackTrace();
			}
		}


		drawable.drawImage(cardImage, 0, 0, this);

		if (!Game.getPlayerToShow().cardNotAlreadyPlayed(card) || !Game.getPlayerToShow().canPay(card)){

			if (enabled){
				if (discard || validate){
					if (discard){
						drawable.setColor(new Color(0, 0, 0, 155));
						drawable.fillRoundRect(0, 0, cardImage.getWidth(), cardImage.getHeight(), 25, 25);
						drawable.setColor(new Color(255, 255, 255));
						drawable.drawString("DISCARD",this.getWidth()/2-20, this.getHeight()/2-2);
					}else if (validate){
						drawable.setColor(new Color(0, 100, 0, 120));
						drawable.fillRoundRect(0, 0, cardImage.getWidth(), cardImage.getHeight(), 25, 25);
						drawable.setColor(new Color(255, 255, 255));
						drawable.drawString("VALIDATE A STEP",this.getWidth()/2-40, this.getHeight()/2-2);
					}
				}else{
					BufferedImage croix = null;
					String url = "croix";
					if (!big){
						url +="Mini";
					}
					url += ".png";
					try {
						croix = ImageIO.read(getImage(url));
					} catch (IOException e) {
						System.err.println(this.card.toString());
						e.printStackTrace();
					}
					drawable.drawImage(croix, 0, 0, this);
				}
			}
		}
	}

	private URL getCardImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/Cartes/"+nom);
	}

	private URL getImage(String nom) {
		ClassLoader cl = getClass().getClassLoader();
		return cl.getResource("Images/Cartes/"+nom);
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getURL(){
		return this.url;
	}

	public int getMiniWidth() {
		return miniWidth;
	}

	public int getMiniHeight() {
		return miniHeight;
	}

	public int getBigWidth() {
		return bigWidth;
	}

	public int getBigHeight() {
		return bigHeight;
	}

}
