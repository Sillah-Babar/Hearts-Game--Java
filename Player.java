import java.util.*;

public class Player extends Hand{
    ArrayList<Card> cards;
    public Player()
    {
       cards=new ArrayList<Card>();
    }
    public void takeCard(Card c)
    {

        cards.add(c);
    }
 public boolean hasCard(Card c)
 {
     Iterator<Card> i = cards.iterator();
     while (i.hasNext()) {
      if(i.next().getShortName().equals(c.getShortName()))
          return true;

     }
return false;
 }
public boolean hasSuit(Suits s)
{
    for(int i=0 ;i<cards.size() ;i++)
    {
        if(cards.get(i).getSuit().equals(s))
            return true;
    }
return false;
}
public void playCard(Trick t, int Player)
{
    //all other computer players
    if(Player==0 || Player==1 || Player==2) {
        Card p=null;

        ArrayList<Card> clubs = new ArrayList<Card>();
        ArrayList<Card> hearts = new ArrayList<Card>();
        ArrayList<Card> diamonds = new ArrayList<Card>();
        ArrayList<Card> spades = new ArrayList<Card>();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getSuit() == Suits.Clubs)
                clubs.add(cards.get(i));
            if (cards.get(i).getSuit() == Suits.Hearts)
                hearts.add(cards.get(i));
            if (cards.get(i).getSuit() == Suits.Spades)
                spades.add(cards.get(i));
            if (cards.get(i).getSuit() == Suits.Diamonds)
                diamonds.add(cards.get(i));
        }
        if(t.getLeadingSuit().equals(Suits.Hearts) && hearts.size()>0)
        {
            Integer lead=t.HighestCard().ValueOf();
            Integer ap=hearts.get(0).ValueOf();
            int index=0;
            for(int i=1 ;i<hearts.size() ;i++)
            {

                if(lead<ap)
                {
                    ap=hearts.get(i).ValueOf();
                    index=i;

                }
            }
            p=hearts.get(index);
        }
        else  if(t.getLeadingSuit().equals(Suits.Diamonds) && diamonds.size()>0)
        {
            Integer lead=t.HighestCard().ValueOf();
            Integer ap=diamonds.get(0).ValueOf();
            int index=0;
            for(int i=1 ;i<diamonds.size() ;i++)
            {

                if(lead<ap)
                {
                    ap=diamonds.get(i).ValueOf();
                    index=i;

                }
            }
            p=diamonds.get(index);
        }
        else  if(t.getLeadingSuit().equals(Suits.Spades) && spades.size()>0)
        {
            Integer lead=t.HighestCard().ValueOf();
            Integer ap=spades.get(0).ValueOf();
            int index=0;
            for(int i=1 ;i<spades.size() ;i++)
            {

                if(lead<ap)
                {
                    ap=spades.get(i).ValueOf();
                    index=i;

                }
            }
            p=spades.get(index);
        }
        else  if(t.getLeadingSuit().equals(Suits.Clubs) && clubs.size()>0)
        {
            Integer lead=t.HighestCard().ValueOf();
            Integer ap=clubs.get(0).ValueOf();
            int index=0;
            for(int i=1 ;i<clubs.size() ;i++)
            {

                if(lead<ap)
                {
                    ap=clubs.get(i).ValueOf();
                    index=i;

                }
            }
            p=clubs.get(index);
        }
        else
        {
            if(!(this.hasSuit(t.getLeadingSuit())))
            {
                p=this.cards.get(0);
            }
        }
        this.cards.remove(p);
        t.add(p,Player);

    }
    else
    {
        Scanner obj=new Scanner(System.in);
        System.out.println("Your Cards "+cards.toString());
        System.out.println("Enter the index of the card you want to play");
        Integer p= obj.nextInt();
        while(p<0 || p>this.cards.size()-1)
        {
            System.out.println("Enter the index of the card you want to play.Index was out of bounds");
            p=obj.nextInt();
        }
        System.out.println("Leading suit+ "+t.getLeadingSuit());
        boolean newp=this.hasSuit(t.getLeadingSuit());
        System.out.println(newp);


        if(newp)
        {
            if(this.cards.get(p).getSuit().equals(t.getLeadingSuit()))
            {
                Card n=cards.get(p);
                this.cards.remove(n);
                t.add(n,Player);

            }
            else
            {
                while(!(this.cards.get(p).getSuit().equals(t.getLeadingSuit())))
                {
                    System.out.println("Enter the index of a card from the leading suit");
                    p=obj.nextInt();
                    while(p<0 || p>this.cards.size()-1)
                    {
                        System.out.println("Enter the index of the card you want to play.Index was out of bounds");
                        p=obj.nextInt();
                    }
                }
                Card n=cards.get(p);
                this.cards.remove(n);
                t.add(n,Player);

            }
        }
        else
        {
            Card n=cards.get(p);
            this.cards.remove(n);
            t.add(n,Player);

        }


    }

}
public boolean CanLead()
{
    int count=0;
    for(int i=0 ; i<cards.size() ;i++)
    {
        if(cards.get(i).getSuit()==Suits.Clubs)
            count++;
    }
    if(count>=2)
        return true;
    return false;
}
    public void lead(Trick t,int player)
    {
        if(t.getLeader()!=Seats.West)
        {
            ArrayList<Card> clubs=new ArrayList<Card>();
            ArrayList<Card> hearts=new ArrayList<Card>();
            ArrayList<Card> diamonds=new ArrayList<Card>();
            ArrayList<Card>  spades=new ArrayList<Card>();
            for(int i=0 ; i<cards.size() ;i++)
            {
                if(cards.get(i).getSuit()==Suits.Clubs)
                    clubs.add(cards.get(i));
                if(cards.get(i).getSuit()==Suits.Hearts)
                    hearts.add(cards.get(i));
                if(cards.get(i).getSuit()==Suits.Spades)
                    spades.add(cards.get(i));
                if(cards.get(i).getSuit()==Suits.Diamonds)
                    diamonds.add(cards.get(i));
            }
            Card rm=null;
            if(clubs.size()>0) {
                t.setLeadingSuit(clubs.get(0).getSuit());
                t.add(clubs.get(0),player);
                rm=clubs.get(0);
            }
            else if(spades.size()>0)
            {
                t.setLeadingSuit(spades.get(0).getSuit());
                t.add(spades.get(0),player);
                rm=spades.get(0);
            }
            else if(diamonds.size()>0)
            {
                t.setLeadingSuit(diamonds.get(0).getSuit());
                t.add(diamonds.get(0),player);
                rm=diamonds.get(0);
            }
            if(rm!=null)
            this.cards.remove(rm);

        }
        else
        {
            Scanner obj=new Scanner(System.in);

            System.out.println("Your Cards "+this.cards);
            System.out.println("Enter the index of the card you want to lead with");
            int x=obj.nextInt();
            while(x<0 || x>this.cards.size()-1)
            {
                System.out.println("Enter the index of the card you want to lead with.Index was out of bounds");
                x=obj.nextInt();
            }
            if(!(this.hasSuit(Suits.Spades)==false && this.hasSuit(Suits.Clubs)==false && this.hasSuit(Suits.Diamonds)==false)) {
                while (cards.get(x).getSuit().equals(Suits.Hearts)) {
                    System.out.println("You cannot lead with a hearts card");
                    x = obj.nextInt();
                }
            }
            t.setLeadingSuit(cards.get(x).getSuit());
            t.add(cards.get(x),3);
            Card p=cards.get(x);
            cards.remove(p);
        }

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
    public void ArrangeCards()
    {
        ArrayList<Card> resultant=new ArrayList<Card>();
        ArrayList<Card> newcars=new ArrayList<Card>();
        for(int i=0 ; i<cards.size() ;i++)
        {
            if(cards.get(i).getSuit().equals(Suits.Clubs))
                newcars.add(cards.get(i));
        }
        sort(newcars);
        resultant.addAll(newcars);
        newcars.clear();
        for(int i=0 ; i<cards.size() ;i++)
        {
            if(cards.get(i).getSuit().equals(Suits.Diamonds))
                newcars.add(cards.get(i));
        }
        sort(newcars);
        resultant.addAll(newcars);
        newcars.clear();
        for(int i=0 ; i<cards.size() ;i++)
        {
            if(cards.get(i).getSuit().equals(Suits.Spades))
                newcars.add(cards.get(i));
        }
        sort(newcars);
        resultant.addAll(newcars);
        newcars.clear();
        for(int i=0 ; i<cards.size() ;i++)
        {
            if(cards.get(i).getSuit().equals(Suits.Hearts))
                newcars.add(cards.get(i));
        }
        sort(newcars);
        resultant.addAll(newcars);
        newcars.clear();
       //System.out.println(resultant);
       cards=resultant;

    }

}
