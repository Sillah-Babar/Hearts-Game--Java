public class Score {

    //this array is for adding the score of each player to its index after each round
    //and then after each hand until we pass the 100 scoreboard
    //0 north
    //1 east
    //2 south
    //3 west
   private int[] scores;

   public Score()
   {
       scores=new int[4];
       for(int i=0 ;i<4 ;i++)
           scores[i]=0;

   }

   public boolean reachedTarget(int t)
   {
       for(int i=0 ;i<4 ;i++)
       {
           if(scores[i]>=100)
               return true;
       }
     return false;
   }

    public int winner()
    {
        int max=2000;
        int index=0;
        for(int i=0 ;i<4 ;i++)
        {
            if(scores[i]<max)
            {
                max=scores[i];
                index=i;
            }
        }
    return index;
    }

    public void DisplayScore()
    {
        System.out.println("-------------SCORES--------------");
        System.out.println("North: "+scores[0]);
        System.out.println("East: "+scores[1]);
        System.out.println("South: "+scores[2]);
        System.out.println("West: "+scores[3]);
        System.out.println("Winner: "+returnLeader(winner()));
    }
    public void add(int seats, int m)
    {
       scores[seats]+=m;
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
}