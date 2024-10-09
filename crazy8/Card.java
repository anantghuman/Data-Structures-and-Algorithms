import java.util.*;

public class Card{
    
    private char suit;
    private int rank;
    private final String[] wordOf = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static final HashMap<Character, String> suitName = new HashMap<>() {{
       put('d', "Diamonds");
       put('h', "Hearts");
       put('c', "Clubs");
       put('s', "Spades");
    }};

    // Initializes a card instance
    public Card(char suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }

    // Accessor for suit
    public char getSuit(){
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        return rank;
    }

    // Returns a human readable form of the card (eg. King of Diamonds)
    public String toString(){
        return wordOf[rank - 1] + " of " + suitName.get(suit);
    }

    public static String getSuitName(char c) {
        return suitName.get(c);
    }
}
