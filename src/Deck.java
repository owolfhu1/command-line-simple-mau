import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> deck = new ArrayList<>();
    ArrayList<Card> discard = new ArrayList<>();

    //builds a deck of cards when new Deck is made
    public Deck() {
        String[] suits = {"hearts", "tiles", "clovers", "pikes"};
        String[] values = {"ace", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten", "jack", "queen", "king"};

        //make a deck
        for (int s = 0; s < suits.length; s++)
            for (int v = 0; v < values.length; v++)
                deck.add(new Card(suits[s], values[v]));

        //shuffle it
        Collections.shuffle(deck);

        //put a card in discard
        discard.add(draw());
    }

    public Card draw() {
        //if the deck isnt empty draw a card
        if (!deck.isEmpty())
            return deck.remove(0);
        //if it is, put discard into deck, shuffle, and draw a card
        else {
            deck = discard;
            discard = new ArrayList<>();
            Collections.shuffle(deck);
            discard.add(draw());
            return deck.remove(0);
        }
    }

    public int deckSize() {
        return deck.size();
    }

    public int discardSize() {
        return discard.size();
    }

    public Card topDiscard() {
        return discard.get(discardSize() - 1);
    }

    public ArrayList<Integer> legalMoves (ArrayList<Card> hand) {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++)
            if (hand.get(i).getValue().equals(topDiscard().getValue()) || hand.get(i).getSuit().equals(topDiscard().getSuit()))
                moves.add(i);
        return moves;
    }

    public void discardCard(Card card) {
        discard.add(card);
    }
}
