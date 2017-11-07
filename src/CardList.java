import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class CardList extends AbstractList{

	ArrayList<Card> cardList;

	public CardList(){
		this.cardList = new ArrayList();
	}
	
	public CardList(ArrayList c){
		this.cardList = c;
	}

	public boolean add(Card e){
		return cardList.add(e);
	}
	
	public boolean addAll(CardList c){
		return cardList.addAll(c);
	}
	
	public Card get(int arg0) {
		return cardList.get(arg0);
	}

	public int size() {
		return cardList.size();
	}

	public boolean contains(Card c){
		boolean result = true;
		String nameCompared = c.toString();
		for (Card object : cardList){
			String nameParcouru = object.toString();
			if (nameParcouru == nameCompared){
				break;
			}else{
				if (nameParcouru.contains(nameCompared)){
					break;
				}else{
					result = false;
				}
			}
		}
		return result;
	}

	public boolean contientTout(CardList c){
		boolean result = true;
		for (Card o : c.cardList){
			if (!this.contains(o)){
				result = false;
				break;
			}
		}
		return result;
	}

	public ArrayList<Card> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
}