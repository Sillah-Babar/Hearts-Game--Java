import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Hand {
	private final ArrayList<Card> cards ;
    public Hand()
	{
		cards = new ArrayList<Card>();
	}
	public void PlayCard( Card c)
	{
		cards.add(c);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
}
