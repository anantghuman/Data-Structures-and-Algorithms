
import java.util.*;

public class Player {

    private ArrayList<Card> hand; // the player's hand
    private Scanner input;

    public Player() {
        hand = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // Adds a card to the player's hand
    public void addCard(Card c) {
        hand.add(c);
    }

    // Covers all the logic regarding a human player's turn
    // public so it may be called by the Game class
    public Card playsTurn(Deck deck) {
        System.out.println("Your cards are: " + handToString());
        while (true) {
            System.out.println("Enter the suit of the card you want to play (x to draw a card): ");
            char s = input.next().charAt(0);
            input.nextLine();
            if (s == 'x') {
                if (deck.canDeal()) {
                    Card newCard = deck.deal();
                    addCard(newCard);
                    System.out.println("You drew " + newCard);
                    continue;
                }
                System.out.println("There are no cards left to deal");
                return null;
            }
            System.out.println("Enter the rank of the card you want to play (ace = 1, jack = 11, queen = 12, king = 13): ");
            int r = input.nextInt();
            input.nextLine();
            Card c = hasCard(s, r);
            if (c != null) {
                hand.remove(c);
                return c;
            }
            System.out.println("You don't have " + new Card(s, r));
        }
    }


    // Accessor for the players hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString() {
        return hand.toString();
    }

    private Card hasCard(char s, int r) {
        for (Card card : hand) {
            if (card.getSuit() == s && card.getRank() == r)
                return card;
        }
        return null;
    }
}
