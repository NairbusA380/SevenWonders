import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CardList<T> extends AbstractList{

	List<T> cardList;

	public CardList(){
		this.cardList = new ArrayList<T>();
	}
	
	public CardList(ArrayList<T> c){
		this.cardList = c;
	}

	public boolean add(Object e){
		return cardList.add((T)e);
	}
	
	@SuppressWarnings("unchecked")
	public boolean addAll(CardList<? extends T> c){
		return cardList.addAll(c);
	}
	
	public T get(int arg0) {
		return cardList.get(arg0);
	}

	public int size() {
		return cardList.size();
	}

	public boolean contains(Object c){
		boolean result = true;
		T caste = (T)c;
		String nameCompared = caste.toString();
		for (T object : cardList){
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

	public boolean contientTout(CardList<T> c){
		boolean result = true;
		for (Object o : c){
			if (!this.contains(o)){
				result = false;
				break;
			}
		}
		return result;
	}

	public List<T> getCardList() {
		return cardList;
	}

	public void setCardList(List<T> cardList) {
		this.cardList = cardList;
	}
	
	

}