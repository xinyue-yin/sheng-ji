public class Game {
    public static int num;

    public Hand player1;
    private Hand player2;
    private Hand player3;
    private Hand player4;
    private Deck deck;


    public Game(int i){
        player1 = new Hand();
        player2 = new Hand();
        player3 = new Hand();
        player4 = new Hand();
        deck = new Deck(true);
        num = i;
    }

    public void initializeGame(){
        deck.shuffle();
        for(int i = 0; i < 12; i++){
            player1.addCard(deck.dealCard());
            player2.addCard(deck.dealCard());
            player3.addCard(deck.dealCard());
            player4.addCard(deck.dealCard());
        }
        player1.sortBySuit();
        player2.sortBySuit();
        player3.sortBySuit();
        player4.sortBySuit();
        giveHoleCards();
    }

    private void giveHoleCards(){
        Hand h = Hand.lead;
        for (int i = 0; i < 5; i++){
            h.addCard(deck.dealCard());
        }
        h.putDownHoleCards();
    }

    public void nextRound(){

    }
}
