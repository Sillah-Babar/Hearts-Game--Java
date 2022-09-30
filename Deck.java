import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Deck {

private List<Card> cards;
public Deck()
{
    cards=new ArrayList<Card>();


    int i = 0;

    for (Suits suit : Suits.values()) {
        for (Ranks rank : Ranks.values()) {
            cards.add(new Card(suit, rank)) ;
            ++i;
        }
    }
    //System.out.println("Full deck=" +cards);
}
public void shuffle()
{
    ArrayList<Card> shuffledDeck = new ArrayList<>();

    while ( cards.size() > 0) {
        int index = (int) (Math.random() *  cards.size());
        shuffledDeck.add(cards.remove(index));
    }
    cards=shuffledDeck;

}
public boolean hasCards()
{
    if(cards.size()>0)
        return true;
return false;
}
public Card dealCard()
    {

        if (this.hasCards()==true) {
            Card T;
            T=cards.get(cards.size() - 1);
            cards.remove(cards.size() - 1);
           return T;
        }

        return null;
    }

    public String toString()
    {
        String res = "";
        for (int i=0 ; i<cards.size() ;i++)
          res+= cards.toString() ;
        return res;
    }

}
