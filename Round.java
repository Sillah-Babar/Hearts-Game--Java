import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    //0 north
    //1 east
    //2 south
    //3 west
    public Player[] players;
    public Deck deck;

    public Seats seats;
    public Round()
    {
        players=new Player[4];
        for(int i=0 ;i<4 ;i++)
            players[i]=new Player();
        deck=new Deck();

    }
    //parameter shows how to distribute the cards in the first round
    //shuffles the deck at the start of the game and hands each player 13 cards
public void deal(int p)
{

    deck.shuffle();
    for(int i=0 ; i<4 ;i++) {
     for(int j=0 ; j<13 ;j++) {
         Card k = deck.dealCard();
         players[i].takeCard(k);
     }
    }
   players[0].cards.toString();
}
//validates input for the index that has been entered to remove a card
public Integer inputValidation(int x)
{

    while (x<0 || x>=players[3].cards.size())
    {
        System.out.println("Wrong index, Enter again");
        Scanner obj=new Scanner(System.in);
        x=obj.nextInt();
    }
    return x;
}
//the numbers of cards that should be exchanged should be the highest of hearts as I am not trying to win tricks
// and the Queen of spades should also be removed from the deck
public ArrayList<Card> exchangeLogic( ArrayList<Card> m)
{
    ArrayList<Card> result=new ArrayList<Card>();
    ArrayList<Card> cardsofHearts=new ArrayList<Card>();
    int count=0;
    //if there is a spade of Queen in the deck then you might want to give it away first
    for(int i=0;i<m.size() ;i++)
    {
        if(m.get(i).getRank().equals(Ranks.Queen) && m.get(i).getSuit().equals(Suits.Spades))
        {
            result.add(m.get(i));

            count++;
        }
        else if(m.get(i).getSuit().equals(Suits.Hearts))
        {
         cardsofHearts.add(m.get(i));
        }
    }

     sort(cardsofHearts);
     int k=0;
     //you might want to give away the hearts next
     for(int i=count ; i<3 ;i++)
     {
         if(cardsofHearts.size()>(3-i)) {
             result.add(cardsofHearts.get(k));
             k++;
             count++;
         }
     }
     //if there are no hearts in the deck , then we can exhange other cards in the deck
    if(count>=0 && count<3)
    {
        k=0;
        for(int i=count ; i<3 ;i++)
        {
            if(m.size()>(3-i)) {
                result.add(m.get(k));
                k++;
                count++;
            }
        }
    }

    m.removeAll(result);
     return result;

}
    public void sort(ArrayList<Card> newcars)
    {
        for(int i=0 ;i< newcars.size();i++)
        {
            for(int j=i+1 ; j<newcars.size() ;j++)
            {
                //System.out.println(newcars.get(i).getRank().getSymbol());
                String isym=newcars.get(i).getRank().getSymbol();
                String jsym=newcars.get(j).getRank().getSymbol();
                int newi=0;
                int newj=0;
                if(isym .equals( "A"))
                {
                    newi=14;
                }
                else if(isym.equals("K"))
                {
                    newi=13;
                }
                else if(isym.equals("Q"))
                {
                    newi=12;
                }
                else if(isym.equals("J"))
                {
                    newi=10;
                }
                else if(isym.equals("T"))
                {
                    newi=9;
                }
                else
                {
                    newi=Integer.parseInt(isym);
                }
                if(jsym .equals("A"))
                {
                    newj=14;
                }
                else if(jsym .equals("T"))
                {
                    newj=10;
                }
                else if(jsym.equals("K"))
                {
                    newj=13;
                }
                else if(jsym.equals("Q"))
                {
                    newj=12;
                }
                else if(jsym.equals("J"))
                {
                    newj=11;
                }
                else
                {
                    newj=Integer.parseInt(jsym);
                }
                if(newj>newi)
                {
                    Card temp=newcars.get(i);
                    newcars.set(i,newcars.get(j));
                    newcars.set(j,temp);

                }


            }
        }
    }


//exchnages the cards at the start of the round according to the round number
public void exchange(int round)
{

    System.out.println("Enter the indices of the cards you wan to exchange");
    Scanner obj=new Scanner(System.in);
    System.out.println("Enter index 1");
    Integer index1=obj.nextInt();
    index1=inputValidation(index1);
    System.out.println("Enter index 2");
    Integer index2=obj.nextInt();
    index2=inputValidation(index2);
    System.out.println("Enter index 3");
    Integer index3=obj.nextInt();
    index3=inputValidation(index3);
    int x1=index1;
    int x2=index2;
    int x3=index3;
    ArrayList<Card> newarray=new ArrayList<Card>();
    newarray.add(players[3].cards.get(x1));
    newarray.add(players[3].cards.get(x2));
    newarray.add(players[3].cards.get(x3));
    players[3].cards.removeAll(newarray);
    ArrayList<Card>  res1=exchangeLogic(players[0].cards);
    ArrayList<Card>  res2=exchangeLogic(players[1].cards);
    ArrayList<Card>  res3=exchangeLogic(players[2].cards);
    round=round%4+1;
    if(round==1)
    {
        //removes the cards at the start of the round to swap it with the partner on the left

        players[0].cards.addAll(newarray);
        players[1].cards.addAll(res1);
        players[2].cards.addAll(res2);
        players[3].cards.addAll(res3);
    }
    else if(round==2)
    {
        //removes the cards at the start of the round to swap it with the partner on the left
        players[2].cards.addAll(newarray);
        players[1].cards.addAll(res3);
        players[0].cards.addAll(res2);
        players[3].cards.addAll(res1);

    }else if(round==3)
    {

        players[1].cards.addAll(newarray);
        players[2].cards.addAll(res1);
        players[0].cards.addAll(res3);
        players[3].cards.addAll(res2);


    }else
    {
     System.out.println("No Cards can be exchanged in this Round");
    }

}
public Seats returnLeader(int x)
{
    if(x==3)
        return Seats.West;
    else if(x==0)
        return Seats.North;
    else if(x==1)
        return Seats.East;
    else
        return Seats.South;
}
public void play( Score s,int round)
{
//ROUND 1
    System.out.println("_________________________________________________");
    System.out.println("Round Number "+round);

    System.out.println("Dealing the card to All Players");
    System.out.println("You are "+seats.West);
    System.out.println("Your Cards are as follows");
    deal(round);
    players[3].ArrangeCards();
    System.out.println(players[3].cards.toString());
    exchange(round);

    System.out.println("The Cards Have been exchanged for the Round: ");
    System.out.println("Your cards are as follows: ");
    players[3].ArrangeCards();
    System.out.println(players[3].cards.toString());

    Scanner obj=new Scanner(System.in);
    for(int i=0 ; i<13 ;i++)
    {
        Trick t=new Trick();
        System.out.println("Trick "+(i+1)+"---------------------------------");
        boolean LeadingPlayer=false;
        int player=0;
        for(int j=3 ; j>=0 ;j--)
        {
           LeadingPlayer= players[j].CanLead();
            if(LeadingPlayer==true) {
                player=j;
                j = -1;

            }
        }

        t.setLeader(returnLeader(player));
        System.out.println("It is player "+t.getLeader()+" to lead the round");

        //the leading player opens up the round
        players[player].lead(t,player);
        if(t.getCards().size()>0)
        System.out.println("Card Played On the Table: "+t.getCards().get(t.getCards().size()-1)+" by "+returnLeader(player));



        for(int j=0 ; j<3 ;j++)
        {

            player++;

            if(player>3)
                player=player%4;
            System.out.println(player);
            players[player].playCard(t,player);
            System.out.println("Card Played On the Table: "+t.getCards().get(t.getCards().size()-1)+" by "+returnLeader(player));
        }
        System.out.println();
        System.out.println("The Winning Suit is "+t.HighestCard().getShortName());
        System.out.println("The Trick is Won by:  "+t.getWinner());
        players[t.winner].getCards().addAll(t.getCards());

        s.add(t.winner,t.getScore());




    }
    System.out.println("_________________________________________________");
    s.DisplayScore();

}

}
