public class Game {

    public static void main(String[] args) {
        Round round;
        Score score;

        score=new Score();

        int roundNumber=1;
        while(score.reachedTarget(100)==false) {
            round=new Round();
            round.play(score, roundNumber);
           roundNumber++;
        }



    }
}
