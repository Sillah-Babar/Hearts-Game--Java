import java.util.Arrays;

public class Card {
	private final Suits suit;
	private final Ranks rank;

	public Card(Suits suit, Ranks rank) {
		this.suit = suit;
		this.rank = rank;
	}

   public Integer ValueOf()
   {
	   Integer ap=13;
	   if((rank.getSymbol()).equals("A"))
		   ap=14;
	   else if((rank.getSymbol()).equals("K"))
		   ap=13;
	   else if((rank.getSymbol()).equals("Q"))
		   ap=12;
	   else if((rank.getSymbol()).equals("J"))
		   ap=11;
	   else if((rank.getSymbol()).equals("T"))
		   ap=10;
	   else
	   {
		   ap=Integer.parseInt(rank.getSymbol());
	   }

   return ap;
   }

	public Suits getSuit() {
		return this.suit;
	}

	public Ranks getRank() {
		return this.rank;
	}

	private int ordinal() {
		return this.suit.ordinal() * Ranks.NUMBER_OF_RANKS + this.rank.ordinal();
	}

	public boolean equals(final Card other) {
		return this.suit == other.suit &&
				this.rank == other.rank;
	}

	public int compareTo(final Card other) {
		return this.suit == other.suit ?
				this.rank.compareTo(other.rank) :
				this.suit.compareTo(other.suit);
	}

	public String getFullName() {
		return rank.toString() + " of " + suit.toString();
	}

	public String getShortName() {
		return rank.getSymbol() + suit.getSymbol();
	}

	@Override
	public String toString() {
		return getShortName();
	}
}
