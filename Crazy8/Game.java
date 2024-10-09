import java.util.*;

public class Game{

    private char currentSuit; // need in case an 8 is played
    private Card faceup;
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    // sets up the Game object for play
    public Game(){
        p1 = new Player();
        cards = new Deck();
        input = new Scanner(System.in);
        compHand = new ArrayList<>();
        currentSuit = ' ';
    }

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        cards.shuffle();
        for (int i = 0; i < 14; i++) {
            if (i % 2 == 0)
                p1.addCard(cards.deal());
            else
                compHand.add(cards.deal());
        }
        System.out.println("Dealing cards...");
        faceup = cards.deal();
        System.out.println("The first card is " + faceup);
        while(cards.canDeal()) {
            boolean exit = false;
            while (true) {
                Card pCard = p1.playsTurn(cards);
                if (pCard != null) {
                    if (isValid(pCard)) {
                        faceup = pCard;
                        System.out.println("You played " + pCard);
                        currentSuit = ' ';
                        if (p1.getHand().size() == 0)
                            exit = true;
                        if (pCard.getRank() == 8) {
                            System.out.println("Enter the suit that you want to select (d, h, c, s): ");
                            currentSuit = input.next().charAt(0);
                            input.nextLine();
                            System.out.println("You set the suit to " + Card.getSuitName(currentSuit));
                        }
                        break;
                    }
                    p1.addCard(pCard);
                    System.out.println("You cannot play " + pCard);
                } else {
                    exit = true;
                    break;
                }
            }
            if (exit)
                break;
            Card cCard = computerTurn();
            if (cCard != null) {
                faceup = cCard;
                System.out.println("The computer plays " + cCard);
                currentSuit = ' ';
                if (cCard.getRank() == 8) {
                    currentSuit = cards.getRandomSuit();
                    System.out.println("The computer sets the suit to " + Card.getSuitName(currentSuit));
                }
            } else
                break;
        }
        if (compHand.size() == p1.getHand().size())
            System.out.println("Tie! " + compHand.size() + "-" + p1.getHand().size());
        else if(compHand.size() < p1.getHand().size())
            System.out.println("Computer Wins! " + compHand.size() + "-" + p1.getHand().size());
        else
            System.out.println("Player Wins! " + p1.getHand().size() + "-" + compHand.size());

        System.out.println("Do you want to keep playing (y for yes: n for no)? ");
        char c = input.next().charAt(0);
        input.nextLine();
        return c == 'y';
    }

    /* Naive computer player AI that does one of two actions:
        1) Plays the first card in their hand that is a valid play
        2) If no valid cards, draws until they can play

        You may choose to use a different approach if you wish but
        this one is fine and will earn maximum marks
     */
    private Card computerTurn() {
        for (int i = 0; i < compHand.size(); i++) {
            if (isValid(compHand.get(i))) {
                return compHand.remove(i);
            }
        }
        Card c;
        do {
            if (cards.canDeal()) {
                c = cards.deal();
                compHand.add(c);
                System.out.println("The computer draws a card");
            } else {
                System.out.println("There are no cards left to deal");
                return null;
            }
        } while (!isValid(c));
        return compHand.remove(compHand.size() - 1);
    }

    private boolean isValid(Card c) {
        if (currentSuit != ' ')
            return c.getSuit() == currentSuit;
        return faceup.getRank() == c.getRank() || faceup.getSuit() == c.getSuit() || c.getRank() == 8;
    }
}
