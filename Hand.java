
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Hand {

    private ArrayList<ArrayList<Card>> hand;   // The cards in the hand.
    public static boolean hasDominateCard;
    public static Card dCard;
    public static Hand lead;
    /**
     * Create a hand that is initially empty.
     */
    public Hand() {
        //    public final static int SPADES = 0;   // Codes for the 4 suits, plus Joker.
        //    public final static int HEARTS = 1;
        //    public final static int DIAMONDS = 2;
        //    public final static int CLUBS = 3;
        //    public final static int JOKER = 4;
        hand = new ArrayList<>();
        hand.add(new ArrayList<>());
        hand.add(new ArrayList<>());
        hand.add(new ArrayList<>());
        hand.add(new ArrayList<>());
        hand.add(new ArrayList<>());
    }

    /**
     * Remove all cards from the hand, leaving it empty.
     */
    public void clear() {
        hand.clear();
    }

    /**
     * Add a card to the hand.  It is added at the end of the current hand.
     * @param c the non-null card to be added.
     * @throws NullPointerException if the parameter c is null.
     */
    public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException("Can't add a null card to a hand.");
        hand.get(c.getSuit()).add(c);
        if(c.getValue()==Game.num && !hasDominateCard){
            System.out.println("You have received a potential dominant card. Do you wish to show this card as the dominant card? Y or N");
            String response = Main.scan.nextLine();
            if (response.equalsIgnoreCase("Y")){
                hasDominateCard = true;
                dCard = c;
                lead = this;
                System.out.println("You have set the dominant card to " + c.toString());
            }
        }
    }

    public boolean hasCard(Card c) {
        return hand.contains(c);
    }

    public ArrayList<Card> putDownHoleCards(){
        ArrayList<Card> hole = new ArrayList<Card>();
        int numCard = 6;
        while (numCard > 0) {
            System.out.println("Select 6 cards to put down as hole cards. " + numCard + " remaining.");
            System.out.println("First, select a suit: S for Spades, H for Hearts, D for Diamonds, C for Clubs.");
            String response = Main.scan.next();
            System.out.println("Choose a card in that suit: 1 for Ace, to 13 for King.");
            Integer resInt = Main.scan.nextInt();
            Card c;
            switch (response) {
                case "S":
                case "s":
                    c = new Card(resInt, Card.SPADES);
                    break;
                case "H":
                case "h":
                    c = new Card(resInt, Card.HEARTS);
                    break;
                case "D":
                case "d":
                    c = new Card(resInt, Card.DIAMONDS);
                    break;
                case "C":
                case "c":
                    c = new Card(resInt, Card.CLUBS);
                    break;
                default:
                    c = null;
            }
            if (c == null) {
                System.out.println("Invalid Input, try again.");
            } else if (hasCard(c)) {
                removeCard(c);
                hole.add(c);
                numCard--;
            } else {
                System.out.println("This card is not in your hand, try again.");
            }
        }
        return hole;
    }

    /**
     * Remove a card from the hand, if present.
     * @param c the card to be removed.  If c is null or if the card is not in
     * the hand, then nothing is done.
     */
    public void removeCard(Card c) {
        hand.remove(c);
    }

    /**
     * Remove the card in a specified position from the hand.
     * @param position the position of the card that is to be removed, where
     * positions are numbered starting from zero.
     * @throws IllegalArgumentException if the position does not exist in
     * the hand, that is if the position is less than 0 or greater than
     * or equal to the number of cards in the hand.
     */
    public void removeCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + position);
        hand.remove(position);
    }

    /**
     * Returns the number of cards in the hand.
     */
    public int getCardCount() {
        return hand.size();
    }


    /**
     * Sorts the cards in the hand so that cards of the same value are
     * grouped together.  Cards with the same value are sorted by suit.
     * Note that aces are considered to have the lowest value, 1.
     */
    public void sortByValue() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getValue() < c.getValue() ||
                        (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }

    public boolean hasSpades(){
        for (int i = 1; i < hand.size(); i++){
            Card c = hand.get(i);
            if (c.getSuit()==Card.SPADES){
                return true;
            }
        }
        return false;
    }

    public boolean hasHearts(){
        for (int i = 1; i < hand.size(); i++){
            Card c = hand.get(i);
            if (c.getSuit()==Card.HEARTS){
                return true;
            }
        }
        return false;
    }

    public boolean hasClubs(){
        for (int i = 1; i < hand.size(); i++){
            Card c = hand.get(i);
            if (c.getSuit()==Card.CLUBS){
                return true;
            }
        }
        return false;
    }

    public boolean hasDiamonds(){
        for (int i = 1; i < hand.size(); i++){
            Card c = hand.get(i);
            if (c.getSuit()==Card.DIAMONDS){
                return true;
            }
        }
        return false;
    }

}