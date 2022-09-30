import java.util.ArrayList;
//this class is for the trick being played in each round
public class Trick {
    //the cards being on the table for each trick

    private ArrayList<Card> cards;
   //the leading player for that trick
    private Seats leader;
    //the score of the trick
private int score;
    //the winner of the trick
    public int winner;

    private Suits LeadingSuit;
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public Card HighestCard()
    {
        Integer ap=-4;
        Integer index=0;
        for(int i=0 ;i<cards.size() ;i++)
        {
            if(cards.get(i).ValueOf()>ap && cards.get(i).getSuit().equals(LeadingSuit))
            {
                ap=cards.get(i).ValueOf();
                index=i;
            }

        }
        if(cards.size()==0)
            return null;
        return cards.get(index);
    }

    public void setLeader(Seats leader) {
        this.leader = leader;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public void setLeadingSuit(Suits leadingSuit) {
        LeadingSuit = leadingSuit;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Seats getLeader() {
        return leader;
    }

    public Suits getLeadingSuit() {
        return LeadingSuit;
    }


Trick()
{
    cards=new ArrayList<Card>();
    leader=Seats.West;//the game starts with the interactive user
    winner=5;//noone is the winner at the start of the game
    LeadingSuit=Suits.Clubs;
}
    public void add(Card p,int player){
        cards.add(p);
        if(p.ValueOf()>= HighestCard().ValueOf() && p.getSuit().equals(HighestCard().getSuit()))
        {
            winner=player;
        }


    }


public Seats getWinner()
{
    if(winner==3)
        return Seats.West;
    else if(winner==0)
        return Seats.North;
    else if(winner==1)
        return Seats.East;
    else
        return Seats.South;

}
public int getScore()
{
    int score=0;
    for(int i=0 ;i<cards.size() ;i++)
    {
        if(cards.get(i).getSuit().equals(Suits.Spades) && cards.get(i).getRank().getSymbol().equals("Q"))
            score+=13;
        if(cards.get(i).getSuit().equals(Suits.Hearts) )
            score+=1;
    }
return score;
}
}
